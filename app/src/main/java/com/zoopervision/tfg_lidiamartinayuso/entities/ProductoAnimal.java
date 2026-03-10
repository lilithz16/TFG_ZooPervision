package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos_animales")
public class ProductoAnimal {

    @PrimaryKey(autoGenerate = true)
    public int id_producto_animal;

    public String nombre;
    public String descripcion;
    public String tipo;
    public String proveedor;
    public boolean activo;
}