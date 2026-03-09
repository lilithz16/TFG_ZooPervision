package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.InventarioAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;

import java.util.List;

public class StockBajoActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_bajo);

        recycler = findViewById(R.id.recyclerStockBajo);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<Inventario> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .inventarioDao()
                .obtenerStockBajo();

        InventarioAdapter adapter = new InventarioAdapter(lista, new InventarioAdapter.OnInventarioClickListener() {

            @Override
            public void onItemClick(Inventario item) {}

            @Override
            public void onItemLongClick(Inventario item) {}

        });

        recycler.setAdapter(adapter);
    }
}