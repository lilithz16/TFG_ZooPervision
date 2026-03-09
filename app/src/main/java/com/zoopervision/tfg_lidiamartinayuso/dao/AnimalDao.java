package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;

import java.util.List;

@Dao
public interface AnimalDao {

    @Insert
    void insertar(Animal animal);

    @Update
    void actualizar(Animal animal);

    @Delete
    void eliminar(Animal animal);

    @Query("SELECT * FROM animales")
    List<Animal> obtenerTodos();

    @Query("SELECT * FROM animales WHERE id_animal = :id")
    Animal obtenerPorId(int id);
}