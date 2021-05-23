package com.fetchrewards.pointsBackendServlets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpendItem {

    @JsonProperty("payer")
    public String payer;

    @JsonProperty("points")
    public int points;

    public SpendItem(String Payer, int points) {
        this.payer = Payer;
        this.points = points;

    }
}
