package com.example.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recetario.dao.UsuarioDao;
import com.example.recetario.extras.Constante;
import com.example.recetario.modelo.Usuario;

public class EditarUsuario extends AppCompatActivity {


    Usuario usuario = Constante.usuarioActual;
    EditText etUsuario, etContraVieja, etContraNueva, etContraNueva2;
    Button Actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
//        elementos editables
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etContraVieja = (EditText) findViewById(R.id.etContraVieja);
        etContraNueva = (EditText)findViewById(R.id.etContraNueva);
        etContraNueva2 = (EditText)findViewById(R.id.etContraNuevaDos);
        Actualizar = (Button) findViewById(R.id.btnActualizar);

//        llenado de los valoree
        etUsuario.setText(usuario.getUsuario());

        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

    }

//    metodo para ir a misrecetas
    public void irMisRecetas(View v){
        Intent i = new Intent(EditarUsuario.this,MisRecetas.class);
        startActivity(i);
    }
    //
    public void actualizar(){
        //                obtener informacion agregada
        String contraVieja = etContraVieja.getText().toString();
        String NuevoUsuario = etUsuario.getText().toString();
        String contraNueva = etContraNueva.getText().toString();
        String contraNuevaDos = etContraNueva2.getText().toString();
        if(!(NuevoUsuario.equals("") || contraVieja.equals("") || contraNueva.equals("") ||  contraNuevaDos.equals(""))){
//                    ver si la contraseña vieja coincide
            if (usuario.getContrasena().equals(contraVieja)){
                if (contraNueva.equals(contraNuevaDos)){
                    UsuarioDao usuaDao = new UsuarioDao();
                    Usuario nuevoUsua = new Usuario(usuario.getIdUsuario(), NuevoUsuario, contraNueva);

                    usuaDao.Actualizar(this, nuevoUsua);
                    Toast.makeText(this, "Usuario modificado", Toast.LENGTH_LONG).show();
                    irMisRecetas(null);

                }else{
                    Toast.makeText(EditarUsuario.this, "Las contraseñas nuevas no coinciden", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(EditarUsuario.this, "La contraseña actual no coincide", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(EditarUsuario.this, "Llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

}
