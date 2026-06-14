package Systeme.Formulaire.Fraude;

import Systeme.Formulaire.Fraude.Type.Calculatrice;
import Systeme.Formulaire.Fraude.Type.IAG.IAG;
import Systeme.Formulaire.Fraude.Type.IAG.IAGConnectee;
import Systeme.Formulaire.Fraude.Type.Papier;

import java.util.Objects;

/**
 * Classe abstraite qui s'occupe de la gestion et la description de tout type de fraudes
 * @author axel
 */
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
    /**
     * Description du premiere attribut du type
     */
    protected String descriptionAttribut1;
    /**
     * Description du type
     */
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

    /**
     * Constructeur par défaut initialisant une fraude vierge.
     */
    public Fraude(){
        this(null,null,null,null);
    }

    /**
     * Retourne la date de relevée de la fraude.
     * @return la date de relevée de la fraude
     */
    public String getDateReleve() {
        return dateReleve;
    }

    /**
     * Retourne le contenu de la fraude.
     * @return le contenu de la fraude
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Retourne l'attribut 1 de la fraude.
     * @return l'attribut 1 de la fraude
     */
    public String getAttribut1() {
        return attribut1;
    }

    /**
     * Méthode abstraite pour récupérer le second attribut spécifique.
     * @return Le contenu du deuxième attribut sous forme de chaîne de caractères.
     */
    public abstract String getAttribut2();

    /**
     * Retourne la description du type.
     * @return la description du type
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retourne la description de l'attribut 1 du type.
     * @return la description de l'attribut 1 du type
     */
    public String getDescriptionAttribut1() {
        return descriptionAttribut1;
    }

    /**
     * Méthode abstraite pour récupérer la description du second attribut.
     * @return Le label du deuxième attribut sous forme de chaîne de caractères.
     */
    public abstract String getDescriptionAttribut2();

    /**
     * Fabrique permettant de créer une instance de fraude vide en fonction d'un numéro de type.
     * @param num Chaîne représentant l'identifiant du type ("1" = IAG, "2" = Papier, "3" = Calculatrice, "4" = IAGConnectee).
     * @return Une instance vierge de la sous-classe correspondante, ou null si le type est inconnu.
     */
    public static Fraude getType(String num){
        return switch (num) {
            case "1" -> new IAG(null, null, null);
            case "2" -> new Papier(null, null, null, null);
            case "3" -> new Calculatrice(null, null, null, null);
            case "4" -> new IAGConnectee(null, null, null, null);
            default -> null;
        };
    }

    /**
     * Fabrique permettant de créer et de remplir une instance de fraude à partir d'un tableau de chaînes.
     * @param fraudeStr Tableau contenant les paramètres textuels de la fraude.
     * - `fraudeStr[1]` : Le numéro de type de fraude.
     * - `fraudeStr[0]` : La date de relevé.
     * - `fraudeStr[2]` : Le contenu.
     * - `fraudeStr[3]` : L'attribut 1.
     * - `fraudeStr[4]` : L'attribut 2 (si applicable).
     * @return Une instance configurée de Fraude, ou null si le type est invalide.
     */
    public static Fraude setType(String[] fraudeStr){
        String numType = fraudeStr[1];
        return switch (numType) {
            case "1" -> new IAG(fraudeStr[0], fraudeStr[2], fraudeStr[3]);
            case "2" -> new Papier(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            case "3" -> new Calculatrice(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            case "4" -> new IAGConnectee(fraudeStr[0],fraudeStr[2],fraudeStr[3],fraudeStr[4]);
            default -> null;
        };
    }

    /**
     * Retourne une représentation textuelle standardisée de la fraude.
     * @return Une chaîne de caractères listant le type de classe, la date, le contenu et l'attribut 1.
     */
    @Override
    public String toString() {
        return " Fraude: " + getClass().getSimpleName() +
                ", dateReleve: " + dateReleve +
                ", contenu: " + contenu +
                ", " + descriptionAttribut1 + ": " + attribut1 ;
    }

    /**
     * Vérifie l'égalité entre deux objets de type Fraude.
     * @param o L'objet a comparé avec l'instance courante.
     * @return true si les objets sont de même classe et ont des attributs identiques, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fraude fraude = (Fraude) o;
        return Objects.equals(dateReleve, fraude.dateReleve) && Objects.equals(contenu, fraude.contenu) && Objects.equals(attribut1, fraude.attribut1) && Objects.equals(descriptionAttribut1, fraude.descriptionAttribut1) && Objects.equals(description, fraude.description);
    }
}