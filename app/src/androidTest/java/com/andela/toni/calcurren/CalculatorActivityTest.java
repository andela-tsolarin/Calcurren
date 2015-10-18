package com.andela.toni.calcurren;

import android.test.ActivityInstrumentationTestCase2;

public class CalculatorActivityTest extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    public CalculatorActivityTest(){
        super(CalculatorActivity.class);
    }


    //test to see if activity exists
    public void testActivityExists() {
        CalculatorActivity activity = getActivity();
        assertNotNull(activity);
    }
}
