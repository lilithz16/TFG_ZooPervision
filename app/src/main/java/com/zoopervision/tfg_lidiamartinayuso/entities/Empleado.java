package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "empleados")
public class Empleado {

    @PrimaryKey(autoGenerate = true)
    public int id_empleado;

    public String nombre;
    public String apellidos;
    public String dni;
    public String telefono;
    public String email;
    public String direccion;
    public String cargo;
    public String fecha_alta;
    public double salario;
    public String observaciones;
    public String foto;
    public boolean activo;

}