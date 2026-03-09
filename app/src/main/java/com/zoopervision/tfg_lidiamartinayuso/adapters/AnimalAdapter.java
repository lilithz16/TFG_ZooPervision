package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    List<Animal> lista;
    OnAnimalClickListener listener;

    public interface OnAnimalClickListener {
        void onAnimalClick(Animal animal);
        void onAnimalLongClick(Animal animal);
    }

    public AnimalAdapter(List<Animal> lista, OnAnimalClickListener listener) {
        this.lista = lista;
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

        Animal animal = lista.get(position);

        holder.nombre.setText(animal.nombre);
        holder.info.setText(animal.tipo + " - " + animal.edad + " años");

        holder.itemView.setOnClickListener(v -> {
            listener.onAnimalClick(animal);
        });

        holder.itemView.setOnLongClickListener(v -> {
            listener.onAnimalLongClick(animal);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}