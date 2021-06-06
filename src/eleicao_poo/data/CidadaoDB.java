package eleicao_poo.data;

import eleicao_poo.entities.Cidadao;
import eleicao_poo.entities.Eleitor;
import eleicao_poo.entities.MenorIdade;
import eleicao_poo.entities.Politico;

import java.text.ParseException;
import java.util.ArrayList;


public class CidadaoDB implements Database<Cidadao>{
    private ArrayList<Cidadao> cidadoes;
    private static final CidadaoDB singleton = new CidadaoDB();

    private CidadaoDB(){
        this.cidadoes = new ArrayList<Cidadao>();
     }

    public static CidadaoDB getInstance() {
        return singleton;
    }

    @Override
    public Cidadao find(String cpf) {
        try {
            Cidadao.verificaCpf(cpf);
            for(Cidadao cidadao : this.cidadoes) {
                if (cidadao.getCpf().equals(cpf)) {
                    if (cidadao instanceof Politico) 
                        return (Politico) cidadao;
                    else if(cidadao instanceof Eleitor)
                        return (Eleitor) cidadao;
                    else
                        return (MenorIdade) cidadao;    
                }   
            }
            return null;
        } catch(ParseException pe) {
            System.err.println(pe.getMessage());
            return null;
        }
    }

    public Eleitor findEleitorByTitulo(String titulo) {
        try {
            Eleitor.verificaTitulo(titulo);
            for(Cidadao cidadao: this.cidadoes) {
                if(cidadao instanceof Eleitor) {
                    Eleitor el = (Eleitor) cidadao;
                    if (el.getTitulo().equals(titulo))
                        return el;
                }
            }
            return null;
        } catch(ParseException pe) {
            System.err.println(pe.getMessage());
            return null;
        }
    }

    public Politico findPoliticoByCpf(String cpf) {
        try {
            Cidadao.verificaCpf(cpf);
            for(Cidadao cidadao: this.cidadoes) {
                if (cidadao.getCpf().equals(cpf))
                    if(cidadao instanceof Politico) {
                        Politico politico = (Politico) cidadao;
                        return politico;
                    }
            }
            return null;
        } catch(ParseException pe) {
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
        if(!cidadaoExistente.equals(null))
            throw new Exception("Já existe um cidadao cadastrado com o cpf informado");
        else    
            this.cidadoes.add(cidadao);
    }

    @Override
    public boolean remove(Cidadao cidadao) {
        return this.cidadoes.remove(cidadao);
    }
}
