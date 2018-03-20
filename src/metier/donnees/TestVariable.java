package metier.donnees;

import bsh.Interpreter;

/**
 * Created by mj150192 on 06/01/17.
 */
public class TestVariable {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval("a = 5 ");
            Variable var1 = new Variable("a", "entier", interpreter.get("a"));

            /*
            Variable var1 = new Booleen("var1", true);
            Variable var2 = new Entier("i", 55);
            Variable var3 = new Caractere("cara", 'G');
            Variable var4 = new Reel("var2", 55.8);

            System.out.println(var1);
            System.out.println(var2);
            System.out.println(var3);
            System.out.println(var4);
            */
            System.out.println(var1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
