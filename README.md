# Projet De développement d'un site Web permettant la réservation pour une vaccination 

## Maintainers : 
- CHIADMI Salma
- NAKIB Wassil

## Technologies 
- Angular 
- Spring 
- PostgreSQL
## Dépendances 
- installer jwt_decode avec la commande 'npm install jwt-decode" : permet de récupérer les rôles en décodant le token jwt" 
## Organisation du code 
- Proj_FS_Back : Back-end de l'application divisé en : 
  - Controllers
  - Entities
  - Services
  - Repositories
  - Dto
  - Security 
- Proj_FS_Front : Front-end de l'application
    -> /src/app/core : contient les services nécessaire à la connexion à l'API back-end et les modèles de données 
    -> /src/app/features : contient les composants angulare front-end : reservation (ce qui est nécéssaire à la reservation) et admin (ce qui est nécessaires aux admins/superadmins/medecins)

## Pour tester :
#### Lancer l'application java ProjetJavaApplication.
#### Lancer 'ng serve' depuis /Proj_FS_Front.

-  Vous pouvez essayer de prendre un rdv le 22/03/2025 dans le centre de Lyon. 

- Vous pouvez vous login en tant qu'admin avec:
 - username : Alice.Martin@gmail.com
 - password : admin1 (hashé et stocké dans la base de données) 
  

- Vous pouvez vous login en tant que super-admin avec:
 - username : wnakib21@gmail.com
 - password : admin1 (hashé et stocké dans la base de données) 
  
 - Une fois connecté, nous pouvons accéder à la partie /admin du backend l'aide du token jwt ('Bearer <token>") à l'aide d'un guard.
    --> créer un médecin si vous le souhaitez
 
 ### Système d'autorisation
 Un système d'autorisation en back-end a été mis en place selon les rôles.

 Le token jwt est supprimé lors de la déconnexion.




![Screenshot](/images_readme/FS.png)

