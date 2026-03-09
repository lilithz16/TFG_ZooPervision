package com.zoopervision.tfg_lidiamartinayuso.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zoopervision.tfg_lidiamartinayuso.dao.AnimalDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.EmpleadoDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.InventarioDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.RecintoDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.UsuarioDao;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;
import com.zoopervision.tfg_lidiamartinayuso.entities.Empleado;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;
import com.zoopervision.tfg_lidiamartinayuso.entities.Usuario;

@Database(
        entities = {
                Usuario.class,
                Empleado.class,
                Animal.class,
                Recinto.class,
                Inventario.class
        },
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    public abstract EmpleadoDao empleadoDao();

    public abstract AnimalDao animalDao();

    public abstract RecintoDao recintoDao();
    public abstract InventarioDao inventarioDao();

}