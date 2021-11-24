package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EncuestaActivity extends AppCompatActivity {
    private static final String TAG = EncuestaActivity.class.getSimpleName();

    private TextView tv1;
    private TextInputEditText ti1, ti2, ti3, ti4;
    private Spinner sp1;
    private Switch sw1;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6;
    private RadioButton rb1, rb2, rb3;
    private SeekBar sb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        tv1 = findViewById(R.id.textView12);
        sb1 = findViewById(R.id.seekBar2);
        ti1 = findViewById(R.id.txt_nom);
        ti2 = findViewById(R.id.txt_fnac);
        ti3 = findViewById(R.id.txt_tel);
        ti4 = findViewById(R.id.txt_email);
        ch1 = findViewById(R.id.checkBox);
        ch2 = findViewById(R.id.checkBox2);
        ch3 = findViewById(R.id.checkBox3);
        ch4 = findViewById(R.id.checkBox4);
        ch5 = findViewById(R.id.checkBox5);
        ch6 = findViewById(R.id.checkBox6);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        sp1 = findViewById(R.id.spinner);
        sw1 = findViewById(R.id.switch1);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv1.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

        try {
            double op = 152 / 0;
        } catch (ArithmeticException ae) {
            Log.w(TAG, "Error", ae);
        }
    }

    public void verDatos(View view) {
        String nom = "Nombre: " + ti1.getText().toString();
        String fnac = "Fecha de nacimiento: " + ti2.getText().toString();
        String tel = "Teléfono: " + ti3.getText().toString();
        String email = "Correo electrónico: " + ti4.getText().toString();
        String nivel_ing = "Nivel de inglés: " + sp1.getSelectedItem();
        String gus_prog = "¿Te gusta programar? ";
        if(sw1.isChecked())
            gus_prog += "SI";
        else
            gus_prog += "NO";
        String lengs = "Lenguajes de programación: ";
        if(ch1.isChecked())
            lengs += ch1.getText().toString() + ", ";
        if(ch2.isChecked())
            lengs += ch2.getText().toString() + ", ";
        if(ch3.isChecked())
            lengs += ch3.getText().toString() + ", ";
        if(ch4.isChecked())
            lengs += ch4.getText().toString() + ", ";
        if(ch5.isChecked())
            lengs += ch5.getText().toString() + ", ";
        if(ch6.isChecked())
            lengs += ch6.getText().toString();
        String exp = "Tiempo de experiencia: ";
        if(rb1.isChecked())
            exp += rb1.getText().toString();
        else if(rb2.isChecked())
            exp += rb2.getText().toString();
        else if(rb3.isChecked())
            exp += rb3.getText().toString();
        String nivel_sat = "Nivel de satisfacción (1-10): " + tv1.getText().toString();
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Datos")
            .setMessage(nom + "\n" + fnac + "\n" + tel + "\n" + email + "\n" + nivel_ing +
                    "\n" + gus_prog + "\n" + lengs + "\n" + exp + "\n" + nivel_sat)
            .setPositiveButton("Aceptar", null).show();
    }
}