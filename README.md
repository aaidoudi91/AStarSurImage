# A-Star sur Image : Modélisation et Recherche de Chemin Optimal

Ce projet implémente l'algorithme A-Star (A*) sur une image modélisée en graphe, permettant de visualiser le chemin optimal entre deux pixels. 

Une **décritpion détaillé** de ce projet, ces classes et méthodes est disponible dans le document `Aidoudi_MiniProjet.pdf`.

## Contenu du projet
Le projet comprend :
- Une archive exécutable `CodeMiniProjet.jar`.
- Le code source en Java, via les packages `graphe` et `ui`.
- Un document de compte rendu `Aidoudi_MiniProjet.pdf` expliquant le fonctionnement et les choix algorithmiques.

## Exécution
Le projet est fourni sous forme d'une archive Java exécutable. Pour le lancer, utilisez la commande suivante :

```sh
 java --module-path [cheminVersLib] --add-modules javafx.controls -jar CodeMiniProjet.jar
```

> **Remarque** : `cheminVersLib` doit être remplacé par le chemin vers la bibliothèque JavaFX, disponible sur [gluonhq.com](https://gluonhq.com/products/javafx).

Si JavaFX est déjà installé sur votre système, la commande peut être simplifiée en :

```sh
java -jar CodeMiniProjet.jar
```

## Utilisation
Le programme propose deux modes d'exécution :

### A) Graphe aléatoire
Pour générer un graphe aléatoire et trouver le plus court chemin, utilisez la commande suivante :

```sh
java -jar CodeMiniProjet.jar aleatoire [taillePixels] [nombrePixels]
```

- `taillePixels` : taille des pixels affichés.
- `nombrePixels` : dimensions du graphe (génère un graphe de `nombrePixels * nombrePixels`).

> **Attention** : Assurez-vous que le rapport `nombrePixels / taillePixels` permet un affichage complet du graphe.

### B) Image
Pour analyser une image et calculer le chemin optimal, utilisez la commande :

```sh
java -jar CodeMiniProjet.jar image [taillePixels] [cheminImage]
```

- `taillePixels` : taille des pixels affichés.
- `cheminImage` : chemin vers l'image à traiter.

> **Attention** :
> - Privilégiez une image de faible résolution pour optimiser l'affichage et les performances.
> - Adaptez `taillePixels` pour assurer un affichage optimal du graphe.

### Exemples de commandes

- Pour exécuter l'algorithme sur une image :

```
java --module-path cheminVersLib --add-modules javafx.controls,javafx.fxml -jar CodeMiniProjet.jar image 3 labyrinthe2.jpg
```

- Pour exécuter l'algorithme sur un graphe aléatoire :

```
java --module-path cheminVersLib --add-modules javafx.controls,javafx.fxml -jar CodeMiniProjet.jar aleatoire 5 128
```

## Auteur & Crédits
Projet réalisé dans le cadre pédagogique de l'UE d'Algorithmique Avancée

Auteur : Aidoudi Aaron
