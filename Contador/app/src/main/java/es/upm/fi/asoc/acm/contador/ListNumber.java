package es.upm.fi.asoc.acm.contador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_number);

        ArrayList<Integer> numbers = new ArrayList<>();

        for(Integer i = 0; i <=100; i++){
            numbers.add(i);
        }

        final ListView list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(ListNumber.this,
                android.R.layout.simple_list_item_1,
                numbers);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Integer selectedNumber = (Integer) list.getItemAtPosition(position);
                Intent i = new Intent(ListNumber.this, MainActivity.class);
                i.putExtra(MainActivity.NEW_NUMBER, selectedNumber);
                startActivity(i);
            }
        });
    }
}
