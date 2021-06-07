package eleicao_poo.maquina;

import java.util.Scanner;

import eleicao_poo.controller.CidadaoController;
import eleicao_poo.controller.PartidoController;
import eleicao_poo.entities.Votacao;

public class Driver {
    private Scanner input;
    private CidadaoController cidadaoController;
    private PartidoController partidoController;
    private Votacao votacao;

    public Driver() {
        this.input = new Scanner(System.in);
        this.cidadaoController = new CidadaoController(this);
        this.partidoController = new PartidoController(this);
        this.votacao = new Votacao(this);
    }

    public void menu() {
        int option = 0;
        System.out.println("Ecolha uma opção: ");
        System.out.println("1 - Acessar menu de cidadao");
        System.out.println("2 - Acessar menu de partido");
        System.out.println("3 - Acessar menu de votação");
        System.out.println("4 - Encerrar sistema");
        try {
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    this.cidadaoController.menu();
                    break;
                case 2:
                    this.partidoController.menu();
                    break;
                case 3:
                    this.votacao.menu();
                    break;
                case 4:
                    System.exit(0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public PartidoController getPartidoController() {
        return this.partidoController;
    }

    public CidadaoController getCidadaoController() {
        return this.cidadaoController;
    }

    public Votacao getVotacao() {
        return this.votacao;
    }
}
