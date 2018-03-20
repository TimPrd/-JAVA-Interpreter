package metier;

import iut.algo.Clavier;
import metier.donnees.Constante;
import metier.donnees.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bsh.Interpreter;

/**
* Classe gérant l'interprétation du fichier algo.
* @author Justin ALLARD, Thibaut EMION, Robin LEPETIT,
* Julia MARINELLI, Timothé PARDIEU - © Groupe 5, 2017
* @version 06-01-2017
*/
public class Arthour {

    private Lecture lec;
    private HashMap<Integer, String> contenu;
    private ArrayList<Variable> variables;
    private ArrayList<Constante> constantes;
    private Interpreter itp;
    private Console console;

    /**
     * Permet de récupérer l'algorithme sous forme d'HashMap pour traiter les données.
     * @param nomFichier Fichier algo à traiter.
     */
    public Arthour(String nomFichier, Console console) {
        lec = new Lecture(nomFichier);
        contenu = lec.getContenu();
        this.console = console;

        itp = new Interpreter();

        variables = new ArrayList<Variable>();
        constantes = new ArrayList<Constante>();
    }

    /**
     * Cherche dans le fichier la 1ère ligne contenant l'expression recherchée.
     * @param expression Expression à rechercher.
     * @return Numéro de ligne de l'expression passée en paramètre.
     */
    public int chercherLigne(String expression) {
        for (int i = getNbLigne() - 1; i > 0; i--) {
            Scanner sc = new Scanner(contenu.get(i));
            if (sc.nextLine().contains(expression)) {
                return i;
            }
            sc.close();
        }
        return -1;
    }

    public void traiterLigne(int i) {
        if (i > chercherLigne("variable") && i < chercherLigne("DEBUT") && contenu.get(i).contains(":")) {
            creerVariable(i);
            System.out.println(contenu.get(i));
        }
        if (i < chercherLigne("DEBUT") && contenu.get(i).contains("<-")) {
            creerConstante(i);
        }
        if (i > chercherLigne("DEBUT") && contenu.get(i).contains("<-")) {
            traiterAffectation(i);
        }
    }

    public void lireVariables()
    {
        int ind = 0;
        while (ind!=-1) {
            ind = chercherLigne("lire");
            String ligne = getLigne(ind);
            String exp = ligne.substring(ligne.indexOf("(") + 1, ligne.length() - 1);
            System.out.println(exp + ":");
            int valeur = Clavier.lire_int();
            getVariable(exp).setValeur(valeur);
            actualiserVariables();
        }
    }

    /**
     * Détermine à partir du fichier l'ensemble des variables déclarées,
     * et les stocke dans une ArrayList.
     */
    public void creerVariable(int i) {
        String nomTmp = "";
        String typeTmp = "";

        try {
            Scanner sc = new Scanner(contenu.get(i));

            sc.next();

            if (sc.hasNext()) {
                nomTmp = sc.next();
                sc.next();
                typeTmp = sc.next();

                variables.add(new Variable(nomTmp, typeTmp));
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Détermine à partir du fichier l'ensemble des constantes déclarées,
     * et les stocke dans une ArrayList.
     */
    public void creerConstante(int i) {
        String nomTmp = "";
        String valeurTmp = "";

        try {
            Scanner sc = new Scanner(contenu.get(i));

            sc.next();

            if (sc.hasNext()) {
                nomTmp = sc.next();
                sc.next();
                valeurTmp = sc.next();

                constantes.add(new Constante(nomTmp, valeurTmp));
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traiterAffectation(int i) {
        actualiserVariables();

        String ligne = contenu.get(i);

        String nomTmp = "";

        try {
            Scanner sc = new Scanner(ligne);

            sc.next();

            nomTmp = sc.next();
            Object valeurTmp = itp.eval((ligne.substring(ligne.indexOf("-") + 1, ligne.length())));

            getVariable(nomTmp).setValeur(valeurTmp);

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualiserVariables() {
        for (Variable v : variables) {
            try {
                itp.eval(v.getNomVar() + " = " + v.getValeur());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Nombre de lignes du fichier algo.
     */
    public Integer getNbLigne()
    {
        return contenu.size();
    }

    /**
     * @param i Numéro de ligne.
     * @return Ligne à l'indice passé en paramètre.
     */
    public String getLigne(int i)
    {
        return contenu.get(i);
    }

    /**
     * @return Lecteur associé.
     */
    public Lecture getLec() {
        return lec;
    }

    /**
     * @param lec Nouveau lecteur.
     */
    public void setLec(Lecture lec) {
        this.lec = lec;
    }

    /**
     * @return Ensemble des lignes du fichier sous forme d'HashMap.
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

    public void setContenu(int i, String chaine) {
        contenu.put(i, chaine);
    }
    /**
     * @return Ensemble des variables déclarées dans le fichier.
     */
    public ArrayList<Variable> getVariables() {
        return variables;
    }

    /**
     * @param variables Nouvel ensemble de variables.
     */
    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    /**
     * Retourne la variable associée au nom passé en paramètre.
     * @param nom Nom de la variable recherchée.
     * @return Variable trouvée, null si inexistante.
     */
    public Variable getVariable(String nom) {
        for (Variable v : variables) {
            if (v.getNomVar().equals(nom))
                return v;
        }

        return null;
    }

}
