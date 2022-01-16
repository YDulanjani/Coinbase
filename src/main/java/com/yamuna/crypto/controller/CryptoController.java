package com.yamuna.crypto.controller;

import com.yamuna.crypto.dao.Coins;
import com.yamuna.crypto.dao.Transactions;
import com.yamuna.crypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @RequestMapping("/coins")
    public List<Coins> getAllCoins(){
        return cryptoService.getAllCoins();
    }

    @RequestMapping(method = RequestMethod.POST, value="/coins")
    public void addCoin(@RequestBody Coins coin){
        cryptoService.addCoin(coin);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/coins/update")
    public void updateCoin(@RequestBody Coins coin){
        cryptoService.updateCoin(coin);
    }

    @RequestMapping(method = RequestMethod.POST, value="/buy")
    public void buyCoin(@RequestBody Transactions transaction){
        cryptoService.buyCoin(transaction);
    }

    @RequestMapping(method = RequestMethod.POST, value="/sell")
    public void sellCoin(@RequestBody Transactions transaction){
        cryptoService.sellCoin(transaction);
    }

    @RequestMapping(method = RequestMethod.GET, value="/transactions")
    public List<Transactions> sellCoin(){
        return cryptoService.getAllTransactions();
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public void updateTransaction(@RequestBody Transactions transaction){
        cryptoService.updateCoinTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/cancel")
    public void cancelTransaction(@RequestBody Transactions transaction){
        cryptoService.cancelTransaction(transaction);
    }

}
