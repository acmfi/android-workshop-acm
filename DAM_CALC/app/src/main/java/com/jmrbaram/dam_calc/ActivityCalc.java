package com.jmrbaram.dam_calc;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityCalc extends Activity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
        }
    }

    private enum Estado { E1, E2, E3 }

    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdiv,bmul,bmin,bplu,bdot,beq;
    private TextView tvM1,tvOp,tvM2,tvResult;
    private Estado e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //Set default state
        this.e = Estado.E1;

        //Find button views
        configButtonViews();

        //Listeners
        configButtonsListeners();



    }

    private void configButtonViews() {
        this.tvM1 = (TextView)findViewById(R.id.tv_member1);
        this.tvM2 = (TextView)findViewById(R.id.tv_member2);
        this.tvOp = (TextView)findViewById(R.id.tv_operation);
        this.tvResult = (TextView)findViewById(R.id.tv_result);
        this.b0 = (Button)findViewById(R.id.b0);
        this.b1 = (Button)findViewById(R.id.b1);
        this.b2 = (Button)findViewById(R.id.b2);
        this.b3 = (Button)findViewById(R.id.b3);
        this.b4 = (Button)findViewById(R.id.b4);
        //this.b5 = (Button)findViewById(R.id.b5);
        //this.b6 = (Button)findViewById(R.id.b6);
        //this.b7 = (Button)findViewById(R.id.b7);
        //this.b8 = (Button)findViewById(R.id.b8);
        //this.b9 = (Button)findViewById(R.id.b9);
        //this.bdiv = (Button)findViewById(R.id.bdiv);
        this.bmul = (Button)findViewById(R.id.bmul);
        //this.bmin = (Button)findViewById(R.id.bmin);
        this.bplu = (Button)findViewById(R.id.bplu);
        //this.bdot = (Button)findViewById(R.id.bdot);
        this.beq = (Button)findViewById(R.id.beq);
    }

    private void configButtonsListeners() {
        this.b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(true);
                setSymbol("0");
            }
        });
        this.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(true);
                setSymbol("1");
            }
        });
        this.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(true);
                setSymbol("2");
            }
        });
        this.b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(true);
                setSymbol("3");
            }
        });
        this.b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(true);
                setSymbol("4");
            }
        });
        this.bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(false);
                setSymbol("x");
            }
        });
        this.bplu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(false);
                setSymbol("+");
            }
        });
        this.beq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeOperation();
            }
        });

    }

    private void changeState(boolean isNumber) {
        switch (this.e){
            case E1:
                if (!isNumber) this.e = Estado.E2;
                break;
            case E2:
                if (isNumber) this.e = Estado.E3;
                break;
            case E3:
                if (!isNumber) this.e = Estado.E2;
                break;
            default:
        }
    }

    private void setSymbol(String c) {
        switch (this.e){
            case E1:
                this.tvM1.setText(this.tvM1.getText()+c);
                break;
            case E2:
                this.tvOp.setText(c);
                break;
            case E3:
                this.tvM2.setText(this.tvM2.getText()+c);
                break;
            default:
                Log.e("ERROR","Estado no esperado");
        }
    }


    private void makeOperation() {
        int result1 = Integer.parseInt(this.tvM1.getText().toString());
        int result2 = Integer.parseInt(this.tvM2.getText().toString());
        String operation = this.tvOp.getText().toString();
        int total = 0;
        switch (operation){
            case "+":
                total = result1+result2;
                break;
            case "x":
                total = result1*result2;
                break;
            default:
                Log.e("ERROR","Operación no definida");
        }

        this.tvResult.setText(Integer.toString(total));

        //Reset
        this.e = Estado.E1;
        this.tvM1.setText("");
        this.tvOp.setText("");
        this.tvM2.setText("");

    }
}
