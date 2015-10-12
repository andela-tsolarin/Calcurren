package com.andela.toni.calcurren.converters;

import com.andela.toni.calcurren.models.Quantity;
public class CurrencyConverter implements Converter {

    @Override
    public double convert(String from, String to, double value, Quantity[] quantities) {
        Quantity fromQuantity = null;
        Quantity toQuantity = null;

        for (int i = 0; i < quantities.length; i++) {

            if (quantities[i].getKey() == from) {
                fromQuantity = quantities[i];
            }

            if (quantities[i].getKey() == to) {
                toQuantity = quantities[i];
            }
        }

        if (fromQuantity == null || toQuantity == null) {
            return value;
        }

        double exchange = toQuantity.getValue() / fromQuantity.getValue();
        return value * exchange;
    }
}