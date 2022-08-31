import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.* ;
public class Stock {
    Connection con;
    ArrayList<Article> articles = new ArrayList<Article>();
    Scanner S = new Scanner(System.in);
    //constructeurs
    Stock()
    {
        System.out.println("constructeur par defaut stock");
    }
    void ajouterArticle()
    {
        try{
            Article A = new Article();
            articles.add(A);

            String sql = "INSERT INTO article (reference, designation, prixHT, tauxTVA) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, A.reference);
            statement.setString(2, A.designation);
            statement.setInt(3, A.prixHT);
            statement.setInt(4, A.tauxTVA);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new article was inserted successfully!");
            }

        }  catch (Exception e) {
            System.out.println("Erreur "+e);
            // gestion des exceptions
        }


    }
    void modifierArticle()
    {
        System.out.println("reference Article :");
        String ref = S.next();
        Article A = new Article();
            
        try{
        String sql = "UPDATE article SET designation=?, prixHT=?, tauxTVA=? WHERE reference=?";
 
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, A.designation);
            statement.setInt(2, A.prixHT);
            statement.setInt(3, A.tauxTVA);
            statement.setString(4, ref);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Article was updated successfully!");
            }else{
                System.out.println("Failed :(")  ;
            }
        }  catch (Exception e) {
            System.out.println("Erreur "+e);
            // gestion des exceptions
        }

    }
    void supprimerArticle()
    {
        System.out.println("reference Article :");
        String ref = S.next();

        try{
        String sql = "DELETE FROM Article WHERE reference=?";
 
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ref);
         
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A article was deleted successfully!");
        }else{
            System.out.println("Failed :(");
        }
    }  catch (Exception e) {
        System.out.println("Erreur "+e);
        // gestion des exceptions
    }
    }
    void afficher()
    {
        try{
        System.out.println("designation reference prixHT tauxTVA PrixTTC");

        String sql = "SELECT * FROM article";
 
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
         
        int count = 0;
         
        while (result.next()){
            String reference = result.getString(1);
            String designation = result.getString(3);
            int prixHT = result.getInt(2);
            int tauxTVA = result.getInt(4);
         
            String output = "article #%d: %s - %s - %d - %d";
            System.out.println(String.format(output, ++count, reference, designation, prixHT, tauxTVA));
        }
    }  catch (Exception e) {
        System.out.println("Erreur "+e);
        // gestion des exceptions
    }
        
    }
    int menu(){
        System.out.println("**************************************");
        System.out.println("*bienvenue app gestion articles      *");
        System.out.println("1 : ajouter article");
        System.out.println("2 : modifier article");
        System.out.println("3 : supprimer article");
        System.out.println("4 : afficher articles");
        System.out.println("5 : quitter");
        System.out.println("**************************************");
        System.out.println("donner votre choix :");
        int choix = S.nextInt();
        return choix;
    }
    void makeConnection()
    {
        
        try {
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
             // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
             // Nom de la base ;
            String nomBase =  "stock" ;  // dépend du contexte
             // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
             // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "" ;  // dépend du contexte
             // Connexion
            con = DriverManager.getConnection(
               conString, nomConnexion, motDePasse) ;
            
            

         }  catch (Exception e) {
            System.out.println("Erreur "+e);
             // gestion des exceptions
         }
        
       
    }
    
    public static void main (String args[])
    {
        int choix=0;
        
        Stock stock = new Stock();
        stock.makeConnection();
        do
        {
            choix = stock.menu();
            switch (choix){
                case 1 :
                    stock.ajouterArticle();
                    break;
                case 2 :
                    stock.modifierArticle();
                    break;
                case 3 :
                    stock.supprimerArticle();
                    break;
                case 4 :
                    stock.afficher();;
                    break;
                default :
                    break;
            }
        }while (choix != 5);
        
    }
    
}
