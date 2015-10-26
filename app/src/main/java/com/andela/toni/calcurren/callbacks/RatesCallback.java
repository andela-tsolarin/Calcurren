package com.andela.toni.calcurren.callbacks;

import com.andela.toni.calcurren.models.Quantity;

/**
 * Created by tonie on 10/26/2015.
 */
public interface RatesCallback {
    void onfinish(Quantity[] quantities);
}
