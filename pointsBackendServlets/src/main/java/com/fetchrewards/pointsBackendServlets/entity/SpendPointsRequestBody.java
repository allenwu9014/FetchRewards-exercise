package com.fetchrewards.pointsBackendServlets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpendPointsRequestBody {

    @JsonProperty("points")
    public int points;

    public int getPoints() {
        return points;
    }
}
