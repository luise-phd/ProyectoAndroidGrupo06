package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EmpleadoActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private EditText et1, et2, et3, et4, et5, et6;
    private Spinner sp1, sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        admin = new MyDBSQLiteHelper(this, vars.db, null, vars.ver);

        //Activar el supoorte para la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getParametros();

        et1 = findViewById(R.id.input_nombre);
        et2 = findViewById(R.id.input_apellido);
        et3 = findViewById(R.id.input_fecha_nac);
        et4 = findViewById(R.id.input_telefono);
        et5 = findViewById(R.id.input_direccion);
        et6 = findViewById(R.id.input_email);
        sp1 = findViewById(R.id.spinner_estado_civil);
        sp2 = findViewById(R.id.spinner_cargo);
    }

    public void agregarDatos(View view) {
        String nom = et1.getText().toString();
        String ape = et2.getText().toString();
        if (!nom.equals("") && !ape.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("apellidos", ape);
            long reg = db.insert("empleado", null, cv);
            if (reg == -1) {
                Toast.makeText(this, "No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et1.requestFocus();
                et2.setText("");
            }
        } else {
            Toast.makeText(this, "Por favor, ingrese los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void listarDatos(View view) {

    }

    public void eliminarDatos(View view) {

    }

    public void buscarDatos(View view) {

    }

    public void editarDatos(View view) {

    }

    public void limpiarDatos(View view) {

    }

    //Destruir la aplicación
    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public void getParametros() {
        Bundle extras = getIntent().getExtras();
//        String msg = extras.getString("msg");
//        int year = extras.getInt("year");
//        Toast.makeText(this, msg + " " + year, Toast.LENGTH_SHORT).show();
    }

    public void goToActivityMain(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
}