package outil;

import modele.Scenario;

import java.io.*;
import java.util.*;

public class LectureEcriture {

    public LectureEcriture(File parFile) {

    }

    //////////////////////////Acheteur, vendeur///////////////////////////////////////
    public static Scenario lectureScenario(File fichier) throws IOException {
        Scenario scenario = new Scenario();
        TreeMap membre = lectureMembre(new File("Ressources" + File.separator + "membres_APLI.txt")); /*A utiliser*/
        Set<String> setVilles = new HashSet<>();                    //Un set pour récuperer toutes les villes
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;

        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ->");
                String tokenV = tokenizer.nextToken();
                String tokenA = tokenizer.nextToken();


                //AJOUT VENDEURS/ ACHETEURS/ VILLES DANS LE SCENARIO
                scenario.ajoutVendeurAcheteur(tokenA, tokenV);               //Ajout acheteur et vendeur dans les deux listes dédiées
                setVilles.add((String) membre.get(tokenV));                 //Récupérer la villes du vendeur mis dans un set
                setVilles.add((String) membre.get(tokenA));                 //Récupérer la villes de l'acheteur mis dans un set

                //AJOUT DES ELEMENTS DANS LES LISTES DES DEGRES LE ET LS
                scenario.ajoutLe((String) membre.get(tokenA), (String) membre.get(tokenV));
                scenario.ajoutLe((String) membre.get(tokenV));
                scenario.ajoutLs((String) membre.get(tokenV), (String) membre.get(tokenA));
                scenario.ajoutLs((String) membre.get(tokenA));
            }
        }
        while (ligne != null);
        bufferEntree.close();
        scenario.ajoutVilles(setVilles);
        return scenario;
    }
    //////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    public static void ecritureScenario(String nomFichier, Scenario scenario) throws IOException {          //Pour l'ecriture d'un nouveau scenario
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) {                 //On parcours les vendeurs
            sortie.println(vendeur + " -> " + scenario.getAcheteurs().get(i));
            i++;
        }
        sortie.close();
    }

    ///////////////////////////////////////DISTANCE//////////////////////////////////
    public static String[][] lectureDistance(File ressources) throws IOException {
        String[][] tabDistances = new String[100][100];
        BufferedReader bufferEntree = new BufferedReader(new FileReader(ressources));
        String ligne;
        StringTokenizer tokenizer;
        int i = 0;
        do {
            ligne = bufferEntree.readLine(); // On selectionne la
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String distance = tokenizer.nextToken(); // Prend le premier token qui correspond au nom de la ville
                int j = 0;
                while (tokenizer.hasMoreTokens()) {
                    distance = tokenizer.nextToken();   // Prend le token suivant la ville. La première distance de la ville
                    tabDistances[i][j] = distance;
                    j++;
                }
                i++;
            }
        }
        while (ligne != null);
        bufferEntree.close();
        return tabDistances;
    }
    //////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////VILLE, INDICE//////////////////////////////////
    public static TreeMap numVille(File ressources) throws IOException {            //Stocké les villes et leurs indices
        TreeMap<String, Integer> numVille = new TreeMap<>();                        //Map pour les villes, indices
        //String [][] tabDistances = lectureDistance(new File("Ressources" + File.separator + "distances.txt"));
        BufferedReader bufferEntree = new BufferedReader(new FileReader(ressources));
        String ligne;
        StringTokenizer tokenizer;

        int i = 0;                          //Pour l'indice
        do {
            ligne = bufferEntree.readLine(); // On selectionne la
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String nomVille = tokenizer.nextToken();                      // Prend le premier token qui correspond au nom de la ville
                numVille.put(nomVille, i);                                    //On ajoute la ville et sont indice
                while (tokenizer.hasMoreTokens()) {
                    nomVille = tokenizer.nextToken();                         // Prend le token suivant la ville. La première distance de la ville
                }
                i++;                    //On avance de 1
            }
        }
        while (ligne != null);
        bufferEntree.close();
        return numVille;
    }
    //////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////Membre,Ville//////////////////////////////////
    public static TreeMap lectureMembre(File fichier) throws IOException {              //Lire les membres avec leur ville
        TreeMap<String, String> membresMap = new TreeMap<>();                           //Une map pour les membres , ville
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;

        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String tokenM = tokenizer.nextToken();                              //Prend le membre
                String tokenV = tokenizer.nextToken();                              //Prend la ville
                membresMap.put(tokenM, tokenV);                                     //Ajout dans la map le membre et la ville
            }
        }
        while (ligne != null);
        bufferEntree.close();

        return membresMap;
    }
    //////////////////////////////////////////////////////////////////////////////

    /////////////////Main test//////////////////////////////
    public static void main(String[] args) throws IOException {
        Scenario scenario = lectureScenario(new File("Ressources" + File.separator + "scenario_0.txt"));
        TreeMap membres = lectureMembre(new File("Ressources" + File.separator + "membres_APLI.txt"));
        TreeMap indiceVilles = numVille(new File("Ressources" + File.separator + "distances.txt"));
        String[][] distance = lectureDistance(new File("Ressources" + File.separator + "distances.txt"));

    }
}
