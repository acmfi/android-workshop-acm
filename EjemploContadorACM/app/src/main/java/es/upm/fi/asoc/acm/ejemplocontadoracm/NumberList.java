package es.upm.fi.asoc.acm.ejemplocontadoracm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by pau on 17/11/15.
 */
public class NumberList extends ListActivity {

    ArrayList<Integer> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_list);

        numbers = createList(0,100);

        ArrayAdapter listAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, numbers);
        setListAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Integer numberSelected = (Integer) getListView().getAdapter().getItem(position);
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(MainActivity.START_NUMBER, numberSelected);
        startActivity(i);
    }

    private ArrayList<Integer> createList(Integer start, Integer finish){
        ArrayList<Integer> array = new ArrayList<Integer>();
        for(Integer i = start; i <= finish; i++){
            array.add(i);
        }
        return array;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
