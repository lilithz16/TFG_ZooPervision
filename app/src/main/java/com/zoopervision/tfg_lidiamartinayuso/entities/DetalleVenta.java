package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "detalle_ventas")
public class DetalleVenta {

    @PrimaryKey(autoGenerate = true)
    public int id_detalle;

    public int id_venta;

    public String producto;

    public int cantidad;

    public double precio_unitario;

    public double subtotal;
}
