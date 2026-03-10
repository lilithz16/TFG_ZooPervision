package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.VentaAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.util.List;

public class ListaVentasActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnNuevaVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ventas);

        recycler = findViewById(R.id.recyclerVentas);
        btnNuevaVenta = findViewById(R.id.btnNuevaVenta);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarVentas();

        btnNuevaVenta.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrarVentaActivity.class);
            startActivity(intent);

        });
    }

    private void cargarVentas(){

        List<Venta> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .ventaDao()
                .obtenerTodas();

        VentaAdapter adapter =
                new VentaAdapter(lista, venta -> {

                    Intent intent = new Intent(
                            ListaVentasActivity.this,
                            DetalleVentaActivity.class
                    );

                    intent.putExtra("id", venta.id_venta);

                    startActivity(intent);
                });

        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarVentas();
    }
}