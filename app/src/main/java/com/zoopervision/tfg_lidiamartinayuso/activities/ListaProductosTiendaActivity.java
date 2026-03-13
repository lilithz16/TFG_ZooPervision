package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.adapters.ProductoTiendaAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoTienda;

import java.util.List;

public class ListaProductosTiendaActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnAgregar;
    SearchView searchProductos;

    ProductoTiendaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos_tienda);

        recycler = findViewById(R.id.recyclerProductos);
        btnAgregar = findViewById(R.id.btnAgregarProducto);
        searchProductos = findViewById(R.id.searchProductos);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarProductos();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioProductoTiendaActivity.class);
            startActivity(intent);

        });

        searchProductos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

    private void cargarProductos(){

        List<ProductoTienda> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .productoTiendaDao()
                .obtenerTodos();

        adapter = new ProductoTiendaAdapter(lista, new ProductoTiendaAdapter.OnProductoClickListener() {

            @Override
            public void onProductoClick(ProductoTienda producto) {

                Intent intent = new Intent(ListaProductosTiendaActivity.this, FormularioProductoTiendaActivity.class);
                intent.putExtra("id", producto.id_producto_tienda);
                startActivity(intent);
            }

            @Override
            public void onProductoLongClick(ProductoTienda producto) {

                new AlertDialog.Builder(ListaProductosTiendaActivity.this)
                        .setTitle("Eliminar producto")
                        .setMessage("¿Quieres eliminar este producto?")
                        .setPositiveButton("Sí", (dialog, which) -> {

                            DatabaseClient
                                    .getInstance(ListaProductosTiendaActivity.this)
                                    .getAppDatabase()
                                    .productoTiendaDao()
                                    .eliminar(producto);

                            cargarProductos();
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
        cargarProductos();
    }
}