package Systeme.Formulaire.Fraude;

import Systeme.Formulaire.Fraude.Type.Calculatrice;
import Systeme.Formulaire.Fraude.Type.IAG.IAG;
import Systeme.Formulaire.Fraude.Type.IAG.IAGConnectee;
import Systeme.Formulaire.Fraude.Type.Papier;

public abstract class Fraude {
    /**
     * Date de relevée de la fraude
     */
    protected String dateReleve;
    /**
     * Specification du contenu de fraude relevée
     */
    protected String contenu;
    /**
     * Premier attribut du type
     */
    protected String attribut1;

    protected String descriptionAttribut1;

    protected String description;

    /**
     * Créer une instance de Systeme.Fromulaire.Fraude.Fraude, qui contient une date de relevée,
     * une description, un contenu et deux attributs.
     * @param dateReleve Date de relevée de la fraude
     * @param contenu Specification du contenu de fraude relevée
     * @param attribut1 Premier attribut du type
     */
    public Fraude(String dateReleve, String contenu, String attribut1, String description) {
        this.dateReleve = dateReleve;
        this.contenu = contenu;
        this.attribut1 = attribut1;
        this.description = description;
    }

    public String getDateReleve() {
        return dateReleve;
    }

    public String getContenu() {
        return contenu;
    }

    public String getAttribut1() {
        return attribut1;
    }

    public abstract String getAttribut2();

    public String getDescription(){
        return description;
    }

    public String getDescriptionAttribut1() {
        return descriptionAttribut1;
    }

    public abstract String getDescriptionAttribut2();

    public static Fraude getType(String num){
        return switch (num) {
            case "1" -> new IAG(null, null, null);
            case "2" -> new Papier(null, null, null, null);
            case "3" -> new Calculatrice(null, null, null, null);
            case "4" -> new IAGConnectee(null, null, null, null);
            default -> null;
        };
    }

    public static Fraude setType(String[] fraudeStr){
        String numType = fraudeStr[1];
        return switch (numType) {
            case "1" -> new IAG(fraudeStr[0], fraudeStr[2], fraudeStr[3]);
            case "2" -> new Papier(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            case "3" -> new Calculatrice(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            case "4" -> new IAGConnectee(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            default -> getType(numType);
        };
    }

    @Override
    public String toString() {
        return " Fraude: " + getClass().getSimpleName() +
                ", dateReleve: " + dateReleve +
                ", contenu: " + contenu +
                ", " + descriptionAttribut1 + ": " + attribut1 ;
    }
}
