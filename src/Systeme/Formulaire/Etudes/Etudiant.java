package Systeme.Formulaire.Etudes;

import java.util.Objects;

/**
 * Classe représentant l'entité apprenante identifiée au sein de l'établissement.
 * @author axel
 */
public class Etudiant {

    /** Matricule ou numéro d'identification unique de l'étudiant. */
    private final String NUMERO_APPRENANT;

    /** Nom de famille de l'apprenant. */
    private final String NOM;

    /** Prénom de l'apprenant. */
    private final String PRENOM;

    /** Cursus de formation actuellement suivi par l'étudiant. */
    private final Cursus CURSUS;

    /**
     * Constructeur d'un Étudiant complet.
     * @param NOM Nom de famille.
     * @param PRENOM Prénom usuel.
     * @param NUMERO_APPRENANT Code d'identification.
     * @param CURSUS Niveau d'étude actuel (Enum).
     */
    public Etudiant(String NOM, String PRENOM, String NUMERO_APPRENANT, Cursus CURSUS) {
        this.NUMERO_APPRENANT = NUMERO_APPRENANT;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.CURSUS = CURSUS;
    }

    /**
     * Constructeur secondaire de commodité alimenté par un tableau de données textuelles.
     * @param etudiant Contient obligatoirement dans l'ordre :
     * - `etudiant[0]` : Nom
     * - `etudiant[1]` : Prénom
     * - `etudiant[2]` : Numéro apprenant
     * - `etudiant[3]` : Identifiant numérique de la classe (Cursus)
     */
    public Etudiant(String[] etudiant){
        this(etudiant[0],etudiant[1], etudiant[2], Etudiant.getCursus(etudiant[3]));
    }

    /** Récupère le nom de famille de l'étudiant.
     * @return NOM : String
     */
    public String getNOM() {
        return NOM;
    }

    /** Récupère le prénom de l'étudiant.
     * @return PRENOM : String
     */
    public String getPRENOM() {
        return PRENOM;
    }

    /** Récupère l'identifiant unique.
     * @return NUMERO_APPRENANT : String
     */
    public String getNUMERO_APPRENANT() {
        return NUMERO_APPRENANT;
    }

    /** Récupère le Cursus de l'étudiant.
     * @return CURSUS : Cursus
     */
    public Cursus getCURSUS() {
        return CURSUS;
    }

    /**
     * Convertisseur associant une saisie utilisateur numérique ("1" à "6") à un Cursus de l'école.
     * @param cursus Identifiant sous forme de chaîne de caractères.
     * @return L'objet Cursus correspondant, ou null en cas d'erreur de saisie.
     */
    public static Cursus getCursus(String cursus){
        return switch (cursus) {
            case "1" -> Cursus.E1;
            case "2" -> Cursus.E2;
            case "3" -> Cursus.E3e;
            case "4" -> Cursus.E3a;
            case "5" -> Cursus.E4;
            case "6" -> Cursus.E5;
            default -> null;
        };
    }

    /**
     * Formate l'affichage de l'identité de l'étudiant.
     * @return Format string standardisé : "(NUM) NOM PRENOM".
     */
    @Override
    public String toString() {
        return "(" + NUMERO_APPRENANT + ")" +
                ' ' + NOM +
                ' ' + PRENOM +
                " -> " + CURSUS;
    }

    /**
     * Valide si deux instances d'Étudiant représentent le même individu.
     * @param o Objet à tester.
     * @return true si les numéros d'apprenant concordent, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(NUMERO_APPRENANT, etudiant.NUMERO_APPRENANT) && Objects.equals(NOM, etudiant.NOM) && Objects.equals(PRENOM, etudiant.PRENOM) && CURSUS == etudiant.CURSUS;
    }
}
