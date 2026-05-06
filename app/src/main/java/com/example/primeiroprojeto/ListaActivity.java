package com.example.primeiroprojeto;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ChamadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Configuração inicial do RecyclerView
        rv = findViewById(R.id.rvChamados);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Pegamos a lista do repositório
        List<Chamado> lista = ChamadoRepo.getInstance().getTodos();

        // Criamos um novo adapter SEMPRE para garantir que ele pegue os novos dados
        adapter = new ChamadoAdapter(lista);
        rv.setAdapter(adapter);
    }

    private void atualizarLista(List<Chamado> lista) {
        // Criamos o adapter com a lista recebida
        adapter = new ChamadoAdapter(lista);
        // Entregamos o adapter para o componente visual
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filtro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        List<Chamado> listaCompleta = ChamadoRepo.getInstance().getTodos();
        List<Chamado> listaFiltrada = new ArrayList<>();

        int id = item.getItemId();

        if (id == R.id.filtro_todos) {
            atualizarLista(listaCompleta);
            return true;
        }

        String statusBusca = "";
        if (id == R.id.filtro_aberto) statusBusca = "Aberto";
        else if (id == R.id.filtro_atendimento) statusBusca = "Em Atendimento";
        else if (id == R.id.filtro_concluido) statusBusca = "Concluído";

        for (Chamado c : listaCompleta) {
            if (c.getStatus().equalsIgnoreCase(statusBusca)) {
                listaFiltrada.add(c);
            }
        }

        atualizarLista(listaFiltrada);
        return super.onOptionsItemSelected(item);
    }
}