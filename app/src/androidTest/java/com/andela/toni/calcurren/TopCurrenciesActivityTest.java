package com.andela.toni.calcurren;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Andela on 10/18/15.
 */
public class TopCurrenciesActivityTest extends ActivityInstrumentationTestCase2<TopCurrenciesActivity> {

    public TopCurrenciesActivityTest(){
        super(TopCurrenciesActivity.class);
    }


    //test to see if activity exists
    public void testActivityExists() {
        TopCurrenciesActivity activity = getActivity();
        assertNotNull(activity);
    }
}
