package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recintos")
public class Recinto {

    @PrimaryKey(autoGenerate = true)
    public int id_recinto;

    public String nombre;
    public String tipo;
    public int capacidad;
}