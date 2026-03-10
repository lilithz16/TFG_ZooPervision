package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;

public class DashboardActivity extends AppCompatActivity {

    TextView txtAnimales;
    TextView txtRecintos;
    TextView txtProductosAnimales;
    TextView txtProductosTienda;
    TextView txtStockBajo;
    TextView txtVentasHoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtAnimales = findViewById(R.id.txtAnimales);
        txtRecintos = findViewById(R.id.txtRecintos);
        txtProductosAnimales = findViewById(R.id.txtProductosAnimales);
        txtProductosTienda = findViewById(R.id.txtProductosTienda);
        txtStockBajo = findViewById(R.id.txtStockBajo);
        txtVentasHoy = findViewById(R.id.txtVentasHoy);

        cargarEstadisticas();
    }

    private void cargarEstadisticas(){

        var db = DatabaseClient
                .getInstance(this)
                .getAppDatabase();

        int animales = db.animalDao().contarAnimales();
        int recintos = db.recintoDao().contarRecintos();
        int productosAnimales = db.productoAnimalDao().contarProductosAnimales();
        int productosTienda = db.productoTiendaDao().contarProductosTienda();
        int stockBajo = db.inventarioDao().contarStockBajo();

        Double ventasHoy = db.ventaDao().ventasHoy();

        if(ventasHoy == null){
            ventasHoy = 0.0;
        }

        txtAnimales.setText("Animales: " + animales);
        txtRecintos.setText("Recintos: " + recintos);
        txtProductosAnimales.setText("Productos animales: " + productosAnimales);
        txtProductosTienda.setText("Productos tienda: " + productosTienda);
        txtStockBajo.setText("Stock bajo: " + stockBajo);
        txtVentasHoy.setText("Ventas hoy: " + ventasHoy + "€");

    }
}