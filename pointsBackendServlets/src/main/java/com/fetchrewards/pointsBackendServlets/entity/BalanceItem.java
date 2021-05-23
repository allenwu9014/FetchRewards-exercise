package com.fetchrewards.pointsBackendServlets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceItem {

    @JsonProperty("payer")
    public String payer;

    @JsonProperty("count")
    public int count;

    public BalanceItem(String payer, int count) {
        this.payer = payer;
        this.count = count;

    }
}


