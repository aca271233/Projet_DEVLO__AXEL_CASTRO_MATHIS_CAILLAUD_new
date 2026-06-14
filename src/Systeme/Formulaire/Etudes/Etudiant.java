package Systeme.Formulaire.Etudes;

import java.util.Objects;

public class Etudiant {
    /**
     * Numero apprenant associé à l'étudiant
     */
    private final String NUMERO_APPRENANT;
    /**
     * Nom de l'étudiant
     */
    private final String NOM;
    /**
     * Prénom de l'étudiant
     */
    private final String PRENOM;
    /**
     * Systeme.Fromulaire.Etudes.Cursus suivit par l'étudiant
     */
    private final Cursus CURSUS;

    /**
     * Créer une instance d'Systeme.Fromulaire.Etudes.Etudiant, qui comporte un nom, un prenom, un numero apprenant et un cursus.
     * @param NUMERO_APPRENANT Numero apprenant associé à l'étudiant
     * @param NOM Nom de l'étudiant
     * @param PRENOM Prénom de l'étudiant
     * @param CURSUS Systeme.Fromulaire.Etudes.Cursus suivit par l'étudiant
     */
    public Etudiant(String NOM, String PRENOM, String NUMERO_APPRENANT, Cursus CURSUS) {
        this.NUMERO_APPRENANT = NUMERO_APPRENANT;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.CURSUS = CURSUS;
    }

    /**
     * Instance par défaut d'Systeme.Fromulaire.Etudes.Etudiant
     */
    public Etudiant(String[] etudiant){
        this(etudiant[0],etudiant[1], etudiant[2], Etudiant.getCursus(etudiant[3]));
    }

    /**
     * Acesseur du nom de l'étudiant
     * @return nom : String
     */
    public String getNOM() {
        return NOM;
    }

    /**
     * Acesseur du prénom de l'étudiant
     * @return prénom : String
     */
    public String getPRENOM() {
        return PRENOM;
    }

    /**
     * Acesseur du numéro d'apprenant de l'étudiant
     * @return numeroAprenant : String
     */
    public String getNUMERO_APPRENANT() {
        return NUMERO_APPRENANT;
    }

    public Cursus getCURSUS() {
        return CURSUS;
    }

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

    @Override
    public String toString() {
        return "(" + NUMERO_APPRENANT + ")" +
                ' ' + NOM +
                ' ' + PRENOM +
                " -> " + CURSUS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(NUMERO_APPRENANT, etudiant.NUMERO_APPRENANT) && Objects.equals(NOM, etudiant.NOM) && Objects.equals(PRENOM, etudiant.PRENOM) && CURSUS == etudiant.CURSUS;
    }
}
