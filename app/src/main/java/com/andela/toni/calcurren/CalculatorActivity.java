package com.andela.toni.calcurren;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends ActionBarActivity {

    private TextView numbers;
    private String inView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        numbers = (TextView) findViewById(R.id.figures);
        inView = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void numButtonClicked(View v) {
        Button num = (Button) findViewById(v.getId());
        inView = (numbers.getText() == "0") ? num.getText().toString() : inView + num.getText().toString();
        numbers.setText(inView);
    }

    public void operatorButtonClicked(View v) {

    }

    public void operationButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnC:
                inView = "0";
                break;
            case R.id.btnDel:
                inView = inView.substring(0, inView.length() - 1);
                break;
        }

        inView = (inView.length() == 0) ? "0" : inView;
        numbers.setText(inView);
    }
}
