package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.AnimalAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.AnimalConRecinto;

import java.util.List;

public class ListaAnimalesRecintoActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales_recinto);

        recycler = findViewById(R.id.recyclerAnimalesRecinto);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        int idRecinto = getIntent().getIntExtra("id_recinto", -1);

        List<AnimalConRecinto> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .animalDao()
                .obtenerAnimalesConRecinto();

        AnimalAdapter adapter = new AnimalAdapter(lista, new AnimalAdapter.OnAnimalClickListener() {

            @Override
            public void onAnimalClick(AnimalConRecinto animal) {}

            @Override
            public void onAnimalLongClick(AnimalConRecinto animal) {}
        });

        recycler.setAdapter(adapter);
    }
}