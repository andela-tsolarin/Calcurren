package com.andela.toni.calcurren;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.andela.toni.calcurren.config.Globals;
import com.andela.toni.calcurren.converters.Converter;
import com.andela.toni.calcurren.converters.CurrencyConverter;
import com.andela.toni.calcurren.helpers.ExchangeRateHelper;
import com.andela.toni.calcurren.helpers.Helper;
import com.andela.toni.calcurren.models.Quantity;
import com.andela.toni.calcurren.operations.CalculatorOperations;
import com.andela.toni.calcurren.enums.MathOperator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorOperations calcOps;
    private List<String> currencyList;
    private Spinner currSpinner;
    private Spinner baseSpinner;
    private String baseCurrency;
    private Converter converter;
    private boolean operatorClicked;

    TextView numDisplay;
    TextView histDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        converter = new CurrencyConverter();

        numDisplay = (TextView) findViewById(R.id.figures);
        histDisplay = (TextView) findViewById(R.id.history);
        this.operatorClicked = false;

        currSpinner = (Spinner) findViewById(R.id.currentCurrency);
        baseSpinner = (Spinner) findViewById(R.id.baseCurrency);
        calcOps = new CalculatorOperations(converter);

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

        baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String baseQuantity = baseSpinner.getSelectedItem().toString();
                if (!numDisplay.getText().toString().equals("0")) {
                    double num = Double.parseDouble(numDisplay.getText().toString());
                    double res = converter.convert(baseCurrency, baseQuantity, num, Globals.QUANTITIES);
                    numDisplay.setText(Double.toString(res));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
            Intent topCurrenciesIntent = new Intent(this, TopCurrenciesActivity.class);
            startActivity(topCurrenciesIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void numButtonClicked(View v) {

        Button btnNum = (Button) findViewById(v.getId());
        String tappedNum = btnNum.getText().toString();
        String displayNum = this.numDisplay.getText().toString();

        if (displayNum.equals("0") && tappedNum.equals("0")) {
            // Do nothing
        } else{

            if (displayNum.length() == 1 && displayNum.equals("0") || this.operatorClicked) {
                this.numDisplay.setText(tappedNum);
            } else {
                this.numDisplay.append(tappedNum);
            }
        }

        this.operatorClicked = false;
    }

    public void operatorButtonClicked(View v) {

        String currentQuantity = currSpinner.getSelectedItem().toString();
        String baseQuantity = baseSpinner.getSelectedItem().toString();
        this.baseCurrency = baseQuantity;

        String displayNum = this.numDisplay.getText().toString();

        if (displayNum.equals("0")) {
            return;
        }

        switch (v.getId()) {
            case R.id.btnPlus:
                displayNum = calcOps.addOperand(displayNum, MathOperator.ADD, currentQuantity, baseQuantity);
                break;
            case R.id.btnMinus:
                displayNum = calcOps.addOperand(displayNum, MathOperator.SUBTRACT, currentQuantity, baseQuantity);
                break;
            case R.id.btnMult:
                displayNum = calcOps.addOperand(displayNum, MathOperator.MULTIPLY, currentQuantity, baseQuantity);
                break;
            case R.id.btnDiv:
                displayNum = calcOps.addOperand(displayNum, MathOperator.DIVIDE, currentQuantity, baseQuantity);
                break;
            case R.id.btnEquals:
                displayNum = calcOps.addOperand(displayNum, MathOperator.EQUAL, currentQuantity, baseQuantity);
                break;
        }

        this.operatorClicked = true;
        this.numDisplay.setText(displayNum);
        this.histDisplay.setText(calcOps.getHistory());
    }

    public void backSpace() {

        String displayNum = this.numDisplay.getText().toString();
        displayNum = displayNum.substring(0, displayNum.length() - 1);
        displayNum = (displayNum.length() == 0) ? "0" : displayNum;
        this.numDisplay.setText(displayNum);
    }

    public void operationButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.btnDel:
                backSpace();
                break;
            case R.id.btnC:
                calcOps.clearHistory();
                this.numDisplay.setText("0");
                break;
        }

        this.histDisplay.setText(calcOps.getHistory());
    }
}
