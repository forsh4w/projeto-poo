package data;

import java.util.ArrayList;

import pessoa.Eleitor;

public class EleitoresDB implements Database<Eleitor> {
    private ArrayList<Eleitor> eleitores;
    
    @Override
    public void add(Eleitor eleitor) {
        this.eleitores.add(eleitor);
    }

    @Override
    public Eleitor find(String titulo) {
        for(Eleitor el: this.eleitores) {
            if(el.getTitulo().equals(titulo))
                return el;
        }
        return null;
    }

    @Override
    public ArrayList<Eleitor> findAll() {
        return this.eleitores;
    }

    @Override
    public boolean remove(Eleitor entity) {
        return this.eleitores.remove(entity);
    }

    
}
