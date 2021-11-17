package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CamaraActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private ImageButton btnCamara;
    private ImageView imgView;
    private EditText txtDescr;

    private Bitmap bmp1, bmp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        admin = new MyDBSQLiteHelper(this, vars.db, null, vars.ver);

        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView);
        imgView.setImageDrawable(null);
        txtDescr = findViewById(R.id.input_descripcion);
    }

    public void abrirCamara(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
    }

    @Override
    //Carga el menu en la actividad
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camara, menu);
        return true;
    }

    //Verfifica la opcion de menu seleccionada
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_guardar) {
            String des = txtDescr.getText().toString();
            if (!des.equals("")) {
                String imgCodificada = "";
                imgView.buildDrawingCache(true);
                bmp1 = imgView.getDrawingCache(true);
                if (imgView.getDrawable() != null) {
                    bmp2 = Bitmap.createScaledBitmap(bmp1, imgView.getWidth(), imgView.getHeight(), true);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp2.compress(Bitmap.CompressFormat.PNG, 25, baos);
                    byte[] imagen = baos.toByteArray();
                    imgCodificada = Base64.encodeToString(imagen, Base64.DEFAULT);
                }
                else
                    Toast.makeText(this, "Por favor, tome la fotografía", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Por favor, ingrese la descripción", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_buscar) {

        }

        return super.onOptionsItemSelected(menuItem);
    }
}