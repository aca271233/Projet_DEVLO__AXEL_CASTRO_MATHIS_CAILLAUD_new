package Systeme.Formulaire.Etudes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static Systeme.Formulaire.Etudes.Etudiant.getCursus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtudiantTest {

    private Etudiant etudiant;

    private static Collection<Object[]> dt(){
        Object[][] data = new Object[][]{
                {Cursus.E1,"1"},
                {Cursus.E2,"2"},
                {Cursus.E3e,"3"},
                {Cursus.E3a,"4"},
                {Cursus.E4,"5"},
                {Cursus.E5,"6"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testConstructeurEtudiantPrincipal(){
        etudiant = new Etudiant("Castro", "Axel", "1", Cursus.E3e);
        assertEquals("Castro", etudiant.getNOM(), "Le nom de l'étudiant n'a pas était correctement attribué");
        assertEquals("Axel", etudiant.getPRENOM(), "Le Prénom de l'étudiant n'a pas était correctement attribué");
        assertEquals("1", etudiant.getNUMERO_APPRENANT(), "Le numéro apprenant de l'étudiant n'a pas était correctement attribué");
        assertEquals(Cursus.E3e, etudiant.getCURSUS(), "Le cursus de l'étudiant n'a pas était correctement attribué");
    }

    @Test
    public void testConstructeurEtudiantSecondaire(){
        String[] listeAttributs = {"Castro","Axel","1","3"};
        etudiant = new Etudiant(listeAttributs);
        assertEquals("Castro", etudiant.getNOM(), "Le nom de l'étudiant n'a pas était correctement attribué");
        assertEquals("Axel", etudiant.getPRENOM(), "Le Prénom de l'étudiant n'a pas était correctement attribué");
        assertEquals("1", etudiant.getNUMERO_APPRENANT(), "Le numéro apprenant de l'étudiant n'a pas était correctement attribué");
        assertEquals(Cursus.E3e, etudiant.getCURSUS(), "Le cursus de l'étudiant n'a pas était correctement attribué");
    }

    @ParameterizedTest
    @MethodSource("dt")
    public void testGetCursus(Cursus cursus, String numCursus){
        assertEquals(cursus, getCursus(numCursus), "Le numéro du cursus sélectioné ne correspond pas au bon cursus");
    }

    @Test
    public void testEquals(){
        etudiant = new Etudiant("Castro", "Axel", "1", Cursus.E3e);
        Etudiant etudiant2 = new Etudiant("Castro", "Axel", "1", Cursus.E3e);
        assertTrue(etudiant.equals(etudiant2), "Les deux instances étudiant ne sont pas égales");
    }
}
