package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoAnimal;

import java.util.List;

@Dao
public interface ProductoAnimalDao {

    @Insert
    void insertar(ProductoAnimal producto);

    @Update
    void actualizar(ProductoAnimal producto);

    @Delete
    void eliminar(ProductoAnimal producto);

    @Query("SELECT * FROM productos_animales")
    List<ProductoAnimal> obtenerTodos();

    //para el dashboard
    @Query("SELECT COUNT(*) FROM productos_animales")
    int contarProductosAnimales();
}