package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Proveedor;

public class FormularioProveedorActivity extends AppCompatActivity {

    EditText etEmpresa, etContacto, etTelefono, etEmail, etDireccion, etObservaciones;
    Button btnGuardar;

    int idProveedor = -1;
    Proveedor proveedorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_proveedor);

        etEmpresa = findViewById(R.id.etEmpresa);
        etContacto = findViewById(R.id.etContacto);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etObservaciones = findViewById(R.id.etObservaciones);
        btnGuardar = findViewById(R.id.btnGuardarProveedor);

        idProveedor = getIntent().getIntExtra("id", -1);

        if(idProveedor != -1){
            cargarProveedor();
        }

        btnGuardar.setOnClickListener(v -> guardarProveedor());
    }

    private void cargarProveedor(){

        proveedorActual = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .proveedorDao()
                .obtenerPorId(idProveedor);

        etEmpresa.setText(proveedorActual.nombre_empresa);
        etContacto.setText(proveedorActual.contacto);
        etTelefono.setText(proveedorActual.telefono);
        etEmail.setText(proveedorActual.email);
        etDireccion.setText(proveedorActual.direccion);
        etObservaciones.setText(proveedorActual.observaciones);
    }

    private void guardarProveedor(){

        if(idProveedor == -1){

            Proveedor proveedor = new Proveedor();

            proveedor.nombre_empresa = etEmpresa.getText().toString();
            proveedor.contacto = etContacto.getText().toString();
            proveedor.telefono = etTelefono.getText().toString();
            proveedor.email = etEmail.getText().toString();
            proveedor.direccion = etDireccion.getText().toString();
            proveedor.observaciones = etObservaciones.getText().toString();

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .proveedorDao()
                    .insertar(proveedor);

        }else{

            proveedorActual.nombre_empresa = etEmpresa.getText().toString();
            proveedorActual.contacto = etContacto.getText().toString();
            proveedorActual.telefono = etTelefono.getText().toString();
            proveedorActual.email = etEmail.getText().toString();
            proveedorActual.direccion = etDireccion.getText().toString();
            proveedorActual.observaciones = etObservaciones.getText().toString();

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .proveedorDao()
                    .actualizar(proveedorActual);
        }

        finish();
    }
}