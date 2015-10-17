package com.andela.toni.calcurren;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andela.toni.calcurren.config.Globals;
import com.andela.toni.calcurren.models.Quantity;
import com.andela.toni.calcurren.operations.QuantityOperations;

import java.util.ArrayList;
import java.util.List;


public class TopCurrenciesActivity extends AppCompatActivity {

    private List<String> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_currencies);
        this.currencyList = new ArrayList<>();
        if (Globals.QUANTITIES != null) {
            getTopTen();
            populateListView();
        }
    }

    private void populateListView() {
        ListView lvCurrency = (ListView)findViewById(R.id.listViewCurrencies);
        ArrayAdapter<String> arrayAdapter = getCurrencyAdapter();
        lvCurrency.setAdapter(arrayAdapter);
    }

    private ArrayAdapter<String> getCurrencyAdapter() {
        return new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, this.currencyList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_currencies, menu);
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

    public void getTopTen() {
        QuantityOperations qOps = new QuantityOperations();
        List<Quantity> quantities = qOps.getSorted(Globals.QUANTITIES);
        int upperLimit = (quantities.size() < 10) ? quantities.size() : 10;
        for (int i = 0; i < upperLimit; i++) {
            this.currencyList.add(quantities.get(i).getKey());
        }
    }
}
