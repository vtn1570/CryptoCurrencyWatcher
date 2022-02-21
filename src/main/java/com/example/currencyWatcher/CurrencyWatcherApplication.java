package com.example.currencyWatcher;

import com.example.currencyWatcher.model.Coin;
import com.example.currencyWatcher.repository.CoinsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CurrencyWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyWatcherApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(CoinsRepository coinsRepository) {
        return args -> {
            List<Coin> coins = new ArrayList<>();
            Coin coin = new Coin();
            coin.setId(90L);
            coin.setSymbol("BTC");
            coins.add(coin);
            Coin coin2 = new Coin();
            coin2.setId(80L);
            coin2.setSymbol("ETH");
            coins.add(coin2);
            Coin coin3 = new Coin();
            coin3.setId(48543L);
            coin3.setSymbol("SOT");
            coins.add(coin3);
            coinsRepository.saveAll(coins);
        };
    }
}
