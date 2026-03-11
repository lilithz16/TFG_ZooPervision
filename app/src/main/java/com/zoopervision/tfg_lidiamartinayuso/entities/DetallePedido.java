package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "detalle_pedidos")
public class DetallePedido {

    @PrimaryKey(autoGenerate = true)
    public int id_detalle;

    public int id_pedido;

    public String producto;

    public int cantidad;

    public double precio_unitario;

    public double subtotal;
}