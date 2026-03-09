package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;

public class FormularioAnimalActivity extends AppCompatActivity {

    EditText etNombre, etTipo, etEdad;
    Button btnGuardar;

    int idAnimal = -1;
    Animal animalActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_animal);

        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        etEdad = findViewById(R.id.etEdad);
        btnGuardar = findViewById(R.id.btnGuardar);

        idAnimal = getIntent().getIntExtra("id", -1);

        if(idAnimal != -1){
            cargarAnimal();
        }

        btnGuardar.setOnClickListener(v -> guardarAnimal());
    }

    private void cargarAnimal(){

        animalActual = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .animalDao()
                .obtenerPorId(idAnimal);

        etNombre.setText(animalActual.nombre);
        etTipo.setText(animalActual.tipo);
        etEdad.setText(String.valueOf(animalActual.edad));
    }

    private void guardarAnimal(){

        if(idAnimal == -1){

            Animal animal = new Animal();

            animal.nombre = etNombre.getText().toString();
            animal.tipo = etTipo.getText().toString();
            animal.edad = Integer.parseInt(etEdad.getText().toString());

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .animalDao()
                    .insertar(animal);

        }else{

            animalActual.nombre = etNombre.getText().toString();
            animalActual.tipo = etTipo.getText().toString();
            animalActual.edad = Integer.parseInt(etEdad.getText().toString());

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .animalDao()
                    .actualizar(animalActual);
        }

        finish();
    }
}