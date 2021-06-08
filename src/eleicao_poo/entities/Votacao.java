package eleicao_poo.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                case 2:
                    this.consultarResultadoParcial();
                case 3:
                    this.auditarVotos();
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
            votante.votar();
            candidato.addVoto();
            if (!this.votos.containsValue(votante))
                this.votos.put(candidato, votante);
            else {
                throw new Exception("Eleitor já votou");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.menu();
        }

    }

    // Quantidade de votos dos politicos ordenados de maior para o menor
    public void consultarResultadoParcial() {
        ArrayList<Politico> politicos = new ArrayList<Politico>();
        votos.forEach((politico, eleitor) -> politicos.add(politico));
        Collections.sort(politicos, new VotosComparator());
        for (Politico p : politicos) {
            System.out.println("===============================");
            System.out.printf("Nome do politico: %s \n", p.getNome());
            System.out.printf("Cpf do politico: %s \n", p.getCpf());
            System.out.printf("Partido do politico: %s \n", p.getPartido().getNome());
            System.out.printf("Quantidade de votos: %d \n", p.getVotos());
            System.out.println("===============================");
        }
        this.menu();
    }

    // Todos os dados eleitorais dos votantes e dos cadindatos
    public void auditarVotos() {
        ArrayList<Politico> politicos = new ArrayList<Politico>();
        ArrayList<Eleitor> eleitores = new ArrayList<Eleitor>();
        votos.forEach((politico, eleitor) -> {
            politicos.add(politico);
            eleitores.add(eleitor);
        });
        System.out.println("--------------------------------");
        System.out.println("Dados dos votantes:");
        for (Eleitor e : eleitores) {
            System.out.println(e.getDadosEleitorais());
        }

        System.out.println("--------------------------------");

        System.out.println("Dados dos candidatos:");
        for (Politico p : politicos) {
            System.out.println(p.getDadosEleitorais());
        }
        System.out.println("--------------------------------");
        this.menu();
    }
}

class VotosComparator implements Comparator<Politico> {
    @Override
    public int compare(Politico p1, Politico p2) {
        return p1.getVotos() < p2.getVotos() ? -1 : p1.getVotos() == p2.getVotos() ? 0 : 1;
    }
}