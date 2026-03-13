package com.zoopervision.tfg_lidiamartinayuso.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zoopervision.tfg_lidiamartinayuso.R;
import com.zoopervision.tfg_lidiamartinayuso.entities.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {

    List<Pedido> lista;
    List<Pedido> listaCompleta;
    OnPedidoClickListener listener;

    public interface OnPedidoClickListener {
        void onPedidoClick(Pedido pedido);
        void onPedidoLongClick(Pedido pedido);
    }

    public PedidoAdapter(List<Pedido> lista, OnPedidoClickListener listener){
        this.lista = lista;
        this.listaCompleta = new ArrayList<>(lista);
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, estado;

        public ViewHolder(View itemView){
            super(itemView);

            id = itemView.findViewById(R.id.txtPedidoId);
            estado = itemView.findViewById(R.id.txtPedidoEstado);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        Pedido pedido = lista.get(position);

        holder.id.setText("Pedido #" + pedido.id_pedido);
        holder.estado.setText("Estado: " + pedido.estado);

        //colos segun estado
        if(pedido.estado.equals("pendiente")){
            holder.estado.setTextColor(Color.RED);
        }else{
            holder.estado.setTextColor(Color.GREEN);
        }

        holder.itemView.setOnClickListener(v -> listener.onPedidoClick(pedido));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onPedidoLongClick(pedido);
            return true;
        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    //buscador
    public void filtrar(String texto){

        lista.clear();

        if(texto.isEmpty()){
            lista.addAll(listaCompleta);
        }else{

            texto = texto.toLowerCase();

            for(Pedido pedido : listaCompleta){

                if(String.valueOf(pedido.id_pedido).contains(texto)
                        || pedido.estado.toLowerCase().contains(texto)
                        || pedido.fecha_pedido.toLowerCase().contains(texto)){

                    lista.add(pedido);
                }
            }
        }

        notifyDataSetChanged();
    }
}