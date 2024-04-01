import java.util.*;

public class Menu {

        Scanner sc = new Scanner(System.in);
        Entreprise company = new Entreprise();
        Employe e = new Employe();

    public  void menu() {

        System.out.println("\t\t\t\t\t\t\t\t\t\t 😎 GESTION DES EMPLOYES DE L'ENTREPRISE DSTI2C 👌");

        //Menu

        int choix = 0;
        do {
            System.out.println("\t\t\t\t\t\t\t\t+----------------------------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t\t\t|            [*][*]😀             MENU            😀[*][*]             |");
            System.out.println("\t\t\t\t\t\t\t\t+----------------------------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t\t\t| 1. Ajouter un employé                                             [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 2. Afficher la liste des employés                                 [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 3. Modifier le salaire d'un employé                               [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 4. Supprimer un employé                                           [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 5. Rechercher un employé et afficher ses informations             [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 6. Afficher la masse salariale de l'entreprise DSTI2C             [*]|");
            System.out.println("\t\t\t\t\t\t\t\t| 7. Quitter                                                        [*]|");
            System.out.println("\t\t\t\t\t\t\t\t+-------------------------------------------------------------------[*]+");
            System.out.println();

            try {
                System.out.print("[*] Veuillez sélectionner une option dans la liste déroulante ☺️: ");
                choix = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez choisir un entier ");
            }

            switch (choix) {

                case 1:
                    company.addEmploye(e);
                    break;

                case 2:
                    company.afficher_listeEmployes();
                    break;

                case 3:
                    company.modifier_salaire();
                    break;

                case 4:
                    company.supprimr_employe();
                    break;

                case 5:
                    company.rechercher_employe();
                    break;

                case 6:
                    company.afficher_masseSalariale();
                    break;

                case 7:
                    System.out.println("[*] Merci d'avoir utilisé notre application 😃 ");

                    break;

                default:
                    System.out.println("Choix invalide 🫥, Veillez réesayer");
            }


        } while (choix != 7) ;
    }
}
