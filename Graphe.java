package graphe;

import java.util.*;

/**
 * Représente un graphe où les sommets sont des pixels d'une image, et les arêtes les relations de voisinage.
 */
public class Graphe {
    private final Map<Pixel, List<Arete>> listeAdjacence = new HashMap<>(); // Associe chaque pixel à une liste d'arêtes

    /**
     * Ajoute un pixel dans la liste d'adjacence du graphe.
     * Si le pixel n'est pas déjà dans la liste, il est ajouté avec une liste vide d'arêtes.
     * @param pixel à ajouter dans le graphe.
     */
    public void ajouterPixel(Pixel pixel) {
        listeAdjacence.putIfAbsent(pixel, new ArrayList<>());
    }

    /**
     * Ajoute une arête entre deux pixels avec un poids spécifié.
     * L'arête est ajoutée à la liste d'adjacence du pixel de départ.
     * @param depuis le pixel de départ de l'arête.
     * @param vers le pixel d'arrivée de l'arête.
     * @param poids de l'arête, représentant la différence d'intensité.
     */
    public void ajouterArete(Pixel depuis, Pixel vers, int poids) {
        listeAdjacence.get(depuis).add(new Arete(depuis, vers, poids));
    }

    /**
     * Retourne la liste des arêtes sortantes d'un pixel donné.
     * Si le pixel n'a pas d'arêtes, une liste vide est retournée.
     * @param pixel dont on veut récupérer les arêtes sortantes.
     * @return la liste des arêtes du pixel.
     */
    public List<Arete> getAretes(Pixel pixel) {
        return listeAdjacence.getOrDefault(pixel, new ArrayList<>());
    }

    /**
     * Retourne la liste d'adjacence complète du graphe.
     * @return La map représentant la liste d'adjacence.
     */
    public Map<Pixel, List<Arete>> getListeAdjacence() {
        return listeAdjacence;
    }

    /**
     * Permet de créer un graphe à partir d'une matrice d'intensités.
     * Chaque cellule de la matrice est représentée par un pixel, et des arêtes pondérées
     * sont ajoutées entre les pixels adjacents, la pondération étant basée sur la différence d'intensité.
     * @param matriceIntensite La matrice contenant les intensités des pixels (valeurs entières entre 0 et 255).
     * @return Un objet de type Graphe représentant la matrice sous forme de graphe pondéré.
     */
    public static Graphe depuisMatrice(int[][] matriceIntensite) {
        Graphe graphe = new Graphe(); // Création d'un nouveau graphe vide
        int lignes = matriceIntensite.length; // Nombre de lignes dans la matrice
        int colonnes = matriceIntensite[0].length; // Nombre de colonnes dans la matrice
        Pixel[][] pixels = new Pixel[lignes][colonnes]; // Tableau pour stocker les pixels correspondants

        // Étape 1 : Créer les pixels et les ajouter au graphe
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                // Création d'un pixel pour chaque cellule de la matrice
                Pixel pixel = new Pixel(i, j, matriceIntensite[i][j]);
                pixels[i][j] = pixel; // Stockage du pixel dans le tableau pour utilisation future
                graphe.ajouterPixel(pixel); // Ajout du pixel au graphe
            }
        }

        // Étape 2 : Ajouter les arêtes entre les pixels adjacents
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                Pixel pixel = pixels[i][j]; // Pixel courant
                // Ajouter une arête avec le pixel au-dessus (si existant)
                if (i > 0) graphe.ajouterArete(pixel, pixels[i - 1][j],
                            Math.abs(pixel.intensite - pixels[i - 1][j].intensite));
                // Ajouter une arête avec le pixel à gauche (si existant)
                if (j > 0) graphe.ajouterArete(pixel, pixels[i][j - 1],
                            Math.abs(pixel.intensite - pixels[i][j - 1].intensite));
                // Ajouter une arête avec le pixel en dessous (si existant)
                if (i < lignes - 1) graphe.ajouterArete(pixel, pixels[i + 1][j],
                            Math.abs(pixel.intensite - pixels[i + 1][j].intensite));

                // Ajouter une arête avec le pixel à droite (si existant)
                if (j < colonnes - 1) graphe.ajouterArete(pixel, pixels[i][j + 1],
                            Math.abs(pixel.intensite - pixels[i][j + 1].intensite));

            }
        }

        return graphe; // Retourner le graphe créé
    }

}