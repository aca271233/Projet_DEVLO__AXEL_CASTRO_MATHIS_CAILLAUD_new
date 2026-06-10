package Systeme.Formulaire.Fraude.Type.IAG;

import Systeme.Formulaire.Fraude.Fraude;

public class IAG extends Fraude {

    public IAG(String dateReleve, String contenu, String attribut1, String description) {
        super(dateReleve, contenu, attribut1, description);
        this.descriptionAttribut1 = "nom du service utilisé";
    }

    public IAG(String dateReleve, String contenu, String attribut1) {
        this(dateReleve, contenu, attribut1,"correspond à l’utilisation d’un service d’intelligence artificielle générative");
    }

    public String getAttribut2(){
        return null;
    }

    public String getDescriptionAttribut2(){
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
