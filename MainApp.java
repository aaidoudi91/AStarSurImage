package ui;

import graphe.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Classe principale de l'application JavaFX qui permet de visualiser le calcul du plus court chemin.
 * L'application peut fonctionner avec un graphe généré aléatoirement ou avec une image.
 */
public class MainApp extends Application {
    private static boolean modeAleatoire = true; // Par défaut, utilise une image donnée avec le programme
    private static int taillePixel = 7; // Taille de chaque pixel affiché
    private static int tailleGrille = 64; // Taille de la grille carrée pour le mode aléatoire
    private static String cheminImage; // Chemin vers le fichier image (mode image)
    private int[][] matriceIntensites; // Matrice représentant les intensités des pixels
    private Graphe graphe; // Graphe généré ou basé sur l'image

    /**
     * Point d'entrée principal de JavaFX.
     * Initialise l'application et choisit le mode (aléatoire ou image).
     * @param stage la fenêtre principale de l'application.
     */
    @Override
    public void start(Stage stage) {
        try {
            if (modeAleatoire) {
                executerGrapheAleatoire(stage); // Initialiser le graphe aléatoire
            } else {
                executerGrapheDepuisImage(stage); // Initialiser le graphe basé sur une image
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Exécute l'application en mode graphe aléatoire.
     * Gère la création de la matrice, du graphe et l'affichage du plus court chemin.
     * @param stage la fenêtre principale.
     */
    private void executerGrapheAleatoire(Stage stage) {
        // Génère une matrice de valeurs aléatoires
        matriceIntensites = new int[tailleGrille][tailleGrille];
        Random aleatoire = new Random();
        for (int i = 0; i < tailleGrille; i++) {
            for (int j = 0; j < tailleGrille; j++) {
                matriceIntensites[i][j] = aleatoire.nextInt(256); // Intensité aléatoire entre 0 et 255
            }
        }

        // Génération du graphe et calcul du plus court chemin
        graphe = Graphe.depuisMatrice(matriceIntensites);
        Pixel depart = new Pixel(0, 0, matriceIntensites[0][0]); // Point de départ
        Pixel arrivee = new Pixel(tailleGrille - 1, tailleGrille - 1,
                matriceIntensites[tailleGrille - 1][tailleGrille - 1]); // Point d'arrivée
        List<Pixel> chemin = AEtoile.plusCourtChemin(graphe, depart, arrivee); // Calcul du chemin

        // Création du canevas pour dessiner la grille et le chemin
        Canvas canevas = new Canvas(tailleGrille * taillePixel, tailleGrille * taillePixel);
        GraphicsContext gc = canevas.getGraphicsContext2D();
        dessinerImage(gc); // Dessiner la grille
        dessinerChemin(gc, chemin); // Dessiner le chemin

        // Configuration de la fenêtre et affichage
        stage.setScene(new Scene(new StackPane(canevas)));
        stage.setTitle("Mini Projet Aidoudi : Graphe Aléatoire");
        stage.show();
    }

    /**
     * Exécute l'application en mode image.
     * Charge une image, génère le graphe et affiche le plus court chemin.
     * @param stage la fenêtre principale.
     * @throws Exception en cas d'erreur de chargement de l'image.
     */
    private void executerGrapheDepuisImage(Stage stage) throws Exception {
        matriceIntensites = chargerImage(cheminImage); // Charge l'image en matrice

        // Création du graphe et calcul du plus court chemin
        graphe = Graphe.depuisMatrice(matriceIntensites);
        int largeur = matriceIntensites[0].length;
        int hauteur = matriceIntensites.length;
        Pixel depart = new Pixel(0, 0, matriceIntensites[0][0]);
        Pixel arrivee = new Pixel(hauteur - 1, largeur - 1, matriceIntensites[hauteur - 1][largeur - 1]);
        List<Pixel> chemin = AEtoile.plusCourtChemin(graphe, depart, arrivee);

        // Dessin sur le canevas
        Canvas canevas = new Canvas(largeur * taillePixel, hauteur * taillePixel);
        GraphicsContext gc = canevas.getGraphicsContext2D();
        dessinerImage(gc);
        dessinerChemin(gc, chemin);

        // Affichage
        stage.setScene(new Scene(new StackPane(canevas)));
        stage.setTitle("Mini Projet Aidoudi : Image");
        stage.show();
    }

    /**
     * Charge une image et convertit ses valeurs RGB en niveaux de gris.
     * @param cheminFichier chemin vers le fichier image.
     * @return une matrice représentant les intensités des pixels.
     * @throws Exception en cas d'erreur de lecture du fichier.
     */
    private int[][] chargerImage(String cheminFichier) throws Exception {
        BufferedImage img = ImageIO.read(new File(cheminFichier)); // Chargement de l'image
        int largeur = img.getWidth();
        int hauteur = img.getHeight();
        int[][] matrice = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                int rgb = img.getRGB(j, i);
                int rouge = (rgb >> 16) & 0xFF;
                int vert = (rgb >> 8) & 0xFF;
                int bleu = rgb & 0xFF;
                matrice[i][j] = (rouge + vert + bleu) / 3; // Conversion en niveaux de gris
            }
        }
        return matrice;
    }

    /**
     * Dessine la grille basée sur la matrice d'intensités.
     * @param gc le contexte graphique.
     */
    private void dessinerImage(GraphicsContext gc) {
        for (int i = 0; i < matriceIntensites.length; i++) {
            for (int j = 0; j < matriceIntensites[0].length; j++) {
                gc.setFill(Color.grayRgb(matriceIntensites[i][j])); // Couleur en niveaux de gris
                gc.fillRect(j * taillePixel, i * taillePixel, taillePixel, taillePixel);
            }
        }
    }

    /**
     * Dessine le chemin le plus court sur le canevas.
     * @param gc le contexte graphique.
     * @param chemin la liste des pixels représentant le chemin.
     */
    private void dessinerChemin(GraphicsContext gc, List<Pixel> chemin) {
        gc.setStroke(Color.RED); // Couleur du chemin
        gc.setLineWidth(2); // Épaisseur des lignes
        for (int i = 0; i < chemin.size() - 1; i++) {
            Pixel depuis = chemin.get(i);
            Pixel jusquA = chemin.get(i + 1);
            gc.strokeLine(
                    depuis.y * taillePixel + taillePixel / 2.0,
                    depuis.x * taillePixel + taillePixel / 2.0,
                    jusquA.y * taillePixel + taillePixel / 2.0,
                    jusquA.x * taillePixel + taillePixel / 2.0
            );
        }
    }

    /**
     * Point d'entrée principal de l'application.
     * Configure les arguments et lance JavaFX.
     * @param args arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        switch (args.length) {
            case 0: // Si aucun argument n'est donné, lance l'application avec l'image donnée en exemple
                launch(); // Lance JavaFX
                break;
            case 3: // Si 3 arguments sont donnés, lance l'application selon le choix de l'utilisateur
                try {
                    String mode = args[0];
                    taillePixel = Integer.parseInt(args[1]);

                    if ("aleatoire".equalsIgnoreCase(mode)) {
                        modeAleatoire = true;
                        tailleGrille = Integer.parseInt(args[2]);
                    } else if ("image".equalsIgnoreCase(mode)) {
                        modeAleatoire = false;
                        cheminImage = args[2];
                    } else {
                        System.err.println("Mode invalide : " + mode);
                        System.exit(1);
                    }
                } catch (NumberFormatException e) { // Si une erreur se glisse dans les arguments, rappel de la méthode
                    System.err.println("Usage : ... CodeMiniProjet.jar [aleatoire taillePixels nombrePixels] " +
                            "ou [image taillePixels cheminImage]");
                    System.exit(1);
                }
                launch(); // Lance JavaFX
                break;
            default: // Si l'utilisateur ne donne ni 0 ni 3 arguments, rappel de la méthode de lancement
                System.err.println("Usage : ... CodeMiniProjet.jar [aleatoire taillePixels nombrePixels] " +
                        "ou [image taillePixels cheminImage]");
                System.exit(1);
        }
    }
}
