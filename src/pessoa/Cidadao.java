package pessoa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class Cidadao {
    private String nome;
    private String cpf;
    private Date dataNascimento;

    public Cidadao(String nome, String cpf, Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Cidadao(String nome, String cpf, String dataNascimento) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.nome = nome;
            this.cpf = cpf;
            this.dataNascimento = sdf.parse(dataNascimento);
        } catch (ParseException pe) {
            throw pe;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void verificaCpf(String cpf) throws ParseException {
        if (!Pattern.matches("\\d{11}", cpf))
            throw new ParseException("Campo cpf com formado inválido (o campo possui tamanho 11 e só aceita digitos)",
                    1);
    }

    abstract public String getDadosEleitorais();
}
