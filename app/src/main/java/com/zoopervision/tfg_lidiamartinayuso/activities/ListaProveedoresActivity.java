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
import com.zoopervision.tfg_lidiamartinayuso.adapters.ProveedorAdapter;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Proveedor;

import java.util.List;

public class ListaProveedoresActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button btnAgregar;
    SearchView searchProveedores;

    ProveedorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proveedores);

        recycler = findViewById(R.id.recyclerProveedores);
        btnAgregar = findViewById(R.id.btnAgregarProveedor);
        searchProveedores = findViewById(R.id.searchProveedores);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarProveedores();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioProveedorActivity.class);
            startActivity(intent);

        });

        searchProveedores.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

    private void cargarProveedores(){

        List<Proveedor> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .proveedorDao()
                .obtenerTodos();

        adapter = new ProveedorAdapter(lista, new ProveedorAdapter.OnProveedorClickListener() {

            @Override
            public void onProveedorClick(Proveedor proveedor) {

                Intent intent = new Intent(ListaProveedoresActivity.this, FormularioProveedorActivity.class);
                intent.putExtra("id", proveedor.id_proveedor);
                startActivity(intent);
            }

            @Override
            public void onProveedorLongClick(Proveedor proveedor) {

                new AlertDialog.Builder(ListaProveedoresActivity.this)
                        .setTitle("Eliminar proveedor")
                        .setMessage("¿Quieres eliminar este proveedor?")
                        .setPositiveButton("Sí", (dialog, which) -> {

                            DatabaseClient
                                    .getInstance(ListaProveedoresActivity.this)
                                    .getAppDatabase()
                                    .proveedorDao()
                                    .eliminar(proveedor);

                            cargarProveedores();
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
        cargarProveedores();
    }
}