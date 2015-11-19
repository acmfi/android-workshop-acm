package com.jmrbaram.dam_calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivitySP extends Activity {

    private Button btnGuardar;
    private EditText edtMail, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        btnGuardar = (Button)findViewById(R.id.bt_guardar);
        edtMail = (EditText)findViewById(R.id.et_mail);
        edtName = (EditText)findViewById(R.id.et_name);

        //Recuperamos las preferencias
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String correo = prefs.getString("email", "por_defecto@email.com");
        String nombre = prefs.getString("nombre", "nombre_por_defecto");

        edtMail.setText(correo);
        edtName.setText(nombre);

        Log.i("Preferences", "Correo: " + correo);
        Log.i("Preferences", "Nombre: " + nombre);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Guardamos las preferencias
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", edtMail.getText().toString());
                editor.putString("nombre", edtName.getText().toString());
                editor.commit();

                finish();
            }
        });
    }
}
