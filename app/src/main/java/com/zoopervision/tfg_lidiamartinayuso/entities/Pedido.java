package com.zoopervision.tfg_lidiamartinayuso.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos")
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public int id_pedido;

    public int id_proveedor;

    public String fecha_pedido;

    public String fecha_entrega;

    public String estado; //pendiente o recibido
}