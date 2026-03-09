package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;

import java.util.ArrayList;
import java.util.List;

public class FormularioAnimalActivity extends AppCompatActivity {

    EditText etNombre, etTipo, etEdad;
    Spinner spinnerRecintos;
    Button btnGuardar;

    int idAnimal = -1;
    Animal animalActual;

    List<Recinto> listaRecintos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_animal);

        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        etEdad = findViewById(R.id.etEdad);
        spinnerRecintos = findViewById(R.id.spinnerRecintos);
        btnGuardar = findViewById(R.id.btnGuardar);

        cargarRecintos();

        idAnimal = getIntent().getIntExtra("id", -1);

        if(idAnimal != -1){
            cargarAnimal();
        }

        btnGuardar.setOnClickListener(v -> guardarAnimal());
    }

    private void cargarRecintos(){

        listaRecintos = DatabaseClient
                .getInstance(this)
                .getAppDatabase()
                .recintoDao()
                .obtenerTodos();

        List<String> nombres = new ArrayList<>();

        for(Recinto r : listaRecintos){
            nombres.add(r.nombre);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRecintos.setAdapter(adapter);
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

        int posicion = spinnerRecintos.getSelectedItemPosition();
        int idRecinto = listaRecintos.get(posicion).id_recinto;

        if(idAnimal == -1){

            Animal animal = new Animal();

            animal.nombre = etNombre.getText().toString();
            animal.tipo = etTipo.getText().toString();
            animal.edad = Integer.parseInt(etEdad.getText().toString());
            animal.id_recinto = idRecinto;

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .animalDao()
                    .insertar(animal);

        }else{

            animalActual.nombre = etNombre.getText().toString();
            animalActual.tipo = etTipo.getText().toString();
            animalActual.edad = Integer.parseInt(etEdad.getText().toString());
            animalActual.id_recinto = idRecinto;

            DatabaseClient
                    .getInstance(this)
                    .getAppDatabase()
                    .animalDao()
                    .actualizar(animalActual);
        }

        finish();
    }
}