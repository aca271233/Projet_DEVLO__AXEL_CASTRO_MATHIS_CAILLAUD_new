package Systeme.Formulaire;

import Systeme.Formulaire.Etudes.Cursus;
import Systeme.Formulaire.Etudes.Etudiant;
import Systeme.Formulaire.Fraude.Fraude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static Systeme.Formulaire.Formulaire.getCompteur;
import static Systeme.Formulaire.Fraude.Fraude.setType;
import static org.junit.jupiter.api.Assertions.*;

public class FormulaireTest {

    private Formulaire formulaire;

    private LinkedList<Etudiant> etudiants;

    @BeforeEach
    public void faireAvant(){
        etudiants = new LinkedList<>();
        etudiants.add(new Etudiant("Castro","Axel","1", Cursus.E3e));
        formulaire = new Formulaire(Formulaire.generatDate(),Formulaire.generatDate(),null,new HashMap<>(),etudiants);
    }

    @Test
    public void testConstructeur(){
        Formulaire formulaire = new Formulaire();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatteur = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateFormatee = date.format(dateFormatteur);
        assertEquals(dateFormatee,formulaire.getDATE_CREATION(),"La date de création n'est pas conforme");
        assertEquals(dateFormatee,formulaire.getDateModification(),"La date de modification n'est pas conforme");
        assertEquals(getCompteur(),formulaire.getIdentifiant(),"L'idantifiant n'est pas correctement généré");
        assertNull(formulaire.getEpreuve(),"L'epreuve n'est pas null");
        assertEquals(new HashMap<Etudiant, ArrayList<Fraude>>(), formulaire.getEtudiantFraudes(),"Le dictionnaire des étudiants et fraude ne correspond pas");
        assertEquals(new LinkedList<Etudiant>(), formulaire.getEtudiants(), "La liste des etudiants ne correspond pas");
    }

    @Test
    public void testGetEtudiantsFormulaire(){
        assertEquals("(1) Castro Axel -> E3e", formulaire.getEtudiantsFormulaire()[0], "La lsite des étudiants n'est pas conforme");
    }

    @Test
    public void testGetEtudiantsNom(){
        assertEquals("(1) Castro Axel -> E3e", formulaire.getEtudiantsNom("Castro").get(0),"Les etudiants retourner ne correspondent pas au nom saisi");
        assertEquals(new ArrayList<>(),formulaire.getEtudiantsNom("Francois"),"Les etudiants retourner correspondent au nom saisi");
    }

    @Test
    public void testGetEtudiantsPrenom(){
        assertEquals("(1) Castro Axel -> E3e", formulaire.getEtudiantsPrenom("Axel").get(0),"Les etudiants retourner ne correspondent pas au prénom saisi");
        assertEquals(new ArrayList<>(),formulaire.getEtudiantsPrenom("Francois"),"Les etudiants retourner correspondent au prénom saisi");
    }

    @Test
    public void testGetEtudiantsNumApp(){
        assertEquals("(1) Castro Axel -> E3e", formulaire.getEtudiantsNumApp("1").get(0),"Les etudiants retourner ne correspondent pas au numéro apprenant saisi");
        assertEquals(new ArrayList<>(),formulaire.getEtudiantsNumApp("4"),"Les etudiants retourner correspondent au numéro apprenant saisi");
    }

    @Test
    public void testSetDateDeModification(){
        formulaire.setDateModification();
        assertEquals(Formulaire.generatDate(), formulaire.getDateModification(), "La date de modification n'est pas mise à jour");
    }

    @Test
    public void testContainsEpreuve(){
        String[] epreuve = new String[]{"e3b12",null,null,null, "1"};
        formulaire.setEpreuve(epreuve);
        Assertions.assertTrue(formulaire.containsEpreuve("e3b12"),"L'epreuve n'est pas la bonne");
    }

    @Test
    public void testContainsEtudiant(){
        Assertions.assertTrue(formulaire.containsEtudiant("1"),"L'etudiant cherché n'est pas trouvé alors qu'il existe");
        Assertions.assertFalse(formulaire.containsEtudiant("2"), "L'étudiant cherché est trouvé alors qui n'existe pas");
    }

//    @Test
//    public void testContainsEtudiantFormulaire(){
//        String[] etudiant = new String[]{"1","Castro","Axel","3"};
//        assertEquals(new Etudiant(etudiant), formulaire.containsEtudiantFormulaire(etudiant),"L'etudiant n'est pas dans le formulaire");
//    }

    @Test
    public void testCreatEtudiantFraude(){
        String[] etudiant = new String[]{"Caillaud","Mathis","2","3"};
        String[][] fraude = new String[][]{{"20/06/2024", "4", "contenu test", "attribut1", "attribut2"}};
        HashMap<Etudiant, ArrayList<Fraude>> map = new HashMap<>();
        ArrayList<Fraude> fraudes = new ArrayList<>();
        fraudes.add(setType(fraude[0]));
        Etudiant etudiantInstance = new Etudiant(etudiant);
        map.put(etudiantInstance,fraudes);
        formulaire.creatEtudiantFraude(etudiant,fraude);
        LinkedList<Etudiant> etudiants = formulaire.getEtudiants();
        assertEquals(map.get(etudiantInstance),formulaire.getEtudiantFraudes().get(etudiants.get(1)),"La map des étuiants et fraudes n'est pas correctement créée");
        String[][] fraude2 = new String[][]{{"22/04/2026","2","contenu test2","att1","att2"}};
        fraudes.add(setType(fraude2[0]));
        map.put(etudiantInstance,fraudes);
        formulaire.creatEtudiantFraude(etudiant,fraude2);
        assertEquals(map.get(etudiantInstance),formulaire.getEtudiantFraudes().get(etudiants.get(1)),"La map des étuiants et fraudes n'est pas correctement créée");
    }


}