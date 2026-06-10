package Systeme.Formulaire.Fraude.Type;

import Systeme.Formulaire.Fraude.Fraude;

public class Calculatrice extends Fraude {
    /**
     * Deuxième attribut du type
     */
    private final String attribut2;

    private final String descriptionAttribut2;

    public Calculatrice(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1, "Correspond à l'utilisation de programme sur la calculatrice");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "programme stocké";
        this.descriptionAttribut1 = "marque de l'appareil";
    }

    public Calculatrice(String[] calculatrice) {
        this(calculatrice[0],calculatrice[1],calculatrice[2],calculatrice[3]);
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
