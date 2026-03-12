package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetallePedido;
import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarPedidoActivity extends AppCompatActivity {

    EditText etProducto, etCantidad, etPrecio;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        etProducto = findViewById(R.id.etProducto);
        etCantidad = findViewById(R.id.etCantidad);
        etPrecio = findViewById(R.id.etPrecio);

        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> registrarPedido());
    }

    private void registrarPedido(){

        String producto = etProducto.getText().toString();
        int cantidad = Integer.parseInt(etCantidad.getText().toString());
        double precio = Double.parseDouble(etPrecio.getText().toString());

        Pedido pedido = new Pedido();

        pedido.fecha_pedido = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        pedido.estado = "pendiente";
        pedido.id_proveedor = 1; // luego se podrá seleccionar proveedor

        long idPedido = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .pedidoDao()
                .insertar(pedido);

        DetallePedido detalle = new DetallePedido();

        detalle.id_pedido = (int) idPedido;
        detalle.producto = producto;
        detalle.cantidad = cantidad;
        detalle.precio_unitario = precio;

        DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .detallePedidoDao()
                .insertar(detalle);

        finish();
    }
}