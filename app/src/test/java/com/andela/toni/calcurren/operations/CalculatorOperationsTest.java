package com.andela.toni.calcurren.operations;

import com.andela.toni.calcurren.converters.CurrencyConverter;
import com.andela.toni.calcurren.enums.MathOperator;
import com.andela.toni.calcurren.models.Quantity;

import junit.framework.TestCase;

/**
 * Created by tonie on 10/24/2015.
 */
public class CalculatorOperationsTest extends TestCase {

    Quantity[] quantities;
    CalculatorOperations calcOps;

    public void setUp() throws Exception {
        super.setUp();
        Quantity quantity = new Quantity();
        quantity.setKey("NGN");
        quantity.setValue(200);

        Quantity quantity1 = new Quantity();
        quantity1.setKey("GBP");
        quantity1.setValue(0.7);

        quantities = new Quantity[] { quantity, quantity1 };
        calcOps = new CalculatorOperations(new CurrencyConverter());
        calcOps.setConversionQuantities(quantities);
    }

    public void testGetOperatorSymbol() throws Exception {
        assertEquals("+", calcOps.getOperatorSymbol(MathOperator.ADD));
        assertEquals("-", calcOps.getOperatorSymbol(MathOperator.SUBTRACT));
        assertEquals("*", calcOps.getOperatorSymbol(MathOperator.MULTIPLY));
        assertEquals("/", calcOps.getOperatorSymbol(MathOperator.DIVIDE));
        assertEquals("", calcOps.getOperatorSymbol(MathOperator.EQUAL));
    }

    public void testAddOperand() throws Exception {
        String displayStr = calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        assertEquals("0", displayStr);
        displayStr = calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        assertEquals("10.0", displayStr);
        assertEquals("NGN 5 + NGN 5 + ", calcOps.getHistory());
    }

    public void testClearHistory() throws Exception {
        calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        calcOps.clearHistory();
        assertEquals("", calcOps.getHistory());
    }

    public void testGetHistory() throws Exception {
        calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        calcOps.addOperand("5", MathOperator.ADD, "NGN", "NGN");
        assertEquals("NGN 5 + NGN 5 + ", calcOps.getHistory());
    }
}