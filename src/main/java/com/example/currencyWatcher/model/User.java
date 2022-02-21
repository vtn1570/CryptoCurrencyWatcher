package com.example.currencyWatcher.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private double price;

    @Column(name = "is_change")
    private boolean isChangedPrice = false;

}
