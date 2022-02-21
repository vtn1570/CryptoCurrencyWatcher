package com.example.currencyWatcher.service;


import com.example.currencyWatcher.model.Coin;
import com.example.currencyWatcher.repository.CoinsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CoinService {

    final
    CoinsRepository coinsRepository;

    public CoinService(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    public boolean isExistCoinInDB(String symbol) {
        return coinsRepository.getCoinBySymbol(symbol) == null;
    }


    @Scheduled(fixedRate = 60000, initialDelay = 2000)
    public void updatePrice() {
        RestTemplate restTemplate = new RestTemplate();
        List<Coin> coinsFromRepository = coinsRepository.findAll();
        coinsFromRepository.forEach((el) -> {
            Coin[] coinsFromRest = restTemplate.getForObject(
                    "https://api.coinlore.net/api/ticker/?id=" + el.getId(),
                    Coin[].class);
            assert coinsFromRest != null;
            el = coinsFromRest[0];
            coinsRepository.save(el);
        });
    }
}
