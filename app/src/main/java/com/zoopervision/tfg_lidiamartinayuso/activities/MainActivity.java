package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;

public class MainActivity extends AppCompatActivity {

    Button btnAnimales, btnRecintos, btnInventario, btnProductos;
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


        btnAnimales.setOnClickListener(v -> {

            Intent intent = new Intent(this, ListaAnimalesActivity.class);
            startActivity(intent);

        });
    }
}