package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

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

    Button btnNuevoPedido;
    RecyclerView recycler;
    SearchView searchPedidos;
    PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        btnNuevoPedido = findViewById(R.id.btnNuevoPedido);
        searchPedidos = findViewById(R.id.searchPedidos);

        btnNuevoPedido.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrarPedidoActivity.class);
            startActivity(intent);

        });

        recycler = findViewById(R.id.recyclerPedidos);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarPedidos();

        //buscador
        searchPedidos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                if(adapter != null){
                    adapter.filtrar(newText);
                }
                return true;
            }
        });
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