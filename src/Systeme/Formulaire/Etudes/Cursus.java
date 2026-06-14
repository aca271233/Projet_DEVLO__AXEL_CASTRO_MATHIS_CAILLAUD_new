package Systeme.Formulaire.Etudes;

/**
 * Représente différents groupes d’année/niveaux académiques dans une école d’ingénieurs française.
 * Comprend les classes de CPGE (Classes Préparatoires aux Grandes Écoles),
 * classes préparatoires intégrées, et cycles d’ingénieurs dans les deux cycles réguliers
 * et en alternance.
 */
public enum Cursus {
    /**
     * First year of integrated preparatory program
     */
    E1,

    /**
     * Second year of integrated preparatory program
     */
    E2,

    /**
     * First year of engineering cycle - regular track
     */
    E3e,

    /**
     * First year of engineering cycle - work-study track
     */
    E3a,

    /**
     * Second year of engineering cycle
     */
    E4,

    /**
     * Final year of engineering cycle
     */
    E5
}
