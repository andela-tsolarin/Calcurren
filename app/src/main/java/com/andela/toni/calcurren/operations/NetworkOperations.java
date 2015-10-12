package com.andela.toni.calcurren.operations;

import android.util.Log;

import com.andela.toni.calcurren.helpers.Helper;
import com.loopj.android.http.*;
import com.andela.toni.calcurren.config.Constants;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Andela on 10/9/15.
 */
public class NetworkOperations {

    public void getJsonResponse(final Helper helper) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(Constants.API_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                helper.finished(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                Log.d("Error", "Error occured");
            }

        });
    }
}
