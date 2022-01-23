package com.yamuna.crypto.controller;

import com.yamuna.crypto.dao.Coins;
import com.yamuna.crypto.dao.Transactions;
import com.yamuna.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crypto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @RequestMapping("/coins")
    public String getAllCoins(Model model){
        List<Coins> coins = cryptoService.getAllCoins();
        model.addAttribute("coins", coins);
        model.addAttribute("coin", new Coins());
        return "main";
    }

//    @RequestMapping(method = RequestMethod.POST, value="/coins")
//    public void addCoin(@RequestBody Coins coin){
//        cryptoService.addCoin(coin);
//    }

    @RequestMapping(method = RequestMethod.POST, value="/coins")
    public String addCoin(@ModelAttribute Coins coins){
        cryptoService.addCoin(coins);
        return "redirect:/crypto/coins";
    }

    @RequestMapping(method = RequestMethod.POST, value="/coins/update")
    public void updateCoin(@RequestBody Coins coin){
        cryptoService.updateCoin(coin);
    }

//    @RequestMapping(method = RequestMethod.POST, value="/buy")
//    public void buyCoin(@RequestBody Transactions transaction){
//        cryptoService.buyCoin(transaction);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value="/sell")
//    public void sellCoin(@RequestBody Transactions transaction){
//        cryptoService.sellCoin(transaction);
//    }


    @RequestMapping(method = RequestMethod.POST, value="/buy")
    public String buyCoin(@ModelAttribute Transactions transaction){
        cryptoService.buyCoin(transaction);
        return "redirect:/crypto/transactions";
    }

    @RequestMapping(method = RequestMethod.POST, value="/sell")
    public String sellCoin(@ModelAttribute Transactions transaction){
        cryptoService.sellCoin(transaction);
        return "redirect:/crypto/transactions";
    }


    @RequestMapping(method = RequestMethod.GET, value="/transactions")
    public String getTransactions(Model model){
        List<Transactions> allTransactions = cryptoService.getAllTransactions();
        model.addAttribute("transactions", allTransactions);
        model.addAttribute("transaction", new Transactions());
        return "transactions";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public String updateTransaction(@RequestBody Transactions transaction){
        cryptoService.updateCoinTransaction(transaction);
        return "redirect:/crypto/transactions";
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/cancel")
    public void cancelTransaction(@RequestBody Transactions transaction){
        cryptoService.cancelTransaction(transaction);
    }

}
