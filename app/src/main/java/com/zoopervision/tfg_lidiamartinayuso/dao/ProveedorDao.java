package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Proveedor;

import java.util.List;

@Dao
public interface ProveedorDao {

    @Insert
    void insertar(Proveedor proveedor);

    @Update
    void actualizar(Proveedor proveedor);

    @Delete
    void eliminar(Proveedor proveedor);

    @Query("SELECT * FROM proveedores")
    List<Proveedor> obtenerTodos();

    @Query("SELECT * FROM proveedores WHERE id_proveedor = :id")
    Proveedor obtenerPorId(int id);
}