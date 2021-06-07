package data;

import java.util.ArrayList;

import pessoa.Politico;

public class PoliticosDB implements Database<Politico> {
    private ArrayList<Politico> politicos;

    @Override
    public void add(Politico politico) {
        this.politicos.add(politico);
    }

    @Override
    public Politico find(String codigo) {
        for (Politico politico : this.politicos) {
            if (politico.getCodigo().equals(codigo))
                return politico;
        }
        return null;
    }

    @Override
    public ArrayList<Politico> findAll() {
        return this.politicos;
    }

    @Override
    public boolean remove(Politico entity) {
        return this.politicos.remove(entity);
    }
}
