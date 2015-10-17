package com.andela.toni.calcurren;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.andela.toni.calcurren.config.Globals;
import com.andela.toni.calcurren.helpers.ExchangeRateHelper;
import com.andela.toni.calcurren.helpers.Helper;
import com.andela.toni.calcurren.models.Quantity;
import com.andela.toni.calcurren.operations.CalculatorOperations;
import com.andela.toni.calcurren.enums.MathOperator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorOperations calcOps;
    private List<String> currencyList;
    private Spinner currSpinner;
    private Spinner baseSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        TextView numDisplay = (TextView) findViewById(R.id.figures);
        TextView histDisplay = (TextView) findViewById(R.id.history);

        currSpinner = (Spinner) findViewById(R.id.currentCurrency);
        baseSpinner = (Spinner) findViewById(R.id.baseCurrency);
        calcOps = new CalculatorOperations(numDisplay, histDisplay);

        Helper helper = new ExchangeRateHelper(this);
        helper.getQuantities();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    public void populateSpinner(Quantity[] quantities) {

        calcOps.setConversionQuantities(quantities);
        Globals.QUANTITIES = quantities;
        this.currencyList = new ArrayList<>();
        for (int i = 0; i < quantities.length; i++) {
            currencyList.add(quantities[i].getKey());
        }

        ArrayAdapter<String> currencyAdapter = getCurrencyAdapter();
        currSpinner.setAdapter(currencyAdapter);
        baseSpinner.setAdapter(currencyAdapter);
    }

    private ArrayAdapter<String> getCurrencyAdapter() {
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, this.currencyList);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return currencyAdapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void topCurrenciesClicked(View v) {
        Intent topCurrenciesIntent = new Intent(this, TopCurrenciesActivity.class);
        startActivity(topCurrenciesIntent);
    }

    public void numButtonClicked(View v) {
        Button btnNum = (Button) findViewById(v.getId());
        calcOps.appendNum(btnNum.getText().toString());
    }

    public void operatorButtonClicked(View v) {

        String curr = currSpinner.getSelectedItem().toString();
        String base = baseSpinner.getSelectedItem().toString();

        switch (v.getId()) {
            case R.id.btnPlus:
                calcOps.applyOperator(MathOperator.ADD, curr, base);
                break;
            case R.id.btnMinus:
                calcOps.applyOperator(MathOperator.SUBTRACT, curr, base);
                break;
            case R.id.btnMult:
                calcOps.applyOperator(MathOperator.MULTIPLY, curr, base);
                break;
            case R.id.btnDiv:
                calcOps.applyOperator(MathOperator.DIVIDE, curr, base);
                break;
            case R.id.btnEquals:
                calcOps.applyOperator(MathOperator.EQUAL, curr, base);
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
