package metier;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lr151084 on 06/01/17.
 */
public class DefMotsClefs
{
    private ArrayList<String> alMotsClefs;

    public DefMotsClefs()
    {
        alMotsClefs = new ArrayList<>();

        try
        {
            File fichier = new File("donnees/motsClefs.data");
            FileInputStream stream = new FileInputStream(fichier);

            Scanner scanFichier = new Scanner(stream);

            while (scanFichier.hasNext())
            {
                alMotsClefs.add(scanFichier.nextLine());
            }
        } catch (Exception e)
        {
            System.out.println("err");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAlMotsClefs()
    {
        return this.alMotsClefs;
    }
}