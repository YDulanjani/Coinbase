package com.yamuna.crypto.service;

import com.yamuna.crypto.dao.Coins;
import com.yamuna.crypto.dao.Transactions;
import com.yamuna.crypto.repository.CryptoRepository;
import com.yamuna.crypto.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Coins> getAllCoins() {
        List<Coins> coins = cryptoRepository.findAll();
        return coins;
    }

    public void addCoin(Coins coin) {
        cryptoRepository.save(coin);
    }

    public void updateCoin(Coins coin) {
        cryptoRepository.save(coin);
    }

    public void buyCoin(Transactions transaction) {
        transaction.setType("BUY");
        transactionRepository.save(transaction);
    }

    public void sellCoin(Transactions transaction) {
        transaction.setType("SELL");
        transactionRepository.save(transaction);
    }

    public void updateCoinTransaction(Transactions transaction) {
        transactionRepository.save(transaction);
    }

    public void cancelTransaction(Transactions transaction) {
        transactionRepository.delete(transaction);
    }

//    public void cancelTransaction(Long id) {
//        transactionRepository.deleteById(id);
//    }

    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
