package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ventas")
public class Venta {

    @PrimaryKey(autoGenerate = true)
    public int id_venta;

    public String producto;
    public int cantidad;
    public double precio_unitario;
    public double total;
    public String fecha;
    public int id_empleado;
}