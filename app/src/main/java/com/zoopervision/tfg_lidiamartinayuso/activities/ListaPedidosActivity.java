package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.PedidoAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.util.List;

public class ListaPedidosActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        recycler = findViewById(R.id.recyclerPedidos);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarPedidos();
    }

    private void cargarPedidos(){

        List<Pedido> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .pedidoDao()
                .obtenerTodos();

        PedidoAdapter adapter = new PedidoAdapter(lista, new PedidoAdapter.OnPedidoClickListener() {

            @Override
            public void onPedidoClick(Pedido pedido) {

                Intent intent = new Intent(ListaPedidosActivity.this, DetallePedidoActivity.class);

                intent.putExtra("id", pedido.id_pedido);

                startActivity(intent);
            }

            @Override
            public void onPedidoLongClick(Pedido pedido) {

                new AlertDialog.Builder(ListaPedidosActivity.this)
                        .setTitle("Eliminar pedido")
                        .setMessage("¿Deseas eliminar este pedido?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {

                            DatabaseClient
                                    .getInstance(ListaPedidosActivity.this)
                                    .getAppDatabase()
                                    .pedidoDao()
                                    .eliminar(pedido.id_pedido);

                            cargarPedidos();
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPedidos();
    }
}