package graphe;

import java.util.*;

/**
 * Classe implémentant l'algorithme A* (A-star) pour trouver le chemin le plus court
 * entre deux pixels dans un graphe pondéré. L'algorithme utilise une fonction
 * heuristique pour prioriser les nœuds à explorer.
 */
public class AEtoile {

    /**
     * Trouve le chemin le plus court entre deux pixels dans un graphe en utilisant l'algorithme A*.
     * @param graphe dans lequel effectuer la recherche.
     * @param depart pixel de départ.
     * @param arrivee pixel de destination.
     * @return Une liste de pixels représentant le chemin le plus court, ou une liste vide si aucun chemin n'existe.
     */
    public static List<Pixel> plusCourtChemin(Graphe graphe, Pixel depart, Pixel arrivee) {
        Map<Pixel, Integer> scoreG = new HashMap<>(); // Stocke la distance minimale depuis le départ pour chaque pixel
        Map<Pixel, Integer> scoreF = new HashMap<>(); // Stocke le score f=g+h (coût estimé total) pour chaque pixel
        Map<Pixel, Pixel> precedents = new HashMap<>(); // Stocke le chemin parcouru

        // File de priorité pour traiter les pixels en fonction de leur score f minimal
        PriorityQueue<Pixel> filePriorite = new PriorityQueue<>(Comparator.comparingInt(scoreF::get));

        // Initialisation des scores pour tous les pixels
        for (Pixel pixel : graphe.getListeAdjacence().keySet()) {
            scoreG.put(pixel, Integer.MAX_VALUE); // Distance infinie au départ
            scoreF.put(pixel, Integer.MAX_VALUE); // Coût estimé infini
        }

        // Initialisation pour le pixel de départ
        scoreG.put(depart, 0); // La distance au départ est 0
        scoreF.put(depart, heuristique(depart, arrivee)); // f = h (car g = 0 au début)
        filePriorite.add(depart); // Ajouter le point de départ à la file de priorité

        // Boucle principale de l'algorithme A*
        while (!filePriorite.isEmpty()) {
            Pixel courant = filePriorite.poll(); // Retirer le pixel avec le plus faible score f

            if (courant.equals(arrivee)) break; // Si on atteint la destination, on peut arrêter la recherche

            for (Arete arete : graphe.getAretes(courant)) { // Pour chaque voisin du pixel courant
                Pixel voisin = arete.vers; // Pixel voisin
                int tentativeScoreG = scoreG.get(courant) + arete.poids; // Distance g potentielle

                if (tentativeScoreG < scoreG.get(voisin)) { // Si une meilleure distance est trouvée pour ce voisin
                    // Mettre à jour le prédécesseur et les scores
                    precedents.put(voisin, courant);
                    scoreG.put(voisin, tentativeScoreG);
                    scoreF.put(voisin, tentativeScoreG + heuristique(voisin, arrivee));

                    // Ajouter le voisin à la file si ce n'est pas déjà fait
                    if (!filePriorite.contains(voisin)) {
                        filePriorite.add(voisin);
                    }
                }
            }
        }

        // Reconstruction du chemin depuis la destination
        List<Pixel> chemin = new ArrayList<>();
        for (Pixel pixel = arrivee; pixel != null; pixel = precedents.get(pixel)) {
            chemin.add(pixel);
        }
        Collections.reverse(chemin); // Le chemin est reconstruit en sens inverse

        // Si le chemin ne commence pas au point de départ, renvoyer une liste vide
        return chemin.isEmpty() || !chemin.get(0).equals(depart) ? Collections.emptyList() : chemin;
    }

    /**
     * Calcule une estimation heuristique de la distance entre deux pixels.
     * @param a Le premier pixel.
     * @param b Le deuxième pixel.
     * @return Une estimation du coût entre les deux pixels.
     */
    private static int heuristique(Pixel a, Pixel b) {
        // Calcul de la distance entre les deux pixels
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
