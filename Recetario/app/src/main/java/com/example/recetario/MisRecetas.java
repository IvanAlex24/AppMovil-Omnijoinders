package com.example.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MisRecetas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_recetas);
    }

    //metodo para ir a las recetas
    public void irRecetas(View v){
        Intent i = new Intent(MisRecetas.this, Recetas.class);
        startActivity(i);
    }

    //metodo para volver al inicio
    public void cerrar(View v){
        Intent i = new Intent(MisRecetas.this, Inicio.class);
        startActivity(i);
    }

    //    metodo para ir a editar usuario
    public void irEditarUsuario(View v){
        Intent i = new Intent(MisRecetas.this, EditarUsuario.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){

        getMenuInflater().inflate(R.menu.menu,m);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcionMenu){
//        capturar id del item
        int id = opcionMenu.getItemId();
        if(id == R.id.opcEditar){
            irEditarUsuario(null);
            return true;
        }
        if(id == R.id.opcCerrar){
            cerrar(null);
            Toast.makeText(MisRecetas.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(opcionMenu);
    }

}
