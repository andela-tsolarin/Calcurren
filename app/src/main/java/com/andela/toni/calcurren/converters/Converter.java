package com.andela.toni.calcurren.converters;

import com.andela.toni.calcurren.models.Quantity;

public interface Converter {
    double convert(String from, String to, double value, Quantity[] quantities);
}
