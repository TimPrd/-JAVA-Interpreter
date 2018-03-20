package metier.donnees;

/**
 * Classe permettant de manipuler des variables
 * contenues dans le fichier algo.
 * @author Justin ALLARD, Thibaut EMION, Robin LEPETIT,
 * Julia MARINELLI, Timothé PARDIEU - © Groupe 5, 2017
 * @version 06-01-2017
 */
public class Variable {

    private String nomVar;
    private String typeVar;
    private Object valeur;

    public Variable(String nomVar, String typeVar, Object valeur) {
        this(nomVar, typeVar);
        this.valeur = valeur;
    }

    public Variable(String nomVar, String typeVar) {
        this.nomVar = nomVar;
        this.typeVar = typeVar;
    }

    public String getTypeVar() {
        return typeVar;
    }

    public void setTypeVar(String typeVar) {
        this.typeVar = typeVar;
    }

    public String getNomVar() {
        return nomVar;
    }

    public void setNomVar(String nomVar) {
        this.nomVar = nomVar;
    }

    public Object getValeur() {
        return valeur;
    }

    public void setValeur(Object valeur) { this.valeur = valeur; }

    public String toString() {
        return nomVar + " " + typeVar + "  " + valeur;
    }

}
