package com.ingluise.ProyectoAndroidGrupo06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpleadoFirebaseActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private EditText et1, et2, et3, et4;

    String emps = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_firebase);

        et1 = findViewById(R.id.txt_dni);
        et2 = findViewById(R.id.txt_nombre);
        et3 = findViewById(R.id.txt_apellidos);
        et4 = findViewById(R.id.txt_salario);
    }

    @Override
    //Carga el menu en la actividad
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    public void testCrash(View view) {
        throw new RuntimeException("Test Crash"); // Force a crash
    }

    //Verfifica la opcion de menu seleccionada
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_agregar) {
            String dni = et1.getText().toString();
            String nom = et2.getText().toString();
            String ape = et3.getText().toString();
            String sal = et4.getText().toString();
            if (!dni.equals("") && !nom.equals("") && !ape.equals("") && !sal.equals("")) {
//                Empleado emp = new Empleado(dni, nom, ape, Integer.parseInt(sal));
                Empleado emp = new Empleado();
                emp.setDni(dni);
                emp.setNombre(nom);
                emp.setApellidos(ape);
                emp.setSalario(Integer.parseInt(sal));
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference().child("empleado").push();
                myRef.setValue(emp);
                et1.setText("");
                et1.requestFocus();
                et2.setText("");
                et3.setText("");
                et4.setText("");
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_listar) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("empleado").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String dni = "", nom = "", ape = "";
                    int sal = 0;
                    emps = "";
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Empleado emp = dataSnapshot.getValue(Empleado.class);
                        dni = emp.getDni();
                        nom = emp.getNombre();
                        ape = emp.getApellidos();
                        sal = emp.getSalario();
                        emps += dni + " - " + nom + " " + ape + " - " + sal + "\n";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Empleados")
                    .setMessage(emps)
                    .setPositiveButton("Aceptar", null).show();
        }
        else if(id == R.id.mnu_eliminar) {
            String dni = et1.getText().toString();
            if (!dni.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("empleado").orderByChild("dni").equalTo(dni).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                dataSnapshot.getRef().removeValue();
                            }
                            Toast.makeText(getApplicationContext(), "Empleado eliminado", Toast.LENGTH_SHORT).show();
                            et1.requestFocus();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "El empleado no existe", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else
                Toast.makeText(this, "Por favor, ingrese el DNI", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_buscar) {
            String dni = et1.getText().toString();
            if (!dni.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("empleado").orderByChild("dni").equalTo(dni).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Empleado emp = dataSnapshot.getValue(Empleado.class);
                                et2.setText(emp.getNombre());
                                et1.requestFocus();
                                et3.setText(emp.getApellidos());
                                et4.setText(""+emp.getSalario());
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "El empleado no existe", Toast.LENGTH_SHORT).show();
                            et2.setText("");
                            et3.setText("");
                            et4.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else
                Toast.makeText(this, "Por favor, ingrese el DNI", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_editar) {
            String dni = et1.getText().toString();
            String nom = et2.getText().toString();
            String ape = et3.getText().toString();
            String sal = et4.getText().toString();
            if (!dni.equals("") && !nom.equals("") && !ape.equals("") && !sal.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("empleado").orderByChild("dni").equalTo(dni).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String key = "";
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            key = dataSnapshot.getKey();
                        }
                        myRef.child("empleado").child(key).child("dni").setValue(dni);
                        myRef.child("empleado").child(key).child("nombre").setValue(nom);
                        myRef.child("empleado").child(key).child("apellidos").setValue(ape);
                        myRef.child("empleado").child(key).child("salario").setValue(Integer.parseInt(sal));
                        et1.setText("");
                        et1.requestFocus();
                        et2.setText("");
                        et3.setText("");
                        et4.setText("");
                        Toast.makeText(getApplicationContext(), "Registro editado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_limpiar) {
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }

        return super.onOptionsItemSelected(menuItem);
    }
}