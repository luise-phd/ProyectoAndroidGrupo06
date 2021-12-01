package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        Log.i("Información", "onCreate");
        tv1 = (TextView) findViewById(R.id.textView);
        tv1.setText("Hola Mundo!");
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
//            Toast.makeText(this, ""+tv1.getTop(), Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo onClik del boton
    public void goToActivitySecond(View view) {
        Intent newIntent = new Intent(this, EmpleadoActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        newIntent.putExtra("msg", "Hola MinTIC");
//        newIntent.putExtra("year", 2020);
        startActivity(newIntent);
    }

    //Dialogo para confirmar salir de la aplicación
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Información")
                    .setMessage("¿Desea salir?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    //Carga el menu en la actividad
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Verfifica la opcion de menu seleccionada
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_empleado) {
            SharedPreferences settings = getSharedPreferences("id", Context.MODE_PRIVATE);
//            Toast.makeText(this, settings.getString("user", ""), Toast.LENGTH_SHORT).show();
            if(settings.getString("user", "").equals("admin")) {
                Intent newIntent = new Intent(this, EmpleadoActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                newIntent.putExtra("msg", "Hola MinTIC");
                newIntent.putExtra("year", 2020);
                startActivity(newIntent);
            }
            else {
                Toast.makeText(this, "No tiene permiso para esta actividad", Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.mnu_horizontal_scrollview) {
            Intent newIntent = new Intent(this, HorizontalScrollViewActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_scrollview) {
            Intent newIntent = new Intent(this, ScrollViewActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_api_rest) {
            Intent newIntent = new Intent(this, APIRestActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_cargo_firebase) {
            Intent newIntent = new Intent(this, CargoFirebaseActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_empleado_firebase) {
            Intent newIntent = new Intent(this, EmpleadoFirebaseActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_encuesta) {
            Intent newIntent = new Intent(this, EncuestaActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_imagenes) {
            Intent newIntent = new Intent(this, ImagenesActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_ubicacion) {
            Intent newIntent = new Intent(this, LocationActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_nomina) {
            Intent newIntent = new Intent(this, NominaActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_recycler_view) {
            Intent newIntent = new Intent(this, RecyclerViewActivity.class);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_recycler_view_basic) {
            Intent newIntent = new Intent(this, RecyclerViewBasicActivity.class);
            startActivity(newIntent);
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Información","onDestroy");
    }
}