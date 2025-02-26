# Projet De développement d'un site Web permettant la réservation pour une vaccination 

## Maintainers : 
- CHIADMI Salma
- NAKIB Wassil

## Technologies 
- Angular 
- Spring 
- PostgreSQL

## Organisation du code 
- Proj_FS_Back : Back-end de l'application 
- Proj_FS_Front : Front-end de l'application
    -> /src/app/core : contient les services nécéssaire à la connexion à l'API back-end et les modèles de données 
    -> /src/app/features : contient les composants angulare front-end : reservation (ce qui est nécéssaire à la reservation) et admin (ce qui est nécessaires aux admins)

## Pour tester :
#### Lancer l'application java ProjetJavaApplication.
#### Lancer 'ng serve' depuis /Proj_FS_Front.

-  Vous pouvez essayer de prendre un rdv le 04/03/2025 dans le centre de Lyon.

- Vous pouvez vous login avec:
 - username : Alice.Martin@gmail.com
 - password : admin1 (hashé et stocké dans la base de donnée) et accès à l'aide d'un token jwt à la page ('Bearer <token>") à l'aide d'un guard.
  



![Screenshot](/images_readme/FS.png)

