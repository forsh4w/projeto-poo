package pessoa;

import java.text.ParseException;
import java.util.Date;

import entidade.PartidoPolitico;

public class Politico extends Cidadao {
    private PartidoPolitico partido;
    private Integer votos;
    private String codigo;

    public Politico(String nome, String cpf, Date dataNascimento, PartidoPolitico partido, Integer votos, String codigo)
            throws ParseException {
        super(nome, cpf, dataNascimento);
        this.partido = partido;
        this.codigo = codigo;
        this.votos = 0;
    }

    public Politico(String nome, String cpf, Date dataNascimento, PartidoPolitico partido, String codigo)
            throws ParseException {
        super(nome, cpf, dataNascimento);
        this.partido = partido;
        this.codigo = codigo;
        this.votos = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public PartidoPolitico getPartido() {
        return partido;
    }

    public Integer getVotos() {
        return votos;
    }

    public void addVoto() {
        this.votos++;
    }

    @Override
    public String getDadosEleitorais() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome do candidato:").append(super.getNome()).append("Nome do partido: ")
                .append(this.partido.getNome());
        return builder.toString();
    }

}
