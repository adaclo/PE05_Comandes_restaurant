package Activitats.PE05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PE05_AcarretaAdrian {

    // COLORS
    final String RESET = "\u001B[0m";
    final String ROJO = "\u001B[31m";
    final String VERDE = "\u001B[32m";
    final String AMARILLO = "\u001B[33m";
    final String AZUL = "\u001B[34m";
    String r; // VARIABLE PER INPUTS
    
    Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        PE05_AcarretaAdrian p = new PE05_AcarretaAdrian();
        p.principal();
    }
    public void principal() {
        String comanda="";
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
                comanda=crearComanda(comanda);
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

    public String crearComanda(String comanda) {
        String producte;
        System.out.println("\n------------------------------------");
        System.out.println("=========== NOVA COMANDA ===========");
        System.out.println("------------------------------------\n");
        System.out.print(AMARILLO + "\n> Introdueix el nom del client: " + RESET);
        String nom = s.next();
        comanda="\n------------------------------------\n============= TIQUET =============\n------------------------------------\nClient: "+nom+"\n\nProducte\tQuantitat\tPreu unit.\tSubtotal\n------------------------------------";
        r="n";
        do {
            s.nextLine();
            producte = producteFormatat();
            System.out.print(AMARILLO + "\n> Preu unitari (€): " + RESET);
            String preuUnitatString = s.next();
            try {
                double preuUnitat = Double.parseDouble(preuUnitatString);
                System.out.print(AMARILLO + "\n> Quantitat: " + RESET);
                String quantitatString = s.next();
                int quantitat = Integer.parseInt(quantitatString);
                double subtotal=quantitat*preuUnitat;
                System.out.print(AMARILLO + "\n> Vols afegir un altre producte? (s/n): " + RESET);
                r = s.next();
                comanda=comanda+"\n"+producte+"\t"+quantitatString+"\t\t"+preuUnitatString+"\t\t"+subtotal;
            } catch (InputMismatchException e) {
                System.out.println(ROJO+"\n(!) Sisplau introdueix un número vàlid"+RESET);
                break;
            } catch (NumberFormatException e) {
                System.out.println(ROJO+"\n(!) Sisplau introdueix un número vàlid"+RESET);
                break;
            } catch (Exception e) {
                System.out.println(ROJO+"\nERROR DESCONEGUT: "+e+RESET);
                break;
            }
        } while (!r.equalsIgnoreCase("n"));
        System.out.println(AMARILLO+"\nS'està generant el tiquet..."+RESET);
        imprimirTiquet(comanda);
        return comanda;
    }

    public void imprimirTiquet(String comanda) {
        System.out.println(comanda);
    }

    public String producteFormatat() {
        String nomFormatat="";
        Boolean validName=false;
        do {
            System.out.print(AMARILLO + "\n> Introdueix el producte: " + RESET);
            String r = s.nextLine();
            if (r.length()<16) {
                nomFormatat=r;
                int limit = 16-r.length();
                for (int i=0;i<=limit;i++) {
                    nomFormatat=nomFormatat+" ";
                    System.out.println(nomFormatat.length());
                }
                validName=true;
            } else {
                System.out.println(ROJO+"(!) Sis plau introdueix un nom de producte de menys de 16 caràcters"+RESET);
            }
        } while (!validName);
        return nomFormatat;
    }
}
