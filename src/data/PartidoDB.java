package data;

import java.util.ArrayList;

import entidade.PartidoPolitico;

public class PartidoDB implements Database<PartidoPolitico> {
    ArrayList<PartidoPolitico> partidos;


    @Override
    public void add(PartidoPolitico entity) {
        this.partidos.add(entity);
    }

    @Override
    public PartidoPolitico find(String field) {
        for(PartidoPolitico pp : this.partidos) {
            if (pp.getCodigo().equals(field))
                return pp;
        }
        return null;
    }

    @Override
    public ArrayList<PartidoPolitico> findAll() {
        return this.partidos;
    }

    @Override
    public boolean remove(PartidoPolitico entity) {
        return this.partidos.remove(entity);
        
    }
    
}
