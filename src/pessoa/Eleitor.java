package pessoa;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

public class Eleitor extends Cidadao {
    private String titulo;

    public Eleitor(String nome, String cpf, String dataNascimento, String titulo) throws ParseException {
        super(nome, cpf, dataNascimento);
        this.titulo = titulo;
    }

    public Eleitor(String nome, String cpf, Date dataNascimento, String titulo) throws ParseException {
        super(nome, cpf, dataNascimento);
        this.titulo = titulo;

    }

    public static void verificaTitulo(String titulo) throws ParseException {
        if (!Pattern.matches("\\d{12}", titulo))
            throw new ParseException(
                    "Campo de titulo de eleitor está formatado de maneira errada (O campo possui tamnho 12 e só aceita digitos)",
                    0);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDadosEleitorais() {
        StringBuilder builder = new StringBuilder();
        builder.append("nome: ").append(this.getNome()).append("Titulo de eleitor: ").append(this.titulo);
        return builder.toString();
    }

}
