# Codingweek-28

Groupe composé de MOY Kélian, THUILLIER Killian, CHRISTIEN Adrien, HOYAU Christophe

## Vidéo Présentation

lien de la vidéo de présentation 
https://youtu.be/8K31yQM0EzM

## Créer un .jar
### Créer le jar
exporter la variable d'environnement de javafx
```sh
export JAVAFX_HOME=/usr/share/openjfx
```

clean build gradle puis créé le jar, le lance (pour tester) puis le déplace en le nommant `App.jar` dans le dossier `dist/App.jar`
```sh
./gradlew clean build && ./gradlew shadowjar && java --module-path ${JAVAFX_HOME}/lib --add-modules=javafx.base,javafx.controls,javafx.fxml -jar build/libs/my-application-1.0.0.jar && cp build/libs/my-application-1.0.0.jar dist/App.jar

```

### lancer le dernier `.jar` créé (lancer le projet quoi)
```sh
java --module-path ${JAVAFX_HOME}/lib --add-modules=javafx.base,javafx.controls,javafx.fxml -jar dist/App.jar
```

### vous pouvez vous connecter avec les identifiants suivants
identifiant : mdp
Jdoe : null




## Planning

### Pour Lundi 08/01

Livrable prévu: connexion, inscription et gestion des utilisateurs.

Terminer les étapes préliminaires et le modèle de donnée

### Pour Mardi 09/01

Fixer les bugs de la veille et avoir une archive JAR exécutable.

Livrable prévu: page d'acceuil, création des objets bien et service ainsi que leur gestion, page Profil.

### Pour Mercredi 10/01

Livrable prévu : accueil + profil + css + chat

### Pour Jeudi 11/01

Livrable prévu : page addItem + recherche + calendrier + css

### Pour Vendredi 12/01

fin du css and minor fixes

## Modèle de donnée

