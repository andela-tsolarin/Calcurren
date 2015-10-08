package com.andela.toni.calcurren.operations;

import android.widget.*;
import com.andela.toni.calcurren.enums.MathOperator;

public class CalculatorOperations {

    private String focusedStr;
    private TextView numberDisplay;
    private double leftOperand;
    private MathOperator operator;

    public CalculatorOperations(TextView numDisplay) {

        this.leftOperand = 0;
        this.numberDisplay = numDisplay;
        this.focusedStr = "0";
        this.numberDisplay.setText(focusedStr);
        operator = null;
    }

    public void appendNum(String num) {

        if (this.focusedStr.length() < 14) {
            this.focusedStr = (this.focusedStr == "0") ? "" : this.focusedStr;
            this.focusedStr = (this.numberDisplay.getText() == "0") ? num : this.focusedStr + num;
            this.numberDisplay.setText(this.focusedStr);
        }
    }

    public void applyOperator(MathOperator op) {

        boolean firstTime = this.operator == null;
        double focusedNum = Double.parseDouble(this.focusedStr);
        this.leftOperand = (this.operator == null) ? focusedNum : performOperation(this.leftOperand, focusedNum);
        this.operator = op;
        if (this.operator == MathOperator.EQUAL) {
            this.operator = null;
        }
        this.focusedStr = "0";
        this.numberDisplay.setText((firstTime) ? this.focusedStr : Double.toString(this.leftOperand));
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
