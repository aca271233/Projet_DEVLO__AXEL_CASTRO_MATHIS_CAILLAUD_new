package Systeme.Formulaire.Fraude.Type;

import Systeme.Formulaire.Fraude.Fraude;

public class Papier extends Fraude {
    /**
     * Deuxième attribut du type
     */
    private final String attribut2;

    private final String descriptionAttribut2;

    public Papier(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1,"correspond à un document physique non autorisé");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "état";
        this.descriptionAttribut1 = "dimension";
    }

    @Override
    public String getAttribut2() {
        return attribut2;
    }

    public String getDescriptionAttribut2() {
        return descriptionAttribut2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + descriptionAttribut2 + ": " + attribut2;
    }
}
