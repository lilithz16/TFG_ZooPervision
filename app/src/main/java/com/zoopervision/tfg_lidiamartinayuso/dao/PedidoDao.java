package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.util.List;

@Dao
public interface PedidoDao {

    @Insert
    long insertar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("DELETE FROM pedidos WHERE id_pedido = :id")
    void eliminar(int id);

    @Query("SELECT * FROM pedidos ORDER BY id_pedido DESC")
    List<Pedido> obtenerTodos();

    @Query("SELECT * FROM pedidos WHERE id_pedido = :id")
    Pedido obtenerPorId(int id);

    @Query("UPDATE pedidos SET estado = :estado WHERE id_pedido = :id")
    void actualizarEstado(int id, String estado);
}