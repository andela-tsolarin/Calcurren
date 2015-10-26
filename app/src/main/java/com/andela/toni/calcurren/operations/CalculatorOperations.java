package com.andela.toni.calcurren.operations;

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

    public String addOperand(String num, MathOperator operator, String currentQuantity, String baseQuantity) {

        String displayStr = performCalculation(num, operator, currentQuantity, baseQuantity);
        addToHistory(num, operator, currentQuantity);
        return displayStr;
    }

    private void addToHistory(String num, MathOperator operator, String currentQuantity) {
        currentQuantity = currentQuantity == null ? "" : currentQuantity;
        this.history += currentQuantity + " " + num + " " + getOperatorSymbol(operator) + " ";
    }

    private String performCalculation(String num, MathOperator operator, String currentQuantity, String baseQuantity) {
        boolean firstTime = this.operator == null;
        if (firstTime) {
            clearHistory();
        }
        double focusedNum = Double.parseDouble(num);
        focusedNum = (currentQuantity != null && baseQuantity != null) ?
                converter.convert(currentQuantity, baseQuantity, focusedNum, this.quantities) : focusedNum;
        this.leftOperand = (this.operator == null) ? focusedNum : performOperation(this.leftOperand, focusedNum);
        this.operator = operator;
        if (this.operator == MathOperator.EQUAL) {
            this.operator = null;
        }

        String number = "0";
        return (firstTime) ? number : Double.toString(this.leftOperand);
    }

    public void clearHistory() {
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
