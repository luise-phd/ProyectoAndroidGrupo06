package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor fila;

    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        //Activar el supoorte para la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        admin = new MyDBSQLiteHelper(this, vars.db, null, vars.ver);

        Bundle extras = getIntent().getExtras();
        String nomTabla = extras.getString("nomTabla");

        setTitle(nomTabla);

        lv1 = findViewById(R.id.listView);
        ArrayList<String> listado = new ArrayList<String>();
//        listado.add("Elemento # 1");
//        listado.add("Elemento # 2");
//        for(int i=3; i<=50; i++) {
//            listado.add("Elemento # "+i);
//        }

        db = admin.getReadableDatabase();
        fila = db.rawQuery("SELECT * FROM empleado", null);
        while (fila.moveToNext()) {
            listado.add(fila.getString(0) + " - " + fila.getString(1) + "\n" +
                    fila.getString(fila.getColumnIndex("nombre")) + " " + fila.getString(3) + "\n" + fila.getString(4));
        }
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listado);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), "Posición: "+pos+"\n"+lv1.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }
        });
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
}