# RobotFindsKitten
RobotFindsKitten est un jeu textuel programmé en Java où un robot explore une grille pour retrouver un objet spécifique, le kitten, dissimulé parmi d’autres symboles et pièges.

## Objectif du jeu
Le joueur contrôle un robot (#) qui se déplace sur une grille 2D. L’objectif est de trouver le symbole représentant le kitten, en évitant les obstacles et en collectant des objets utiles (clés, téléporteurs).
Le jeu repose sur des déplacements en temps réel, une grille avec des murs, des portes, des objets aléatoires et des conditions de victoire.

## Fonctionnalités
Génération procédurale de la grille (avec murs, portes, espaces, objets).
Détection des déplacements autorisés (prise en compte des murs et des portes).
Collecte d'objets :
  Clés (') pour ouvrir des portes (!).
  Téléporteurs (T) pour se téléporter une fois aléatoirement.
  Objets non-kitten (@, *, $, ...) avec descriptions affichées.
  Kitten généré aléatoirement sur la grille : une fois trouvé, le jeu se termine.

## Commandes
  w : haut
  a : gauche
  s : bas
  d : droite
  t : utiliser un téléporteur (si débloquer)


## Architecture
Classes principales
Grille : contient la matrice du jeu, la logique de génération, de déplacement, d’interaction avec les objets et de victoire.
Robot : entité contrôlée par l'utilisateur.
NonKitten : objets leurres avec descriptions uniques.
Point : utilitaire pour gérer les coordonnées.
Case : gestion des symboles disponibles.

# Exemple de grille (extrait console)

<img width="439" height="290" alt="Screenshot 2025-07-22 065223" src="https://github.com/user-attachments/assets/3e4ea8dc-4aeb-45c3-8369-98400b2aee67" />



## Exécution
Compiler le projet (structure Java standard).
Lancer la classe contenant le main (typiquement RobotFindsKitten).
Le robot est placé aléatoirement. Le joueur peut alors interagir via le terminal.

