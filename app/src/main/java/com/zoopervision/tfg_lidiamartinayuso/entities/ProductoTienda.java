package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos_tienda")
public class ProductoTienda {

    @PrimaryKey(autoGenerate = true)
    public int id_producto_tienda;

    public String nombre;
    public String descripcion;
    public double precio;
    public String tipo; //entrada o souvenir
    public boolean activo;
}