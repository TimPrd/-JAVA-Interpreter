package metier;

import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestCouleur
{
    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_BOLD = "\u001b[1m";
    public static final String ANSI_AT55 = "\u001b[10;10H";
    public static final String ANSI_REVERSEON = "\u001b[7m";
    public static final String ANSI_NORMAL = "\u001b[0m";
    public static final String ANSI_WHITEONBLUE = "\u001b[37;44m";

    public static void main(String args[])
    {
        AnsiConsole.systemInstall();
        ArrayList<String> alKeyword = new DefMotsClefs().getAlMotsClefs();
//        for (String s : alKeyword)
//            System.out.println(s);

        ArrayList<String> alLignes = TestCouleur.lireTest();

        for (String s : alLignes)
            System.out.println(s);

        AnsiConsole.out.println(ANSI_CLS + ANSI_NORMAL);

        for (int ligne = 0; ligne < alLignes.size(); ligne++)
        {
            for (String key : alKeyword)
            {
                alLignes.set(ligne, alLignes.get(ligne).replace(key, ANSI_BLUE + key + ANSI_NORMAL));
            }
        }

        for (String s : alLignes)
            System.out.println(s);

//        System.out.println(alLignes.get(0));
//        AnsiConsole.out.print(ANSI_BLUE + alLignes.get(0));
//        AnsiConsole.out.print("\n");

//        AnsiConsole.out.println(ANSI_CLS);
        AnsiConsole.systemInstall();
    }

    public static ArrayList<String> lireTest()
    {
        ArrayList<String> alRet = new ArrayList<>();

        try
        {
            File fichier = new File("donnees/test.txt");
            FileInputStream stream = new FileInputStream(fichier);

            Scanner scanFichier = new Scanner(stream);

            while (scanFichier.hasNext())
            {
                alRet.add(scanFichier.nextLine());
            }
        } catch (Exception e)
        {
            System.out.println("errr");
            e.printStackTrace();
        }
        return alRet;
    }
}