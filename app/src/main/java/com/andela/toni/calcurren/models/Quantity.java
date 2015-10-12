package com.andela.toni.calcurren.models;


public class Quantity implements Comparable<Quantity> {

    private String key;
    private double value;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public int compareTo(Quantity quantity) {
        if (this.getValue() > quantity.getValue()) {
            return 1;
        } else if(quantity.getValue() > this.getValue()) {
            return -1;
        }

        return 0;
    }
}
