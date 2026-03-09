package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.activities.ListaAnimalesRecintoActivity;
import com.zoopervision.tfg_lidiamartinayuso.entities.Recinto;

import java.util.List;

public class RecintoAdapter extends RecyclerView.Adapter<RecintoAdapter.ViewHolder> {

    List<Recinto> lista;
    OnRecintoClickListener listener;

    public interface OnRecintoClickListener {
        void onRecintoClick(Recinto recinto);
        void onRecintoLongClick(Recinto recinto);
    }

    public RecintoAdapter(List<Recinto> lista, OnRecintoClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, info;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombreRecinto);
            info = itemView.findViewById(R.id.txtInfoRecinto);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recinto, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Recinto recinto = lista.get(position);

        holder.nombre.setText(recinto.nombre);
        holder.info.setText(recinto.tipo + " - Capacidad: " + recinto.capacidad);

        holder.itemView.setOnClickListener(v -> {
            listener.onRecintoClick(recinto);
        });

        holder.itemView.setOnLongClickListener(v -> {
            listener.onRecintoLongClick(recinto);
            return true;
        });

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ListaAnimalesRecintoActivity.class);
            intent.putExtra("id_recinto", recinto.id_recinto);
            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}