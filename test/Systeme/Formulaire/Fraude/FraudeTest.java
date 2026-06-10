package Systeme.Formulaire.Fraude;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static Systeme.Formulaire.Fraude.Fraude.getType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FraudeTest {

    private Fraude fraude;

    private static Collection<Object[]> dt(){
        Object[][] data = new Object[][]{
                {Type.IAG,"1"},
                {Type.PAPIER,"2"},
                {Type.CALCULATRICE,"3"},
                {Type.IAG_CONNECTEE,"4"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testConstructeurFraudePrincipal(){
        fraude = new Fraude("02/02/26", Type.CALCULATRICE, "contenu de test", "TI", "Flappy Bird");
        assertEquals("02/02/26", fraude.getDateReleve(), "La date de relevée de la fraude n'a pas était correctement attribué");
        assertEquals(Type.CALCULATRICE, fraude.getTYPE(), "Le type de la fraude n'a pas était correctement attribuée");
        assertEquals("contenu de test", fraude.getContenu(), "Le contenu de la fraude n'a pas était correctement attribuée");
        assertEquals("TI", fraude.getAttribut1(), "Le première attribut de la fraude n'a pas était correctement attribuée");
        assertEquals("Flappy Bird", fraude.getATTRIBUT2(), "Le deuxième attribut de la fraude l'épreuve n'a pas était correctement attribuée");
    }

    @Test
    public void testConstructeurFraudeSecondaire(){
        String[] listeAttributs = {"02/02/26", "3", "contenu de test", "TI", "Flappy Bird"};
        fraude = new Fraude(listeAttributs);
        assertEquals("02/02/26", fraude.getDateReleve(), "La date de relevée de la fraude n'a pas était correctement attribuée");
        assertEquals(Type.CALCULATRICE, fraude.getTYPE(), "Le type de la fraude n'a pas était correctement attribué");
        assertEquals("contenu de test", fraude.getContenu(), "Le contenu de la fraude n'a pas était correctement attribué");
        assertEquals("TI", fraude.getAttribut1(), "Le première attribut de la fraude n'a pas était correctement attribué");
        assertEquals("Flappy Bird", fraude.getATTRIBUT2(), "Le deuxième attribut de la fraude l'épreuve n'a pas était correctement attribué");
    }

    @ParameterizedTest
    @MethodSource("dt")
    public void testGetType(Type type, String numType){
        assertEquals(type, getType(numType), "Le numéro du type sélectionée ne correspond pas au bon type");
    }
}
