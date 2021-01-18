## Présentation

Ce projet est un API REST qui permet de les actions suivantes : 
- remonter la liste de livres 
- commander des livres 
- consulter la commande

## Prérequis

Ce projet nécessite 
- Java 8 pour l'exécution

- Les variables d'environnement suivantes doivent être renseignées :
* JAVA_HOME : doit pointer vers le répertoire racine de Java 8, par exemple JAVA_HOME="C:\Program Files (x86)\Java\jdk1.8_66"
* MAVEN_HOME : doit pointer vers le répertoire racine de Maven 3.3.9, par exemple MAVEN_HOME="C:\Program Files (x86)\Apache\apache-maven-x.x.x"
* Path : doit contenir une entrée vers "%JAVA_HOME%\bin", vers "%MAVEN_HOME%\bin"

## Génération de l'exécutable

Pour générer l'exécutable Java :
- récupérer le projet gestion-livres puis lancer une compilation Maven avec la commande 'mvn clean install'

L'exécutable 'gestion-livres-0.0.1-SNAPSHOT.jar' est alors généré dans le dossier 'target' du projet.

## Lancement

> Lancer le script "startup.bat" pour le démarrage du serveur

## Utilisation de l'api
> Documentation d'utilisation de l'api : http://localhost:9090/swagger-ui.html
> Exemple pour faire passer une commande : 
Appel Post au http://localhost:9090/api/commandes
Body :
[
    {
        "livre": {
            "id":"1"
        },
        "quantiteCommande": 3
    },
    {
        "livre": {
           "id":"2"
        },
        "quantiteCommande": 2
    }
]


