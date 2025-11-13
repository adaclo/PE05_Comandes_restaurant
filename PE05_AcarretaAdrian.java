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
    final int IVA = 10;
    String r; // VARIABLE PER INPUTS
    double totalProductes;
    double IVAperAplicar;
    double totalNet;

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
                comanda=afegirProductes(comanda);
                break;
            case "3":
                imprimirTiquet(comanda);
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
        Boolean validName=false;
        System.out.println("\n------------------------------------");
        System.out.println("=========== NOVA COMANDA ===========");
        System.out.println("------------------------------------\n");
        do {
            System.out.print(AMARILLO + "\n> Introdueix el nom del client: " + RESET);
            String nom = s.next();
            if (nom.length()>24 || nom.length()<1) {
                System.out.println(ROJO+"(!) Sisplau introdueix un nom amb 1-24 caràcters"+RESET);
            } else {
                validName=true;
                comanda="\n------------------------------------------------------------------------\n============================== TIQUET ==============================\n------------------------------------------------------------------------\nClient: "+nom+"\n\nProducte\tQuantitat\tPreu unit.\tSubtotal\n------------------------------------------------------------------------";
                r="n";
                comanda=afegirProductes(comanda);     
            }
        } while(!validName);
        return comanda;
    }

    public String afegirProductes(String comanda) {
        if (!comanda.equals("")) {
            Boolean comandaCompletada=false;
            Boolean validOpt=false;
            String producte;
                do {
                    s.nextLine();
                    producte = producteFormatat();
                    
                    try {
                        System.out.print(AMARILLO + "\n> Preu unitari (0.01-999999): " + RESET);
                        String preuUnitatString = s.next();
                        double preuUnitat = Double.parseDouble(preuUnitatString);
                        if (preuUnitat>999999 || preuUnitat<0.01) {
                            System.out.println(ROJO+"\n(!) Sisplau introdueix un número entre 0.01 i 999999"+RESET);
                        } else {
                            System.out.print(AMARILLO + "\n> Quantitat (1-999): " + RESET);
                            String quantitatString = s.next();
                            int quantitat = Integer.parseInt(quantitatString);
                            if (quantitat>999 || quantitat<1) {
                                System.out.println(ROJO+"\n(!) Sisplau introdueix un número entre 1 i 999"+RESET);
                            } else {
                                double subtotal=quantitat*preuUnitat;
                                totalProductes=totalProductes+subtotal;
                                IVAperAplicar=totalProductes*0.10;
                                totalNet=totalProductes+IVAperAplicar;
                                do {
                                    System.out.print(AMARILLO + "\n> Vols afegir un altre producte? (s/n): " + RESET);
                                    r = s.next();
                                    if (r.equalsIgnoreCase("s") || r.equalsIgnoreCase("n")) {
                                        validOpt=true;
                                    } else {
                                        System.out.println(ROJO+"\n(!) Sisplau introdueix una opció vàlida"+RESET);
                                    }
                                } while (validOpt==false);
                                comanda=comanda+"\n"+producte+"\t"+quantitat+"\t\t"+String.format("%.2f", preuUnitat)+" $\t\t"+String.format("%.2f", subtotal)+" $";
                                comandaCompletada=true;
                            }
                        }
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
            
            if (comandaCompletada) {
                imprimirTiquet(comanda);
            }
        } else {
            System.out.println(ROJO+"(!) Sisplau primer crea una comanda per utilitzar això\n"+RESET);
        }
        return comanda;
    }

    public void imprimirTiquet(String comanda) {
        if (comanda.equals("")) {
            System.out.println(ROJO+"(!) Sisplau primer crea una comanda per utilitzar això\n"+RESET);
        } else {
            comanda=comanda+"\n------------------------------------------------------------------------"+"\nTotal sense IVA:\t\t\t\t\t"+String.format("%.2f", totalProductes)+" $\nIVA (10%):      \t\t\t\t\t"+String.format("%.2f", IVAperAplicar)+" $\nTOTAL A PAGAR:  \t\t\t\t\t"+String.format("%.2f", totalNet)+" $\n========================================================================\n";
            System.out.println(AMARILLO+"\nS'està generant el tiquet..."+RESET);
            System.out.println(comanda);
        }
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
                for (int i=0;i<limit;i++) {
                    nomFormatat=nomFormatat+" ";
                }
                validName=true;
            } else {
                System.out.println(ROJO+"(!) Sisplau introdueix un nom de producte de menys de 16 caràcters"+RESET);
            }
        } while (!validName);
        return nomFormatat;
    }
}
