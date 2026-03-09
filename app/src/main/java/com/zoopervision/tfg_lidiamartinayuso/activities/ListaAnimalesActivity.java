package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.AnimalAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;
import com.zoopervision.tfg_lidiamartinayuso.entities.AnimalConRecinto;

import java.util.List;

public class ListaAnimalesActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animales);

        recycler = findViewById(R.id.recyclerAnimales);
        btnAgregar = findViewById(R.id.btnAgregarAnimal);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarAnimales();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioAnimalActivity.class);
            startActivity(intent);

        });
    }

    private void cargarAnimales() {

        List<AnimalConRecinto> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .animalDao()
                .obtenerAnimalesConRecinto();

        AnimalAdapter adapter = new AnimalAdapter(lista, new AnimalAdapter.OnAnimalClickListener() {

            @Override
            public void onAnimalClick(AnimalConRecinto animal) {

                Intent intent = new Intent(ListaAnimalesActivity.this, FormularioAnimalActivity.class);
                intent.putExtra("id", animal.id_animal);
                startActivity(intent);
            }

            @Override
            public void onAnimalLongClick(AnimalConRecinto animal) {

                new AlertDialog.Builder(ListaAnimalesActivity.this)
                        .setTitle("Eliminar animal")
                        .setMessage("¿Quieres eliminar este animal?")
                        .setPositiveButton("Sí", (dialog, which) -> {

                            Animal a = DatabaseClient
                                    .getInstance(ListaAnimalesActivity.this)
                                    .getAppDatabase()
                                    .animalDao()
                                    .obtenerPorId(animal.id_animal);

                            DatabaseClient
                                    .getInstance(ListaAnimalesActivity.this)
                                    .getAppDatabase()
                                    .animalDao()
                                    .eliminar(a);

                            cargarAnimales();
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarAnimales();
    }
}