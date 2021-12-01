package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImagenesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_imagenes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public void abrirGaleria(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //De la cámara
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }

        //De la galería
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackPressed() {
        finish();
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

        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }

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
                    db = admin.getWritableDatabase();
                    cv = new ContentValues();
                    cv.put("descripcion", des);
                    cv.put("img", imgCodificada);
                    long reg = db.insert("imagenes", null, cv);
                    if (reg == -1)
                        Toast.makeText(this, "No se puedo agregar el registro", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                        txtDescr.setText("");
                        txtDescr.requestFocus();
                        imgView.destroyDrawingCache();
                        imgView.setImageBitmap(null);
                        imgView.setImageDrawable(null);
                    }
                    db.close();
                }
                else
                    Toast.makeText(this, "Por favor, tome la fotografía", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Por favor, ingrese la descripción", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_buscar) {
            LayoutInflater li = LayoutInflater.from(this);
            View view = li.inflate(R.layout.inputdialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(view);
            final EditText userInput = view.findViewById(R.id.input_desc);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!userInput.getText().toString().equals("")) {
                                Bitmap decodedByte = null;
                                byte[] decodedString;
                                db = admin.getReadableDatabase();
                                fila = db.rawQuery("SELECT * from imagenes WHERE descripcion='" + userInput.getText().toString() + "'", null);
                                String des = "";
                                if (fila.moveToFirst()) {
                                    des = fila.getString(1);
                                    if (!fila.getString(2).equals("")) {
                                        decodedString = Base64.decode(fila.getString(2), Base64.DEFAULT);
                                        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        txtDescr.setText(des);
                                        imgView.setImageBitmap(decodedByte);
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "La imagen no existe", Toast.LENGTH_SHORT).show();
                                    txtDescr.setText("");
                                    imgView.destroyDrawingCache();
                                    imgView.setImageBitmap(null);
                                    imgView.setImageDrawable(null);
                                }
                                db.close();
                            } else
                                Toast.makeText(getApplicationContext(), "Por favor, ingrese la descripción", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", null);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(menuItem);
    }
}