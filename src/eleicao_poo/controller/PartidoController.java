package eleicao_poo.controller;

import java.util.Scanner;

import eleicao_poo.data.PartidoDB;
import eleicao_poo.entities.PartidoPolitico;
import eleicao_poo.maquina.Driver;

public class PartidoController {
    private Scanner input;
    private Driver driver;
    private PartidoDB partidoDB;

    public PartidoController(Driver driver) {
        this.input = new Scanner(System.in);
        this.driver = driver;
        this.partidoDB = PartidoDB.getInstance();
    }

    public void menu() {
        int option = 0;
        System.out.println("Ecolha uma opção: ");
        System.out.println("1 - Cadastrar partido");
        System.out.println("2 - Consultar dados de um partido");
        System.out.println("3 - Retornar ao menu principal");
        option = this.input.nextInt();
        this.input.nextLine();
        switch (option) {
            case 1:
                this.cadastrarPartido();
                break;
            case 2:
                this.visalizarDadosPartido();
                break;
            case 3:
                this.driver.menu();
                break;
            default:
                System.err.println("Digite uma opção válida");
                this.menu();
        }
    }

    private void cadastrarPartido() {
        String nome_partido = "";
        String codigo_partido = "";
        String sigla_partido = "";
        String posicaoPolitica = "";
        System.out.println("Digite o nome do partido");
        nome_partido = this.input.nextLine();
        System.out.println("Digite a sigla do partido");
        sigla_partido = this.input.nextLine();
        System.out.println("Digite a plataforma do partido");
        posicaoPolitica = this.input.nextLine();
        System.out.println("Digite o codigo do partido");
        codigo_partido = this.input.nextLine();
        this.partidoDB.add(new PartidoPolitico(nome_partido, codigo_partido, posicaoPolitica, sigla_partido));
        this.menu();

    }

    public void visalizarDadosPartido() {
        PartidoPolitico partido = this.buscarPartido();
        System.out.println("-----------------------------------");
        System.out.println("Nome:" + partido.getNome());
        System.out.println("Codigo:" + partido.getCodigo());
        System.out.println("Posicao politica: " + partido.getPosicaoPolitica());
        System.out.println("Sigla:" + partido.getSigla());
        System.out.println("-----------------------------------");
        this.menu();

    }

    public PartidoPolitico buscarPartido() {
        String codigo_partido = "";
        PartidoPolitico partido = null;
        System.out.println("Digite o codigo do partido");
        codigo_partido = this.input.nextLine();
        partido = this.partidoDB.find(codigo_partido);
        if (partido.equals(null))
            System.err.println("Não foi encontrado nenhum partido com o código informado");
        return partido;
    }

}
