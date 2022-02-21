package com.example.currencyWatcher.service;

import com.example.currencyWatcher.math.MathFormulas;
import com.example.currencyWatcher.model.Coin;
import com.example.currencyWatcher.model.User;
import com.example.currencyWatcher.repository.CoinsRepository;
import com.example.currencyWatcher.repository.UsersRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class UserService {

    final
    UsersRepository usersRepository;

    final
    CoinsRepository coinsRepository;

    static Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UsersRepository usersRepository, CoinsRepository coinsRepository) {
        this.usersRepository = usersRepository;
        this.coinsRepository = coinsRepository;
    }

    public void saveUser(User user) {
        Coin coin = coinsRepository.getCoinBySymbol(user.getSymbol());
        if (coin != null) {
            user.setPrice(coin.getPrice_usd());
            usersRepository.save(user);
        }
    }

    public Optional<User> findByName(String name) {
        return usersRepository.findAll().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    public boolean isExistUserInRepository(String name) {
        return findByName(name).isPresent();
    }

    @Scheduled(fixedRate = 61000)
    public void checkPriceOfUser() {
        List<User> users = usersRepository.findAll();
        users.forEach((user) -> {
            double priceFromRepository = coinsRepository.getCoinBySymbol(user.getSymbol()).getPrice_usd();
            double userPrice = user.getPrice();
            if (!user.isChangedPrice()) {
                if (MathFormulas.priceChangeOnePercent(userPrice, priceFromRepository)) {
                    logger.warning(user.getName() + " price is 1% bigger/smaller than current price");
                    user.setChangedPrice(true);
                    usersRepository.save(user);
                }
            }
        });
    }
}
