package Systeme.Formulaire.Fraude;

import Systeme.Formulaire.Fraude.Type.Calculatrice;
import Systeme.Formulaire.Fraude.Type.IAG.IAG;
import Systeme.Formulaire.Fraude.Type.IAG.IAGConnectee;
import Systeme.Formulaire.Fraude.Type.Papier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static Systeme.Formulaire.Fraude.Fraude.getType;
import static Systeme.Formulaire.Fraude.Fraude.setType;
import static org.junit.jupiter.api.Assertions.*;

public class FraudeTest {

    private static Collection<Object[]> dt1(){
        Object[][] data = new Object[][]{
                {new IAG(null,null,null),"1"},
                {new Papier(null,null,null,null),"2"},
                {new Calculatrice(null,null,null,null),"3"},
                {new IAGConnectee(null,null,null,null),"4"}
        };
        return Arrays.asList(data);
    }

    private static Collection<Object[]> dt2(){
        Object[][] data = new Object[][]{
                {new IAG(null,null,null),"1"},
                {new Papier(null,null,null,null),"2"},
                {new Calculatrice(null,null,null,null),"3"},
                {new IAGConnectee(null,null,null,null),"4"},
                {null,"5"}
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("dt1")
    public void testConstructeurFraude(Fraude type, String numType){
        String description="";
        String descriptionAttribut1="";
        String descriptionAttribut2="";
        switch (numType){
            case "1" -> {
                description = "correspond à l’utilisation d’un service d’intelligence artificielle générative";
                descriptionAttribut1 = "nom du service utilisé";
                descriptionAttribut2 = null;
            }
            case "2" -> {
                description = "correspond à un document physique non autorisé";
                descriptionAttribut1 = "dimension";
                descriptionAttribut2 = "état";
            }
            case "3" -> {
                description = "Correspond à l'utilisation de programme sur la calculatrice";
                descriptionAttribut1 = "marque de l'appareil";
                descriptionAttribut2 = "programme stocké";
            }
            case "4" -> {
                description = "correspond à un cas particulier d’IAG avec accès réseau";
                descriptionAttribut1 = "nom du service utilisé";
                descriptionAttribut2 = "adresse IP utilisée";
            }
        }
        assertNull(type.getDateReleve(), "La date de relevée de la fraude n'a pas était correctement attribué");
        assertNull(type.getContenu(), "Le contenu de la fraude n'a pas était correctement attribuée");
        assertNull(type.getAttribut1(), "Le première attribut de la fraude n'a pas était correctement attribuée");
        assertNull(type.getAttribut2(), "Le deuxième attribut de la fraude n'a pas était correctement attribuée");
        assertEquals(description,type.getDescription(), "La description de la fraude n'est pas la bonne");
        assertEquals(descriptionAttribut1,type.getDescriptionAttribut1(), "La description du premier attribut n'est pas la bonne");
        assertEquals(descriptionAttribut2,type.getDescriptionAttribut2(), "La description du deuxième attribut n'est pas la bonne");
    }

    @ParameterizedTest
    @MethodSource("dt2")
    public void testGetType(Fraude type, String numType){
        assertEquals(type, getType(numType), "Le numéro du type sélectionée ne correspond pas au bon type");
    }

    @ParameterizedTest
    @MethodSource("dt1")
    public void testSetType(Fraude type, String numType){
        String[] fraudeStr;
        if(numType.equals("1")){
            fraudeStr = new String[]{type.getDateReleve(),numType,type.getContenu(),type.getAttribut1()};
        }
        else{
            fraudeStr = new String[]{type.getDateReleve(),numType,type.getContenu(),type.getAttribut1(),type.getAttribut2()};
        }
        assertEquals(type, setType(fraudeStr), "Le numéro du type sélectionée n'instancie pas lr bon type");
    }

    @ParameterizedTest
    @MethodSource("dt2")
    void testEqualsAutreType(Fraude type, String numType) {
        if(numType.equals("1")){
            Assertions.assertNotEquals(new IAGConnectee(null, null, null, null), type, "Les deux instances sont de même type");
        }
        else{
            Assertions.assertNotEquals(new IAG(null,null,null), type,"Les deux instances sont de même type");
        }
    }
}
