package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;

public class MainActivity extends AppCompatActivity {

    Button btnAnimales, btnRecintos, btnInventario, btnStockBajo, btnProductos;
    Button btnVentas, btnProveedores, btnPedidos, btnEmpleados, btnDashboard;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comprobarStockBajo();

        btnAnimales = findViewById(R.id.btnAnimales);
        btnRecintos = findViewById(R.id.btnRecintos);
        btnInventario = findViewById(R.id.btnInventario);
        btnProductos = findViewById(R.id.btnProductos);
        btnVentas = findViewById(R.id.btnVentas);
        btnProveedores = findViewById(R.id.btnProveedores);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnEmpleados = findViewById(R.id.btnEmpleados);
        btnStockBajo = findViewById(R.id.btnStockBajo);
        btnDashboard = findViewById(R.id.btnDashboard);


        btnAnimales.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaAnimalesActivity.class));
        });

        btnRecintos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaRecintosActivity.class));
        });

        btnInventario.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaInventarioActivity.class));
        });

        btnStockBajo.setOnClickListener(v -> {
            startActivity(new Intent(this, StockBajoActivity.class));
        });

        btnProductos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaProductosTiendaActivity.class));
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
/*
        btnEmpleados.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaEmpleadosActivity.class));
        });
*/
        btnDashboard.setOnClickListener(v -> {

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);

        });
    }
    private void comprobarStockBajo(){

        int stockBajo = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .inventarioDao()
                .contarStockBajo();

        if(stockBajo > 0){

            btnStockBajo.setText("Stock Bajo (" + stockBajo + ")");
            btnStockBajo.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

        }
    }
}