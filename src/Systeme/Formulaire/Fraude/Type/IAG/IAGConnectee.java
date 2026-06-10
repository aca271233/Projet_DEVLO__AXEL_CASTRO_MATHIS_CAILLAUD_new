package Systeme.Formulaire.Fraude.Type.IAG;

public class IAGConnectee extends IAG{
    /**
     * Deuxième attribut du type
     */
    private final String attribut2;

    private final String descriptionAttribut2;

    public IAGConnectee(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1, "correspond à un cas particulier d’IAG avec accès réseau");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "adresse IP utilisée";
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
