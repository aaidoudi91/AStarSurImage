package graphe;

/**
 * Représente un pixel dans une image en termes de ses coordonnées et de son intensité lumineuse.
 */
public class Pixel {
    public int x, y;  // Coordonnées (x, y) du pixel dans la grille (image)
    public int intensite;  // Intensité du pixel, représentant l'intensité lumineuse du pixel

    public Pixel(int x, int y, int intensite) {
        this.x = x;
        this.y = y;
        this.intensite = intensite;
    }

    /**
     * Redéfinition de la méthode equals pour comparer les pixels en fonction de leurs coordonnées
     * @param obj le pixel avec lequel comparé le pixel courant
     * @return true si les pixels sont identiques, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Si l'objet comparé est le même (référence identique)
        if (obj == null || getClass() != obj.getClass()) return false;  // Vérification du type et de la nullité
        Pixel pixel = (Pixel) obj;
        return x == pixel.x && y == pixel.y;
    }

    /**
     * Redéfinition de la méthode hashCode pour garantir une bonne distribution dans les HashMap
     * Le calcul est basé sur les coordonnées x et y, et le nombre 31 un choix classique pour éviter les collisions.
     * @return le hashcode du pixel courant
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}