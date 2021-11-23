package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CargoFirebaseActivity extends AppCompatActivity {
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);

        et1 = findViewById(R.id.input_cargo);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hola mundo!");
    }

    @Override
    //Carga el menu en la actividad
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    //Verfifica la opcion de menu seleccionada
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_agregar) {

        }
        else if(id == R.id.mnu_listar) {

        }
        else if(id == R.id.mnu_eliminar) {

        }
        else if(id == R.id.mnu_buscar) {

        }
        else if(id == R.id.mnu_editar) {

        }
        else if(id == R.id.mnu_limpiar) {
            et1.setText("");
        }

        return super.onOptionsItemSelected(menuItem);
    }
}