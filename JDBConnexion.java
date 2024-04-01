import java.sql.*;
import java.util.ArrayList;

public class JDBConnexion {

        private String url = "jdbc:mysql://localhost:3306/entreprise";
        private String user = "root";
        private String password = "";


        //Connexion à la base de données
        public Connection seConnecter() throws SQLException {
            Connection connection = DriverManager.getConnection(url,user,password);
            return connection;
        }

        //Traitement des requêtes

    public void traitementRequete(String requete) {
        try (Connection connexion = seConnecter();
             PreparedStatement requetePrepare = connexion.prepareStatement(requete)) {
            requetePrepare.execute();
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    //Recherche employé

        public void rechercher_Employe(String matricul) {
            try {

                Connection connection = seConnecter();
                Statement statement = connection.createStatement();
                String query = "select * from employes where matricules= \""+matricul+"\"";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    System.out.println();
                    System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+------------+--------------+");
                    System.out.println("| Matricule  | Nom            | Prénom         | Salaire Brut | Fonction        | Nombre Enfants  | Prime      | Retenue    | Salaire Net  |");
                    System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+------------+--------------+");
                    do {
                        System.out.printf("| %-10s | %-14s | %-14s | %-12.2f | %-15s | %-15d | %-10.2f | %-10.2f | %-12.2f |\n",
                                resultSet.getString("matricules"),
                                resultSet.getString("noms"),
                                resultSet.getString("prenoms"),
                                resultSet.getDouble("salairesBrut"),
                                resultSet.getString("fonctions"),
                                resultSet.getInt("nbrEnfants"),
                                resultSet.getDouble("primes"),
                                resultSet.getDouble("retenus"),
                                resultSet.getDouble("salairesNet"));
                    } while (resultSet.next());
                    System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+------------+--------------+");
                } else {
                    System.out.println("Aucun employé trouvé avec le matricule : " + matricul);
                }} catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    //Affichage liste des employés

        public void afficher_employe() {
            try {
                String query;
                Connection connection = seConnecter();
                Statement statement = connection.createStatement();
                query = "select * from employes";
                ResultSet resultSet = statement.executeQuery(query);
                System.out.println();
                System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+--------------+----------------+");
                System.out.println("| Matricule  | Nom            | Prenom         | Salaire Brut | Fonction        | Nombre Enfants  | Prime      | Retenu       | Salaire Net    |");
                System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+--------------+----------------+");

                while (resultSet.next()) {

                  System.out.printf("| %-10s | %-14s | %-14s | %-12.2f | %-15s | %-15d | %-10.2f | %-12.2f | %-14.2f |\n",resultSet.getString("matricules"),resultSet.getString("noms"),resultSet.getString("prenoms"),resultSet.getDouble("salairesBrut"),resultSet.getString("fonctions"),resultSet.getInt("nbrEnfants"),resultSet.getDouble("primes"),resultSet.getDouble("retenus"),resultSet.getDouble("salairesNet"));
                }
                System.out.println("+------------+----------------+----------------+--------------+-----------------+-----------------+------------+--------------+----------------+");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    //Calcul de la masse salariale

        public void calculer_masse_salariale() {
            double somme = 0;
            try {

                Connection connection = seConnecter();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT salairesNet from employes");
                while (resultSet.next()) {
                    somme +=  resultSet.getDouble("salairesNet");
                }
                System.out.printf("La masse salariale de l'entreprise est de : %.1f\n" ,somme);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    // Fermeture de la connexion

        public void seDeconnecter(Connection connection) throws SQLException {
            connection.close();
        }
}



















/*             // Créer une ArrayList pour stocker les données
            ArrayList<String[]> data = new ArrayList<>();

            String[] header = {"ID", "Matricule", "Prenom", "Nom", "Salaire Brut", "Fonction","Nombre Enfants", "Salaire Net"};
            data.add(header);

            while (resultSet.next()) {
                String[] rowData = {
                        String.valueOf(resultSet.getInt(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                };
                data.add(rowData);
            }


            for (String[] row : data) {
                System.out.format("| %3s | %-15s | %-15s | %-10s |\n", row);
            }
 */