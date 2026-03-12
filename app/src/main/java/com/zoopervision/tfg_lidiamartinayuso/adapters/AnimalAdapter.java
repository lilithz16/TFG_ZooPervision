package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.AnimalConRecinto;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    List<AnimalConRecinto> lista;
    List<AnimalConRecinto> listaCompleta;

    OnAnimalClickListener listener;

    public interface OnAnimalClickListener {
        void onAnimalClick(AnimalConRecinto animal);
        void onAnimalLongClick(AnimalConRecinto animal);
    }

    public AnimalAdapter(List<AnimalConRecinto> lista, OnAnimalClickListener listener) {

        this.lista = lista;
        this.listaCompleta = new ArrayList<>(lista);
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, info;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombre);
            info = itemView.findViewById(R.id.txtInfo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AnimalConRecinto animal = lista.get(position);

        holder.nombre.setText(animal.nombre);

        holder.info.setText(
                animal.tipo + " - Recinto: " + animal.nombreRecinto
        );

        holder.itemView.setOnClickListener(v -> listener.onAnimalClick(animal));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onAnimalLongClick(animal);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void filtrar(String texto){

        lista.clear();

        if(texto.isEmpty()){
            lista.addAll(listaCompleta);
        }else{

            texto = texto.toLowerCase();

            for(AnimalConRecinto animal : listaCompleta){

                if(animal.nombre.toLowerCase().contains(texto)
                        || animal.tipo.toLowerCase().contains(texto)
                        || animal.nombreRecinto.toLowerCase().contains(texto)){

                    lista.add(animal);
                }
            }
        }

        notifyDataSetChanged();
    }
}