package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.zoopervision.tfg_lidiamartinayuso.entities.DetallePedido;

import java.util.List;

@Dao
public interface DetallePedidoDao {

    @Insert
    void insertar(DetallePedido detalle);

    @Delete
    void eliminar(DetallePedido detalle);

    @Query("SELECT * FROM detalle_pedidos WHERE id_pedido = :idPedido")
    List<DetallePedido> obtenerPorPedido(int idPedido);

    @Query("DELETE FROM detalle_pedidos WHERE id_pedido = :idPedido")
    void eliminarPorPedido(int idPedido);
}