package com.example.currencyWatcher.controller;


import com.example.currencyWatcher.model.Coin;
import com.example.currencyWatcher.model.User;
import com.example.currencyWatcher.repository.CoinsRepository;
import com.example.currencyWatcher.service.CoinService;
import com.example.currencyWatcher.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WatcherController {

    final
    UserService userService;

    final
    CoinsRepository coinsRepository;

    final
    CoinService coinService;

    public WatcherController(UserService userService, CoinsRepository coinsRepository, CoinService coinService) {
        this.userService = userService;
        this.coinsRepository = coinsRepository;
        this.coinService = coinService;
    }

    @GetMapping("/")
    public String navigation(Model model) {
        model.addAttribute("coin", new Coin());
        return "navigation";
    }

    @PostMapping("/")
    public String getCoinBySymbol(@ModelAttribute Coin coin) {
        Coin coinBySymbol = coinsRepository.getCoinBySymbol(coin.getSymbol());
        if (coinBySymbol == null) {
            return "redirect:/";
        }
        Long id = coinBySymbol.getId();
        return "redirect:/coin/" + id;
    }

    @GetMapping("coin/{id}")
    public String showCoin(@PathVariable("id") Long id, Model model) {
        Coin coin = coinsRepository.findById(id).orElseThrow();
        model.addAttribute("coin", coin);
        return "coin";
    }

    @GetMapping("/coins")
    public String showCoins(Model model) {
        List<Coin> coins = coinsRepository.findAll();
        model.addAttribute("coins", coins);
        return "coins";
    }

    @GetMapping("/registration")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user) {
        if (userService.isExistUserInRepository(user.getName()) || coinService.isExistCoinInDB(user.getSymbol())) {
            return "registration";
        }
        userService.saveUser(user);
        return "successRegistration";
    }
}
