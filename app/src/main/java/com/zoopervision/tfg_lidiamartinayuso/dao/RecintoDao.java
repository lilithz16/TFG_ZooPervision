package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;

import java.util.List;

@Dao
public interface RecintoDao {

    @Insert
    void insertar(Recinto recinto);

    @Update
    void actualizar(Recinto recinto);

    @Delete
    void eliminar(Recinto recinto);

    @Query("SELECT * FROM recintos")
    List<Recinto> obtenerTodos();

    @Query("SELECT * FROM recintos WHERE id_recinto = :id")
    Recinto obtenerPorId(int id);
}