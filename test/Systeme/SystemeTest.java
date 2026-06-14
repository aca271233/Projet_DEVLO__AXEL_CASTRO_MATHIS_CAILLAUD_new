package Systeme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SystemeTest {
    private Systeme systeme;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        // Redirection de la sortie console pour vérifier les affichages si nécessaire
        System.setOut(new PrintStream(outputStreamCaptor));
        systeme = new Systeme();
    }

    // Restauration des flux après chaque test
    void restoreSystem() {
        System.setIn(systemIn);
    }

    @Test
    void testAffichageStatistiques() {
        // Test de la branche principale des calculs statistiques (Moyenne, Écart-type, Graphe)
        assertDoesNotThrow(() -> systeme.affichageStatistiques());
        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Moyenne du nombre de fraudes par formulaires"));
        assertTrue(output.contains("Nombre de fraudes"));
        assertTrue(output.contains("Matrice du graphe"));
    }

    @Test
    void testDeleteFormulaire_Success() {
        // L'ID 0 est généré par défaut dans le constructeur de Systeme
        systeme.deleteFormulaire("4");
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Votre saisie a bien été prise en compte"));
    }

    @Test
    void testDeleteFormulaire_NotFound() {
        // ID valide mathématiquement mais inexistant dans le système
        systeme.deleteFormulaire("999");
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Aucuns résultats trouvés"));
    }

    @Test
    void testDeleteFormulaire_InvalidFormat() {
        // String non convertible en entier (déclenche verificationInt -> false)
        systeme.deleteFormulaire("abc");
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Erreur de saisie"));
    }

    @Test
    void testSearchEtudiantsNom() {
        // Recherche un nom initialisé par défaut ("Boyer")
        assertDoesNotThrow(() -> systeme.searchEtudiantsNom("Boyer"));
        // Recherche un nom inexistant pour tester la branche vide
        systeme.searchEtudiantsNom("Inexistant");
        assertTrue(outputStreamCaptor.toString().contains("Aucuns résultats trouvés"));
    }

    @Test
    void testSearchEtudiantsPrenom() {
        assertDoesNotThrow(() -> systeme.searchEtudiantsPrenom("Maria"));
        systeme.searchEtudiantsPrenom("Inexistant");
        assertTrue(outputStreamCaptor.toString().contains("Aucuns résultats trouvés"));
    }

    @Test
    void testSearchEtudiantsNumApp() {
        assertDoesNotThrow(() -> systeme.searchEtudiantsNumApp("44912"));
        systeme.searchEtudiantsNumApp("99999");
        assertTrue(outputStreamCaptor.toString().contains("Aucuns résultats trouvés"));
    }

    @Test
    void testAffichageDefault() {
        systeme.affichageDefault();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Erreur dans votre choix"));
    }

    @Test
    void testSearchFormulairesEpreuve_Success() {
        // Simule la saisie du code ECUE "es32fd" par l'utilisateur
        String simulatedInput = "es32fd\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> systeme.searchFormulairesEpreuve(scanner));
        restoreSystem();
    }

    @Test
    void testSearchFormulairesEtudiant_Success() {
        // Simule la saisie d'un numéro d'apprenant valide "44912"
        String simulatedInput = "44912\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> systeme.searchFormulairesEtudiant(scanner));
        restoreSystem();
    }

    @Test
    void testInviteFormulaire_Add_Cancel() {
        // Choix épreuve: 0
        // Nom: Dupont, Prénom: Jean, Num: 12345, Cursus: 1, Date: 12/06/2026, Type: 1, Contenu: RAS, Attribut1: ChatGPT
        // Option finale: A (Annuler) pour couper la boucle
        String simulatedInput = "0\nDupont\nJean\n12345\n1\n12/06/2026\n1\nRAS\nChatGPT\nA\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> systeme.inviteFormulaire(scanner, true));
        restoreSystem();
    }

    @Test
    void testInviteFormulaire_Add_Success() {
        // Saisie complète valide se terminant par "T" (Terminer)
        // Incorpore un type de fraude 4 pour couvrir la branche de vérification de l'IP
        String simulatedInput = "0\nMartin\nBob\n67890\n2\n14/06/2026\n4\nFraude IP\nGemini\n192.168.1.1\nT\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> systeme.inviteFormulaire(scanner, true));
        restoreSystem();
    }

    @Test
    void testInviteFormulaire_InvalidEpreuveNum() {
        // Saisie d'un numéro d'épreuve hors limites (ex: 99)
        String simulatedInput = "99\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        systeme.inviteFormulaire(scanner, true);
        assertTrue(outputStreamCaptor.toString().contains("Erreur de saisie"));
        restoreSystem();
    }

    @Test
    void testModifFormulaire_Success() {
        // --- PRÉPARATION DES ENTRÉES SIMULÉES ---
        // 1. "1"             -> Sélection du formulaire à modifier (ID 1)
        // 2. "Dupond"        -> Insérer le nom de l'étudiant
        // 3. "Jean"          -> Insérer le prénom de l'étudiant
        // 4. "12345"         -> Insérer le numéro apprenant
        // 5. "3"             -> Choisir le numéro du cursus concerné
        // 6. "15/06/2026"    -> Date de relevé de la fraude
        // 7. "1"             -> Type de fraude concerné (1 = IAG)
        // 8. "Copie carbone" -> Contenu de la fraude
        // 9. "ChatGPT"       -> Attribut 1 (Spécifique au type 1)
        // 10. "T"            -> Option finale : "Terminer" (déclenche la modification)

        String simulatedInput = "1\nDupond\nJean\n12345\n3\n15/06/2026\n1\nCopie carbone\nChatGPT\nT\n";

        // Injection du flux simulé dans le système
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        // --- EXÉCUTION ---
        // On appelle la méthode publique qui va router vers modifFormulaire("1", ...)
        assertDoesNotThrow(() -> systeme.inviteFormulaire(scanner, false));

        // --- NETTOYAGE ---
        System.setIn(systemIn);
    }
}
