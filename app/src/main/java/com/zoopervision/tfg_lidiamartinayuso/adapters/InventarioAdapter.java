package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.Inventario;

import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.ViewHolder> {

    List<Inventario> lista;
    OnInventarioClickListener listener;

    public interface OnInventarioClickListener {
        void onItemClick(Inventario item);
        void onItemLongClick(Inventario item);
    }

    public InventarioAdapter(List<Inventario> lista, OnInventarioClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, info;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombreInventario);
            info = itemView.findViewById(R.id.txtInfoInventario);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventario, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Inventario item = lista.get(position);

        holder.nombre.setText(item.nombre);

        String texto = "Stock: " + item.stock;

        if(item.stock <= item.stock_minimo){
            texto += " ⚠ STOCK BAJO";
        }

        holder.info.setText(texto);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(item);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}