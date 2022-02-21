package com.example.currencyWatcher.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coin {

    @Id
    private Long id;

    @Column(
            name = "symbol_of_coin",
            unique = true
    )
    private String symbol;

    @Column(name = "price")
    private double price_usd;
}
