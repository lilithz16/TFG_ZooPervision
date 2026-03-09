package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.RecintoAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;

import java.util.List;

public class ListaRecintosActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recintos);

        recycler = findViewById(R.id.recyclerRecintos);
        btnAgregar = findViewById(R.id.btnAgregarRecinto);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarRecintos();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioRecintoActivity.class);
            startActivity(intent);

        });
    }

    private void cargarRecintos() {

        List<Recinto> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .recintoDao()
                .obtenerTodos();

        RecintoAdapter adapter = new RecintoAdapter(lista, new RecintoAdapter.OnRecintoClickListener() {

            @Override
            public void onRecintoClick(Recinto recinto) {

                Intent intent = new Intent(ListaRecintosActivity.this, FormularioRecintoActivity.class);
                intent.putExtra("id", recinto.id_recinto);
                startActivity(intent);
            }

            @Override
            public void onRecintoLongClick(Recinto recinto) {

                new AlertDialog.Builder(ListaRecintosActivity.this)
                        .setTitle("Eliminar recinto")
                        .setMessage("¿Quieres eliminar este recinto?")
                        .setPositiveButton("Sí", (dialog, which) -> {

                            DatabaseClient
                                    .getInstance(ListaRecintosActivity.this)
                                    .getAppDatabase()
                                    .recintoDao()
                                    .eliminar(recinto);

                            cargarRecintos();
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
        cargarRecintos();
    }
}