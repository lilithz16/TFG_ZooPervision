package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventario")
public class Inventario {

    @PrimaryKey(autoGenerate = true)
    public int id_item;

    public String nombre;
    public String tipo;

    public int stock;
    public int stock_minimo;

    public String proveedor;

}