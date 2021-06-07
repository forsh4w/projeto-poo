package eleicao_poo.entities;

import java.util.Hashtable;
import java.util.Scanner;

import eleicao_poo.controller.CidadaoController;
import eleicao_poo.maquina.Driver;

public class Votacao {
    private Scanner input;
    private Driver driver;
    private Hashtable<Politico, Eleitor> votos;

    public Votacao(Driver driver) {
        this.input = new Scanner(System.in);
        this.driver = driver;
        this.votos = new Hashtable<Politico, Eleitor>();
        this.input = new Scanner(System.in);
    }

    public void menu() {
        int option = 0;
        System.out.println("Escolha uma opção");
        System.out.println("1 - votar");
        System.out.println("2 - consultar resultado parcial");
        System.out.println("3 - auditar votos");
        System.out.println("4 - Voltar ao menu principal");
        option = this.input.nextInt();
        this.input.nextLine();
        try {

            switch (option) {
                case 1:
                    this.votar();
                    break;
                case 4:
                    this.driver.menu();
                    break;
                default:
                    System.err.println("Insira uma opção valida");
                    this.menu();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void votar() throws Exception {
        CidadaoController cd = this.driver.getCidadaoController();
        Eleitor votante = null;
        Politico candidato = null;
        try {
            votante = cd.findEleitor();
            candidato = cd.findPoliticoByCpf();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        votante.votar();
        candidato.addVoto();
        if (!this.votos.containsValue(votante))
            this.votos.put(candidato, votante);
        else {
            throw new Exception("Eleitor já votou");
        }

    }

    // TODO: IMPLEMENTAR METODO DE CONSULTA PARCIAL
    public void consultarResultadoParcial() {
    }

    // TODO: IMPLEMENTAR METODO DE CONSULTA PARCIAL
    public void auditarVotos() {
    }

}
