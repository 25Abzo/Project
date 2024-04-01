import java.util.*;
public class Employe {
    private String matricule;
    private String prenom;
    private String nom;
    private double salaire_brut;
    private String fonction;
    private int nbre_enfants;
    private double salaire_net;
    private double primes;
    private double retenues;

    //Constructeur sans paramètre

    public Employe() {
    }

    //Constructeur avec paramètres

    public Employe(String matricule, String prenom, String nom, double salaire_brut, String fonction, int nbre_enfants, double salaire_net, double primes, double retenues) {
        this.matricule = matricule;
        this.prenom = prenom;
        this.nom = nom;
        this.salaire_brut = salaire_brut;
        this.fonction = fonction;
        this.nbre_enfants = nbre_enfants;
        this.salaire_net = salaire_net;
        this.primes = primes;
        this.retenues = retenues;
    }


    //getteurs

    public String getMatricule() {
        return matricule;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire_brut() {
        return salaire_brut;
    }

    public String getFonction() {
        return fonction;
    }

    public int getNbre_enfants() {
        return nbre_enfants;
    }

    public double getSalaire_net() {
        return salaire_net;
    }

    public double getPrimes() {
        return primes;
    }

    public double getRetenues() {
        return retenues;
    }

    //setteurs

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalaire_brut(double salaire_brut) throws IllegalArgumentException {
        if (salaire_brut < 0.0) {
            throw new IllegalArgumentException("Le salaire brut doit être positif");
        }
        this.salaire_brut = salaire_brut;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public void setNbre_enfants(int nbre_enfants) {
        this.nbre_enfants = nbre_enfants;
    }

    public void setSalaire_net(double salaire_net) {
        this.salaire_net = salaire_net;
    }

    public void setPrimes(double primes) {
        this.primes = primes;
    }

    public void setRetenues(double retenues) {
        this.retenues = retenues;
    }


    //Methode pour calculer les primes

    public void calculer_primes() {
//        double prime = 0.0;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Entrez la fonction de l'employé (COMPTABLE, TECHNICIEN, SECRETAIRE, COMMERCIAL, Autre) :");
//        fonction = sc.nextLine();

//        if (fonction == null || fonction.isEmpty()) {
//            throw new IllegalArgumentException("La fonction ne doit pas être vide");
//        }

        switch (fonction) {
            case "Comptable":
            case "Technicien":
                setPrimes(getSalaire_brut() * 0.1);
                break;
            case "Secretaire":
                setPrimes(getSalaire_brut() * 0.05);
                break;
            case "Commercial":
                setPrimes(getSalaire_brut() * 0.07);
                break;
            default:
                setPrimes(getSalaire_brut() * 0.02);

        }

        //Affichage de la prime
//        System.out.println("La prime est de : " + prime);

    }

    //Méthode pour calculer les retenues

    public void calculer_retenues () {
//        double retenues = 0.0;
//
//        if (nbre_enfants < 0) {
//            throw new IllegalArgumentException("Le nombre d'enfants doit être positif");
//        }

        switch (nbre_enfants) {
            case 0:
            case 1:
            case 2:
                setRetenues(getSalaire_brut() * 0.05);
                break;
            case 3:
            case 4:
                setRetenues(getSalaire_brut() * 0.03);
                break;
            default:
                setRetenues(getSalaire_brut() * 0.015);
        }



        //Affichage de la retenue
//        System.out.println("La retenue est de : " + retenues);
    }

    //Methode pour calculer le salaire net

    public void calculer_salaire_net() {
        double mts_salaire = (getSalaire_brut() + getPrimes() - getRetenues());
        setSalaire_net(mts_salaire);
    }


}
