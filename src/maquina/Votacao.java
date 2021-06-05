package maquina;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import pessoa.Cidadao;
import pessoa.Eleitor;

public class Votacao {
    private Scanner input;
    private ArrayList<Eleitor> eleitores;


    public Votacao() {
        this.input = new Scanner(System.in);
        this.eleitores = new ArrayList<Eleitor>();
    }

    public void menu() throws Exception {
        int option = 0;
        System.out.println("Digite a opcao:");
        System.out.println("0 - Fechar o programa");
        System.out.println("1 - Cadastrar eleitor");
        option = input.nextInt();
        input.nextLine();
        if(option == 1)
            this.CadastrarEleitor();
        else if (option == 0)
            System.exit(0);
        else {
            System.out.println("Digite uma opção válida");
            this.menu();
        } 

    }

    public void CadastrarEleitor() throws Exception{
        String cpf;
        String nome;
        String titulo;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try {
            System.out.println("Digite o nome do eleitor:");
            nome = input.nextLine();
            System.out.println("Digite o cpf do eleitor:");
            cpf = input.nextLine();
            Cidadao.verificaCpf(cpf);
            if (this.BuscarEleitorPorCpf(cpf) != null)
                throw new Exception("Já existe um eleitor cadastrado com esse CPF");
            System.out.println("Digte o numero do eleitor:");
            titulo = input.nextLine();
            Eleitor.verificaTitulo(titulo);
            if(this.BuscarEleitorPorTitulo(titulo) != null)
                throw new Exception("Já existe um eleitor cadastro com esse titulo");
            System.out.println("Digte a data de nascimento do eleitor (dd/mm/yyyy)");
            dataNascimento = sdf.parse(input.nextLine());
            Eleitor eleitor = new Eleitor(cpf, nome, dataNascimento, titulo);
            eleitores.add(eleitor);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.menu();
        }
        
    }

    public Eleitor BuscarEleitorPorCpf(String cpf) {
        for(Eleitor e : eleitores) {
            if (e.getCpf() == cpf)
                return e;
        }
        return null;
    }

    public Eleitor BuscarEleitorPorTitulo(String numeroDoEleitor) {
        for(Eleitor e : eleitores) {
            if (e.getTitulo() == numeroDoEleitor)
                return e;
        }
        return null;
    }


}



