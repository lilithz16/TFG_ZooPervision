package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.VentaAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.util.List;

public class ListaVentasActivity extends AppCompatActivity {

    Button btnNuevaVenta;
    RecyclerView recycler;
    SearchView searchVentas;
    VentaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ventas);

        btnNuevaVenta = findViewById(R.id.btnNuevaVenta);
        searchVentas = findViewById(R.id.searchVentas);
        recycler = findViewById(R.id.recyclerVentas);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        btnNuevaVenta.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrarVentaActivity.class);
            startActivity(intent);

        });

        cargarVentas();

        //buscador
        searchVentas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(adapter != null){
                    adapter.filtrar(newText);
                }

                return true;
            }
        });
    }

    private void cargarVentas(){

        List<Venta> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .ventaDao()
                .obtenerTodas();

        adapter = new VentaAdapter(lista, new VentaAdapter.OnVentaClickListener() {

            @Override
            public void onVentaClick(Venta venta) {

                Intent intent = new Intent(ListaVentasActivity.this, DetalleVentaActivity.class);
                intent.putExtra("id", venta.id_venta);
                startActivity(intent);
            }

            @Override
            public void onVentaLongClick(Venta venta) {

                new AlertDialog.Builder(ListaVentasActivity.this)
                        .setTitle("Eliminar venta")
                        .setMessage("¿Deseas eliminar esta venta?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {

                            DatabaseClient
                                    .getInstance(ListaVentasActivity.this)
                                    .getAppDatabase()
                                    .ventaDao()
                                    .eliminar(venta.id_venta);

                            cargarVentas();
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
        cargarVentas();
    }
}