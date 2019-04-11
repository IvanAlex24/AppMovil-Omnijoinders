package com.example.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recetario.dao.RecetaDao;
import com.example.recetario.extras.Constante;
import com.example.recetario.extras.ExampleAdapter;
import com.example.recetario.extras.ExampleClickAdapter;
import com.example.recetario.modelo.Receta;

import java.util.ArrayList;

public class Recetas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        Constante.recetaActual = null;

        // Setting up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Getting your ArrayList - this will be up to you
        RecetaDao recetaDao = new RecetaDao();
        ArrayList<Receta> recetas = new ArrayList<>();
        for(Receta r : recetaDao.ver(this))
            if(r.getIdUsuario() == Constante.usuarioActual.getIdUsuario())
                recetas.add(r);
        Constante.recetasUsuario = recetas;

        // RecyclerView with a click listener
        ExampleClickAdapter clickAdapter = new ExampleClickAdapter(recetas);
        clickAdapter.setOnEntryClickListener(new ExampleClickAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                // stuff that will happen when a list item is clicked
                Constante.recetaActual = Constante.recetasUsuario.get(position);
                Intent i = new Intent(Recetas.this, MiReceta.class);
                startActivity(i);
            }
        });
        recyclerView.setAdapter(clickAdapter);
    }


    public void irMiReceta(View v){
        Intent i = new Intent(Recetas.this,MiReceta.class);
        startActivity(i);
    }
    //metodo para ir a NuevaReceta
    public void irNuevaReceta(View v){
        Intent i = new Intent(Recetas.this,NuevaReceta.class);
        startActivity(i);
    }

    //metodo para volver al inicio
    public void cerrar(View v){
        Intent i = new Intent(Recetas.this, Inicio.class);
        startActivity(i);
    }

//    metodo para ir a editar usuario
    public void irEditarUsuario(View v){
        Intent i = new Intent(Recetas.this, EditarUsuario.class);
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
            Toast.makeText(Recetas.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(opcionMenu);
    }
}
