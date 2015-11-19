package es.upm.fi.asoc.acm.ejemplocontadoracm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String START_NUMBER = "startFrom";
    private int number;
    private TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra(START_NUMBER)){
            number = getIntent().getIntExtra(START_NUMBER,0);
        } else {
            number = 0;
        }

        setContentView(R.layout.activity_main);

        tvNumber = (TextView) findViewById(R.id.number);
        changeNumber(number);
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NumberList.class);
                startActivity(i);
            }
        });

        Button btnBack = (Button) findViewById(R.id.back_number);

        Button btnNext = (Button) findViewById(R.id.next_number);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number<100){
                    number = number + 1;
                    changeNumber(number);
                } else {
                    Toast.makeText(getApplicationContext(), "No more please", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnReset = (Button) findViewById(R.id.reset);
        btnReset.setOnClickListener(this);
    }

    public void onBackClick(View view){
        if(number>0){
            number = number - 1;
            changeNumber(number);
        } else {
            Toast.makeText(this,"This is the end!",Toast.LENGTH_SHORT).show();
        }
    }

    private void changeNumber(int number){
        if(number>=0 && number <=100){
            tvNumber.setText(String.valueOf(number));
        }
    }

    @Override
    public void onClick(View view) {
        number = 0;
        changeNumber(number);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
