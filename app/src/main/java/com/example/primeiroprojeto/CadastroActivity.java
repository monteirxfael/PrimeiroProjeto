package com.example.primeiroprojeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Esta linha liga o Java ao XML que criamos
        setContentView(R.layout.activity_cadastro);

        // Referenciando os componentes do XML pelo ID
        final EditText editTitulo = findViewById(R.id.editTitulo);
        final EditText editLocal = findViewById(R.id.editLocal);
        final EditText editDescricao = findViewById(R.id.editDescricao);
        final Spinner spinnerTipo = findViewById(R.id.spinnerTipo);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTitulo.getText().toString();
                String local = editLocal.getText().toString();
                String descricao = editDescricao.getText().toString();
                String tipo = spinnerTipo.getSelectedItem().toString();

                // Data atual formatada
                String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(new java.util.Date());

                if (titulo.isEmpty() || local.isEmpty()) {
                    android.widget.Toast.makeText(CadastroActivity.this, "Preencha o título e o local!", android.widget.Toast.LENGTH_SHORT).show();
                } else {
                    // Criando o objeto e salvando no repositório
                    Chamado novoChamado = new Chamado(titulo, dataAtual, descricao, local, tipo);
                    ChamadoRepo.getInstance().adicionar(novoChamado);

                    android.widget.Toast.makeText(CadastroActivity.this, "Chamado cadastrado!", android.widget.Toast.LENGTH_SHORT).show();
                    finish(); // Volta para a tela principal
                }
            }
        });
    }
}