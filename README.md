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

- le mail "julien.bernard@example.com" est dans la base de données. Vous pouvez essayer de prendre un rdv le 01/03/2025 dans le centre de Paris. (avec ou sans le mail fourni)

Nous utilisons cette commande sur pgAdmin pour que l'id du patient soit généré à partir de 3 :
-> 'SELECT setval('t_patients_id_seq', 3, true);'


![Screenshot](/images_readme/FS.png)

