package com.fetchrewards.pointsBackendServlets.entity;

import java.util.Objects;

public class Transaction {
    private String payer;
    private int points;
    private String timestamp;

    public Transaction(String payer, int points, String timestamp) {
        this.payer = payer;
        this.points = points;
        this.timestamp = timestamp;
    }

    public String getPayer() {
        return payer;
    }

    public int getPoints() {
        return points;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int setPoints(int points) {
        this.points = points;
        return this.points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, points, timestamp);
    }

    @Override
    public String toString() {
        return "{" + "\""+
                "payer\": \"" + payer + '\"' + ", \""+
                "points\": " + points + ", "  +
                "\"timestamp\": \"" + timestamp + '\"' +
                '}';
    }
}
