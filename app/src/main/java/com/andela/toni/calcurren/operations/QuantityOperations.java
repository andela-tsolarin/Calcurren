package com.andela.toni.calcurren.operations;

import com.andela.toni.calcurren.models.Quantity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andela on 10/12/15.
 */
public class QuantityOperations {

    public List<Quantity> getSorted(Quantity[] quantities) {

        List<Quantity> quantityList = new ArrayList<>();
        for (int i = 0; i < quantities.length; i++) {
            quantityList.add(quantities[i]);
        }

        Collections.sort(quantityList);
        return quantityList;
    }
}
