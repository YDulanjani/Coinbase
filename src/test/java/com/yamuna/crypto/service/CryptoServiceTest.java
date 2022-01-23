package com.yamuna.crypto.service;


import com.yamuna.crypto.dao.Coins;
import com.yamuna.crypto.dao.Transactions;
import com.yamuna.crypto.repository.CryptoRepository;
import com.yamuna.crypto.repository.TransactionRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CryptoServiceTest {

    @InjectMocks
    private CryptoService cryptoService;

    @Mock
    private CryptoRepository cryptoRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void getAllCoinsTests() {
        //STUB
        Mockito.when(cryptoRepository.findAll()).thenReturn(Arrays.asList(
                new Coins(1L, "BTC", 8000.0, "Bitcoin"),
                new Coins(2L, "ETM", 5000.0, "Etherium")
        ));

        List<Coins> allCoins = cryptoService.getAllCoins();

        Assertions.assertNotNull(allCoins);
        Assertions.assertEquals(allCoins.size() , 2);
        Assertions.assertEquals(allCoins.get(0).getName() , "BTC");
        Assertions.assertEquals(allCoins.get(1).getName() , "ETM");
    }


    @Test
    public void getAllTransactionsTests() {
        //STUB
        Mockito.when(transactionRepository.findAll()).thenReturn(Arrays.asList(
                new Transactions(1L, "BTC", 5L, "BUY", 10L),
                new Transactions(2L, "BTC", 5L, "SELL", 10L),
                new Transactions(3L, "ETM", 5L, "BUY", 10L),
                new Transactions(4L, "ETM", 5L, "SELL", 10L)
        ));

        List<Transactions> allTransactions = cryptoService.getAllTransactions();

        Assertions.assertNotNull(allTransactions);
        Assertions.assertEquals(allTransactions.size() , 4);
        Assertions.assertEquals(allTransactions.get(0).getType() , "BUY");
        Assertions.assertEquals(allTransactions.get(0).getCoin() , "BTC");
        Assertions.assertEquals(allTransactions.get(0).getAmount() , 5);
        Assertions.assertEquals(allTransactions.get(1).getType() , "SELL");
    }


}