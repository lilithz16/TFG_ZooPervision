package com.zoopervision.tfg_lidiamartinayuso.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zoopervision.tfg_lidiamartinayuso.dao.AnimalDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.EmpleadoDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.InventarioDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.ProductoAnimalDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.ProductoTiendaDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.ProveedorDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.RecintoDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.UsuarioDao;
import com.zoopervision.tfg_lidiamartinayuso.dao.VentaDao;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;
import com.zoopervision.tfg_lidiamartinayuso.entities.Empleado;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;
import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoAnimal;
import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoTienda;
import com.zoopervision.tfg_lidiamartinayuso.entities.Proveedor;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;
import com.zoopervision.tfg_lidiamartinayuso.entities.Usuario;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

@Database(
        entities = {
                Usuario.class,
                Empleado.class,
                Animal.class,
                Recinto.class,
                Inventario.class,
                ProductoAnimal.class,
                ProductoTienda.class,
                Venta.class,
                Proveedor.class
        },
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    public abstract EmpleadoDao empleadoDao();

    public abstract AnimalDao animalDao();

    public abstract RecintoDao recintoDao();
    public abstract InventarioDao inventarioDao();
    public abstract ProductoAnimalDao productoAnimalDao();
    public abstract ProductoTiendaDao productoTiendaDao();
    public abstract VentaDao ventaDao();
    public abstract ProveedorDao proveedorDao();

}