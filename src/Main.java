import Systeme.Systeme;
import java.util.*;

import static Systeme.Systeme.inviteQuestion;

/**
 * Class plrincipal du système, qui s'occupe de gerer l'interaction avec l'tulisateur.
 * Gère plusieurs menus en fonction des choix utilisateurs, indiqués par des numéros.
 */
public class Main {

    public static void main(String[] args){
        String arret = "";
        Systeme systeme = new Systeme();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println(" Gestionnaire de formulaires ");
            System.out.println();
            System.out.println("___________--[MENU]--__________");
            System.out.println("|| 1. Gérer les formulaires  ||");
            System.out.println("|| 2. Rechercher un étudiant ||");
            System.out.println("|| 3. Statistiques           ||");
            System.out.println("|| 4. Arret du programme     ||");
            System.out.println("--___________________________--");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> {
                    System.out.println("___________--[Gérer les formulaires]--___________");
                    System.out.println("|| 1. Ajouter un formulaire                    ||");
                    System.out.println("|| 2. Modifier un formulaire                   ||");
                    System.out.println("|| 3. Retirer un formulaire                    ||");
                    System.out.println("|| 4. Rechercher des formulaires par étudiant  ||");
                    System.out.println("|| 5. Rechercher des formulaires par épreuve   ||");
                    System.out.println("|| 6. Revenir au menu principale               ||");
                    System.out.println("--_____________________________________________--");
                    choix = scanner.nextLine();
                    switch (choix) {
                        case "1" -> {
                            systeme.inviteFormulaire(scanner,true);
                        }
                        case "2" -> {
                            systeme.inviteFormulaire(scanner,false);
                        }
                        case "3" -> {
                            systeme.deleteFormulaire(systeme.inviteSelectionFormulaire(scanner));
                        }
                        case "4" -> {
                            systeme.searchFormulairesEtudiant(scanner);
                        }
                        case "5" -> {
                            systeme.searchFormulairesEpreuve(scanner);
                        }
                        case "6" ->{
                        }
                        default -> {
                            systeme.affichageDefault();
                        }
                    }
                }
                case "2" -> {
                    System.out.println("_-----[Rechercher un étudiant]-----_");
                    System.out.println("|| 1. Par nom                     ||");
                    System.out.println("|| 2. Par prénom                  ||");
                    System.out.println("|| 3. Par numéro apprenant        ||");
                    System.out.println("|| 4. Revenir au menu principale  ||");
                    System.out.println("--__________________________--");
                    choix = scanner.nextLine();
                    switch (choix) {
                        case "1" -> {
                            choix = inviteQuestion(scanner,"Saisir un nom à rechercher");
                            systeme.searchEtudiantsNom(choix);
                        }
                        case "2" -> {
                            choix = inviteQuestion(scanner,"Saisir un prénom à rechercher");
                            systeme.searchEtudiantsPrenom(choix);
                        }
                        case "3" -> {
                            choix = inviteQuestion(scanner,"Saisir un numéro apprenant à rechercher");
                            systeme.searchEtudiantsNumApp(choix);
                        }
                        case "4" -> {
                        }
                        default -> {
                            systeme.affichageDefault();
                        }
                    }
                }
                case "3" -> {
                    systeme.affichageStatistiques();
                }
                case "4" -> {
                    arret = inviteQuestion(scanner,"Etes vous sur de vouloir arreter le programme? (oui/non)");
                }
                default -> {
                    systeme.affichageDefault();
                }
            }
        }while(!arret.equals("oui"));
    }
}
