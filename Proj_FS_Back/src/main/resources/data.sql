-- Insérer des données dans t_centres sans spécifier l'id
INSERT INTO t_centres (ville, nom)
SELECT 'Paris', 'Centre Médical Paris'
WHERE NOT EXISTS (SELECT 1 FROM t_centres WHERE ville = 'Paris' AND nom = 'Centre Médical Paris');

INSERT INTO t_centres (ville, nom)
SELECT 'Lyon', 'Centre Médical Lyon'
WHERE NOT EXISTS (SELECT 1 FROM t_centres WHERE ville = 'Lyon' AND nom = 'Centre Médical Lyon');


INSERT INTO t_centres (ville, nom)
SELECT 'Marseille', 'Centre Médical Marseille'
WHERE NOT EXISTS (SELECT 1 FROM t_centres WHERE ville = 'Marseille' AND nom = 'Centre Médical Marseille');

-- Ajouter un centre par défaut en spécifiant l'id
INSERT INTO t_centres (id, ville, nom)
SELECT 4 , 'Aucune ville' , 'Aucun'
WHERE NOT EXISTS (SELECT 1 FROM t_centres WHERE ville = 'Aucune ville' AND nom = 'Aucun');

-- commencer l'id à 3
SELECT setval(pg_get_serial_sequence('t_centres', 'id'), (SELECT MAX(id) FROM t_centres));



-- Insérer des données dans t_employes sans spécifier l'id
INSERT INTO t_employes (nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone)
SELECT 'Martin', 'Alice', 'Alice.Martin@gmail.com', '$2y$10$YrWNHZA/BnArgaVyju.Vk.7BKDuWjcVTuKAbPNc8RyyOwPZxbBiIe', 1, FALSE, TRUE, FALSE, 0623456789
WHERE NOT EXISTS (SELECT 1 FROM t_employes WHERE mail = 'Alice.Martin@gmail.com'); -- Vérifier la présence d'un employé par mail

INSERT INTO t_employes (nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone)
SELECT 'Durand', 'Paul', 'durand.paul@gmail.com', '$2y$10$1bWeql/sFeDHMFw.0GF.0u6ADIYED5GQrtc4MmaMBNsTFrPGDV.o.', 2, TRUE, FALSE, FALSE, 0634567890
WHERE NOT EXISTS (SELECT 1 FROM t_employes WHERE mail = 'durand.paul@gmail.com'); -- Vérifier la présence d'un employé par mail

INSERT INTO t_employes (nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone)
SELECT 'Nakib', 'Wassil', 'wnakib21@gmail.com', '$2y$10$YrWNHZA/BnArgaVyju.Vk.7BKDuWjcVTuKAbPNc8RyyOwPZxbBiIe', 1, FALSE, FALSE, TRUE, 0678945678
WHERE NOT EXISTS (SELECT 1 FROM t_employes WHERE mail = 'wnakib21@gmail.com'); -- Vérifier la présence d'un employé par mail

INSERT INTO t_employes (nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone)
SELECT 'Chiadmi', 'Selma', 'selma.chiadmiii@gmail.com', '$2y$10$1bWeql/sFeDHMFw.0GF.0u6ADIYED5GQrtc4MmaMBNsTFrPGDV.o.', 3, FALSE, FALSE, TRUE, 0698645678
WHERE NOT EXISTS (SELECT 1 FROM t_employes WHERE mail = 'selma.chiadmiii@gmail.com'); -- Vérifier la présence d'un employé par mail

-- Ajuster la séquence pour t_employes
SELECT setval(pg_get_serial_sequence('t_employes', 'id'), (SELECT MAX(id) FROM t_employes));







-- Insérer des données dans t_creneaux sans spécifier l'id
INSERT INTO t_creneaux (id_centre, jour, heure, disponible)
SELECT 2, '2025-03-22', '14:00:00', TRUE
WHERE NOT EXISTS (SELECT 1 FROM t_creneaux WHERE id_centre = 2 AND jour = '2025-03-22' AND heure = '14:00:00');
-- Ajuster la séquence pour t_creneaux
SELECT setval(pg_get_serial_sequence('t_creneaux', 'id'), (SELECT MAX(id) FROM t_creneaux));

