package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.zoopervision.tfg_lidiamartinayuso.entities.DetalleVenta;

import java.util.List;

@Dao
public interface DetalleVentaDao {

    @Insert
    void insertar(DetalleVenta detalle);

    @Query("SELECT * FROM detalle_ventas WHERE id_venta = :idVenta")
    List<DetalleVenta> obtenerPorVenta(int idVenta);

}