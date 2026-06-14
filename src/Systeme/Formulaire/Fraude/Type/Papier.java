package Systeme.Formulaire.Fraude.Type;

import Systeme.Formulaire.Fraude.Fraude;

/**
 * Spécifie l'infraction par utilisation de documents physiques non autorisés (antisèches, notes).
 * @author axel
 */
public class Papier extends Fraude {

    /** Décrit l'état physique ou l'emplacement de dissimulation du papier. */
    private final String attribut2;

    /** Label décrivant le second attribut ("état"). */
    private final String descriptionAttribut2;

    /**
     * Constructeur de l'infraction sur support papier.
     * @param dateReleve Date du constat.
     * @param contenu Explication du contenu textuel de l'antisèche.
     * @param attribut1 Dimensions du document physique (ex: A4, petit mot manuscrit).
     * @param attribut2 État ou méthode de dissimulation du papier (ex: froissé, sous la trousse).
     */
    public Papier(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1,"correspond à un document physique non autorisé");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "état";
        this.descriptionAttribut1 = "dimension";
    }

    /**
     * Récupère l'état ou le mode de dissimulation du document papier.
     * @return L'état du papier.
     */
    @Override
    public String getAttribut2() {
        return attribut2;
    }

    /**
     * Récupère l'intitulé décrivant le champ de l'attribut 2.
     * @return "état".
     */
    public String getDescriptionAttribut2() {
        return descriptionAttribut2;
    }

    /**
     * Concatène les métadonnées de base et celles spécifiques au support papier.
     * @return Description textuelle formatée.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", " + descriptionAttribut2 + ": " + attribut2;
    }
}
