package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proveedores);

        recycler = findViewById(R.id.recyclerProveedores);
        btnAgregar = findViewById(R.id.btnAgregarProveedor);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarProveedores();

        btnAgregar.setOnClickListener(v -> {

            Intent intent = new Intent(this, FormularioProveedorActivity.class);
            startActivity(intent);

        });
    }

    private void cargarProveedores(){

        List<Proveedor> lista = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .proveedorDao()
                .obtenerTodos();

        ProveedorAdapter adapter =
                new ProveedorAdapter(lista, new ProveedorAdapter.OnProveedorClickListener() {

                    @Override
                    public void onProveedorClick(Proveedor proveedor) {

                        Intent intent = new Intent(
                                ListaProveedoresActivity.this,
                                FormularioProveedorActivity.class
                        );

                        intent.putExtra("id", proveedor.id_proveedor);
                        startActivity(intent);
                    }

                    @Override
                    public void onProveedorLongClick(Proveedor proveedor) {

                        DatabaseClient
                                .getInstance(ListaProveedoresActivity.this)
                                .getAppDatabase()
                                .proveedorDao()
                                .eliminar(proveedor);

                        cargarProveedores();
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