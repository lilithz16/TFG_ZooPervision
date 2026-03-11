package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetalleVenta;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.util.List;

public class DetalleVentaActivity extends AppCompatActivity {

    TextView txtTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        txtTicket = findViewById(R.id.txtTicket);

        int idVenta = getIntent().getIntExtra("id", -1);

        cargarTicket(idVenta);
    }

    private void cargarTicket(int idVenta){

        Venta venta = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .ventaDao()
                .obtenerPorId(idVenta);

        List<DetalleVenta> detalles = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .detalleVentaDao()
                .obtenerPorVenta(idVenta);

        String ticket = "ZooPervision\n\n";

        ticket += "Venta #" + venta.id_venta + "\n";
        ticket += "Fecha: " + venta.fecha + "\n\n";

        for(DetalleVenta d : detalles){

            ticket += d.producto + " x" + d.cantidad +
                    " = " + d.subtotal + "€\n";
        }

        ticket += "\nTOTAL: " + venta.total + "€";

        txtTicket.setText(ticket);
    }
}