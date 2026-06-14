package Systeme.Formulaire;

import Systeme.Formulaire.Etudes.Etudiant;
import Systeme.Formulaire.Examen.Epreuve;
import Systeme.Formulaire.Fraude.Fraude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import static Systeme.Formulaire.Fraude.Fraude.getType;
import static Systeme.Formulaire.Fraude.Fraude.setType;

/**
 * Classe qui s'occupe de la gestion des formulaires
 * @author axel
 */
public class Formulaire{
    private static int compteur=0;
    /**
     * Identifiant du formulaire
     */
    private final int identifiant;
    /**
     * Date de création du formulaire
     */
    private final String DATE_CREATION;
    /**
     * Date de la dernière modification apportée au formulaire
     */
    private String dateModification;
    /**
     * Epreuve associée au formulaire
     */
    private Systeme.Formulaire.Examen.Epreuve epreuve;
    /**
     * Dictionnaire contennant la liste des fraudes associées à chaques étudiants fraudeurs
     */
    private HashMap<Etudiant, ArrayList<Fraude>> etudiantFraudes;
    /**
     * Liste des clés Etudiant
     */
    private LinkedList<Etudiant> etudiants;

    /**
     * Construit un formulaire complet.
     *
     * @param dateCreation      date de création du formulaire
     * @param dateModification  date de dernière modification
     * @param epreuve           épreuve associée
     * @param etudiantFraudes   association étudiants/fraudes
     * @param etudiants         liste des étudiants concernés
     */
    public Formulaire(String dateCreation, String dateModification, Epreuve epreuve, HashMap<Etudiant, ArrayList<Fraude>> etudiantFraudes, LinkedList<Etudiant> etudiants) {
        compteur++;
        this.identifiant=compteur;
        this.DATE_CREATION = dateCreation;
        this.dateModification = dateModification;
        this.epreuve = epreuve;
        this.etudiantFraudes = etudiantFraudes;
        this.etudiants = etudiants;
    }

    /**
     * Construit un formulaire vide avec les dates de création
     * et de modification initialisées à la date courante.
     */
    public Formulaire() {
        this(Formulaire.generatDate(),Formulaire.generatDate(),null,new HashMap<>(), new LinkedList<>());
    }

    /**
     * Retourne l'identifiant du formulaire.
     *
     * @return identifiant du formulaire
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Retourne le nombre total de formulaires créés.
     *
     * @return compteur global des formulaires
     */
    public static int getCompteur() {
        return compteur;
    }

    /**
     * Retourne la date de création du formulaire.
     *
     * @return date de création
     */
    public String getDATE_CREATION() {
        return DATE_CREATION;
    }

    /**
     * Retourne la date de dernière modification.
     *
     * @return date de modification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * Retourne la correspondance entre étudiants et fraudes.
     *
     * @return dictionnaire étudiant/fraudes
     */
    public HashMap<Etudiant, ArrayList<Fraude>> getEtudiantFraudes() {
        return etudiantFraudes;
    }

    /**
     * Retourne la liste des étudiants du formulaire.
     *
     * @return liste des étudiants
     */
    public LinkedList<Etudiant> getEtudiants() {
        return etudiants;
    }

    /**
     * Retourne l'épreuve associée au formulaire.
     *
     * @return épreuve associée
     */
    public Systeme.Formulaire.Examen.Epreuve getEpreuve(){
        return epreuve;
    }

    /**
     * Retourne la liste des étudiants sous forme textuelle.
     *
     * @return tableau contenant les étudiants formatés
     */
    public String[] getEtudiantsFormulaire(){
        int nombreEtudiants = getNombreEtudiants();
        String[] etudiantsToString = new String[nombreEtudiants];
        for(int i=0; i<nombreEtudiants; i++){
            etudiantsToString[i] = etudiants.get(i).toString();
        }
        return etudiantsToString;
    }

    /**
     * Recherche les étudiants correspondant à un nom.
     *
     * @param nom nom recherché
     * @return liste des étudiants trouvés
     */
    public ArrayList<String> getEtudiantsNom(String nom){
        ArrayList<String> etudiantsNom = new ArrayList<>();
        for(Etudiant etudiant: etudiants){
            if(etudiant.getNOM().equals(nom)){
                etudiantsNom.add(etudiant.toString());
            }
        }
        return etudiantsNom;
    }

    /**
     * Recherche les étudiants correspondant à un prénom.
     *
     * @param prenom prénom recherché
     * @return liste des étudiants trouvés
     */
    public ArrayList<String> getEtudiantsPrenom(String prenom){
        ArrayList<String> etudiantsPrenom = new ArrayList<>();
        for(Etudiant etudiant: etudiants){
            if(etudiant.getPRENOM().equals(prenom)){
                etudiantsPrenom.add(etudiant.toString());
            }
        }
        return etudiantsPrenom;
    }

    /**
     * Recherche les étudiants correspondant à un numéro apprenant.
     *
     * @param numApp numéro apprenant recherché
     * @return liste des étudiants trouvés
     */
    public ArrayList<String> getEtudiantsNumApp(String numApp){
        ArrayList<String> etudiantsNumApp = new ArrayList<>();
        for(Etudiant etudiant: etudiants){
            if(etudiant.getNUMERO_APPRENANT().equals(numApp)){
                etudiantsNumApp.add(etudiant.toString());
            }
        }
        return etudiantsNumApp;
    }

    /**
     * Définit l'épreuve associée au formulaire.
     *
     * @param epreuve informations de l'épreuve
     */
    public void setEpreuve(String[] epreuve){
        this.epreuve = new Epreuve(epreuve);
    }

    /**
     * Retourne le nombre d'étudiants présents dans le formulaire.
     *
     * @return nombre d'étudiants
     */
    public int getNombreEtudiants(){
        return etudiants.size();
    }

    /**
     * Met à jour la date de dernière modification.
     */
    public void setDateModification() {
        this.dateModification = generatDate();
    }

    /**
     * Vérifie si le formulaire concerne une épreuve donnée.
     *
     * @param epreuveECUE code ECUE recherché
     * @return true si l'épreuve correspond, false sinon
     */
    public boolean containsEpreuve(String epreuveECUE){
        return getEpreuve().getECUE().equals(epreuveECUE);
    }

    /**
     * Vérifie si un étudiant est présent dans le formulaire.
     *
     * @param etudiantNumApp numéro apprenant recherché
     * @return true si l'étudiant est présent, false sinon
     */
    public boolean containsEtudiant(String etudiantNumApp){
        for(Etudiant etudiant: etudiants){
            if(etudiant.getNUMERO_APPRENANT().equals(etudiantNumApp)){
                return true;
            }
        }
        return false;
    }

    /**
     * Génère la date courante formatée.
     *
     * @return date courante formatée
     */
    public static String generatDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatteur = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateFormatee = date.format(dateFormatteur);
        return dateFormatee;
    }

    /**
     * Recherche un étudiant déjà présent dans le formulaire.
     *
     * @param etudiantStr informations de l'étudiant
     * @return l'étudiant trouvé ou null
     */
    private Etudiant containsEtudiantFormulaire(String[] etudiantStr){
        Etudiant etudiantSelect = new Etudiant(etudiantStr);
        for (Etudiant etudiant : etudiants) {
            if (etudiant.equals(etudiantSelect)) {
                return etudiant;
            }
        }
        return null;
    }

    /**
     * Retourne la description d'un paramètre selon un type de fraude.
     *
     * @param param   nom du paramètre recherché
     * @param numType identifiant du type de fraude
     * @return description correspondante
     */
    public static String getParamType(String param, String numType){
        return switch (param) {
            case "Description" -> getType(numType).getDescription();
            case "Attribut1" -> getType(numType).getDescriptionAttribut1();
            case "Attribut2" -> getType(numType).getDescriptionAttribut2();
            default -> "";
        };
    }

    /**
     * Ajoute un étudiant fraudeur ainsi que les fraudes associées.
     *
     * @param etudiantStr informations de l'étudiant
     * @param fraudesStr  liste des fraudes à enregistrer
     */
    public void creatEtudiantFraude(String[] etudiantStr, String[][] fraudesStr){
        Etudiant etudiant = containsEtudiantFormulaire(etudiantStr);

        if(etudiant != null){
            for (String[] fraudeStr : fraudesStr) {
                Fraude fraude = setType(fraudeStr);
                etudiantFraudes.get(etudiant).add(fraude);
            }
        }
        else{
            ArrayList<Fraude> fraudes = new ArrayList<>();
            for (String[] fraudeStr : fraudesStr) {
                Fraude fraude = setType(fraudeStr);
                fraudes.add(fraude);
            }
            etudiant = new Etudiant(etudiantStr);
            etudiants.add(etudiant);

            setEtudiantFraudes(etudiant,fraudes);
        }
    }

    /**
     * Associe un étudiant à sa liste de fraudes.
     *
     * @param etudiant étudiant fraudeur
     * @param fraudes  liste des fraudes associées
     */
    private void setEtudiantFraudes(Etudiant etudiant, ArrayList<Fraude> fraudes) {
        etudiantFraudes.put(etudiant,fraudes);
    }

    /**
     * Retourne le nombre total de fraudes enregistrées.
     *
     * @return nombre de fraudes
     */
    public int getnNbFraudes(){
        int nb=0;
        Collection<ArrayList<Fraude>> fraudesTot = etudiantFraudes.values();
        for(ArrayList<Fraude> fraudes : fraudesTot){
            nb += fraudes.size();
        }
        return nb;
    }

    /**
     * Retourne les informations d'un étudiant ainsi que ses fraudes.
     *
     * @param i indice de l'étudiant
     * @return liste contenant l'étudiant et ses fraudes
     */
    public ArrayList<String> getEtudiantFraudesToString(int i){
        Etudiant etudiant = etudiants.get(i);
        ArrayList<String> fraudes = new ArrayList<>();
        fraudes.add(etudiant.toString());
        for(Fraude fraude : etudiantFraudes.get(etudiant)){
            fraudes.add(fraude.toString());
        }
        return fraudes;
    }

    /**
     * Retourne une représentation textuelle du formulaire.
     *
     * @return description du formulaire
     */
    @Override
    public String toString() {
        return "Identifiant du formulaire: " + identifiant +
                ", Date de creation: " + DATE_CREATION +
                ", Date de la dernière modification: " + dateModification +
                ", Epreuve: " + epreuve.getECUE();
    }
}
