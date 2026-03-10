package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

//Seria el ticket de las compras que se realizaran en el zoo
public class DetalleVentaActivity extends AppCompatActivity {

    TextView txtTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        txtTicket = findViewById(R.id.txtTicket);

        int id = getIntent().getIntExtra("id", -1);

        Venta venta = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .ventaDao()
                .obtenerPorId(id);

        String ticket =
                "ZooPervision\n\n" +
                        "Venta #" + venta.id_venta + "\n" +
                        "Fecha: " + venta.fecha + "\n\n" +
                        "Total: " + venta.total + "€";

        txtTicket.setText(ticket);
    }
}
