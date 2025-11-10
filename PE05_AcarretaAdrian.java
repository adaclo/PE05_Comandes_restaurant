package Activitats.PE05;

import java.util.Scanner;

public class PE05_AcarretaAdrian {

    // COLORS
    final String RESET = "\u001B[0m";
    final String ROJO = "\u001B[31m";
    final String VERDE = "\u001B[32m";
    final String AMARILLO = "\u001B[33m";
    final String AZUL = "\u001B[34m";
    String r; // VARIABLE PER INPUTS
    String comanda=null;
    Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        PE05_AcarretaAdrian p = new PE05_AcarretaAdrian();
        p.principal();
    }
    public void principal() {
        
        do {
        System.out.println("------------------------------------");
        System.out.println("==== GESTIÓ COMANDES RESTAURANT ====");
        System.out.println("------------------------------------\n");
        System.err.println("1. Crear nova comanda");
        System.err.println("2. Actualitzar comanda anterior");
        System.err.println("3. Visualitzar últim tiquet");
        System.err.println("4. Sortir");
        System.out.print(AMARILLO + "\n> Tria una opció: " + RESET);
        r = s.next();

        switch (r) {
            case "1":
                crearComanda();
                break;
            case "2":
                //actualitzarComanda();
                break;
            case "3":
                //visualitzarTiquet();
                break;
            case "4":
                System.out.println(AMARILLO+"(-) Sortint del programa..."+RESET);
                break;
            default:
                System.out.println(ROJO+"(!) Introdueix un valor vàlid" + RESET);
                break;
        }
        } while (!r.equals("4"));
    }

    public String crearComanda() {
        System.out.println("------------------------------------");
        System.out.println("=========== NOVA COMANDA ===========");
        System.out.println("------------------------------------\n");
        System.out.print(AMARILLO + "\n> Introdueix el nom del client: " + RESET);
        r  = s.next();
        if (r.equals(null)) {
            errorInvalid("nom");
        } else {
            String nom=r;
            Boolean comandaComplerta=false;
            r=null;
            do {
                System.out.print(AMARILLO + "\n> Introdueix el producte: " + RESET);
                r = s.nextLine();
                if (r.equals(null)) {
                    System.out.println(errorInvalid("producte"));           
                } else {
                    System.out.print(AMARILLO + "\n> Preu unitari (€): " + RESET);
                    System.out.print(AMARILLO + "\n> Quantitat: " + RESET);
                    System.out.print(AMARILLO + "\n> Vols afegir un altre producte? (s/n): " + RESET);
                }
            } while (!comandaComplerta);
        }
        return comanda;
    }

    public String errorInvalid(String text) {
        String missatge = ROJO+"(!) Sisplau introdueix un "+text+" vàlid."+RESET;
        return missatge;
    }
}
