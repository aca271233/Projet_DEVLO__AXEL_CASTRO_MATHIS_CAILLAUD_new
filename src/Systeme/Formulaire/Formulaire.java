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
     * Instance par défaut de Formulaire
     * identifiant Identifiant du formulaire géneré automatiquement
     * @param dateCreation Date de création du formulaire
     * @param dateModification Date de la dernière modification apportée au formulaire
     * @param epreuve Epreuve liée au formulaire
     * @param etudiantFraudes Etudiant fraudeurs ainsi que les fraudes qui leurs sont associées
     * @param etudiants Liste des étudiants inscrits dans le formulaire
     */
    public Formulaire(String dateCreation, String dateModification, Systeme.Formulaire.Examen.Epreuve epreuve, HashMap<Etudiant, ArrayList<Fraude>> etudiantFraudes, LinkedList<Etudiant> etudiants) {
        compteur++;
        this.identifiant=compteur;
        this.DATE_CREATION = dateCreation;
        this.dateModification = dateModification;
        this.epreuve = epreuve;
        this.etudiantFraudes = etudiantFraudes;
        this.etudiants = etudiants;
    }

    /**
     * Crée une instance de Formulaire sans paramètres.
     */
    public Formulaire() {
        this(Formulaire.generatDate(),Formulaire.generatDate(),null,new HashMap<>(), new LinkedList<>());
    }

    /**
     * Acesseur de l'identifiant du formulaire
     * @return Identifiant du formulaire
     */
    public int getIdentifiant() {
        return identifiant;
    }

    public Systeme.Formulaire.Examen.Epreuve getEpreuve(){
        return epreuve;
    }


    /**
     * Acesseur de la liste d'étudians
     * @return etudiantsToString : tableau d'etudiants au format d'affichage String
     */
    public String[] getEtudiantsFormulaire(){
        int nombreEtudiants = getNombreEtudiants();
        String[] etudiantsToString = new String[nombreEtudiants];
        for(int i=0; i<nombreEtudiants; i++){
            etudiantsToString[i] = etudiants.get(i).toString();
        }
        return etudiantsToString;
    }

    public ArrayList<String> getEtudiantsNom(String nom){
        ArrayList<String> etudiantsNom = new ArrayList<>();
        for(Etudiant etudiant: etudiants){
            if(etudiant.getNOM().equals(nom)){
                etudiantsNom.add(etudiant.toString());
            }
        }
        return etudiantsNom;
    }

    public ArrayList<String> getEtudiantsPrenom(String prenom){
        ArrayList<String> etudiantsPrenom = new ArrayList<>();
        for(Etudiant etudiant: etudiants){
            if(etudiant.getPRENOM().equals(prenom)){
                etudiantsPrenom.add(etudiant.toString());
            }
        }
        return etudiantsPrenom;
    }

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
     * Définie l'épreuve concerné
     */
    public void setEpreuve(String[] epreuve){
        this.epreuve = new Epreuve(epreuve);
    }

    public int getNombreEtudiants(){
        return etudiants.size();
    }

    public void setDateModification() {
        this.dateModification = generatDate();
    }

    public boolean containsEpreuve(String epreuveECUE){
        return getEpreuve().getECUE().equals(epreuveECUE);
    }

    public boolean containsEtudiant(String etudiantNumApp){
        for(Etudiant etudiant: etudiants){
            if(etudiant.getNUMERO_APPRENANT().equals(etudiantNumApp)){
                return true;
            }
        }
        return false;
    }

    /**
     * Génère un String représentant la date à l'instant où la méthode est apellée.
     * @return dateFormatee : String
     */
    public static String generatDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatteur = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateFormatee = date.format(dateFormatteur);
        return dateFormatee;
    }

    private Etudiant containsEtudiantFormulaire(String[] etudiantStr){
        Etudiant etudiantSelect = new Etudiant(etudiantStr);
        for (Etudiant etudiant : etudiants) {
            if (etudiant.equals(etudiantSelect)) {
                return etudiant;
            }
        }
        return null;
    }
    public static String getParamType(String param, String numType){
        return switch (param) {
            case "Description" -> getType(numType).getDescription();
            case "Attribut1" -> getType(numType).getDescriptionAttribut1();
            case "Attribut2" -> getType(numType).getDescriptionAttribut2();
            default -> "";
        };
    }

    /**
     * Défini les fraudes à deux attributs
     * @param etudiantStr Liste de String des paramètres pour un étudiant
     * @param fraudesStr Liste de Listes de String des paramètres pour chaque fraudes
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
     * Défini les fraudes associées à un etudiant fraudeur
     * @param etudiant Etudiant fraudeur
     * @param fraudes Fraudes associées à l'étudiant
     */
    private void setEtudiantFraudes(Etudiant etudiant, ArrayList<Fraude> fraudes) {
        etudiantFraudes.put(etudiant,fraudes);
    }

    public int getnNbFraudes(){
        int nb=0;
        Collection<ArrayList<Fraude>> fraudesTot = etudiantFraudes.values();
        for(ArrayList<Fraude> fraudes : fraudesTot){
            nb += fraudes.size();
        }
        return nb;
    }

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
     *  Affichage des attributs du formulaire
     * @return String
     */
    @Override
    public String toString() {
        return "Identifiant du formulaire: " + identifiant +
                ", Date de creation: " + DATE_CREATION +
                ", Date de la dernière modification: " + dateModification +
                ", Epreuve: " + epreuve.getECUE();
    }
}
