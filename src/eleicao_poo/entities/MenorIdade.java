package eleicao_poo.entities;

import java.util.Date;

public class MenorIdade extends Cidadao {
    public MenorIdade(String nome, String cpf, Date dataNascimento) {
        super(nome, cpf, dataNascimento);

    }

    @Override
    public String getDadosEleitorais() {
        return "Não pode votar";
    }

}
