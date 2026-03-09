package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;

public class MainActivity extends AppCompatActivity {

    Button btnAnimales, btnRecintos, btnInventario, btnStockBajo, btnProductos;
    Button btnVentas, btnProveedores, btnPedidos, btnEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnimales = findViewById(R.id.btnAnimales);
        btnRecintos = findViewById(R.id.btnRecintos);
        btnInventario = findViewById(R.id.btnInventario);
        btnProductos = findViewById(R.id.btnProductos);
        btnVentas = findViewById(R.id.btnVentas);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnEmpleados = findViewById(R.id.btnEmpleados);
        btnStockBajo = findViewById(R.id.btnStockBajo);


        btnAnimales.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaAnimalesActivity.class));
        });

        btnRecintos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaRecintosActivity.class));
        });
/*
        btnInventario.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaInventarioActivity.class));
        });

        btnStockBajo.setOnClickListener(v -> {
            startActivity(new Intent(this, StockBajoActivity.class));
        });

        btnProductos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaProductosActivity.class));
        });

        btnVentas.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaVentasActivity.class));
        });

        btnProveedores.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaProveedoresActivity.class));
        });

        btnPedidos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaPedidosActivity.class));
        });

        btnEmpleados.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaEmpleadosActivity.class));
        });
*/
    }
}