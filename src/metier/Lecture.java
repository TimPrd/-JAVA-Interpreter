package metier;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import metier.donnees.Variable;

/**
 * Classe gérant la lecture du fichier algo.
 * @author Justin ALLARD, Thibaut EMION, Robin LEPETIT,
 * Julia MARINELLI, Timothé PARDIEU - © Groupe 5, 2017
 * @version 06-01-2017
 */
public class Lecture {

    private File file;
    private FileInputStream stream;
    private HashMap<Integer, String> contenu;
    private ArrayList<Variable> variables;
    private int cptMotsClefsLigne;
    private ArrayList<Integer> alCptMotsClefsLigne;

    /**
     * Stocke le contenu du fichier ligne par ligne dans une HashMap.
     * @param cheminFichier Fichier algo à lire et traiter.
     */
    public Lecture(String cheminFichier)
    {
        alCptMotsClefsLigne = new ArrayList();
        contenu = new HashMap<Integer, String>();
        try
        {
            file = new File(cheminFichier);
            stream = new FileInputStream(file);

            Scanner scanFichier = new Scanner(stream);
            String tmp;
            ArrayList<String> alKeyword = new DefMotsClefs().getAlMotsClefs();

            int i = 0;
            while (scanFichier.hasNext())
            {
                cptMotsClefsLigne = 0;
                tmp = scanFichier.nextLine();
                String[] decoupLigne = tmp.split(" ");
                for (int cpt = 0; cpt < decoupLigne.length; cpt++)
                {
                    for (String key : alKeyword)
                    {
                        if (decoupLigne[cpt].equals(key))
                        {
                            cptMotsClefsLigne++;
                            decoupLigne[cpt] = decoupLigne[cpt].replace(decoupLigne[cpt], TestCouleur.ANSI_BLUE + decoupLigne[cpt] + TestCouleur.ANSI_NORMAL);
                        }
                        //tmp = tmp.replace(key, TestCouleur.ANSI_BLUE + key + TestCouleur.ANSI_NORMAL);
                    }
                }
                tmp = String.join(" ", decoupLigne);
                this.alCptMotsClefsLigne.add(cptMotsClefsLigne);


                contenu.put(i, String.format("%3d %s", i, tmp));
                i++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        variables = new ArrayList<Variable>();
    }

    /**
     * Affiche le contenu du fichier.
     */
    public void affichage() {
        for (Integer i : contenu.keySet()) {
            System.out.print(contenu.get(i));
        }
    }

    /**
     * @return Ensemble des lignes numérotées du fichier.
     */
    public HashMap<Integer, String> getContenu() {
        return contenu;
    }

    /**
     * @param contenu Nouvel ensemble de lignes.
     */
    public void setContenu(HashMap<Integer, String> contenu) {
        this.contenu = contenu;
    }

    public ArrayList<Integer> getAlCptMotsClefsLigne()
    {
        return this.alCptMotsClefsLigne;
    }


    /*
    public void exec() {
        affichage();
        try {
            Scanner input = new Scanner(System.in);
            String str = input.next();
            while (!str.equals("a")) {
                System.out.println("Wololo");
                str = input.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    */

}