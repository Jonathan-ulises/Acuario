package com.example.acuarioutl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TextFileds
    EditText txtEmail;
    EditText txtPass;

    //Buttons
    Button btnIniS;

    //Label
    TextView lbSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPassword);

        btnIniS = findViewById(R.id.btnIniciarS);

        lbSingUp = findViewById(R.id.lbSingUp);

        /**
         * Evento del onClick boton Iniciar Sesion.
         * Envia el nombre de usuario a la activity Principal mediante un intent.
         */
        btnIniS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();

                if(user.isEmpty() && pass.isEmpty()){
                    txtEmail.setError("Introduce un valor valido");
                    txtPass.setError("Introduce un valor valido");
                }else if(user.isEmpty()){
                    txtEmail.setError("Introduce un valor valido");
                }else if(pass.isEmpty()){
                    txtPass.setError("Introduce un valor valido");
                }else{
                    Toast.makeText(getApplicationContext(),"Datos Correctos",Toast.LENGTH_SHORT).show();

                    //Creacion de Intent y direccionamiento de activity
                    Intent intentLogin = new Intent(getApplicationContext(), Principal.class);

                    //Pasar elementos de una activity a otra
                    intentLogin.putExtra("userN", user);

                    //Inicio de la segunda activity con el objeto intent
                    startActivity(intentLogin);
                }

            }
        });

        /**
         * Evento onClick del label de registro
         * Carga la activity Registro
         */
        lbSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSing = new Intent(getApplicationContext(), Registro.class);
                startActivity(intentSing);
            }
        });



    }

}