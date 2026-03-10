package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;

public class FormularioInventarioActivity extends AppCompatActivity {

    EditText etNombre, etTipo, etStock, etStockMinimo, etProveedor;
    Button btnGuardar;

    int idItem = -1;
    Inventario itemActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_inventario);

        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        etStock = findViewById(R.id.etStock);
        etStockMinimo = findViewById(R.id.etStockMinimo);
        etProveedor = findViewById(R.id.etProveedor);
        btnGuardar = findViewById(R.id.btnGuardar);

        idItem = getIntent().getIntExtra("id", -1);

        if(idItem != -1){
            cargarItem();
        }

        btnGuardar.setOnClickListener(v -> guardarItem());
    }

    private void cargarItem(){

        itemActual = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .inventarioDao()
                .obtenerPorId(idItem);

        etNombre.setText(itemActual.nombre);
        etTipo.setText(itemActual.tipo);
        etStock.setText(String.valueOf(itemActual.stock));
        etStockMinimo.setText(String.valueOf(itemActual.stock_minimo));
        etProveedor.setText(itemActual.proveedor);
    }

    private void guardarItem(){

        if(idItem == -1){

            Inventario item = new Inventario();

            item.nombre = etNombre.getText().toString();
            item.tipo = etTipo.getText().toString();
            item.stock = Integer.parseInt(etStock.getText().toString());
            item.stock_minimo = Integer.parseInt(etStockMinimo.getText().toString());
            item.proveedor = etProveedor.getText().toString();

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .inventarioDao()
                    .insertar(item);

        }else{

            itemActual.nombre = etNombre.getText().toString();
            itemActual.tipo = etTipo.getText().toString();
            itemActual.stock = Integer.parseInt(etStock.getText().toString());
            itemActual.stock_minimo = Integer.parseInt(etStockMinimo.getText().toString());
            itemActual.proveedor = etProveedor.getText().toString();

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .inventarioDao()
                    .actualizar(itemActual);
        }

        finish();
    }
}