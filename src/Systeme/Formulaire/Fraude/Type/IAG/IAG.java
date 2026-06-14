package Systeme.Formulaire.Fraude.Type.IAG;

import Systeme.Formulaire.Fraude.Fraude;

/**
 * Gère la fraude via l'usage d'outils d'Intelligence Artificielle Générative.
 * @author axel
 */
public class IAG extends Fraude {

    /**
     * Constructeur étendu permettant de redéfinir la description générique.
     * @param dateReleve Date de la fraude.
     * @param contenu Contenu ou prompt soumis.
     * @param attribut1 Nom du service d'IAG employé (ex: ChatGPT, Gemini).
     * @param description Phrase personnalisée décrivant la fraude.
     */
    public IAG(String dateReleve, String contenu, String attribut1, String description) {
        super(dateReleve, contenu, attribut1, description);
        this.descriptionAttribut1 = "nom du service utilisé";
    }

    /**
     * Constructeur standard pour l'utilisation générale d'une IAG.
     * @param dateReleve Date du constat.
     * @param contenu Description de la triche.
     * @param attribut1 Nom du service tiers (ex: ChatGPT).
     */
    public IAG(String dateReleve, String contenu, String attribut1) {
        this(dateReleve, contenu, attribut1,"correspond à l’utilisation d’un service d’intelligence artificielle générative");
    }

    /**
     * Les IAG standards n'ont pas de second paramètre obligatoire.
     * @return Toujours null pour une IAG simple.
     */
    public String getAttribut2(){
        return null;
    }

    /**
     * Libellé pour le second paramètre de l'IAG.
     * @return Toujours null pour une IAG simple.
     */
    public String getDescriptionAttribut2(){
        return null;
    }

    /**
     * Génère le texte de sortie propre à l'IAG.
     * @return Représentation sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
