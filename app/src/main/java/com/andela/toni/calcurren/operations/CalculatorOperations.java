package com.andela.toni.calcurren.operations;

import android.widget.*;

import com.andela.toni.calcurren.enums.MathOperator;
import com.andela.toni.calcurren.converters.Converter;
import com.andela.toni.calcurren.models.Quantity;

public class CalculatorOperations {

    private Converter converter;
    private Quantity[] quantities;
    private String history;
    private double leftOperand;
    private MathOperator operator;

    public CalculatorOperations(Converter converter) {
        this.converter = converter;
        this.history = "";
    }

    public void setConversionQuantities(Quantity[] quantities) {
        this.quantities = quantities;
    }

    public String getOperatorSymbol(MathOperator operator) {

        String symbol = "";

        switch (operator) {
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

    public String addOperand(String num, MathOperator operator) {

        String displayStr = performCalculation(num, operator);
        addToHistory(num, operator);
        return displayStr;
    }

    private void addToHistory(String num, MathOperator operator) {
        this.history += num + " " + getOperatorSymbol(operator) + " ";
    }

    private String performCalculation(String num, MathOperator operator) {
        boolean firstTime = this.operator == null;
        double focusedNum = Double.parseDouble(num);
        this.leftOperand = (this.operator == null) ? focusedNum : performOperation(this.leftOperand, focusedNum);
        this.operator = operator;
        if (this.operator == MathOperator.EQUAL) {
            clearHistory();
        }

        String number = "0";
        return (firstTime) ? number : Double.toString(this.leftOperand);
    }

    private void clearHistory() {
        this.operator = null;
        this.history = "";
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

    public String getHistory() {
        return this.history;
    }

}
