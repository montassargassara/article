import java.util.ArrayList;
import java.util.Scanner;
public class Stock {
    ArrayList<Article> articles = new ArrayList<Article>();
    Scanner S = new Scanner(System.in);
    //constructeurs
    Stock()
    {
        System.out.println("constructeur par defaut stock");
    }
    void ajouterArticle()
    {
        Article A = new Article();
        articles.add(A);
    }
    void modifierArticle()
    {
        System.out.println("reference Article :");
        String ref = S.next();
        int i=0;
        
        while (i < articles.size()){
            if( articles.get(i).reference.equals(ref))
                break;
            i++;

        }
        if (i==articles.size())
            System.out.println("l'article n'existe pas");
        else{
            articles.get(i).modifier();
            System.out.println("article modifie");    
        }  
    }
    void supprimerArticle()
    {
        System.out.println("reference Article :");
        String ref = S.next();
        int i=0;
        
        while (i < articles.size()){
            if( articles.get(i).reference.equals(ref))
                break;
            i++;

        }
        if (i==articles.size())
            System.out.println("l'article n'existe pas");
        else{
            articles.remove(i);
            System.out.println("article supprime"); 
        }   
    }
    void afficher()
    {
        System.out.println("designation reference prixHT tauxTVA PrixTTC");
        for(int i = 0 ; i<articles.size();i++)
        {
            articles.get(i).afficher();

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
    public static void main (String args[])
    {
        int choix=0;
        Stock stock = new Stock();
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
