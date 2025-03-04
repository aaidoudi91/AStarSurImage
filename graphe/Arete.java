package graphe;

/**
 * Représente une arête dans le graphe, définie par deux pixels ainsi qu'un poids.
 */
public class Arete {
    public Pixel depuis; // Le pixel de départ de l'arête
    public Pixel vers; // Le pixel de départ de l'arête
    public int poids; // Le poids de l'arête, représentant la différence d'intensité entre les pixels

    public Arete(Pixel depuis, Pixel vers, int poids) {
        this.depuis = depuis;
        this.vers = vers;
        this.poids = poids;
    }
}