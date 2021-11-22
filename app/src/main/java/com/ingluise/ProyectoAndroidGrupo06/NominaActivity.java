package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NominaActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private EditText et1;
    private Spinner sp1, sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomina);

        admin = new MyDBSQLiteHelper(this, vars.db, null, vars.ver);

        sp1 = findViewById(R.id.sp_meses);
        sp2 = findViewById(R.id.sp_empleado);
        et1 = findViewById(R.id.input_salario);

        db = admin.getReadableDatabase();
        fila = db.rawQuery("SELECT _id, (dni || ' - ' || nombre || ' ' || apellidos) AS nombre FROM empleado ORDER BY apellidos", null);
        MatrixCursor extra = new MatrixCursor(new String[]{"_id", "nombre"});
        extra.addRow(new String[]{"-1", "Seleccione..."});
        Cursor[] cursors = {extra, fila};
        Cursor cursorExtendido = new MergeCursor(cursors);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_dropdown_item,
                cursorExtendido, new String[]{"nombre"}, new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        sp2.setAdapter(adapter);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String emp = cursorExtendido.getString(1);
                Toast.makeText(getApplicationContext(), ""+emp, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        db.close();
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(), ""+sp1.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}