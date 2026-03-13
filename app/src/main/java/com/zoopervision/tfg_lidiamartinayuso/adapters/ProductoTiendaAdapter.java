package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.ProductoTienda;

import java.util.ArrayList;
import java.util.List;

public class ProductoTiendaAdapter extends RecyclerView.Adapter<ProductoTiendaAdapter.ViewHolder> {

    List<ProductoTienda> lista;
    List<ProductoTienda> listaCompleta;

    OnProductoClickListener listener;

    public interface OnProductoClickListener {
        void onProductoClick(ProductoTienda producto);
        void onProductoLongClick(ProductoTienda producto);
    }

    public ProductoTiendaAdapter(List<ProductoTienda> lista, OnProductoClickListener listener) {
        this.lista = new ArrayList<>(lista);
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
                .inflate(R.layout.item_producto_tienda, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductoTienda producto = lista.get(position);

        holder.nombre.setText(producto.nombre);

        holder.info.setText(
                producto.tipo + " - " + producto.precio + "€"
        );

        holder.itemView.setOnClickListener(v -> listener.onProductoClick(producto));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onProductoLongClick(producto);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    //buscador
    public void filtrar(String texto){

        lista.clear();

        if(texto.isEmpty()){
            lista.addAll(listaCompleta);
        }else{

            texto = texto.toLowerCase();

            for(ProductoTienda producto : listaCompleta){

                if(producto.nombre.toLowerCase().contains(texto)
                        || producto.tipo.toLowerCase().contains(texto)
                        || producto.descripcion.toLowerCase().contains(texto)){

                    lista.add(producto);
                }
            }
        }

        notifyDataSetChanged();
    }
}