package com.andela.toni.calcurren.helpers;

import com.andela.toni.calcurren.callbacks.RatesCallback;

import org.json.JSONObject;

/**
 * Created by Andela on 10/9/15.
 */
public interface Helper {
    void getQuantities(RatesCallback callback);
    void parseResponse(JSONObject response);
}