package com.yamuna.crypto.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String coin;
    private Long amount;
    private String type;
    private Long userId;

    public Transactions() {
    }

    public Transactions(Long id, String coin, Long amount, String type, Long userId) {
        this.id = id;
        this.coin = coin;
        this.amount = amount;
        this.type = type;
        this.userId = userId;
    }

    public Transactions(String coin, Long amount, String type, Long userId) {
        this.coin = coin;
        this.amount = amount;
        this.type = type;
        this.userId = userId;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
