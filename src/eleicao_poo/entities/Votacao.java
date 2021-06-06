package eleicao_poo.entities;

import java.util.Hashtable;
import java.util.Scanner;


public class Votacao {
    private Scanner input;
    private Hashtable<Politico, Eleitor> votos;

    public Votacao() {
        this.input = new Scanner(System.in);
        this.votos = new Hashtable<Politico, Eleitor>();
    }


    public void menu() {
        System.out.print("Escolha uma opção");
    }


    public void votar()  {
        String titulo = "";
        Eleitor votante = null;
        Politico candidato = null;
        
        votante.votar();
        this.votos.put(candidato, votante);
    

        
    }

    

   

}



