package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "proveedores")
public class Proveedor {

    @PrimaryKey(autoGenerate = true)
    public int id_proveedor;

    public String nombre_empresa;

    public String contacto;

    public String telefono;

    public String email;

    public String direccion;

    public String observaciones;
}