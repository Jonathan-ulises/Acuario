package com.example.acuarioutl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acuarioutl.apiRest.RestPeces;
import com.example.acuarioutl.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //TextFileds
    EditText txtEmail;
    EditText txtPass;

    //Buttons
    Button btnIniS;

    //Label
    TextView lbSingUp;
    List<Usuario> usuarios = new ArrayList<>();

    boolean validateU;

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
                boolean va;

                if(user.isEmpty() && pass.isEmpty()){
                    txtEmail.setError("Introduce un valor valido");
                    txtPass.setError("Introduce un valor valido");
                }else if(user.isEmpty()){
                    txtEmail.setError("Introduce un valor valido");
                }else if(pass.isEmpty()){
                    txtPass.setError("Introduce un valor valido");
                }else{

                    consumirServicio();

                    //consumirServicioPOST(user, pass);


                    va = findUser(user, pass);

                    if(!va) {
                        Toast.makeText(getApplicationContext(),"Datos Incorrectos",Toast.LENGTH_SHORT).show();
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


    private void consumirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.23:8084/RESTPeces/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestPeces getCall = retrofit.create(RestPeces.class);

        Call<List<Usuario>> call = getCall.getAllUser();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.code() != 200){
                    Toast.makeText(getApplicationContext(), Integer.toString(response.code()), Toast.LENGTH_SHORT).show();
                }else{
                    for(Usuario u: response.body()) {
                        usuarios.add(u);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No hay conexion", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR_SERVICIO",t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void consumirServicioPOST(String nameU, String passU){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.23:8084/RESTPeces/")

                .build();



        RestPeces getCallPOST = retrofit.create(RestPeces.class);

        Call<ResponseBody> call = getCallPOST.validateUser(nameU, passU);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                try {
                    Toast.makeText(getApplicationContext(), response.body().string()+ "----true", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                validateU = true;

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });


    }

    private boolean findUser(String us, String ps){
        boolean value = false;

        for(Usuario u: usuarios){
            if(!u.getUserName().equals(us) && !u.getPass().equals(ps)){

                continue;

            }else{
                return true;
            }
        }

        return value;
    }
}