package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.util.List;

@Dao
public interface VentaDao {

    @Insert
    void insertar(Venta venta);

    @Query("SELECT * FROM ventas ORDER BY id_venta DESC")
    List<Venta> obtenerTodas();

    @Query("SELECT * FROM ventas WHERE id_venta = :id")
    Venta obtenerPorId(int id);

    @Query("DELETE FROM ventas WHERE id_venta = :id")
    void eliminar(int id);

    @Query("SELECT SUM(total) FROM ventas WHERE date(fecha) = date('now')")
    Double ventasHoy();
}