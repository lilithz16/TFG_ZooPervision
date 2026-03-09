package com.zoopervision.tfg_lidiamartinayuso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.database.DatabaseClient;
import com.zoopervision.tfg_lidiamartinayuso.database.AppDatabase;
import com.zoopervision.tfg_lidiamartinayuso.entities.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crearAdminSiNoExiste();

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login(){

        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();

        if(usuario.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Introduce usuario y contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = DatabaseClient
                .getInstance(this)
                .getAppDatabase();

        Usuario user = db.usuarioDao().login(usuario,password);

        if(user != null){

            Toast.makeText(this,"Bienvenido "+usuario,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            finish();

        }else{

            Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();

        }
    }

    private void crearAdminSiNoExiste(){

        AppDatabase db = DatabaseClient
                .getInstance(this)
                .getAppDatabase();

        int totalUsuarios = db.usuarioDao().contarUsuarios();

        if(totalUsuarios == 0){

            Usuario admin = new Usuario();
            admin.usuario = "admin";
            admin.password = "admin";
            admin.rol = "admin";
            admin.id_empleado = 0;
            admin.activo = true;
            admin.fecha_creacion = "2026";

            db.usuarioDao().insertar(admin);
        }
    }
}
