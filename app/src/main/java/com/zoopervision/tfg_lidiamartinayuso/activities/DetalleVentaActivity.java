package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetalleVenta;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class DetalleVentaActivity extends AppCompatActivity {

    TextView txtTicket;
    Button btnPdfVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        txtTicket = findViewById(R.id.txtTicket);

        int idVenta = getIntent().getIntExtra("id", -1);

        cargarTicket(idVenta);

        btnPdfVenta = findViewById(R.id.btnPdfVenta);

        btnPdfVenta.setOnClickListener(v -> {

            String texto = txtTicket.getText().toString();

            generarPDF(texto, "ticket_venta_" + idVenta);

        });
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

    private void generarPDF(String texto, String nombreArchivo){

        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(300,600,1).create();

        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setTextSize(12);

        int x = 10;
        int y = 25;

        for(String linea : texto.split("\n")){
            canvas.drawText(linea, x, y, paint);
            y += 20;
        }

        document.finishPage(page);

        try{

            File carpeta = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS
                    ),
                    "ZooPervision"
            );

            if(!carpeta.exists()){
                carpeta.mkdirs();
            }

            File archivo = new File(carpeta, nombreArchivo + ".pdf");

            document.writeTo(new FileOutputStream(archivo));

        }catch(Exception e){
            e.printStackTrace();
        }

        document.close();
    }
}