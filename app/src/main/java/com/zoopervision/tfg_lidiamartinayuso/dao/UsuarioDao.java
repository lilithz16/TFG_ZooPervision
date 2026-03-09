package com.zoopervision.tfg_lidiamartinayuso.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.zoopervision.tfg_lidiamartinayuso.entities.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void insertar(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario AND password = :password AND activo = 1 LIMIT 1")
    Usuario login(String usuario, String password);

    @Query("SELECT COUNT(*) FROM usuarios")
    int contarUsuarios();

}