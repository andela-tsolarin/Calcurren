package com.andela.toni.calcurren.operations;

import android.widget.*;

import com.andela.toni.calcurren.converters.CurrencyConverter;
import com.andela.toni.calcurren.enums.MathOperator;
import com.andela.toni.calcurren.converters.Converter;
import com.andela.toni.calcurren.models.Quantity;

import org.w3c.dom.Text;

public class CalculatorOperations {

    private String focusedStr;
    private TextView numberDisplay;
    private TextView histDisplay;
    private double leftOperand;
    private MathOperator operator;
    private Converter converter;
    private Quantity[] quantities;

    public CalculatorOperations(TextView numDisplay, TextView histDisplay) {

        this.numberDisplay = numDisplay;
        this.histDisplay = histDisplay;
        this.clearAll();
        this.converter = new CurrencyConverter();
    }

    public void appendNum(String num) {

        if (this.focusedStr.length() < 14) {
            this.focusedStr = (this.focusedStr == "0") ? "" : this.focusedStr;
            this.focusedStr = (this.numberDisplay.getText() == "0") ? num : this.focusedStr + num;
            this.numberDisplay.setText(this.focusedStr);
        }
    }

    public void setConversionQuantities(Quantity[] quantities) {
        this.quantities = quantities;
    }

    public void applyOperator(MathOperator op, String curr, String base) {

        boolean firstTime = this.operator == null;
        double focusedNum = Double.parseDouble(this.focusedStr);
        focusedNum = converter.convert(curr, base, focusedNum, this.quantities);
        this.leftOperand = (this.operator == null) ? focusedNum : performOperation(this.leftOperand, focusedNum);
        this.operator = op;
        addHistory(op, curr, focusedStr);
        if (this.operator == MathOperator.EQUAL) {
            this.operator = null;
        }
        this.focusedStr = "0";
        this.numberDisplay.setText((firstTime) ? this.focusedStr : Double.toString(this.leftOperand));
    }

    public String getOperatorSymbol(MathOperator op) {

        String symbol = "";

        switch (this.operator) {
            case ADD:
                symbol = "+";
                break;
            case SUBTRACT:
                symbol = "-";
                break;
            case MULTIPLY:
                symbol = "*";
                break;
            case DIVIDE:
                symbol = "/";
                break;
        }

        return symbol;
    }

    public void addHistory(MathOperator op, String currency, String num) {
        this.histDisplay.append(currency + " " + num + " " + getOperatorSymbol(op) + " ");
    }

    public void clearOne() {
        this.focusedStr = this.focusedStr.substring(0, this.focusedStr.length() - 1);
        this.focusedStr = (this.focusedStr.length() == 0) ? "0" : this.focusedStr;
        this.numberDisplay.setText(this.focusedStr);
    }

    public void clearAll() {
        this.focusedStr = "0";
        this.leftOperand = 0;
        this.operator = null;
        this.numberDisplay.setText(this.focusedStr);
    }

    private double performOperation(double a, double b) {

        double result = 0;
        switch (this.operator) {
            case ADD:
                result = a + b;
                break;
            case SUBTRACT:
                result = a - b;
                break;
            case MULTIPLY:
                result = a * b;
                break;
            case DIVIDE:
                result = a / b;
                break;
        }

        return result;
    }
}
