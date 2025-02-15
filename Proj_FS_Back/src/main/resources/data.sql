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

-- Insérer des données dans t_employes
INSERT INTO t_employes (id, nom, prenom, mail, mdp, id_centre, is_med, is_admin, is_super_admin, telephone) VALUES

(1, 'Martin', 'Alice','Alice.Martin@gmail.com', '$2y$10$YrWNHZA/BnArgaVyju.Vk.7BKDuWjcVTuKAbPNc8RyyOwPZxbBiIe', 1, FALSE, TRUE, FALSE, 0623456789), -- Administrateur à Paris
(2, 'Durand', 'Paul','durand.paul@gmail.com', '$2y$10$1bWeql/sFeDHMFw.0GF.0u6ADIYED5GQrtc4MmaMBNsTFrPGDV.o.', 2, TRUE, FALSE, FALSE, 0634567890); -- Médecin à Lyon


/*-- Insérer des données dans t_patients
INSERT INTO t_patients (id, nom, prenom, mail, telephone, date_naissance) VALUES
(1, 'Bernard', 'Julien', 'julien.bernard@example.com', '0656789012', '1985-05-15'),
(2, 'Lemoine', 'Claire', 'claire.lemoine@example.com', '0667890123', '1990-08-22'),
(3, 'Fabre', 'Luc', 'luc.fabre@example.com', '0678901234', '1995-12-10');*/

INSERT INTO t_creneaux (id,id_centre,jour, heure,disponible) VALUES
(1, 1,'2025-01-10', '09:00:00', FALSE), 
(2, 1,'2025-01-18', '09:30:00', FALSE), 
(3,2,'2025-01-29', '14:00:00', TRUE); 


--Insérer des données dans t_reservations
/*INSERT INTO t_reservations (id, id_patient,id_employe,id_creneau, is_validated) VALUES
(1, 1, 1 ,1, TRUE),
(2, 2, 1 ,2, FALSE);*/



