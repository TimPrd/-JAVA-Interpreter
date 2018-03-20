package metier;

import metier.donnees.Variable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;

/**
 * Classe gérant l'affichage console.
 * @author Justin ALLARD, Thibaut EMION, Robin LEPETIT,
 * Julia MARINELLI, Timothé PARDIEU - © Groupe 5, 2017
 * @version 06-01-2017
 */
public class Console
{
    private Arthour interprete;
    private String contenuFichier;
    private ArrayList<Variable> variables;
    private int ligneCourante;

    /**
     * Crée une console liée à un interpréteur de fichier.
     * Affiche la console.
     */
    public Console (String nomFichier)
    {
        interprete = new Arthour(nomFichier, this);
        variables = new ArrayList<Variable>();

        contenuFichier = "";
        afficheAlgo();
        affichageFormat();

        ligneCourante = 0;
        exec();
    }

    /**
     * Execution pas à pas, met à jour l'affichage.
     */
    public void exec() {
        while (ligneCourante < interprete.getNbLigne()) {
            try {
                Scanner defilement = new Scanner(System.in);
                if (defilement.hasNextLine()) {
                    variables = interprete.getVariables();
                    interprete.traiterLigne(ligneCourante++);
                    affichage();
                    System.out.println(ligneCourante);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void affichage() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        contenuFichier = "";
        afficheAlgo();
        affichageFormat();
    }

    /**
     * Affiche le contenu de l'algorithme du fichier, ainsi que
     * la trace des variables.
     */
    public void afficheAlgo() {
        int nbMotsClefsLigne = 0;
        int valFormat = 0;

        for (int i = 1; i < interprete.getNbLigne(); i++)
        {
            nbMotsClefsLigne = interprete.getLec().getAlCptMotsClefsLigne().get(i);
            if (i <= variables.size())
            {
                contenuFichier += "|" + String.format("%-57s", interprete.getLigne(i)) + "|"
                        + " |" + String.format("%-13s", variables.get(i - 1).getNomVar()) + "|" + String.format("%-10s", variables.get(i - 1).getTypeVar())
                        + "|" + String.format("%-17s", variables.get(i - 1).getValeur()) + "|\n";
            } else
            {
                if ( nbMotsClefsLigne != 0)
                {
                    valFormat = nbMotsClefsLigne * 9;
                    contenuFichier += "|" + String.format("%-" + Integer.toString(57 + valFormat) + "s", interprete.getLigne(i)) + "|\n";
                }
                else
                {
                    contenuFichier += "|" + String.format("%-57s", interprete.getLigne(i)) + "|\n";
                }
            }
        }
    }

    /**
     * Affiche un résultat formaté de l'ensemble des éléments.
     */
    public void affichageFormat() {
        AnsiConsole.systemInstall();
        AnsiConsole.out.println(TestCouleur.ANSI_CLS + TestCouleur.ANSI_NORMAL);

        String sRet = "";
            /*TODO*/
            /* LECTURE fichier et intégrer dans sLecture (à l'aide d'une boucle ?)*/

        String sConsole = "RESULTAT DE L'EXECUTION";

        sRet += ".........." + String.format("%50s", "") + "............. \n" +
                "|  CODE  |" + String.format("%50s", "") + "|  DONNEES  |\n" +
                String.format("%104s", "").replaceAll(" ", ".") + "\n" +
                "|" + String.format("%-57s", interprete.getLigne(0)) + "| |     NOM     |   TYPE   |  VALEUR" + String.format("%10s", "|") + "\n" +
                contenuFichier + "\n" +
                String.format("%59s", "").replaceAll(" ", ".") + "\n\n" +
                String.format("%11s", "").replaceAll(" ", ".") + "\n" +
                "| CONSOLE |" + "\n" +
                String.format("%59s", "").replaceAll(" ", ".") + "\n" +
                sConsole + "\n" +
                "|" + String.format("%58s", "|") + "\n" +
                String.format("%59s", "").replaceAll(" ", ".");
        System.out.println(sRet);
        AnsiConsole.systemInstall();
    }

    public int getLigneCourante() {
        return ligneCourante;
    }

}
