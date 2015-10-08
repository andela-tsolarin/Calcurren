package com.andela.toni.calcurren;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.andela.toni.calcurren.operations.CalculatorOperations;
import com.andela.toni.calcurren.enums.MathOperator;

public class CalculatorActivity extends ActionBarActivity {

    private CalculatorOperations calcOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        TextView numDisplay = (TextView) findViewById(R.id.figures);
        calcOps = new CalculatorOperations(numDisplay);
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
//        switch (v.getId()) {
//            case R.id.btnC:
//                inView = "0";
//                break;
//            case R.id.btnDel:
//                inView = inView.substring(0, inView.length() - 1);
//                break;
//        }
//
//        inView = (inView.length() == 0) ? "0" : inView;
//        numbers.setText(inView);
    }
}
