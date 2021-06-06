package eleicao_poo.data;

import java.util.ArrayList;

import eleicao_poo.entities.PartidoPolitico;

public class PartidoDB implements Database<PartidoPolitico> {
    ArrayList<PartidoPolitico> partidos;
    private static final PartidoDB singleton = new PartidoDB();

    private PartidoDB() {
        this.partidos = new ArrayList<PartidoPolitico>();
    }

    public static PartidoDB getInstance() {
        return singleton;
    }

    @Override
    public void add(PartidoPolitico partido) {
        if(this.find(partido.getCodigo())!= null) {
            System.err.println("Já existe um partido com esse código");
            return;
        }
        this.partidos.add(partido);
    }

    @Override
    public PartidoPolitico find(String codigo) {
        for(PartidoPolitico pp : this.partidos) {
            if (pp.getCodigo().equals(codigo))
                return pp;
        }
        return null;
    }

    @Override
    public ArrayList<PartidoPolitico> findAll() {
        return this.partidos;
    }

    @Override
    public boolean remove(PartidoPolitico partido) {
        return this.partidos.remove(partido);
    }
    
}
