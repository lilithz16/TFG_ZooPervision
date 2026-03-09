package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Empleado;

import java.util.List;

@Dao
public interface EmpleadoDao {

    @Insert
    void insertar(Empleado empleado);

    @Update
    void actualizar(Empleado empleado);

    @Delete
    void eliminar(Empleado empleado);

    @Query("SELECT * FROM empleados")
    List<Empleado> obtenerTodos();
}