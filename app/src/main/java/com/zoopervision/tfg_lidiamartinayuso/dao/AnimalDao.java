package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;
import com.zoopervision.tfg_lidiamartinayuso.entities.AnimalConRecinto;

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

    @Query("SELECT animales.id_animal, animales.nombre, animales.tipo, animales.edad, recintos.nombre AS nombreRecinto " +
            "FROM animales " +
            "INNER JOIN recintos ON animales.id_recinto = recintos.id_recinto")
    List<AnimalConRecinto> obtenerAnimalesConRecinto();

    @Query("SELECT * FROM animales WHERE id_recinto = :idRecinto")
    List<Animal> obtenerAnimalesPorRecinto(int idRecinto);

    //para el dashboard
    @Query("SELECT COUNT(*) FROM animales")
    int contarAnimales();
}