package pessoa;
import java.util.Date;
import entidade.PartidoPolitico;

public class Politico extends Cidadao {
    private PartidoPolitico partido;
    private Integer votos;

    public Politico(String nome, String cpf, Date dataNascimento, PartidoPolitico partido) {
        super(nome, cpf, dataNascimento);
        this.partido = partido;
        this.votos = 0;

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
        builder.append("nome do candidato:").append(super.getNome())
            .append("nome do partido: ").append(this.partido.getNome());
        return builder.toString();
    }

}
