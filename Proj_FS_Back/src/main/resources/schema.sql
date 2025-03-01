create table if not exists t_centres  (
    id SERIAL PRIMARY KEY,
    ville varchar(255),
    nom varchar(255)
);

create table if not exists t_employes(
    id SERIAL PRIMARY KEY,
    nom varchar(255),
    prenom varchar(255),
    mail varchar(255),
    mdp varchar(255),
    id_centre int4,
    is_med boolean, 
    is_admin boolean, 
    is_super_admin boolean,
    telephone varchar(15), 
    foreign key (id_centre) references t_centres(id)
);



create table if not exists t_patients (
    id SERIAL PRIMARY KEY, 
    nom varchar(255),
    prenom varchar(255),
    mail varchar(255),
    telephone varchar(15),
    date_naissance date

);



create table if not exists t_creneaux (
    id SERIAL PRIMARY KEY,  
    id_centre int not null,  
    jour date not null,
    heure time not null,
    disponible boolean not null,  
    foreign key (id_centre) references t_centres(id)
    
);


create table if not exists t_reservations (
    id SERIAL PRIMARY KEY, 
    id_patient int,
    id_creneau int ,
    id_employe int,
    is_validated boolean,
    foreign key(id_patient) references t_patients(id),
    foreign key (id_creneau) references t_creneaux(id),
    foreign key(id_employe) references t_employes(id)
    
);


