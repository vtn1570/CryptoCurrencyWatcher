package com.example.currencyWatcher.repository;

import com.example.currencyWatcher.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CoinsRepository extends JpaRepository<Coin, Long> {

    @Query("select c from Coin c where upper(c.symbol) = upper(:symbol)")
    Coin getCoinBySymbol(@Param("symbol") String symbol);
}
