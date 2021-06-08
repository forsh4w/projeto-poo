package eleicao_poo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import eleicao_poo.data.CidadaoDB;
import eleicao_poo.entities.Cidadao;
import eleicao_poo.entities.Eleitor;
import eleicao_poo.entities.MenorIdade;
import eleicao_poo.entities.PartidoPolitico;
import eleicao_poo.entities.Politico;
import eleicao_poo.maquina.Driver;

public class CidadaoController {
    private CidadaoDB cidadaoDB;
    private Driver driver;
    private Scanner input;

    public CidadaoController(Driver driver) {
        this.cidadaoDB = new CidadaoDB();
        this.input = new Scanner(System.in);
        this.driver = driver;
    }

    public void menu() {
        int option = 0;
        System.out.println("Digite a opcao:");
        System.out.println("1 - Cadastrar cidadao");
        System.out.println("2 - Buscar dados eleitorais de um cidadao");
        System.out.println("3 - Retornar ao menu principal");

        option = input.nextInt();
        input.nextLine();
        try {
            if (option == 1)
                this.CadastrarCidadao();
            if (option == 2)
                this.buscarDadosEleitorais();
            else if (option == 3)
                this.driver.menu();
            else {
                System.out.println("Digite uma opção válida");
                this.menu();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.menu();
        }
    }

    private void CadastrarCidadao() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nome = "";
        String cpf = "";
        String dataNascimentoStr = "";
        Date dataNascimento = null;
        int option = 0;
        try {
            System.out.println("Digite o nome do cidadao");
            nome = input.nextLine();
            System.out.println("Digite o cpf do cidadao");
            cpf = input.nextLine();
            Cidadao.verificaCpf(cpf);
            Cidadao cidadao = this.cidadaoDB.find(cpf);
            if (cidadao != null) {
                throw new Exception("Já existe um cidadao com esse cpf no sistema");
            }
            System.out.println("Digite a data de nascimento do cidadao");
            dataNascimentoStr = input.nextLine();
            dataNascimento = sdf.parse(dataNascimentoStr);
            if (!maiorIdade(dataNascimento)) {
                MenorIdade mi = new MenorIdade(nome, cpf, dataNascimento);
                this.cidadaoDB.add(mi);
            } else {
                System.out.println("Ecolha uma opção: ");
                System.out.println("1 - Desejo cadastrar um eleitor");
                System.out.println("2 - Desejo cadastrar um politico");
                option = input.nextInt();
                input.nextLine();
                if (option == 1)
                    this.CadastrarEleitor(nome, cpf, dataNascimento);
                else if (option == 2)
                    this.CadastrarPolitico(nome, cpf, dataNascimento);
                else {
                    System.err.println("Digite uma opção válida");
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.menu();
        }

    }

    private void buscarDadosEleitorais() {
        String cpf = "";
        System.out.println("Digite o cpf do cidadao");
        cpf = input.nextLine();
        try {
            Cidadao cidadao = cidadaoDB.find(cpf);
            if (cidadao == null)
                System.out.println("Não foram encontrados cidadoes com o cpf informado");
            else if (cidadao instanceof Eleitor) {
                System.out.println(((Eleitor) cidadao).getDadosEleitorais());
                if (((Eleitor) cidadao).getJaVotou()) {
                    System.out.println("Já votou.");
                } else {
                    System.out.println("Ainda não votou.");
                }
            } else if (cidadao instanceof Politico) {
                System.out.println(((Politico) cidadao).getDadosEleitorais());
            } else {
                System.out.println(((MenorIdade) cidadao).getDadosEleitorais());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        this.menu();
    }

    public Eleitor findEleitor() {
        String titulo_eleitor = "";
        System.out.println("Digite o titulo do eleitor");
        try {
            titulo_eleitor = this.input.nextLine();
            Eleitor.verificaTitulo(titulo_eleitor);
            return this.cidadaoDB.findEleitorByTitulo(titulo_eleitor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public Politico findPoliticoByCpf() {
        try {
            String cpf = "";
            System.out.println("Digite o cpf do candidato");
            cpf = this.input.nextLine();
            Cidadao.verificaCpf(cpf);
            return this.cidadaoDB.findPoliticoByCpf(cpf);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void CadastrarEleitor(String nome, String cpf, Date dataNascimento) {
        try {
            String tituloEleitor = "";
            System.out.println("Digite o numero do titulo de eleitor");
            tituloEleitor = this.input.nextLine();
            Eleitor.verificaTitulo(tituloEleitor);
            if (cidadaoDB.findEleitorByTitulo(tituloEleitor) == null) {
                Eleitor el = new Eleitor(nome, cpf, dataNascimento, tituloEleitor);
                this.cidadaoDB.add(el);
            } else
                throw new Exception("Já existe um cidadao cadastrado com esse titulo");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.menu();
        }
    }

    private void CadastrarPolitico(String nome, String cpf, Date dataNascimento) {
        PartidoPolitico partido = this.driver.getPartidoController().buscarPartido();
        if (partido == null) {
            System.err.println("Não foi econtrado nenhum partido com o código informado");
        } else {
            try {
                Politico p = new Politico(nome, cpf, dataNascimento, partido);
                this.cidadaoDB.add(p);
                partido.addPolitico(p);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        this.menu();

    }

    private boolean maiorIdade(Date dataNascimento) {
        Date current_date = new Date();
        int CONVERSION_FACTOR = 1000 * 3600 * 24 * 365;
        return ((current_date.getTime() - dataNascimento.getTime()) / CONVERSION_FACTOR >= 18);

    }
}
