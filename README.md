
Robot Finds Kitten – Simulation en Java

** Objectif du projet **
Ce projet implémente une version console simplifiée du jeu Robot Finds Kitten. Un robot évolue dans une grille et explore méthodiquement chaque case jusqu'à localiser une cellule cible contenant le "Kitten".

** Fonctionnement **
La grille est modélisée comme une matrice 2D de cellules.
Le robot commence à une position initiale (souvent en haut à gauche) et explore les cellules une à une.
Lorsqu’il atteint la cellule contenant le "Kitten", la simulation se termine.

**Logique d’exploration **
Le robot parcourt la grille de manière systématique (ligne par ligne ou autre stratégie déterministe), sans recours à l’aléatoire. Cela permet d’avoir un comportement prévisible et testable. À chaque étape, il :
vérifie s’il est sur la bonne case,
marque la case comme visitée,
décide de sa prochaine position.

** Structure du projet **
Grille.java : responsable de l’initialisation de la grille, du placement du robot et du "Kitten", ainsi que de l’affichage de l’état de la grille.
Robot.java : contient la logique de déplacement, de vérification de la condition de victoire, et du suivi des cases visitées.
Main.java : point d’entrée du programme. Initialise la grille et lance la boucle d’exploration.
kitten.txt : message ou contenu affiché lorsque la cible est trouvée

** Instructions de compilation et d’exécution **
Compiler tous les fichiers Java présents dans src/ :
  javac src/*.java

Lancer le programme :
  java -cp src Main



