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
(1, 'Dupont', 'Jean','jean.dupont@gmail.com','abcd', 1, TRUE, FALSE, FALSE, 0612345678), -- Médecin à Paris
(2, 'Martin', 'Alice','Alice.Martin@gmail.com', 'alicemartin', 1, FALSE, TRUE, FALSE, 0623456789), -- Administrateur à Paris
(3, 'Durand', 'Paul','durand.paul@gmail.com', '1234', 2, TRUE, FALSE, FALSE, 0634567890), -- Médecin à Lyon
(4, 'Morel', 'Sophie','sophie.morel@gmail.com', 'motdepasse', 3, FALSE, FALSE, TRUE, 0645678901); -- Super administratrice à Marseille

/*-- Insérer des données dans t_patients*/

INSERT INTO t_creneaux (id,id_centre,jour, heure,disponible) VALUES
(1, 1,'2025-01-10', '09:00:00', FALSE), 
(2, 1,'2025-01-18', '09:30:00', FALSE), 
(3,2,'2025-01-29', '14:00:00', TRUE),
(4, 1, '2025-02-12', '09:00:00', TRUE),
(5, 1, '2025-02-13', '09:30:00', TRUE),
(6, 1, '2025-02-14', '10:00:00', TRUE),
(7, 1, '2025-02-15', '10:30:00', TRUE),
(8, 1, '2025-02-16', '11:00:00', TRUE),
(9, 1, '2025-02-17', '11:30:00', TRUE),
(10, 1, '2025-02-18', '12:00:00', TRUE),
(11, 1, '2025-02-19', '12:30:00', TRUE),
(12, 1, '2025-02-20', '13:00:00', TRUE),
(13, 1, '2025-02-21', '13:30:00', TRUE),
(14, 1, '2025-02-22', '14:00:00', TRUE),
(15, 1, '2025-02-23', '14:30:00', TRUE),
(16, 1, '2025-02-24', '15:00:00', TRUE),
(17, 1, '2025-02-25', '15:30:00', TRUE),
(18, 1, '2025-02-26', '16:00:00', TRUE),
(19, 1, '2025-02-27', '16:30:00', TRUE),
(20, 1, '2025-02-28', '17:00:00', TRUE),
(21, 1, '2025-03-01', '17:30:00', TRUE),
(22, 1, '2025-03-02', '18:00:00', TRUE),
(23, 1, '2025-03-03', '18:30:00', TRUE);


--Insérer des données dans t_reservations
/*INSERT INTO t_reservations (id, id_patient,id_employe,id_creneau, is_validated) VALUES
(1, 1, 1 ,1, TRUE),
(2, 2, 1 ,2, FALSE);*/



