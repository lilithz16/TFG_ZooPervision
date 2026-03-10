package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.InventarioAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;

import java.util.List;

public class ListaInventarioActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inventario);

        recycler = findViewById(R.id.recyclerInventario);
        btnAgregar = findViewById(R.id.btnAgregarInventario);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarInventario();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioInventarioActivity.class);
            startActivity(intent);

        });
    }

    private void cargarInventario(){

        List<Inventario> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .inventarioDao()
                .obtenerTodos();

        InventarioAdapter adapter =
                new InventarioAdapter(lista, new InventarioAdapter.OnInventarioClickListener() {

                    @Override
                    public void onItemClick(Inventario item) {

                        Intent intent = new Intent(ListaInventarioActivity.this, FormularioInventarioActivity.class);
                        intent.putExtra("id", item.id_item);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(Inventario item) {

                        DatabaseClient
                                .getInstance(ListaInventarioActivity.this)
                                .getAppDatabase()
                                .inventarioDao()
                                .eliminar(item);

                        cargarInventario();
                    }
                });

        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarInventario();
    }
}