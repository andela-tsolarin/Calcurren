package com.andela.toni.calcurren.operations;

import com.andela.toni.calcurren.models.Quantity;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by tonie on 10/24/2015.
 */
public class QuantityOperationsTest extends TestCase {

    public void testGetSorted() throws Exception {

        QuantityOperations quantityOps = new QuantityOperations();
        Quantity quantity = new Quantity();
        quantity.setKey("NGN");
        quantity.setValue(200);

        Quantity quantity1 = new Quantity();
        quantity1.setKey("GBP");
        quantity1.setValue(0.7);

        Quantity[] quantities = new Quantity[] { quantity, quantity1 };

        List<Quantity> quantityList = quantityOps.getSorted(quantities);
        assertEquals("GBP", quantityList.get(0).getKey());
    }
}