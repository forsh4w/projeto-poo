package eleicao_poo.entities;

import java.util.ArrayList;

public class PartidoPolitico {
    private String nome;
    private String codigo;
    private String posicaoPolitica;
    private String sigla;
    private ArrayList<Politico> politicos;

    public PartidoPolitico (String nome, String codigo, String posicaoPolitica, String sigla) {
        this.nome = nome;
        this.codigo = codigo;
        this.posicaoPolitica = posicaoPolitica;
        this.sigla = sigla;
        this.politicos = new ArrayList<Politico>();
    }
    

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Politico> getPoliticos() {
        return politicos;
    }
    
    public String getPosicaoPolitica() {
        return posicaoPolitica;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addPolitico(Politico politico) {
        this.politicos.add(politico);
    }
    
    public void setPosicaoPolitica(String posicaoPolitica) {
        this.posicaoPolitica = posicaoPolitica;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
