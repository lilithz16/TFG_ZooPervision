package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.Proveedor;

import java.util.List;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ViewHolder> {

    List<Proveedor> lista;
    OnProveedorClickListener listener;

    public interface OnProveedorClickListener {
        void onProveedorClick(Proveedor proveedor);
        void onProveedorLongClick(Proveedor proveedor);
    }

    public ProveedorAdapter(List<Proveedor> lista, OnProveedorClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, info;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombreProveedor);
            info = itemView.findViewById(R.id.txtInfoProveedor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proveedor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Proveedor proveedor = lista.get(position);

        holder.nombre.setText(proveedor.nombre_empresa);

        holder.info.setText(
                proveedor.contacto + " | " + proveedor.telefono
        );

        holder.itemView.setOnClickListener(v -> listener.onProveedorClick(proveedor));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onProveedorLongClick(proveedor);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}