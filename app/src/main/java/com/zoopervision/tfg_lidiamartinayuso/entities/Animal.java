package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animales")
public class Animal {

    @PrimaryKey(autoGenerate = true)
    public int id_animal;

    public String nombre;
    public String tipo;
    public int edad;
    public String sexo;
    public double peso;
    public String estado_salud;
    public int id_recinto;
    public int id_empleado_cuidador;
}