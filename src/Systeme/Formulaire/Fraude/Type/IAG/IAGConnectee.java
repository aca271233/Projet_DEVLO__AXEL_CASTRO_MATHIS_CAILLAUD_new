package Systeme.Formulaire.Fraude.Type.IAG;

/**
 * Variante de la fraude IAG impliquant un accès réseau direct avec traçabilité IP.
 * @author axel
 */
public class IAGConnectee extends IAG{

    /** Adresse réseau (IPv4) identifiée lors de la connexion à l'IAG. */
    private final String attribut2;

    /** Label qualifiant l'attribut 2 ("adresse IP utilisée"). */
    private final String descriptionAttribut2;

    /**
     * Constructeur d'un signalement d'IAG avec accès réseau.
     * @param dateReleve Date de la capture de l'événement.
     * @param contenu Journal ou historique de la triche réseau.
     * @param attribut1 Nom de l'agent conversationnel ou plateforme en ligne.
     * @param attribut2 Adresse IP source ayant émis la requête frauduleuse.
     */
    public IAGConnectee(String dateReleve, String contenu, String attribut1, String attribut2) {
        super(dateReleve, contenu, attribut1, "correspond à un cas particulier d’IAG avec accès réseau");
        this.attribut2 = attribut2;
        this.descriptionAttribut2 = "adresse IP utilisée";
    }

    /**
     * Accesseur récupérant l'adresse IP associée.
     * @return L'adresse IP en format String.
     */
    @Override
    public String getAttribut2() {
        return attribut2;
    }

    /**
     * Accesseur fournissant la description du champ IP.
     * @return "adresse IP utilisée".
     */
    public String getDescriptionAttribut2() {
        return descriptionAttribut2;
    }

    /**
     * Exporte au format texte l'intégralité du compte rendu d'IAG connectée avec son IP.
     * @return Chaîne enrichie de l'attribut réseau.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", " + descriptionAttribut2 + ": " + attribut2;
    }
}
