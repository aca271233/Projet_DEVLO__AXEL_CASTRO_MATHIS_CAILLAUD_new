package Systeme.Formulaire.Examen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static Systeme.Formulaire.Examen.Epreuve.getModalite;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EpreuveTest {

    private Epreuve epreuve;

    private static Collection<Object[]> dt(){
        Object[][] data = new Object[][]{
                {Modalite.ExamenEcrit,"1"},
                {Modalite.Oral,"2"},
                {Modalite.QCM,"3"},
                {Modalite.SurOrdinateur,"4"},
                {Modalite.Projet,"5"},
                {Modalite.TP,"6"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testConstructeurEpreuvePrincipal(){
        epreuve = new Epreuve("e356gdBe", "06/05/26", "13h30", "1h30",Modalite.SurOrdinateur);
        assertEquals("e356gdBe", epreuve.getECUE(), "Le code Ecue de l'épreuve n'a pas était correctement attribué");
        assertEquals("06/05/26", epreuve.getDATE(), "La date de l'épreuve n'a pas était correctement attribuée");
        assertEquals("13h30", epreuve.getHEURE(), "L'heure de l'épreuve n'a pas était correctement attribuée");
        assertEquals("1h30", epreuve.getDUREE(), "La durée de l'épreuve n'a pas était correctement attribuée");
        assertEquals(Modalite.SurOrdinateur, epreuve.getMODALITE(), "La modalité de l'épreuve n'a pas était correctement attribuée");
    }

    @Test
    public void testConstructeurEpreuveSecondaire(){
        String[] listeAttributs = {"e356gdBe", "06/05/26", "13h30", "1h30","4"};
        epreuve = new Epreuve(listeAttributs);
        assertEquals("e356gdBe", epreuve.getECUE(), "Le code Ecue de l'épreuve n'a pas était correctement attribué");
        assertEquals("06/05/26", epreuve.getDATE(), "La date de l'épreuve n'a pas était correctement attribuée");
        assertEquals("13h30", epreuve.getHEURE(), "L'heure de l'épreuve n'a pas était correctement attribuée");
        assertEquals("1h30", epreuve.getDUREE(), "La durée de l'épreuve n'a pas était correctement attribuée");
        assertEquals(Modalite.SurOrdinateur, epreuve.getMODALITE(), "La modalité de l'épreuve n'a pas était correctement attribuée");
    }

    @ParameterizedTest
    @MethodSource("dt")
    public void testGetModalite(Modalite modalite, String numModalite){
        assertEquals(modalite, getModalite(numModalite), "Le numéro de la modalité sélectionée ne correspond pas a la bonne modalité");
    }

    @Test
    public void testEquals(){
        epreuve = new Epreuve("e356gdBe", "06/05/26", "13h30", "1h30",Modalite.SurOrdinateur);
        Epreuve epreuve2 = new Epreuve("e356gdBe", "06/05/26", "13h30", "1h30",Modalite.SurOrdinateur);
        assertTrue(epreuve.equals(epreuve2), "Les deux instances épreuve ne sont pas égales");
    }
}
