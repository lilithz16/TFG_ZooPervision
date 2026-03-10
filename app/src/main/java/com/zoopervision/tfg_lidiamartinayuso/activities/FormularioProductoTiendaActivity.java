package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoTienda;

public class FormularioProductoTiendaActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion, etPrecio, etTipo;
    Button btnGuardar;

    int idProducto = -1;
    ProductoTienda productoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto_tienda);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etTipo = findViewById(R.id.etTipo);
        btnGuardar = findViewById(R.id.btnGuardar);

        idProducto = getIntent().getIntExtra("id", -1);

        if(idProducto != -1){
            cargarProducto();
        }

        btnGuardar.setOnClickListener(v -> guardarProducto());
    }

    private void cargarProducto(){

        productoActual = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .productoTiendaDao()
                .obtenerTodos()
                .stream()
                .filter(p -> p.id_producto_tienda == idProducto)
                .findFirst()
                .orElse(null);

        if(productoActual != null){

            etNombre.setText(productoActual.nombre);
            etDescripcion.setText(productoActual.descripcion);
            etPrecio.setText(String.valueOf(productoActual.precio));
            etTipo.setText(productoActual.tipo);

        }
    }

    private void guardarProducto(){

        if(idProducto == -1){

            ProductoTienda producto = new ProductoTienda();

            producto.nombre = etNombre.getText().toString();
            producto.descripcion = etDescripcion.getText().toString();
            producto.precio = Double.parseDouble(etPrecio.getText().toString());
            producto.tipo = etTipo.getText().toString();
            producto.activo = true;

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .productoTiendaDao()
                    .insertar(producto);

        }else{

            productoActual.nombre = etNombre.getText().toString();
            productoActual.descripcion = etDescripcion.getText().toString();
            productoActual.precio = Double.parseDouble(etPrecio.getText().toString());
            productoActual.tipo = etTipo.getText().toString();

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .productoTiendaDao()
                    .actualizar(productoActual);
        }

        finish();
    }
}