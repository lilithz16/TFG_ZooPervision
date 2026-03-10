package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoTienda;

import java.util.List;

@Dao
public interface ProductoTiendaDao {

    @Insert
    void insertar(ProductoTienda producto);

    @Update
    void actualizar(ProductoTienda producto);

    @Delete
    void eliminar(ProductoTienda producto);

    @Query("SELECT * FROM productos_tienda")
    List<ProductoTienda> obtenerTodos();

    //para el dashboard
    @Query("SELECT COUNT(*) FROM productos_tienda")
    int contarProductosTienda();
}