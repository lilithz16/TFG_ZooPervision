package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ventas")
public class Venta {

    @PrimaryKey(autoGenerate = true)
    public int id_venta;

    public String fecha;

    public double total;

    public int id_empleado;
}