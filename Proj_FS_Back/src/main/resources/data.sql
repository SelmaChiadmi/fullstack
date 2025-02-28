-- Vider les tables pour éviter les doublons
DELETE FROM t_reservations;
DELETE FROM t_patients;
DELETE FROM t_employes;
DELETE FROM t_centres;
DELETE FROM t_creneaux;

-- Insérer des données dans t_centres
INSERT INTO t_centres (id, ville, nom) VALUES
(1, 'Paris', 'Centre Médical Paris'),
(2, 'Lyon', 'Centre Médical Lyon'),
(3, 'Marseille', 'Centre Médical Marseille');

-- commencer l'id à 3
SELECT setval(pg_get_serial_sequence('t_centres', 'id'), (SELECT MAX(id) FROM t_centres));

-- Insérer des données dans t_employes
INSERT INTO t_employes (id, nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone) VALUES
(1, 'Martin', 'Alice','Alice.Martin@gmail.com', '$2y$10$YrWNHZA/BnArgaVyju.Vk.7BKDuWjcVTuKAbPNc8RyyOwPZxbBiIe', 1, FALSE, TRUE, FALSE, 0623456789), -- Administrateur à Paris
(2, 'Durand', 'Paul','durand.paul@gmail.com', '$2y$10$1bWeql/sFeDHMFw.0GF.0u6ADIYED5GQrtc4MmaMBNsTFrPGDV.o.', 2, TRUE, FALSE, FALSE, 0634567890), -- Médecin à Lyon
(3, 'Dupont', 'Jean','jean.dupont@gmail.com', '$2y$10$YrWNHZA/BnArgaVyju.Vk.7BKDuWjcVTuKAbPNc8RyyOwPZxbBiIe', 3, FALSE, FALSE, TRUE, 0645678901); -- Super Administrateur à Marseille


/*-- Insérer des données dans t_patients*/
INSERT INTO t_creneaux (id,id_centre,jour, heure,disponible) VALUES
(1, 1,'2025-01-10', '09:00:00', FALSE), 
(2, 2,'2025-03-01', '09:30:00', FALSE), 
(3, 3,'2025-03-02', '14:00:00', TRUE),
(4, 1, '2025-03-03', '09:00:00', TRUE),
(5, 2, '2025-03-04', '09:30:00', TRUE),
(6, 3, '2025-03-05', '10:00:00', TRUE),
(7, 1, '2025-03-06', '10:30:00', TRUE),
(8, 2, '2025-03-07', '11:00:00', TRUE),
(9, 3, '2025-03-08', '11:30:00', TRUE),
(10, 1, '2025-03-09', '12:00:00', TRUE),
(11, 2, '2025-03-10', '12:30:00', TRUE),
(12, 3, '2025-03-11', '13:00:00', TRUE),
(13, 1, '2025-03-12', '13:30:00', TRUE),
(14, 2, '2025-03-13', '14:00:00', TRUE),
(15, 3, '2025-03-14', '14:30:00', TRUE),
(16, 1, '2025-03-15', '15:00:00', TRUE),
(17, 2, '2025-03-16', '15:30:00', TRUE),
(18, 3, '2025-03-17', '16:00:00', TRUE),
(19, 1, '2025-03-18', '16:30:00', TRUE),
(20, 2, '2025-03-19', '17:00:00', TRUE),
(21, 3, '2025-03-20', '17:30:00', TRUE),
(22, 1, '2025-03-21', '18:00:00', TRUE),
(23, 2, '2025-03-22', '18:30:00', TRUE);


--Insérer des données dans t_reservations
/*INSERT INTO t_reservations (id, id_patient,id_employe,id_creneau, is_validated) VALUES
(1, 1, 1 ,1, TRUE),
(2, 2, 1 ,2, FALSE);*/



