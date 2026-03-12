package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;

import java.util.List;

@Dao
public interface InventarioDao {

    @Insert
    void insertar(Inventario item);

    @Update
    void actualizar(Inventario item);

    @Delete
    void eliminar(Inventario item);

    @Query("SELECT * FROM inventario")
    List<Inventario> obtenerTodos();

    @Query("SELECT * FROM inventario WHERE id_item = :id")
    Inventario obtenerPorId(int id);
    @Query("SELECT * FROM inventario WHERE nombre = :nombre LIMIT 1")
    Inventario obtenerPorNombre(String nombre);

    @Query("SELECT * FROM inventario WHERE stock <= stock_minimo")
    List<Inventario> obtenerStockBajo();

    //para el dashboard
    @Query("SELECT COUNT(*) FROM inventario WHERE stock <= stock_minimo")
    int contarStockBajo();

}