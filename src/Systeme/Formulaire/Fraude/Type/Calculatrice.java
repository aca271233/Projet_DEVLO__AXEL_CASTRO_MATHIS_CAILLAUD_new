package Systeme.Formulaire.Fraude.Type;

import Systeme.Formulaire.Fraude.Fraude;

/**
 * Représente un cas de fraude lié à l'utilisation illicite de programmes sur calculatrice.
 * @author axel
 */
public class Calculatrice extends Fraude {

    /** Stocke la désignation ou le nom du programme frauduleux utilisé. */
    private final String attribut2;

    /** Label décrivant la nature du second attribut ("programme stocké"). */
    private final String descriptionAttribut2;

    /**
     * Constructeur de la fraude aux programmes de calculatrice.
     * @param dateReleve Date du constat de la fraude.
     * @param contenu Détails sur les circonstances du relevé.
     * @param attribut1 Marque de l'appareil électronique (ex: Texas Instruments, Casio).
     * @param attribut2 Nom du programme ou fichier frauduleux stocké.
     */
    public Calculatrice(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1, "Correspond à l'utilisation de programme sur la calculatrice");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "programme stocké";
        this.descriptionAttribut1 = "marque de l'appareil";
    }

    /**
     * Accesseur pour obtenir le programme stocké.
     * @return Le nom du programme en chaîne de caractères.
     */
    @Override
    public String getAttribut2() {
        return attribut2;
    }

    /**
     * Accesseur pour obtenir le libellé de l'attribut 2.
     * @return "programme stocké".
     */
    public String getDescriptionAttribut2() {
        return descriptionAttribut2;
    }

    /**
     * Génère la chaîne textuelle descriptive complète de la fraude calculatrice.
     * @return Les informations globales de la fraude complétées par les données de la calculatrice.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", " + descriptionAttribut2 + ": " + attribut2;
    }
}
