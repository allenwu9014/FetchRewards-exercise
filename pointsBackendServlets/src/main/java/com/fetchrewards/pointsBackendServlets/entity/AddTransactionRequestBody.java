package com.fetchrewards.pointsBackendServlets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTransactionRequestBody {
    @JsonProperty("payer")
    public String payer;

    @JsonProperty("points")
    public int points;

    @JsonProperty("timestamp")
    public String timestamp;

}
