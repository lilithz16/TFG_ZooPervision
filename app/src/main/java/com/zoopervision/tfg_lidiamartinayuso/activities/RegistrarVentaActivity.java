package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetalleVenta;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarVentaActivity extends AppCompatActivity {

    EditText etProducto, etCantidad, etPrecio;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_venta);

        etProducto = findViewById(R.id.etProducto);
        etCantidad = findViewById(R.id.etCantidad);
        etPrecio = findViewById(R.id.etPrecio);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> registrarVenta());
    }

    private void registrarVenta(){

        String producto = etProducto.getText().toString();
        int cantidad = Integer.parseInt(etCantidad.getText().toString());
        double precio = Double.parseDouble(etPrecio.getText().toString());

        double total = cantidad * precio;

        // Crear venta
        Venta venta = new Venta();

        venta.fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        venta.total = total;
        venta.id_empleado = 1; // luego lo cambiaremos por el usuario logueado

        long idVenta = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .ventaDao()
                .insertar(venta);

        // Crear detalle
        DetalleVenta detalle = new DetalleVenta();

        detalle.id_venta = (int) idVenta;
        detalle.producto = producto;
        detalle.cantidad = cantidad;
        detalle.precio_unitario = precio;
        detalle.subtotal = total;

        DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .detalleVentaDao()
                .insertar(detalle);

        finish();
    }
}