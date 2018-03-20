package metier.donnees;

/**
 * Created by mj150192 on 06/01/17.
 */
public class Constante {

    private String nom;
    private Object valeur;

    public Constante(String nom, Object valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public Object getvaleur() {
        return valeur;
    }

    public void setvaleur(Object valeur) {
        this.valeur = valeur;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom + " " + valeur;
    }
    
}
