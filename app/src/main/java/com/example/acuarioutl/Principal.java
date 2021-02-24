package com.example.acuarioutl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.acuarioutl.model.Acuario;
import com.example.acuarioutl.model.Peces;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity implements PecesFragment.OnListFragmentInteractionListener{

    private TextView txtSaludo;
    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtSaludo = (TextView) findViewById(R.id.txtSaludo);

        Bundle extra = getIntent().getExtras();

        mensaje = "Bienveni@ " + extra.get("userN");

        txtSaludo.setText(mensaje);

    }

    @Override
    public void onListFragmentInteraction(Peces item) {

    }
}