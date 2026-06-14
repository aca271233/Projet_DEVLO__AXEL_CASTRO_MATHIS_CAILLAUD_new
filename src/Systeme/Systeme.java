package Systeme;

import Systeme.Formulaire.Examen.Epreuve;
import Systeme.Formulaire.Formulaire;

//import org.jgrapht.Graph;
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.SimpleGraph;
//import org.jgrapht.nio.dot.DOTExporter;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

import static Systeme.Formulaire.Formulaire.getParamType;

/**
 * Le système gère la sauvgarde de tous les formulaires.
 * Calcul des statistiques liées aux fomulaires.
 * Genère un graphe de corrélation entre les differents étudiants fraudeurs.
 * @author axel
 */
public final class Systeme {

    private static final String[] listeInviteFormulaire = new String[]{
            "Insérer le nom de l'étudiant","Insérer le prénom de l'étudiant",
            "Insérer le numéro apprenant de l'étudiant","Choisir le numéro du cursus concerné",
            "Insérer la date de relevée de la fraude (jj/mm/aaaa)","Choisir le numéro du type de fraude concerné",
            "Ajouter un contenu à la fraude","Choisir l'identifiant du formulaire concerné"};

    private static final String[] listeInviteEpreuve = new String[]{
            "Insérer le code ECUE de l'épreuve","Choisir le numéro de l'épreuve concernée"};

    /**
     * Liste de tous les formulaires reseignés
     */
    private LinkedList<Formulaire> formulaires;

    private LinkedList<String[]> epreuves;

    /**
     * Initialise le système de gestion des formulaires de fraude.
     * <p>
     * Crée les listes de formulaires et d'épreuves puis charge
     * des données de démonstration.
     * </p>
     */
    public Systeme() {
        this.formulaires = new LinkedList<>();
        this.epreuves = new LinkedList<>();

        String[] a = {"es32fd","12/06/2026","13:30","1h30","1"};
        String[] b = {"aQd2F3","02/08/2026","8:15","2h00","4"};
        epreuves.add(a);
        epreuves.add(b);

        String[][] formulaireStr = new String[][]{
                {"Boyer", "Brun", "Garcia", "Bonnet"},
                {"Maria", "Louis", "Victor", "Carla"},
                {"44912","43617","42500","40189"},
                {"1","2","3","4"},
                {"02/06/2026","24/12/2024","07/01/2025","14/07/2026"},
                {"1","2","3","4"},
                {"Utilise son téléphone en haut-parleur en plein milieu de l'examen", "Utilisation d'anti-sèches non-autorisées"
                        ,"Calculatrice non-autorisées"
                        ,"Utilisation de l'IAG durant l'épreuve en se conectant au réseau depuis une autre adresse IP"},
                {"ChatGPT","A4","Ti","Claude"},
                {null,"Bon","Aucuns","192.168.1.93"}
        };
        addFormulaire("0", formulaireStr);
        formulaireStr = new String[][]{
                {"Bernard", "De Sousa Violante"},
                {"Pierre", "Sylvain"},
                {"40301","57817"},
                {"3","1"},
                {"25/12/2025","25/12/2025"},
                {"2","2"},
                {"Partage de feuilles de brouillon avec les réponses", "Utilisation de feuilles de brouillon avec les réponses"},
                {"22cmx8cm","22cmx8cm"},
                {"Pliée en 8","Pliée en 8"}
        };
        addFormulaire("0", formulaireStr);
        formulaireStr = new String[][]{
                {"Bernard","Bernard", "De Sousa Violante","Bonnet"},
                {"Pierre","Pierre", "Sylvain","Carla"},
                {"40301","40301","57817","40189"},
                {"3","3","1","4"},
                {"23/01/2026","27/01/2026","27/01/2026","04/02/2026"},
                {"1","4","4","4"},
                {"Utilistaion de l'IAG sur le téléphone","Utilisation de l'IAG sur poste de laboratoire",
                        "Utilisation de l'IAG sur poste de laboratoire", "Utilisation de l'IAG sur poste de laboratoire"},
                {"ChatGPT","Microsoft Coplilot","Gemini","Gemini"},
                {null,"27.175.182.70","111.48.118.151","81.56.48.46"}
        };
        addFormulaire("1", formulaireStr);
    }

    /**
     * Calcule la moyenne du nombre de fraudes par formulaire.
     *
     * @return la moyenne des fraudes enregistrées par formulaire
     */
    private double getMoyenne() {
        return (double) getTotalFraudes() / getNombreFormulaires();
    }

    /**
     * Calcule l'écart-type du nombre de fraudes par formulaire.
     *
     * @return l'écart-type du nombre de fraudes
     */
    private double getEcartType() {
        double sumTotal=0;
        double moyenne = getMoyenne();
        for (Formulaire formulaire : formulaires) {
            sumTotal += Math.pow(formulaire.getnNbFraudes() - moyenne, 2);
        }
        return Math.sqrt(sumTotal/getTotalFraudes());
    }

    /**
     * Calcule le nombre total de fraudes enregistrées.
     *
     * @return le nombre total de fraudes
     */
    private int getTotalFraudes() {
        int totalFraudes = 0;
        for (Formulaire formulaire : formulaires) {
            totalFraudes += formulaire.getnNbFraudes();
        }
        return totalFraudes;
    }

    /**
     * Récupère la liste des étudiants fraudeurs sans doublon.
     *
     * @return la liste des étudiants distincts concernés par une fraude
     */
    private ArrayList<String> getListeEtudiants() {
        ArrayList<String> listNombreEtudiants = new ArrayList<>();
        for (Formulaire formulaire : formulaires) {
            for (int i=0; i < formulaire.getNombreEtudiants(); i++) { /// /A modifié par getNombreEtudiants
                listNombreEtudiants.remove(formulaire.getEtudiantsFormulaire()[i]);/// /A modifié par getNombreEtudiants
                listNombreEtudiants.add(formulaire.getEtudiantsFormulaire()[i]);/// /A modifié par getNombreEtudiants
            }
        }
        return listNombreEtudiants;
    }

    private int getNombreEtudiants(){
        return getListeEtudiants().size();
    }

    /**
     * Retourne le nombre total de formulaires enregistrés.
     *
     * @return le nombre de formulaires
     */
    private int getNombreFormulaires() {
        return formulaires.size();
    }

    /**
     * Génère la matrice d'adjacence représentant les relations entre
     * étudiants fraudeurs apparaissant dans un même formulaire.
     *
     * @return la matrice d'adjacence du graphe des étudiants fraudeurs
     */
    private int[][] getGrapheEtudiants() {
        ArrayList<String> etudiants = getListeEtudiants();
        int tailleMatrice = etudiants.size();
        int[][] grapheEtudiants = new int[tailleMatrice][tailleMatrice];
        for (Formulaire formulaire : formulaires) {
            String[] etudiantsFromulaire = formulaire.getEtudiantsFormulaire();
            String etudiant;
            String etudiantPlagia;
            int tailleEtudiantsFromulaire = etudiantsFromulaire.length;
            for (int i = 0; i < tailleEtudiantsFromulaire - 1; i++) {
                etudiant = etudiantsFromulaire[i];

                for (int j = i + 1; j < tailleEtudiantsFromulaire; j++) {
                    etudiantPlagia = etudiantsFromulaire[j];

                    int indiceLigne = -1;
                    int indiceColonne = -1;
                    for (int n = 0; n < tailleMatrice; n++) {
                        if (etudiants.get(n).equals(etudiant)) {
                            indiceLigne = n;
                            if (indiceColonne != -1) {
                                break;
                            }
                        }
                        if (etudiants.get(n).equals(etudiantPlagia)) {
                            indiceColonne = n;
                            if (indiceLigne != -1) {
                                break;
                            }
                        }
                    }
                    grapheEtudiants[indiceLigne][indiceColonne] = 1;
                    grapheEtudiants[indiceColonne][indiceLigne] = 1;
                }
            }
        }
        return grapheEtudiants;
    }

    /**
     * Affiche dans la console les caractéristiques du graphe ainsi que
     * sa matrice d'adjacence.
     */
    private void afficheGraphe() {
        int[][] graphe = getGrapheEtudiants();
        int NombreSommets = graphe.length;
        ArrayList<String> etudiants = getListeEtudiants();

        System.out.println ("- Graphe ayant "+NombreSommets+ " sommet(s).");
        System.out.println ("- Matrice du graphe :");


        // affichage d'indices au dessus de la matrice afin d'y voir plus clair
        System.out.print(" ");
        for (int i = 0; i<NombreSommets; i++)
            System.out.print("  "+i);
        System.out.println();

        // affichage d'un trait au dessus de la matrice pour que ça soit plus joli
        for (int i = 0; i<=NombreSommets; i++)
            System.out.print("---");
        System.out.println();


        // affichage du contenu de la matrice, ligne par ligne
        for (int i = 0; i<NombreSommets; i++) {
            System.out.print("|  ");
            for (int j = 0; j<NombreSommets; j++) {
                System.out.print(graphe[i][j]+"  ");
            }
            System.out.println("|  " + etudiants.get(i));
        }


        // affichage d'un trait en dessous de la matrice pour que ça soit plus joli
        for (int i = 0; i<=NombreSommets; i++)
            System.out.print("---");
        System.out.println();

        System.out.print(" ");
        int i=0;
        int j;
        int compteur;
        String builder;
        char c;
        do{
            builder = "";
            compteur = 0;
            for(j =0;j<etudiants.size(); j++){
                if(i<etudiants.get(j).length()){
                    c = etudiants.get(j).charAt(i);
                    builder +="  "+c;
                    compteur+=1;
                }
                else {
                    builder += "   ";
                }
            }
            System.out.println(builder);
            i++;
        }while(!(compteur == 0));

        System.out.println();
        System.out.println("Pour generer le graphe: ");
//        genereGraphe(graphe);
        System.out.println("Copier le tout sur GraphvizOnline:");
        System.out.println("https://dreampuf.github.io/GraphvizOnline");
    }

    /**
     * Génère la représentation Graphviz du graphe des étudiants fraudeurs.
     *
     * @param matriceGraphe matrice d'adjacence du graphe
     */
//    private void genereGraphe(int[][] matriceGraphe){
//        int tailleMatrice = matriceGraphe.length;
//        ArrayList<String> etudiants = getListeEtudiants();
//        for(int i=0; i<etudiants.size(); i++){
//            String etudiant = etudiants.get(i);
//            String[] etudiantFormate = etudiant.split(" ");
//            int j = 1;
//            etudiant="";
//            while (Character.isLetter(etudiantFormate[j].charAt(0))){
//                etudiant += etudiantFormate[j];
//                if(j<etudiantFormate.length-3){
//                    etudiant += "_";
//                }
//                j++;
//            }
//            etudiants.set(i,etudiant);
//        }
//        // Création du graphe
//        Graph<String, DefaultEdge> graphe =
//                new SimpleGraph<>((DefaultEdge.class));
//
//        // Ajouter des sommets
//        for(String etudiant: etudiants){
//            graphe.addVertex(etudiant);
//        }
//
//        // Ajouter des arêtes
//        for(int i=0; i<tailleMatrice; i++){
//            for(int j=0; j<tailleMatrice; j++){
//                if(matriceGraphe[i][j] == 1){
//                    graphe.addEdge(etudiants.get(i),etudiants.get(j));
//                }
//            }
//        }
//
//        // Affichage simple
//        DOTExporter<String, DefaultEdge> exporter =
//                new DOTExporter<>(v -> v);
//
//        StringWriter writer = new StringWriter();
//        exporter.exportGraph(graphe, writer);
//
//        System.out.println(writer);
//    }

    /**
     * Affiche les statistiques globales du système ainsi que le graphe
     * des étudiants fraudeurs.
     */
    public void affichageStatistiques(){
        System.out.printf("Moyenne du nombre de fraudes par formulaires: %.2f%n", getMoyenne());
        System.out.printf("Ecart-type du nombre de fraudes par formulaires: %.2f%n", getEcartType());
        System.out.println("Nombre de fraudes: "+getTotalFraudes());
        System.out.println("Nombre d'étudiants: "+getNombreEtudiants());
        System.out.println("Nombre de formulaires: "+getNombreFormulaires());
        System.out.println("Graphe des étudiants: ");
        afficheGraphe();
    }

    /**
     * Affiche les informations générales d'un formulaire.
     *
     * @param formulaire formulaire à afficher
     */
    private void affichageFormulaire(Formulaire formulaire){
        System.out.println(formulaire.toString());
    }

    /**
     * Affiche le détail des fraudes contenues dans un formulaire.
     *
     * @param formulaire formulaire à afficher
     */
    private void affichageContenuFormulaire(Formulaire formulaire){
        int nbEtudaints = formulaire.getNombreEtudiants();
        for(int i=0; i<nbEtudaints; i++){
            ArrayList<String> etudiant = formulaire.getEtudiantFraudesToString(i);
            System.out.println(etudiant.get(0));
            for(int j=1; j<etudiant.size(); j++) {
                System.out.println(j+"-"+etudiant.get(j));
            }
        }
    }

    /**
     * Affiche une liste de formulaires.
     *
     * @param contenu     indique si le contenu détaillé doit être affiché
     * @param formulaires liste des formulaires à afficher
     */
    private void affichageFormulaires(boolean contenu, LinkedList<Formulaire> formulaires){
        if(formulaires.isEmpty()){
            throwNone();
        }
        else{
            for(Formulaire formulaire: formulaires){
                affichageFormulaire(formulaire);
                if(contenu){
                    affichageContenuFormulaire(formulaire);
                }
            }
        }
    }

    /**
     * Affiche une liste d'étudiants.
     *
     * @param etudiantsStr liste des étudiants à afficher
     */
    private void afficheEtudiants(ArrayList<String> etudiantsStr){
        if(etudiantsStr.isEmpty()){
            throwNone();
        }
        else{
            for(String etudiant: etudiantsStr){
                System.out.println(etudiant);
            }
        }
    }

    /**
     * Recherche les étudiants à partir de leur nom.
     *
     * @param nom nom recherché
     */
    public void searchEtudiantsNom(String nom){
        ArrayList<String> etudiantsNom = new ArrayList<>();
        for(Formulaire formulaire: formulaires){
            for(String etudiant: formulaire.getEtudiantsNom(nom)){
                etudiantsNom.remove(etudiant);
                etudiantsNom.add(etudiant);
            }
        }
        afficheEtudiants(etudiantsNom);
    }

    /**
     * Recherche les étudiants à partir de leur prénom.
     *
     * @param nom prénom recherché
     */
    public void searchEtudiantsPrenom(String nom){
        ArrayList<String> etudiantsPrenom = new ArrayList<>();
        for(Formulaire formulaire: formulaires){
            for(String etudiant: formulaire.getEtudiantsPrenom(nom)){
                etudiantsPrenom.remove(etudiant);
                etudiantsPrenom.add(etudiant);
            }
        }
        afficheEtudiants(etudiantsPrenom);
    }

    /**
     * Recherche un étudiant à partir de son numéro apprenant.
     *
     * @param numApp numéro apprenant recherché
     */
    public void searchEtudiantsNumApp(String numApp){
        ArrayList<String> etudiantNumApp = new ArrayList<>();
        for(Formulaire formulaire: formulaires){
            etudiantNumApp = formulaire.getEtudiantsNumApp(numApp);
            if(!etudiantNumApp.isEmpty()){
                break;
            }
        }
        afficheEtudiants(etudiantNumApp);
    }

    /**
     * Recherche les formulaires associés à une épreuve.
     *
     * @param scanner scanner utilisé pour la saisie utilisateur
     */
    public void searchFormulairesEpreuve(Scanner scanner){
        String[] epreuve = new String[1];
        inviteFormulaireEtudiantEpreuve(scanner, epreuve, 2);
        LinkedList<Formulaire> formulairesEpreuve = new LinkedList<>();
        for (Formulaire formulaire : formulaires) {
            if (formulaire.containsEpreuve(epreuve[0])) {
                formulairesEpreuve.add(formulaire);
            }
        }
        affichageFormulaires(true, formulairesEpreuve);
    }

    /**
     * Recherche les formulaires associés à un étudiant.
     *
     * @param scanner scanner utilisé pour la saisie utilisateur
     */
    public void searchFormulairesEtudiant(Scanner scanner){
        String[] etudiant = new String[1];
        inviteFormulaireEtudiantEpreuve(scanner, etudiant, 1);
        LinkedList<Formulaire> formulairesEtudiant = new LinkedList<>();
        for (Formulaire formulaire : formulaires) {
            if (formulaire.containsEtudiant(etudiant[0])) {
                formulairesEtudiant.add(formulaire);
            }
        }
        affichageFormulaires(true, formulairesEtudiant);
    }

    /**
     * Ajoute un formulaire à la liste des formulaires enregistrés.
     *
     * @param formulaire formulaire à ajouter
     */
    private void addFormulaires(Formulaire formulaire){
        formulaires.add(formulaire);
    }

    /**
     * Supprime un formulaire à partir de son identifiant.
     *
     * @param identifiant identifiant du formulaire à supprimer
     */
    public void deleteFormulaire(String identifiant){
        boolean trouve = false;
        if(verificationInt(identifiant)){
            for(int i=0; i < getNombreFormulaires(); i++){
                if(formulaires.get(i).getIdentifiant() == Integer.parseInt(identifiant)){
                    formulaires.remove(i);
                    trouve = true;
                    throwSuccess();
                    break;
                }
            }
            if(!trouve){
                throwNone();
            }
        }
    }

    /**
     * Crée et ajoute un nouveau formulaire.
     *
     * @param epreuveNum    numéro de l'épreuve associée
     * @param formulaireStr données du formulaire sous forme matricielle
     */
    private void addFormulaire(String epreuveNum, String[][] formulaireStr){
        Formulaire formulaire = new Formulaire();
        formulaire.setEpreuve(epreuves.get(Integer.parseInt(epreuveNum)));
        addEtudiantsFraudes(formulaire, formulaireStr);
        addFormulaires(formulaire);
    }

    /**
     * Modifie un formulaire existant.
     *
     * @param NumFormulaire identifiant du formulaire à modifier
     * @param formulaireStr nouvelles données du formulaire
     */
    private void modifFormulaire(String NumFormulaire, String[][] formulaireStr){
        for(Formulaire formulaire: formulaires){
            if(formulaire.getIdentifiant() == Integer.parseInt(NumFormulaire)){
                formulaire.setDateModification();
                addEtudiantsFraudes(formulaire,formulaireStr);
                break;
            }
        }
    }

    /**
     * Ajoute les étudiants fraudeurs et leurs fraudes dans un formulaire.
     *
     * @param formulaire    formulaire à compléter
     * @param formulaireStr données du formulaire
     */
    private void addEtudiantsFraudes(Formulaire formulaire, String[][] formulaireStr){
        int nombreElementsFormulaire = formulaireStr[0].length;
        for(int k=0; k <nombreElementsFormulaire;){

            String[] etudiant = new String[4];
            etudiant[0] = formulaireStr[0][k];
            etudiant[1] = formulaireStr[1][k];
            etudiant[2] = formulaireStr[2][k];
            etudiant[3] = formulaireStr[3][k];

            String etudiantActuel = formulaireStr[2][k];
            int fraudesPourEtudiant = 0;
            do{
                fraudesPourEtudiant++;
                k++;
                if(k>=nombreElementsFormulaire){
                    break;
                }
            }while(etudiantActuel.equals(formulaireStr[2][k]));
            String[][] fraudes = new String[fraudesPourEtudiant][5];
            for(int i=0; i<fraudesPourEtudiant; i++){
                fraudes[i][0] = formulaireStr[4][k-fraudesPourEtudiant+i];
                fraudes[i][1] = formulaireStr[5][k-fraudesPourEtudiant+i];
                fraudes[i][2] = formulaireStr[6][k-fraudesPourEtudiant+i];
                fraudes[i][3] = formulaireStr[7][k-fraudesPourEtudiant+i];
                fraudes[i][4] = formulaireStr[8][k-fraudesPourEtudiant+i];
            }
            formulaire.creatEtudiantFraude(etudiant,fraudes);
        }
    }

    /**
     * Vérifie qu'une chaîne ne contient que des lettres,
     * espaces ou tirets.
     *
     * @param motAVerif chaîne à vérifier
     * @return true si la chaîne est valide, false sinon
     */
    private static boolean verificationStr(String motAVerif){
        boolean valide = true;
        int tailleMot = motAVerif.length();
        if(tailleMot == 0){
            valide = false;
        }
        for(int i=0; i<tailleMot; i++){
            if (!Character.isLetter(motAVerif.charAt(i)) && motAVerif.charAt(i)!=' ' && motAVerif.charAt(i)!='-') {
                valide = false;
                break;
            }
        }
        if(!valide){
            throwError();
        }
        return valide;
    }

    /**
     * Vérifie qu'une chaîne représente un entier positif.
     *
     * @param nbAVerif valeur à vérifier
     * @return true si la valeur est valide, false sinon
     */
    private static boolean verificationInt(String nbAVerif){
        boolean valide = true;
        int tailleNb = nbAVerif.length();
        if(tailleNb == 0){
            valide = false;
        }
        for(int i=0; i<tailleNb; i++){
            if (!Character.isDigit(nbAVerif.charAt(i))) {
                valide = false;
                break;
            }
        }
        if(!valide){
            throwError();
        }
        return valide;
    }

    /**
     * Vérifie la validité d'une date au format jj/mm/aaaa.
     *
     * @param date date à vérifier
     * @return true si la date est valide, false sinon
     */
    private static boolean verificationDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            throwError();
            return false;
        }
    }

    /**
     * Vérifie qu'une adresse IP est valide.
     *
     * @param adresseIP adresse IP à vérifier
     * @return true si l'adresse IP est valide, false sinon
     */
    private static boolean verificationAdresseIP(String adresseIP){
        if(!adresseIP.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}")){
            throwError();
            return false;
        }
        String[] parties = adresseIP.split("\\.");
        for(String valeur: parties){
            if(Integer.parseInt(valeur) < 0 || Integer.parseInt(valeur) >= 256){
                throwError();
                return false;
            }
        }
        return true;
    }

    /**
     * Affiche une question puis retourne la réponse saisie.
     *
     * @param scanner scanner utilisé pour la lecture
     * @param question question à afficher
     * @return réponse saisie par l'utilisateur
     */
    public static String inviteQuestion(Scanner scanner, String question){
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Affiche un message indiquant qu'aucun résultat n'a été trouvé.
     */
    private static void throwNone(){
        System.out.println("Aucuns résultats trouvés");
    }

    /**
     * Affiche un message indiquant que l'opération a réussi.
     */
    private static void throwSuccess(){
        System.out.println("Votre saisie a bien été prise en compte");
    }

    /**
     * Affiche un message d'erreur de saisie.
     */
    private static void throwError(){
        System.out.println("Erreur de saisie");
    }

    /**
     * Affiche la liste des formulaires et demande la sélection
     * d'un identifiant.
     *
     * @param scanner scanner utilisé pour la lecture
     * @return identifiant du formulaire sélectionné
     */
    public String inviteSelectionFormulaire(Scanner scanner){
        affichageFormulaires(false, formulaires);
        return inviteQuestion(scanner,listeInviteFormulaire[7]);
    }

    /**
     * Affiche les cursus ou les types de fraude disponibles.
     *
     * @param param "Cursus" ou "Fraudes"
     */
    private void affichageCursusFraudes(String param){
        if(param.equals("Cursus")){
            System.out.println("1.E1");
            System.out.println("2.E2");
            System.out.println("3.E3e");
            System.out.println("4.E3a");
            System.out.println("5.E4");
            System.out.println("6.E5");
        } else if (param.equals("Fraudes")) {
            System.out.println("1.IAG: "+getParamType("Description","1"));
            System.out.println("2.PAPIER: "+getParamType("Description","2"));
            System.out.println("3.CALCULATRICE: "+getParamType("Description","3"));
            System.out.println("4.IAG_CONNECTEE: "+getParamType("Description","4"));
        }
    }

    /**
     * Affiche le libellé d'un attribut spécifique à un type de fraude.
     *
     * @param attribut attribut demandé
     * @param numType  type de fraude concerné
     */
    private void affichageAttribut1Attribut2(String attribut, String numType){
        System.out.println(getParamType(attribut,numType)+":");
    }

    /**
     * Affiche la liste des épreuves disponibles.
     */
    private void affichageEpreuves(){
        String[] epreuveStr;
        for(int i=0; i<epreuves.size(); i++){
            epreuveStr = epreuves.get(i);
            Epreuve epreuve = new Epreuve(epreuveStr);
            String affichage = i +". " + epreuve;
            System.out.println(affichage);
        }
    }

    /**
     * Guide l'utilisateur dans la saisie des informations
     * d'un formulaire, d'un étudiant ou d'une épreuve.
     *
     * @param scanner              scanner utilisé pour la lecture
     * @param reponsesFormulaire   tableau recevant les réponses
     * @param param                type de saisie à effectuer
     * @return true si une erreur est détectée, false sinon
     */
    public boolean inviteFormulaireEtudiantEpreuve(Scanner scanner, String[] reponsesFormulaire, int param){
        String[] listeInvite;
        int tailleFormulaire = reponsesFormulaire.length;
        if(param==0){
            listeInvite = listeInviteFormulaire;
            String numType = "";
            String reponse;
            for(int i=0; i< tailleFormulaire; i++) {
                switch (i)
                {
                    case 0, 1:
                    {
                        reponse = inviteQuestion(scanner,listeInvite[i]);
                        if(!verificationStr(reponse)){
                            return true;
                        }
                        String[] parties1 = reponse.split(" ");
                        reponsesFormulaire[i]="";
                        for(int j=0; j<parties1.length; j++){
                            String[] parties2 = parties1[j].split("-");
                            for(int k=0; k<parties2.length; k++){
                                reponsesFormulaire[i] += parties2[k].substring(0, 1).toUpperCase()
                                        + parties2[k].substring(1).toLowerCase();
                                if(k < parties2.length - 1){
                                    reponsesFormulaire[i] += "-";
                                }
                            }
                            if(j < parties1.length - 1){
                                reponsesFormulaire[i] += " ";
                            }
                        }
                        break;
                    }
                    case 2:
                    {
                        reponse = inviteQuestion(scanner,listeInvite[i]);
                        if(!verificationInt(reponse)){
                            return true;
                        }
                        reponsesFormulaire[i] = reponse;
                        break;
                    }
                    case 3:
                    {
                        affichageCursusFraudes("Cursus");
                        reponse = inviteQuestion(scanner,listeInvite[i]);
                        if(!verificationInt(reponse)){
                            return true;
                        }
                        else if(Integer.parseInt(reponse) > 6 || Integer.parseInt(reponse)<1){
                            throwError();
                            return true;
                        }
                        reponsesFormulaire[i] = reponse;
                        break;
                    }
                    case 4:
                    {
                        reponse = inviteQuestion(scanner,listeInvite[i]);
                        if(!verificationDate(reponse)){
                            return true;
                        }
                        reponsesFormulaire[i] = reponse;
                        break;
                    }
                    case 5:
                    {
                        affichageCursusFraudes("Fraudes");
                        reponse = inviteQuestion(scanner,listeInvite[i]);
                        if(!verificationInt(reponse)){
                            return true;
                        }
                        else if(Integer.parseInt(reponse) > 4 || Integer.parseInt(reponse)<1){
                            throwError();
                            return true;
                        }
                        reponsesFormulaire[i] = reponse;
                        numType = reponsesFormulaire[5];
                        if(numType.equals("1")){
                            tailleFormulaire--;
                        }
                        break;
                    }
                    case 6:
                    {
                        reponsesFormulaire[i] = inviteQuestion(scanner,listeInvite[i]);
                        break;
                    }
                    case 7:
                    {
                        affichageAttribut1Attribut2("Attribut1",numType);
                        reponsesFormulaire[i] = scanner.nextLine();
                        break;
                    }
                    case 8:
                    {
                        affichageAttribut1Attribut2("Attribut2",numType);
                        reponsesFormulaire[i] = scanner.nextLine();
                        if(numType.equals("4") && !verificationAdresseIP(reponsesFormulaire[i])){
                            return true;
                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        } else if (param==1) {
            listeInvite = listeInviteFormulaire;
            reponsesFormulaire[0] = inviteQuestion(scanner,listeInvite[2]);
            verificationInt(reponsesFormulaire[0]);
        } else if(param==2){
            listeInvite = listeInviteEpreuve;
            reponsesFormulaire[0] = inviteQuestion(scanner,listeInvite[0]);
        }
        return false;
    }

    /**
     * Permet la création ou la modification d'un formulaire.
     *
     * @param scanner scanner utilisé pour la saisie
     * @param add     true pour ajouter, false pour modifier
     */
    public void inviteFormulaire(Scanner scanner, boolean add){
        String[] reponsesFormulaire = new String[]{null,null,null,null,null,null,null,null,null};
        String choix1;
        boolean erreur;
        ArrayList<ArrayList<String>> formulaire = new ArrayList<>();
        String num;
        if(add){
            affichageEpreuves();
            num = inviteQuestion(scanner, listeInviteEpreuve[1]);
        }
        else{
            num = inviteSelectionFormulaire(scanner);
        }
        if((verificationInt(num))) {
            if(Integer.parseInt(num) < epreuves.size()){
                do {
                    erreur = inviteFormulaireEtudiantEpreuve(scanner, reponsesFormulaire, 0);
                    do {
                        choix1 = inviteQuestion(scanner, "Rajouter un étudiant(R)/Terminer(T)/annuler(A)").toUpperCase();
                    }while (!choix1.equals("T") && !choix1.equals("A") && !choix1.equals("R"));
                    if((choix1.equals("R") || choix1.equals("T")) && !erreur){
                        ArrayList<String> etudiant = new ArrayList<>();
                        Collections.addAll(etudiant, reponsesFormulaire);
                        formulaire.add(etudiant);
                    }
                } while ((!(choix1.equals("T") || choix1.equals("A"))));
                if (choix1.equals("T") && !erreur) {
                    int tailleFormulaire = formulaire.size();
                    String[][] formulaireStr = new String[9][tailleFormulaire];
                    for (int j = 0; j < 9; j++) {
                        for (int k = 0; k < tailleFormulaire; k++) {
                            formulaireStr[j][k] = formulaire.get(k).get(j);
                        }
                    }
                    if(add){
                        addFormulaire(num, formulaireStr);
                    }
                    else{
                        modifFormulaire(num, formulaireStr);
                    }
                    throwSuccess();
                }
            }
            else{
                throwError();
            }
        }
    }

    /**
     * Affiche le message par défaut lorsqu'un choix utilisateur
     * est invalide.
     */
    public void affichageDefault(){
        System.out.println("⚠ Erreur dans votre choix ⚠");
        System.out.println("   Marquué 1, 2, 3 ou 4 ");
        System.out.println("⭣ pour faire la selection ⭣");
    }
}