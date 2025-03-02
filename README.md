README.txt : Mini Projet d'Algorithmique Avancée
Le projet choisi est le Scénario 2 : Graphes et Image

Ce document contient des informations d'usage du programme Java du projet.
Concernant les informations sur leurs contenus, voir le compte rendu dans le document Aidoudi_MiniProjet.pdf.
Des commentaires sont aussi disponibles sur le code de toutes les classes, pour une meilleure compréhension.


I) Exécution 
Le projet est rendu via une archive java, que l'on peut exécuter avec la commande suivante : 
	--> java --module-path cheminVersLib --add-modules javafx.controls -jar CodeMiniProjet.jar
Cette commande indique le chemin vers la librairie JavaFX (noté cheminVersLib et téléchargeable sur gluonhq.com/products/javafx), 
au cas où elle ne serait pas installée.

Si JavaFX est installée, la commande peut être allégée telle que : 
	--> java -jar CodeMiniProjet.jar


II) Usage
Pour tester le programme, il faut entrer les arguments au lancement du programme selon le choix du mode.
	
	A) Graphe aléatoire
	Si l'on veut obtenir le plus court chemin d'un graphe aléatoire, les arguments sont le nombre de pixels du 
	graphe (le résultat sera un graphe de nombrePixels * nombrePixels) et la taille des pixels. La commande est :
		--> ... CodeMiniProjet.jar aleatoire taillePixels nombrePixels
	Attention au ratio nombrePixels / taillePixels pour que tout le graphe soit affiché entièrement.

	B) Image
	Si l'on veut obtenir le plus court chemin depuis une image, il faut tout d'abord faire attention à prendre 
	une image qui n'est pas d'une trop grande résolution, et faire varier la taille des pixels pour que le 	
	résultat soit affichable. La commande est :
		--> ... CodeMiniProjet.jar image taillePixels cheminImage 
	Attention au ratio résolutionImage / taillePixels pour que tout le graphe soit affiché entièrement.


Un exemple de commande d'exécution est donc : 
	--> java --module-path cheminVersLib --add-modules javafx.controls, javafx.fxml -jar CodeMiniProjet.jar image 3 labyrinthe2.jpg
Ou encore : 
	--> java --module-path cheminVersLib --add-modules javafx.controls, javafx.fxml -jar CodeMiniProjet.jar aleatoire 5 128
