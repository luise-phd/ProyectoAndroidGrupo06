package com.ingluise.ProyectoAndroidGrupo06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class RecyclerViewBasicActivity extends AppCompatActivity {
    private final int tam = 100000;
    private final int cols = 3;
    private final int pos = 99999;

    private CustomAdapter ca;

    private LinearLayoutManager llm;
    private GridLayoutManager glm;

    private RecyclerView rv;

    private String[] dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_basic);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.recyclerView);

        initDataset();

        ca = new CustomAdapter(dataset);
        rv.setAdapter(ca);

        setRecyclerViewLayoutManager();
    }

    private void initDataset() {
        dataset = new String[tam];
        for (int i=0; i < dataset.length; i++) {
            dataset[i] = "Este es el elemento # " + i;
        }
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = pos;

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        glm = new GridLayoutManager(this, cols);

//        rv.setLayoutManager(llm);
        rv.setLayoutManager(glm);
        rv.scrollToPosition(scrollPosition);
    }

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