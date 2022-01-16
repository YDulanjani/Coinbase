package com.yamuna.crypto.dao;

import javax.persistence.*;

@Entity
public class Coins {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="Rate")
    private Double rate;

    @Column(name="Description")
    private String description;


    public Coins() {
    }

    public Coins(Long id, String name, Double rate, String description) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
