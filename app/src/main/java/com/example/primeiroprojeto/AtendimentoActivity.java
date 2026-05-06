package com.example.primeiroprojeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AtendimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);

        // 1. Recuperando os dados vindos do Adapter
        final Chamado chamado = (Chamado) getIntent().getSerializableExtra("chamado");
        final int posicao = getIntent().getIntExtra("posicao", -1);

        // 2. Referenciando os componentes do layout
        TextView txtTitulo = findViewById(R.id.txtDetTitulo);
        TextView txtInfo = findViewById(R.id.txtDetInfo);
        final EditText editSolucao = findViewById(R.id.editSolucao);
        final Spinner spinnerStatus = findViewById(R.id.spinnerStatus);
        Button btnFinalizar = findViewById(R.id.btnFinalizar);

        // 3. Preenchendo os campos com os dados do chamado
        if (chamado != null) {
            txtTitulo.setText(chamado.getTitulo());
            txtInfo.setText("Local: " + chamado.getLocal() + "\nData: " + chamado.getData());
            editSolucao.setText(chamado.getSolucao());

            // Seleciona o status correto no Spinner
            if (chamado.getStatus().equals("Em Atendimento")) {
                spinnerStatus.setSelection(1);
            } else if (chamado.getStatus().equals("Concluído")) {
                spinnerStatus.setSelection(2);
            }
        }

        // 4. Configuração do botão de atualizar/finalizar
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chamado != null && posicao != -1) {
                    // Atualiza o objeto com o que o usuário digitou/selecionou
                    chamado.setSolucao(editSolucao.getText().toString());
                    chamado.setStatus(spinnerStatus.getSelectedItem().toString());

                    // Salva a alteração no repositório central
                    ChamadoRepo.getInstance().getTodos().set(posicao, chamado);

                    Toast.makeText(AtendimentoActivity.this, "Status Atualizado!", Toast.LENGTH_SHORT).show();

                    // Fecha a tela e volta para a ListaActivity
                    finish();
                } else {
                    Toast.makeText(AtendimentoActivity.this, "Erro ao atualizar dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}