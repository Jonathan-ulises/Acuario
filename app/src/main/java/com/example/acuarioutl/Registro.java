package com.example.acuarioutl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    //TextFields
    EditText txtEmailR;
    EditText txtPassR;
    EditText txtPassRV;

    //Buttons
    Button btnSing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtEmailR = findViewById(R.id.txtEmailR);
        txtPassR = findViewById(R.id.txtPassR);
        txtPassRV = findViewById(R.id.txtPassRV);

        btnSing = findViewById(R.id.btnSingUp);

        /**
         * Evento onClick del boton Registrar
         * Verifica que no existan campos vacios y que la confirmacion de la contraseña sea
         * correcta.
         */
        btnSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Variable para obtener los datos de los campos de texto.
                String user = txtEmailR.getText().toString();
                String pass = txtPassR.getText().toString();
                String passR = txtPassR.getText().toString();

                /*
                Veridica que los datos sean correctos, basado en si estan vacios o si las
                contraseñas no coinciden.
                 */
                if(user.isEmpty() && pass.isEmpty() && passR.isEmpty()){

                    //Si todos los campos estan vacios mostrar errores en los campos
                    txtEmailR.setError("Ingrese un Email valido");
                    txtPassR.setError("Ingrese una contraseña valida");
                    txtPassRV.setError("Ingrese una contraseña valida");

                }else if(user.isEmpty() && pass.isEmpty()){

                    //Si el usuario y la contraseña estan vacias.
                    txtEmailR.setError("Ingrese un Email valido");
                    txtPassR.setError("Ingrese una contraseña valida");

                }else if(user.isEmpty() && passR.isEmpty()){

                    //Si el usuario y la verificacion de contraseña estan vacias.
                    txtEmailR.setError("Ingrese un Email valido");
                    txtPassRV.setError("Ingrese una contraseña valida");

                }else if(pass.isEmpty() && passR.isEmpty()){

                    //Si la contraseña y la verificacion de la misma estan vacias.
                    txtPassR.setError("Ingrese una contraseña valida");
                    txtPassRV.setError("Ingrese una contraseña valida");

                }else if(user.isEmpty()){

                    //Si el usuario esta vacio
                    txtEmailR.setError("Ingrese un Email valido");

                    /*
                    Si solo falta el campo del usurio, pasara a verificar si las contraseñas
                    coinciden.
                     */
                    if(!txtPassR.getText().toString().equals(txtPassRV.getText().toString())){
                        //Si no coinciden mostrar error de coincidencia.
                        Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }

                }else if(pass.isEmpty()){

                    //Si la contraseña esta vacia.
                    txtPassR.setError("Ingrese una contraseña valida");

                }else if(passR.isEmpty()){

                    //Si la confirmacion de contraseña esta vacia.
                    txtPassRV.setError("Ingrese una contraseña valida");

                }else{

                    //Si todos los campos estan llenos.
                    //Verificar que las contraseñas coinciden.
                    if(!txtPassR.getText().toString().equals(txtPassRV.getText().toString())){
                        //Si no coinciden mostrar error de coincidencia.
                        Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }else{
                        //Si coinciden las contraseñas, mostrar confirmacion.
                        Toast.makeText(getApplicationContext(),"Registro Completado",Toast.LENGTH_SHORT).show();

                        //Pasar a la activity Principal.
                        Intent intentPrincipal = new Intent(getApplicationContext(), Principal.class);
                        intentPrincipal.putExtra("userN", user);
                        startActivity(intentPrincipal);
                    }
                }

            }
        });

    }
}
