package com.example.primeiroprojeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Intent;

public class ChamadoAdapter extends RecyclerView.Adapter<ChamadoAdapter.ViewHolder> {
    private List<Chamado> chamados;

    public ChamadoAdapter(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chamado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Chamado c = chamados.get(position);

        holder.txtTitulo.setText(c.getTitulo());
        holder.txtData.setText("Data: " + c.getData());
        holder.txtStatus.setText("Status: " + c.getStatus());

        // --- LÓGICA DE CORES ---
        // holder.itemView é o CardView completo
        if (c.getStatus().equalsIgnoreCase("Aberto")) {
            // Vermelho bem claro (ou a cor que preferir)
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFEBEE"));
        }
        else if (c.getStatus().equalsIgnoreCase("Em Atendimento")) {
            // Amarelo claro
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFFDE7"));
        }
        else if (c.getStatus().equalsIgnoreCase("Concluído")) {
            // Verde claro
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#E8F5E9"));
        } else {
            // Branco para qualquer outro caso
            holder.itemView.setBackgroundColor(android.graphics.Color.WHITE);
        }

        // Configuração do clique que já está funcionando
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AtendimentoActivity.class);
                intent.putExtra("chamado", c);
                intent.putExtra("posicao", holder.getAdapterPosition());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chamados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtData, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtItemTitulo);
            txtData = itemView.findViewById(R.id.txtItemData);
            txtStatus = itemView.findViewById(R.id.txtItemStatus);
        }
    }
}