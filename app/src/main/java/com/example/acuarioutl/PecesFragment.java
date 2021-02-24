package com.example.acuarioutl;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acuarioutl.apiRest.RestPeces;
import com.example.acuarioutl.model.Peces;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 */
public class PecesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    RecyclerView recyclerView;
    List<Peces> pecesList = new ArrayList<>();

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PecesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PecesFragment newInstance(int columnCount) {
        PecesFragment fragment = new PecesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peces_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }




            consumirServicio();

            /*
            pecesList.add(new Peces("Sumatrano", "Tetra", 25.50f, 4.5f, "https://upload.wikimedia.org/wikipedia/commons/1/12/Tiger_Barb_700.jpg"));
            pecesList.add(new Peces("Gota de Sangre", "Tetra", 15.50f, 5.0f, "https://www.ecured.cu/images/5/5f/Peces123.jpeg"));
            pecesList.add(new Peces("Angel", "Escalar", 50.00f, 2.0f, "https://www.ecured.cu/images/1/1e/Peces1.JPG"));
            */


            recyclerView.setAdapter(new MyPecesRecyclerViewAdapter(pecesList, getContext(), mListener));
        }
        return view;
    }

    public interface OnListFragmentInteractionListener{
        void onListFragmentInteraction(Peces item);
    }


    public void consumirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.23:8084/RESTPeces/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Implementacion del metodo GET de getAllpeces
        RestPeces getCall = retrofit.create(RestPeces.class);

        Call<List<Peces>> call = getCall.getAllPeces();

        call.enqueue(new Callback<List<Peces>>() {


            @Override
            public void onResponse(Call<List<Peces>> call, Response<List<Peces>> response) {
                //Si existe resupuesta
                if(response.code() != 200){
                    Toast.makeText(getContext(), "Error en retrofit", Toast.LENGTH_LONG).show();

                }else{
                    for(Peces p: response.body()){
                        pecesList.add(p);
                    }
                    /*
                    for(int i = 0; i < response.body().size(); i++){
                        pecesList.add(response.body().get(i));
                    }*/

                    /*pecesList = response.body();
                    for(Peces p: pecesList){
                        Log.d("NOMBRE", p.getNombre());
                        Log.d("ESPECIE", p.getEspecie());
                        Log.d("PRECIO", Float.toString(p.getPrecio()));
                        Log.d("CALIFICACION", Float.toString(p.getCalificacion()));
                        Log.d("FOTO", p.getFoto());
                    }*/


                    Toast.makeText(getContext(), pecesList.get(0).getNombre() , Toast.LENGTH_LONG).show();
                    
                }
            }

            @Override
            public void onFailure(Call<List<Peces>> call, Throwable t) {
                //Si ha ocurrido un error
                Toast.makeText(getContext(), "No hay conexion", Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR_SERVICIO",t.getMessage());
                t.printStackTrace();
            }


        });
        //Toast.makeText(getContext(), pecesList.get(0).getEspecie() , Toast.LENGTH_LONG).show();
    }
}

/**
 *
 *

 //Si existe resupuesta
 if(response.code() != 200){
 Toast.makeText(getContext(), "Error en retrofit", Toast.LENGTH_LONG).show();
 }else{
 //Peces p = new Peces(response.body().getNombre(), response.body().getEspecie(), response.body().getPrecio(), response.body().getCalificacion(), response.body().getFoto());
 //listP.add(p);
 Toast.makeText(getContext(), "Si funciona", Toast.LENGTH_LONG).show();
 }


 //Si ha ocurrido un error
 Toast.makeText(getContext(), "No hay conexion", Toast.LENGTH_LONG).show();
 Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
 Log.e("ERROR_SERVICIO",t.getMessage());
 t.printStackTrace();


 */