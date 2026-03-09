package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;

public class FormularioRecintoActivity extends AppCompatActivity {

    EditText etNombre, etTipo, etCapacidad;
    Button btnGuardar;

    int idRecinto = -1;
    Recinto recintoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_recinto);

        etNombre = findViewById(R.id.etNombreRecinto);
        etTipo = findViewById(R.id.etTipoRecinto);
        etCapacidad = findViewById(R.id.etCapacidad);
        btnGuardar = findViewById(R.id.btnGuardarRecinto);

        idRecinto = getIntent().getIntExtra("id", -1);

        if(idRecinto != -1){
            cargarRecinto();
        }

        btnGuardar.setOnClickListener(v -> guardarRecinto());
    }

    private void cargarRecinto(){

        recintoActual = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .recintoDao()
                .obtenerPorId(idRecinto);

        etNombre.setText(recintoActual.nombre);
        etTipo.setText(recintoActual.tipo);
        etCapacidad.setText(String.valueOf(recintoActual.capacidad));
    }

    private void guardarRecinto(){

        if(idRecinto == -1){

            Recinto recinto = new Recinto();

            recinto.nombre = etNombre.getText().toString();
            recinto.tipo = etTipo.getText().toString();
            recinto.capacidad = Integer.parseInt(etCapacidad.getText().toString());

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .recintoDao()
                    .insertar(recinto);

        }else{

            recintoActual.nombre = etNombre.getText().toString();
            recintoActual.tipo = etTipo.getText().toString();
            recintoActual.capacidad = Integer.parseInt(etCapacidad.getText().toString());

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .recintoDao()
                    .actualizar(recintoActual);
        }

        finish();
    }
}