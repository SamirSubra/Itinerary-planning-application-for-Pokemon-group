package modele;

import javafx.scene.control.Label;
import outil.LectureEcriture;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static outil.LectureEcriture.lectureScenario;

public class Scenario {
    List<String> vendeurs;
    List<String> acheteurs;
    List<String> villes;
    TreeMap<String, ArrayList> le = new TreeMap<String, ArrayList>(); //Liste avec comme clé une ville et valeur une liste de ses villes entrantes
    TreeMap<String, ArrayList> ls = new TreeMap<String, ArrayList>(); //Liste avec comme clé une ville et valeur une liste de ses villes sortants

    public Scenario() {
        vendeurs = new ArrayList<>();
        acheteurs = new ArrayList<>();
        villes = new ArrayList<>();
    }

    //Méthodes d'ajouts
    public void ajoutVendeurAcheteur(String vendeur, String acheteur) {  //Méthode utiliser pour l'ajout des vendeurs et acheteurs dans les listes, utilisée dans lecturescenario
        vendeurs.add(vendeur);
        acheteurs.add(acheteur);
    }

    public void ajoutVilles(Set<String> setV) {     //Methode pour ajouter des villes, utlisée dans lecturescenario
        for (String ville : setV) {                 //Va parcourir un set avec toutes les villes
            villes.add(ville);                      //ajoute les villes dans une list (villes)
        }
    }

    ////Nous créons dans cette méthode pour créer une treemap avec les villes entrant car nous allons avoir besoin pour trouver l'itinéraire une méthode faites par la suite
    public void ajoutLe(String parCle, String parVille) {           //Methode pour creer des listes avec les villes entrants, avec des villes en paramètre utilisé dans lecturescenario
        if (le.containsKey(parCle)) {                               //Si la treemap "le" contient la clé récuperer dans lecturescenario
            ArrayList<String> elementsLe = new ArrayList<>();       //On creer une nouvelle liste pour les villes entrant
            elementsLe = le.get(parCle);                            //On renomme la liste avec la clé de la treemap "le"
            elementsLe.add(parVille);                               //On ajoute sa ville entrant à cette liste
            le.put(parCle, elementsLe);                             //On ajoute la clé (une ville) puis la liste avec les villes entrants dans la treemap "le" <String,Arraylist>
        } else {
            ArrayList<String> listeVilles = new ArrayList<>();      //Sinon on créer un nouvelle liste
            listeVilles.add(parVille);                              //On ajoute les villes entrants à la liste
            le.put(parCle, listeVilles);                            //On ajoute la clé (une ville) puis la liste avec les villes entrants dans la treemap "le" <String,Arraylist>
        }
    }

    public void ajoutLe(String parCle) {
        if (le.containsKey(parCle) == false) {
            ArrayList<String> listeVilles = new ArrayList<>();
            le.put(parCle, listeVilles);
        }
    }

    ////Nous créons dans cette méthode pour créer une treemap avec les villes sortantes car nous allons avoir besoin pour trouver l'itinéraire une méthode faites par la suite
    public void ajoutLs(String parCle, String parVille) {           //Methode pour creer des listes avec les villes sortantes avec des villes en paramètre utilisé dans lecturescenario
        if (ls.containsKey(parCle)) {                               //Si la treemap "ls" contient la clé récuperer dans lecturescenario
            ArrayList<String> elementsLe = new ArrayList<>();       //On creer une nouvelle liste pour les villes sortant
            elementsLe = ls.get(parCle);                            //On renomme la liste avec la clé de la treemap "ls"
            elementsLe.add(parVille);                               //On ajoute sa ville sortant à cette liste
            ls.put(parCle, elementsLe);                             //On ajoute la clé (une ville) puis la liste avec les villes sortantes dans la treemap "ls" <String,Arraylist>
        } else {
            ArrayList<String> listeVilles = new ArrayList<>();      //Sinon on créer un nouvelle liste
            listeVilles.add(parVille);                              //On ajoute les villes sortantes à la liste
            ls.put(parCle, listeVilles);                            //On ajoute la clé (une ville) puis la liste avec les villes sortantes dans la treemap "ls" <String,Arraylist>
        }
    }

    public void ajoutLs(String parCle) {
        if (ls.containsKey(parCle) == false) {
            ArrayList<String> listeVilles = new ArrayList<>();
            ls.put(parCle, listeVilles);
        }
    }


    //Créations d'un itinéraire quelconque
    public String[] itineraire() {
        //Construction de la liste des degrés entrants
        TreeMap<String, Integer> e = new TreeMap<String, Integer>();              //Création d'une treemap avec les clefs: des villes,
        Set<String> villesLe = this.le.keySet();                    //On créer un Set avec toutes les villes
        Iterator<String> parcoursLe = villesLe.iterator();          //On parcours le set des villes
        while (parcoursLe.hasNext()) {                              //Tant qu'il y a un élément
            String villeLe = parcoursLe.next();                     //On récupère chaque ville du set
            ArrayList monTableaule = this.le.get(villeLe);          //Prendre les éléments entrant de chaque ville
            e.put(villeLe, monTableaule.size());                    //Ajoute à e la ville et son nombre de degrés entrant
        }

        //Construction de l'ordre
        int n = e.size();
        String[] ordre = new String[n + 2];
        ordre[0] = "Velizy";
        ordre[n + 1] = "Velizy";                  //nous placons Velizy pour l'ordre de départ et d'arriver

        //Boucle qui cherche la source et construit l'ordre avec
        for (int i = 1; i < n + 1; i++) {
            Set<String> villes = e.keySet();                    //On créer un Set avec toutes les villes
            Iterator<String> parcours = villes.iterator();      //On créer un itérateur, on le déclare pour le parcours du set
            while (parcours.hasNext()) {                        //Tant qu'il y a un élément
                String ville = parcours.next();                 //ville va prendre toutes les villes une par une
                if (e.get(ville) == 0) {                        // si ville est égale à 0 :
                    ordre[i] = ville;                           // on ajoute ville dans la liste d'ordre
                    e.remove(ville);                            //on efface ville de e

                    //Boucle pour la décrémentation
                    ArrayList monTableau = this.ls.get(ville);          //Prendre la listes des villes sortant de chaque ville et les mets dans une liste
                    for (int y = 0; y < monTableau.size(); y++) {       //Parcours le tableau des villes sortant
                        String machin = (String) monTableau.get(y);     //On récupère chaque ville sortant
                        int truc = e.get(machin);                       //On récupère le degrés de la ville sortant
                        e.put(machin, truc - 1);                        //On décrémente de 1
                    }
                    break;
                }

            }
        }
        return ordre;
    }
    //Création du meilleure itinéraire, nous prenons comme source la ville qui est la plus proche de la ville précédente
    public String[] meilleur_itineraire() throws IOException {
        //Construction de la liste des degrés entrants
        TreeMap<String, Integer> e = new TreeMap<String, Integer>();              //Création d'une treemap avec les clefs: des villes,
        Set<String> villesLe = this.le.keySet();                    //On créer un Set avec toutes les villes
        Iterator<String> parcoursLe = villesLe.iterator();          //On parcours le set des villes
        while (parcoursLe.hasNext()) {                              //Tant qu'il y a un élément
            String villeLe = parcoursLe.next();                     //On récupère chaque ville du set
            ArrayList monTableaule = this.le.get(villeLe);          //Prendre les éléments entrant de chaque ville
            e.put(villeLe, monTableaule.size());                    //Ajoute à e la ville et son nombre de degrés entrant
        }

        //Construction de l'ordre
        int n = e.size();
        String[] ordre = new String[n + 2];
        ordre[0] = "Velizy";
        ordre[n + 1] = "Velizy";                  //nous placons Velizy pour l'ordre de départ et d'arriver
        String villeRef= "Velizy";

        //Boucle qui cherche la source et construit l'ordre avec
        for (int i = 1; i < n + 1; i++) {
            Set<String> villes = e.keySet();                    //On créer un Set avec toutes les villes
            Iterator<String> parcours = villes.iterator();      //On créer un itérateur, on le déclare pour le parcours du set
            while (parcours.hasNext()) {
                String ville = parcours.next();
                if (e.get(ville) == 0) {
                    Iterator<String> parcours2 = parcours;
                    String source = ville;
                    //Partie servant à chercher la meilleure source
                    while(parcours2.hasNext()){
                        String ville2 = parcours2.next();
                        if(ville2 != ville && e.get(ville2)==0){
                            System.out.println(ville2 + this.getDistance(ville2,villeRef) + source + this.getDistance(source,villeRef));
                            if(this.getDistance(ville2,villeRef)<this.getDistance(source,villeRef))
                                source=ville2;
                        }
                    }
                    ordre[i] = source;
                    villeRef=source;
                    e.remove(source);

                    //Boucle pour la décrémentation
                    ArrayList monTableau = this.ls.get(source);          //Prendre la listes des villes sortant de chaque ville et les mets dans une liste
                    for (int y = 0; y < monTableau.size(); y++) {       //Parcours le tableau des villes sortant
                        String machin = (String) monTableau.get(y);     //On récupère chaque ville sortant
                        int truc = e.get(machin);                       //On récupère le degrés de la ville sortant
                        e.put(machin, truc - 1);                        //On décrémente de 1
                    }
                    break;
                }

            }
        }
        return ordre;
    }

    //Retourne un String avec les ressources du scénario
    public String ressources () throws IOException {
        int tailleScenario = this.getAcheteurs().size();
        ArrayList<String> vendeurs = (ArrayList<String>) this.getVendeurs();
        ArrayList<String> acheteurs = (ArrayList<String>) this.getAcheteurs();
        String ligneRessources = "";
        for(int i = 0;i<tailleScenario;i++){
            String vendeur = vendeurs.get(i);
            String acheteur = acheteurs.get(i);
            ligneRessources += vendeur + " - " + acheteur + " / " + this.getVille(vendeur) + " - " + this.getVille(acheteur) + "\n";
        }
        return ligneRessources;
    }

    //GETTEURS
    public int getDistance(String ville1, String ville2) throws IOException {                   //Methode pour récupérer la distance entre ville, elle sera utilisée dans getDistanceTotale
        String[][] tabDistances = LectureEcriture.lectureDistance(new File("Ressources" + File.separator + "distances.txt"));       //On prend le tableau de distance entre 2 villes
        TreeMap<String,Integer> numVilles = LectureEcriture.numVille(new File("Ressources" + File.separator + "distances.txt"));    //On récupère la ville et son indice
        int distance = Integer.parseInt(tabDistances[numVilles.get(ville1)][numVilles.get(ville2)]);           //On récupère dans la treemap l'indice de la ville en paramètre, on procède pareille pour le 2eme paramètre et on récupère la distance
        return distance;
    }

    //Retourne une distance à partir d'un itinéraire
    public int getDistanceTotale(String[] itineraire) throws IOException {          //Obtenir la distance globale du parcours, on prend en paramètre l'ordre du parcours
        String [] villes = itineraire;                                              //On le récupère
        int distance = 0;                               //On met la distance à 0
        String ville1 = "Velizy";                       //On prend la première ville Vélizy
        for(int i=1;i<villes.length;i++){               //On part de 1 car la 1er ville est vélizy
            distance += this.getDistance(ville1,villes[i]);     //On ajout à la distance, les valeurs récupérer avec le getDistance de la ville d'avant à la ville d'après
            ville1 = villes[i];                 //On remplace la ville d'avant par la ville d'après
            }
        return distance;                        //On return la distance
    }

    //Retourne une ville à partir d'un membre
    public static String getVille (String membre) throws IOException {
        TreeMap membres = LectureEcriture.lectureMembre(new File("Ressources" + File.separator + "membres_APLI.txt"));
        String ville = (String) membres.get(membre);
        return ville;
    }

    public List<String> getAcheteurs() {return acheteurs;}
    public List<String> getVendeurs() {return vendeurs;}
    public List<String> getVilles() {return villes;}

    public String toString() {     //La méthode toString pour afficher les champs
        return "Les vendeurs : " + vendeurs + "\nLes acheteurs : " + acheteurs + "\nLes villes : " + villes + "\nLa liste le des elements entrant : " + le + "\nLa liste ls des elements sortants : " + ls;
    }

    public static void main(String[] args) throws IOException {
        Scenario scenario = lectureScenario(new File("Ressources" + File.separator + "Mon_scenario2.txt"));
        String[] itineraire = scenario.itineraire();
        int distance = scenario.getDistanceTotale(itineraire);

        //Afficher l'itinéraire
        System.out.println("Voici l'itineraire : ");
        for (String villes : itineraire) {
            System.out.println(villes);
        }
        System.out.println(distance);
    }
}