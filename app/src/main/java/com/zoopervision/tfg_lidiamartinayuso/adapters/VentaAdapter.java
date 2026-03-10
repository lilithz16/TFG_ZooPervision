package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.Venta;

import java.util.List;

public class VentaAdapter extends RecyclerView.Adapter<VentaAdapter.ViewHolder> {

    List<Venta> lista;
    OnVentaClickListener listener;

    public interface OnVentaClickListener {
        void onVentaClick(Venta venta);
    }

    public VentaAdapter(List<Venta> lista, OnVentaClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, info;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtVentaId);
            info = itemView.findViewById(R.id.txtVentaInfo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_venta, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Venta venta = lista.get(position);

        holder.id.setText("Venta #" + venta.id_venta);

        holder.info.setText(
                "Fecha: " + venta.fecha +" | Total: " + venta.total + "€"
        );

        holder.itemView.setOnClickListener(v -> listener.onVentaClick(venta));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}