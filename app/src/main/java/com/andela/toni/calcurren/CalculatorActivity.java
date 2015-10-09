package com.andela.toni.calcurren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.andela.toni.calcurren.helpers.ExchangeRateHelper;
import com.andela.toni.calcurren.helpers.Helper;
import com.andela.toni.calcurren.models.Quantity;
import com.andela.toni.calcurren.operations.CalculatorOperations;
import com.andela.toni.calcurren.enums.MathOperator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorOperations calcOps;
    private Spinner currSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        TextView numDisplay = (TextView) findViewById(R.id.figures);
        currSpinner = (Spinner) findViewById(R.id.currentCurrency);
        calcOps = new CalculatorOperations(numDisplay);
        Helper helper = new ExchangeRateHelper(this);
        helper.getQuantities();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    public void populateSpinner(Quantity[] quantities) {

        List<String> currencyList = new ArrayList<>();
        for (int i = 0; i < quantities.length; i++) {
            currencyList.add(quantities[i].getKey());
        }

        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyList);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currSpinner.setAdapter(currencyAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void numButtonClicked(View v) {
        Button btnNum = (Button) findViewById(v.getId());
        calcOps.appendNum(btnNum.getText().toString());
    }

    public void operatorButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.btnPlus:
                calcOps.applyOperator(MathOperator.ADD);
                break;
            case R.id.btnMinus:
                calcOps.applyOperator(MathOperator.SUBTRACT);
                break;
            case R.id.btnMult:
                calcOps.applyOperator(MathOperator.MULTIPLY);
                break;
            case R.id.btnDiv:
                calcOps.applyOperator(MathOperator.DIVIDE);
                break;
            case R.id.btnEquals:
                calcOps.applyOperator(MathOperator.EQUAL);
                break;
        }
    }

    public void operationButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnDel:
                calcOps.clearOne();
                break;
            case R.id.btnC:
                calcOps.clearAll();
                break;
        }
    }
}
