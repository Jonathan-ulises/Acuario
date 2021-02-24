package com.example.acuarioutl;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.acuarioutl.model.Peces;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPecesRecyclerViewAdapter extends RecyclerView.Adapter<MyPecesRecyclerViewAdapter.ViewHolder> {

    //Lista de los peces, la que recibira el BindViewHolder para
    //utilizar los datos de los peces
    private final List<Peces> myPeces;

    private final PecesFragment.OnListFragmentInteractionListener mListener;
    Context ctx;

    public MyPecesRecyclerViewAdapter(List<Peces> items, Context context, PecesFragment.OnListFragmentInteractionListener listener) {
        ctx = context;
        myPeces = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_peces, parent, false);
        return new ViewHolder(view);
    }

    /*
    Coneccion de los datos con la vista, asigna los datos de la lista de
    objetos a los componentes visuales de cada componente del RecyclerView
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = myPeces.get(position);
        holder.txtNombre.setText(holder.mItem.getNombre());
        holder.txtEspecie.setText(holder.mItem.getEspecie());
        holder.txtPrecio.setText(String.valueOf(holder.mItem.getPrecio()));
        holder.rtbCal.setRating(holder.mItem.getCalificacion());

        Glide.with(ctx).load(holder.mItem.getFoto()).into(holder.imgPes);

        /*
        Uso de la libreria Glide, esta requiere de un contexto donde ejecitarse, la URL
        de la imagen, y el componente donde se mostrara la imagen (ImageView en este caso)
         */

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPeces.size();
    }

    /*
    Contendor de la estructura de cada item de la lista, Se declaran los elementos
    de la estructura del Row (fila)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNombre;
        public final TextView txtEspecie;
        public final TextView txtPrecio;
        public final ImageView imgPes;
        public final RatingBar rtbCal;
        public Peces mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombre = (TextView) view.findViewById(R.id.txtNombre);
            txtEspecie = (TextView) view.findViewById(R.id.txtEspecie);
            txtPrecio = (TextView) view.findViewById(R.id.txtPrecio);
            imgPes = (ImageView) view.findViewById(R.id.imgPes);
            rtbCal = (RatingBar) view.findViewById(R.id.rtbCali);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtNombre.getText() + "'";
        }
    }
}