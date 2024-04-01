import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Entreprise {

    JDBConnexion crud = new JDBConnexion();

    Scanner sc = new Scanner(System.in);

    //Ajouter un employé

    public void addEmploye(Employe e) {
        System.out.println();
        System.out.println("\t\t\t\t\t[*][*][*][*] AJOUT D'UN NOUVEL EMPLOYE 😉[*][*][*][*]");
        System.out.print("[*] Donner le prenom de l'employe : ");
        e.setPrenom(sc.nextLine());
        System.out.print("[*] Donner le nom de l'employe : ");
        e.setNom(sc.nextLine());
        System.out.print("[*] Donner la matricule de l'employe : ");
        e.setMatricule(sc.nextLine());
        System.out.print("[*] Donner la fonction de l'employe : ");
        e.setFonction(sc.nextLine());
        System.out.print("[*] Donner le salaire brut de l'employe : ");
        e.setSalaire_brut(sc.nextDouble());
        System.out.print("[*] Donner le nombre d'enfant de l'employe : ");
        e.setNbre_enfants(sc.nextInt());
        System.out.println("[*] Calcul de la prime ...");
        e.calculer_primes();
        System.out.println("[*] Calcul du retenu ...");
        e.calculer_retenues();
        System.out.println("[*] Calcul du salaire Net ...");
        e.calculer_salaire_net();

        String requete = "insert into employes(prenoms, noms, matricules, fonctions, salairesBrut, nbrEnfants, primes, retenus, salairesNet) values ('"+e.getPrenom()+"', '"+e.getNom()+"', '"+e.getMatricule()+"', '"+e.getFonction()+"', '"+e.getSalaire_brut()+"', '"+e.getNbre_enfants()+"', '"+e.getPrimes()+"', '"+e.getRetenues()+"', '"+e.getSalaire_net()+"')";

        crud.traitementRequete(requete);
        System.out.println("Employé ajouté avec succès");

    }


    //Recuperation d'un employe

//    public Employe getCreateEmployee(String matriculeRecup) {
//        String matricule = null;
//        String nom = null;
//        String prenom = null;
//        String fonction = null;
//        double salaire_brut = 0;
//        double primes = 0;
//        double retenues = 0;
//        double salaire_net = 0;
//        int nbrEnfs = 0;
//
//        Employe employe = null;
//
//        try {
//            Connection connexion = crud.seConnecter();
//            Statement statement = connexion.createStatement();
//            ResultSet resultat = statement.executeQuery("SELECT * from employes WHERE matricules=\"" + matriculeRecup + "\"");
//            while (resultat.next()){
//                matricule = resultat.getString("matricules");
//                nom = resultat.getString("noms");
//                prenom = resultat.getString("prenoms");
//                fonction = resultat.getString("fonctions");
//                nbrEnfs = resultat.getInt("nbrEnfants");
//                salaire_brut = resultat.getDouble("salairesBrut");
//                primes = resultat.getDouble("primes");
//                retenues = resultat.getDouble("retenus");
//                salaire_net = resultat.getDouble("salairesNet");
//            }
//            crud.seDeconnecter(connexion);
//        } catch (SQLException e) {
//            System.out.println("Erreur : " + e.getMessage());
//        }
//        return new Employe(matricule,  prenom, nom, salaire_brut, fonction,  nbrEnfs, salaire_net, primes, retenues);
//    }


    public Employe getCreateEmploye(String matriculeRecup) {
        Employe employe = null;

        try (Connection connection = crud.seConnecter();
             Statement statement = connection.createStatement();
             ResultSet resultat = statement.executeQuery("SELECT * FROM employes WHERE matricules = '" + matriculeRecup + "'")) {

            if (resultat.next()) {
                employe = new Employe(
                        resultat.getString("matricules"),
                        resultat.getString("noms"),
                        resultat.getString("prenoms"),
                        resultat.getDouble("salairesBrut"),
                        resultat.getString("fonctions"),
                        resultat.getInt("nbrEnfants"),
                        resultat.getDouble("primes"),
                        resultat.getDouble("retenus"),
                        resultat.getDouble("salairesNet")
                );
            }

        } catch (SQLException ex) {
            System.out.println("[!] Erreur lors de la recherche de l'employé : " + ex.getMessage());
        }

        return employe;
    }



    //Modification Salaire d'un employe

    public void modifier_salaire() {
        System.out.println();
        System.out.println("[*][*][*][*][*] MODIFIER LE SALAIRE D'UN EMPLOYE 😏[*][*][*][*][*]");
        System.out.print("[*] Indiquer la matricule de l'employe dont vous voulez modifier le salaire : ");
        String matriculeModif = sc.nextLine();
        Employe employe = getCreateEmploye(matriculeModif);
        if (employe == null) {
            System.out.println("[!] Employé avec la matricule " + matriculeModif + " introuvable.");
            return;
        }
        System.out.println();
        System.out.print("[*] Donner le nouveau salaire : ");
        employe.setSalaire_brut(sc.nextDouble());
        System.out.println("[*] Mis à jour primes ...");
        employe.calculer_primes();
        System.out.println("[*] Mis à jour retenues ...");
        employe.calculer_retenues();
        System.out.println("[*] Mis à jour salaire net ...");
        employe.calculer_salaire_net();
        String requete = "update employes set salairesBrut="+employe.getSalaire_brut()+",primes="+employe.getPrimes()+",retenus="+employe.getRetenues()+",salairesNet="+employe.getSalaire_net()+" WHERE matricules='"+employe.getMatricule()+"'";
    }

    // Recherche d'un employe en utilisant sa matricule

    public void rechercher_employe() {
        System.out.println();
        System.out.println("[*][*][*][*][*] RECHERCHE D'UN EMPLOYE 🧐[*][*][*][*][*]");
        System.out.print("[*] Donner la matricule de l'employe dont vous voulez afficher les infos : ");
        String matricule_recherche = sc.nextLine();
        crud.rechercher_Employe(matricule_recherche);
        System.out.println();

    }

    //Affichage de la masse salariale

    public void afficher_masseSalariale() {
        crud.calculer_masse_salariale();
        System.out.println();
    }

    //Affichage des employes

    public void afficher_listeEmployes() {
        System.out.println();
        System.out.println("[*][*][*][*][*] Liste des Employes de L'entreprise DSTI2C 🤩[*][*][*][*][*]");
        crud.afficher_employe();
    }


    // Suppression d'un employe

    public void supprimr_employe() {
        System.out.println();
        System.out.println("[*][*][*][*] Supprimer un employe 😥[*][*][*][*]");
        System.out.println("[*] Donner le matricule de l'employe ");
        String suppr = sc.nextLine();
        String requete = "delete from employes where matricules ='" +suppr+"'";
        crud.traitementRequete(requete);
        System.out.println("[*][*] Suppression ...");
        System.out.println();
    }







}
