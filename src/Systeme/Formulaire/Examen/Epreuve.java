package Systeme.Formulaire.Examen;

/**
 * Représente un examen ou une évaluation académique encadrée par un code ECUE.
 * @author axel
 */
public class Epreuve {

    /** Identifiant unique du module d'enseignement (Code ECUE). */
    private final String ECUE;

    /** Date calendaire planifiée pour la tenue de l'épreuve. */
    private final String DATE;

    /** Heure exacte de début de l'épreuve. */
    private final String HEURE;

    /** Durée totale allouée pour composer. */
    private final String DUREE;

    /** @brief Catégorie/Type de format de l'évaluation (Enum Modalite). */
    private final Modalite MODALITE;

    /**
     * Constructeur paramétrique complet pour instancier une Épreuve.
     * @param ECUE Code de l'Unité d'Enseignement Élémentaire.
     * @param DATE Date fixée (ex: "18/06/2026").
     * @param HEURE Heure de début (ex: "08:30").
     * @param DUREE Durée (ex: "2h00").
     * @param MODALITE Instance enum spécifiant les modalités de l'évaluation.
     */
    public Epreuve(String ECUE, String DATE, String HEURE, String DUREE, Modalite MODALITE) {
        this.ECUE = ECUE;
        this.DATE = DATE;
        this.HEURE = HEURE;
        this.DUREE = DUREE;
        this.MODALITE = MODALITE;
    }

    /**
     * Constructeur secondaire initialisant une épreuve depuis un tableau d'éléments textuels.
     * @param epreuve Tableau contenant obligatoirement :
     * - `epreuve[0]` : Le code ECUE
     * - `epreuve[1]` : La date
     * - `epreuve[2]` : L'heure
     * - `epreuve[3]` : La durée
     * - `epreuve[4]` : L'identifiant numérique de la modalité
     */
    public Epreuve(String[] epreuve) {
        this(epreuve[0], epreuve[1], epreuve[2], epreuve[3], getModalite(epreuve[4]));
    }

    /** Récupère le code ECUE.
     * @return Code ECUE.
     */
    public String getECUE() {
        return ECUE;
    }

    /** Récupère la date de l'évaluation.
     * @return Date.
     */
    public String getDATE() {
        return DATE;
    }

    /** Récupère l'heure de début.
     * @return Heure de début.
     */
    public String getHEURE() {
        return HEURE;
    }

    /** Récupère la durée de composition.
     * @return Durée.
     */
    public String getDUREE() {
        return DUREE;
    }

    /** Récupère le type de modalité de l'examen.
     * @return Modalite.
     */
    public Modalite getMODALITE() {
        return MODALITE;
    }

    /**
     * Méthode utilitaire de mappage convertissant une saisie numérique en énumération Modalite.
     * @param modalite Chaîne numérique ("1" à "6").
     * @return L'énumération Modalite correspondante ou null si hors limites.
     */
    public static Modalite getModalite(String modalite) {
        return switch (modalite) {
            case "1" -> Modalite.ExamenEcrit;
            case "2" -> Modalite.Oral;
            case "3" -> Modalite.QCM;
            case "4" -> Modalite.SurOrdinateur;
            case "5" -> Modalite.Projet;
            case "6" -> Modalite.TP;
            default -> null;
        };
    }

    /**
     * Retourne une représentation textuelle standardisée d'une épreuve'.
     * @return Une chaîne de caractères listant le code ECUE, la date, l'heure, la durée et la modalité.
     */
    @Override
    public String toString() {
        return "Epreuve -> " +
                "Code ECUE: " + ECUE +
                ", Date: " + DATE +
                ", Heure: " + HEURE +
                ", Durée: " + DUREE +
                ", Modalité: " + MODALITE
                ;
    }
}