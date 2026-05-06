package com.example.primeiroprojeto;

import java.util.ArrayList;
import java.util.List;

public class ChamadoRepo {
    private static ChamadoRepo instance;
    private List<Chamado> listaChamados = new ArrayList<>();

    private ChamadoRepo() {}

    public static ChamadoRepo getInstance() {
        if (instance == null) {
            instance = new ChamadoRepo();
        }
        return instance;
    }

    public void adicionar(Chamado c) {
        listaChamados.add(c);
    }

    public List<Chamado> getTodos() {
        return listaChamados;
    }
}