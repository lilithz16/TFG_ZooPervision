package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.DetallePedido;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;
import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class DetallePedidoActivity extends AppCompatActivity {

    TextView txtPedido;
    Button btnRecibirPedido, btnPdfPedido;
    int idPedido;
    Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        txtPedido = findViewById(R.id.txtPedido);

        idPedido = getIntent().getIntExtra("id", -1);

        cargarPedido(idPedido);

        //Ocultar el boton si el pedido ya está recibido
        if("recibido".equals(pedido.estado)){
            btnRecibirPedido.setVisibility(View.GONE);
        }

        btnRecibirPedido.setOnClickListener(v -> {

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .pedidoDao()
                    .actualizarEstado(idPedido, "recibido");

            actualizarInventario(idPedido);

            finish();
        });

        btnPdfPedido = findViewById(R.id.btnPdfPedido);

        btnPdfPedido.setOnClickListener(v -> {

            String texto = txtPedido.getText().toString();

            generarPDF(texto, "pedido_" + idPedido);

        });
    }

    private void cargarPedido(int idPedido) {

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

        for (DetallePedido d : detalles) {

            texto += d.producto + " x" + d.cantidad +
                    " = " + d.precio_unitario + "€\n";
        }

        txtPedido.setText(texto);
    }

    private void actualizarInventario(int idPedido) {

        List<DetallePedido> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .detallePedidoDao()
                .obtenerPorPedido(idPedido);

        for (DetallePedido d : lista) {

            Inventario item = DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .inventarioDao()
                    .obtenerPorNombre(d.producto);

            if (item != null) {

                item.stock += d.cantidad;

                DatabaseClient
                        .getInstance(this)
                        .getAppDatabase()
                        .inventarioDao()
                        .actualizar(item);

            }
        }
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