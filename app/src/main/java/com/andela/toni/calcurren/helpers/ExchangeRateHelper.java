package com.andela.toni.calcurren.helpers;

import com.andela.toni.calcurren.CalculatorActivity;
import com.andela.toni.calcurren.models.Quantity;
import com.andela.toni.calcurren.operations.NetworkOperations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ExchangeRateHelper implements Helper {

    private CalculatorActivity activity;
    public ExchangeRateHelper(CalculatorActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getQuantities() {
        NetworkOperations networkOperations = new NetworkOperations();
        networkOperations.getJsonResponse(this);
    }

    @Override
    public void finished(JSONObject response) {

        Quantity[] quantities;

        try {
            JSONObject ratesObject = response.getJSONObject("rates");
            quantities = new Quantity[ratesObject.length()];

            Iterator<String> keys = ratesObject.keys();
            int i = 0;
            while (keys.hasNext()) {
                String key = keys.next();

                Quantity quantity = new Quantity();
                quantity.setKey(key);
                quantity.setValue(ratesObject.getDouble(key));

                quantities[i] = quantity;
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            quantities = new Quantity[0];
        }

        this.activity.populateSpinner(quantities);
    }

}
