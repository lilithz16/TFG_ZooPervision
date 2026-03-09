package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int id_usuario;

    public String usuario;
    public String password;
    public String rol;
    public int id_empleado;
    public boolean activo;
    public String fecha_creacion;

}