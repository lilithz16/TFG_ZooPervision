package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetallePedido;
import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.util.List;

public class DetallePedidoActivity extends AppCompatActivity {

    TextView txtPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        txtPedido = findViewById(R.id.txtPedido);

        int idPedido = getIntent().getIntExtra("id", -1);

        cargarPedido(idPedido);
    }

    private void cargarPedido(int idPedido){

        Pedido pedido = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .pedidoDao()
                .obtenerPorId(idPedido);

        List<DetallePedido> detalles = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .detallePedidoDao()
                .obtenerPorPedido(idPedido);

        String texto = "ZooPervision\n\n";

        texto += "Pedido #" + pedido.id_pedido + "\n";
        texto += "Fecha: " + pedido.fecha_pedido + "\n\n";

        for(DetallePedido d : detalles){

            texto += d.producto + " x" + d.cantidad +
                    " = " + d.precio_unitario + "€\n";
        }

        txtPedido.setText(texto);
    }
}