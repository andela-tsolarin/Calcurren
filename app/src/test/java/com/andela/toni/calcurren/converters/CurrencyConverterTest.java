package com.andela.toni.calcurren.converters;

import junit.framework.TestCase;
import com.andela.toni.calcurren.models.Quantity;

/**
 * Created by tonie on 10/23/2015.
 */
public class CurrencyConverterTest extends TestCase {

    public void testConvert() throws Exception {

        CurrencyConverter converter = new CurrencyConverter();
        double newValue = converter.convert("", "", 5, null);
        assertEquals(5.0, newValue);

        Quantity quantity = new Quantity();
        quantity.setKey("USD");
        quantity.setValue(1);

        Quantity quantity1 = new Quantity();
        quantity1.setKey("NGN");
        quantity1.setValue(200);

        Quantity[] quantities = new Quantity[] { quantity, quantity1 };

        newValue = converter.convert("NGN", "USD", 10000, quantities);
        assertEquals(50.0, newValue);

    }
}