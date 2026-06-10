package Systeme.Formulaire.Examen;

public class Epreuve {
    /**
     * Code ECUE de l'épreuve
     */
    private final String ECUE;
    /**
     * Date de déroulée de l'épreuve
     */
    private final String DATE;
    /**
     * Heure de déroulée de l'épreuve
     */
    private final String HEURE;
    /**
     * Durée de l'épreuve
     */
    private final String DUREE;
    /**
     * Modalitée de l'épreuve
     */
    private final Modalite MODALITE;

    /**
     * Créer une instance d'Systeme.Fromulaire.Examen.Epreuve, qui contient un code ecue,
     * une date, une heure, une durée ainci qu'une modalitée.
     *
     * @param ECUE     Code ECUE de l'épreuve
     * @param DATE     Date de déroulée de l'épreuve
     * @param HEURE    Heure de déroulée de l'épreuve
     * @param DUREE    Durée de l'épreuve
     * @param MODALITE Modalitée de l'épreuve
     */
    public Epreuve(String ECUE, String DATE, String HEURE, String DUREE, Modalite MODALITE) {
        this.ECUE = ECUE;
        this.DATE = DATE;
        this.HEURE = HEURE;
        this.DUREE = DUREE;
        this.MODALITE = MODALITE;
    }

    /**
     * Instance par defaut d'Systeme.Fromulaire.Examen.Epreuve
     */
    public Epreuve(String[] epreuve) {
        this(epreuve[0], epreuve[1], epreuve[2], epreuve[3], getModalite(epreuve[4]));
    }

    public String getECUE() {
        return ECUE;
    }

    public String getDATE() {
        return DATE;
    }

    public String getHEURE() {
        return HEURE;
    }

    public String getDUREE() {
        return DUREE;
    }

    public Modalite getMODALITE() {
        return MODALITE;
    }

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