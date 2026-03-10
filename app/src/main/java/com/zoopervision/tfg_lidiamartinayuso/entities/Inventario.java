package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventario")
public class Inventario {

    @PrimaryKey(autoGenerate = true)
    public int id_item;

    public String nombre;
    public String tipo; //Si es para animal o para tienda

    public int stock;
    public int stock_minimo; //lo uso para sacar alerta en rojo

    public String proveedor;
    public String ubicacion;

}