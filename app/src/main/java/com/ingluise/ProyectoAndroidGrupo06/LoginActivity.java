package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView tv1, tv2;
    private EditText et1, et2;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences("id", Context.MODE_PRIVATE);

        //Ocultar Action Bar
        getSupportActionBar().hide();
        tv1 = (TextView) findViewById(R.id.textView4);
        tv2 = findViewById(R.id.textView13);
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
                String usuario = et1.getText().toString();
                if (usuario.equals(settings.getString("user", ""))) {
                    Toast.makeText(LoginActivity.this, "Su contraseña es: " + settings.getString("pass", ""), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
        texto = "<a href=''>Regístrese</a>";
        tv2.setTextColor(Color.BLUE);
        tv2.setLinkTextColor(Color.BLUE);
        tv2.setText(Html.fromHtml(texto));
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = et1.getText().toString();
                String clave = et2.getText().toString();
                if(!usuario.equals("") && !clave.equals("")) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("user", usuario);
                    editor.putString("pass", clave);
                    editor.commit();
                    et1.setText("");
                    et2.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese el usuario y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciarSesion(View view) {
        String usuario = et1.getText().toString();
        String clave = et2.getText().toString();
        if(usuario.equals(settings.getString("user", "")) && clave.equals(settings.getString("pass", ""))) {
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