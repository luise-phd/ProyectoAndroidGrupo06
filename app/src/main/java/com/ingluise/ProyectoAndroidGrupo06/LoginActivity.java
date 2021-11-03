package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar Action Bar
        getSupportActionBar().hide();
        tv1 = (TextView) findViewById(R.id.textView4);
        tv1.setText("Recordar Contraseña");
        tv1.setTextColor(Color.BLUE);
    }
}