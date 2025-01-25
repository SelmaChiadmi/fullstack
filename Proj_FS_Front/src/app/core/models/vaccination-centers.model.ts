export interface VaccinationCenter{
    id: number;
    nom : string;
    ville : string;
    creneaux: string[]; // Horaires spécifiques à chaque centre
}