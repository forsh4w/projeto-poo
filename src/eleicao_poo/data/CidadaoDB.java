package eleicao_poo.data;

import java.text.ParseException;
import java.util.ArrayList;

import eleicao_poo.entities.Cidadao;
import eleicao_poo.entities.Eleitor;
import eleicao_poo.entities.MenorIdade;
import eleicao_poo.entities.Politico;

public class CidadaoDB implements Database<Cidadao> {
    private ArrayList<Cidadao> cidadoes;

    public CidadaoDB() {
        this.cidadoes = new ArrayList<Cidadao>();
    }

    
    @Override
    public Cidadao find(String cpf) {
        if(this.cidadoes.size() == 0)
            return null;
        try {
            Cidadao.verificaCpf(cpf);
            for (Cidadao cidadao : this.cidadoes) {
                if (cidadao.getCpf().equals(cpf)) {
                    if (cidadao instanceof Politico)
                        return (Politico) cidadao;
                    else if (cidadao instanceof Eleitor)
                        return (Eleitor) cidadao;
                    else
                        return (MenorIdade) cidadao;
                }
            }
            return null;
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
            return null;
        }
    }

    public Eleitor findEleitorByTitulo(String titulo) {
        if(this.cidadoes.size() == 0)
            return null;
        try {
            Eleitor.verificaTitulo(titulo);
            for (Cidadao cidadao : this.cidadoes) {
                if (cidadao instanceof Eleitor) {
                    Eleitor el = (Eleitor) cidadao;
                    if (el.getTitulo().equals(titulo))
                        return el;
                }
            }
            return null;
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
            return null;
        }
    }

    public Politico findPoliticoByCpf(String cpf) {
        if(this.cidadoes.size() == 0)
            return null;
        try {
            Cidadao.verificaCpf(cpf);
            for (Cidadao cidadao : this.cidadoes) {
                if (cidadao.getCpf().equals(cpf))
                    if (cidadao instanceof Politico) {
                        Politico politico = (Politico) cidadao;
                        return politico;
                    }
            }
            return null;
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
            return null;
        }

    }

    @Override
    public ArrayList<Cidadao> findAll() {
        return this.cidadoes;
    }

    @Override
    public void add(Cidadao cidadao) throws Exception {
        Cidadao cidadaoExistente = this.find(cidadao.getCpf());
        if (cidadaoExistente != null)
            throw new Exception("Já existe um cidadao cadastrado com o cpf informado");
        else
            this.cidadoes.add(cidadao);
    }

    @Override
    public boolean remove(Cidadao cidadao) {
        return this.cidadoes.remove(cidadao);
    }
}
