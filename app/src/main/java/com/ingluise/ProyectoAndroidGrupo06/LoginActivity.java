package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView tv1;
    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar Action Bar
        getSupportActionBar().hide();
        tv1 = (TextView) findViewById(R.id.textView4);
        et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        et2 = (EditText) findViewById(R.id.editTextTextPassword);
//        String link = "<a href='https://imaster.academy/course/view.php?id=999&section=3'>iMaster</a>";
        String texto = "<a href=''>Recordar contraseña</a>";
//        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        tv1.setTextColor(Color.BLUE);
        tv1.setLinkTextColor(Color.BLUE);
        tv1.setText(Html.fromHtml(texto));
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Su contraseña es: admin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void iniciarSesion(View view) {
        if(et1.getText().toString().equals("admin") && et2.getText().toString().equals("admin")) {
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
            finish();
        }
        else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
        if(et1.getText().toString().equals("")) {
            Toast.makeText(this, "Por favor, ingrese el usuario", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        else if(et2.getText().toString().equals("")) {
            Toast.makeText(this, "Por favor, ingrese la contraseña", Toast.LENGTH_SHORT).show();
            et2.requestFocus();
        }
    }
}