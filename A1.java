import java.util.Scanner;

//Obtenir Paraules
//Invertir ordre paraules
//Canviar vocals per numeros i 'm' --> '!' i 'd' --> '('
//shift als caràcters amb la quantitat de paraules.

public class A1 {

    private static int obtenirNumeroParaules(String frase) {

        int num;
        Scanner paraules;

        paraules = new Scanner(frase);

        num = 0;
        while (paraules.hasNext()) {
            paraules.next();
            num++;
        }

        paraules.close();

        return num;
    }

    private static String[] obtenirParaules(String frase) {

        int num_paraules;
        String[] llistaParaules;
        Scanner paraules;

        paraules = new Scanner(frase);

        num_paraules = obtenirNumeroParaules(frase);

        llistaParaules = new String[num_paraules];

        for (int i = 0; i < llistaParaules.length; i++) {
            llistaParaules[i] = paraules.next();
        }

        paraules.close();

        return llistaParaules;

    }

    private static char shiftCaracter(char caracter, int salt) {

        char newChar;

        newChar = (char) (caracter + salt);

        return newChar;
    }

    private static String shiftParaula(String paraula, int salt) {

        String newParaula;
        char caracter;

        newParaula = "";
        for (int i = 0; i < paraula.length(); i++) {

            caracter = paraula.charAt(i);
            caracter = shiftCaracter(caracter, salt);
            newParaula = newParaula + caracter;
        }

        return newParaula;
    }

    private static String replaceLetters(String cadena, char[] origen, char[] fin) {

        for (int i = 0; i < origen.length; i++) {

            cadena = replaceLetter(cadena, origen[i], fin[i]);
        }

        return cadena;
    }

    private static String replaceLetter(String cadena, char origen, char fin) {

        String newcadena;
        char caracter;

        newcadena = "";
        for (int i = 0; i < cadena.length(); i++) {

            caracter = cadena.charAt(i);
            if (caracter == origen) {
                caracter = fin;
            }
            newcadena = newcadena + caracter;
        }
        return newcadena;
    }

    private static String[] invertirVector(String[] vector) {

        String[] newVector = new String[vector.length];

        for (int i = 0; i < newVector.length; i++) {

            newVector[i] = vector[newVector.length - i - 1];
        }

        return newVector;
    }

    private static String getFrase(String[] paraules) {

        String frase;

        frase = paraules[0];
        for (int i = 1; i < paraules.length; i++) {
            frase = frase + " " + paraules[i];
        }

        return frase;
    }

    private static String encriptar(String frase) {

        String[] paraules;

        char[] origen = { 'e', 'e', 'i', 'o', 'u', 'l', 'd' };
        char[] fi = { '1', '2', '3', '4', '5', '!', '(' };

        // Num paraules
        frase = replaceLetters(frase, origen, fi);
        paraules = obtenirParaules(frase);
        paraules = invertirVector(paraules);

        for (int i = 0; i < paraules.length; i++) {
            paraules[i] = shiftParaula(paraules[i], i%4);
        }

        frase = getFrase(paraules);

        return frase;
    }

    private static String desencriptar(String frase) {

        String[] paraules;

        char[] origen = { 'a', 'e', 'i', 'o', 'u', 'l', 'd' };
        char[] fi = { '1', '2', '3', '4', '5', '!', '(' };

        // Num paraules
        paraules = obtenirParaules(frase);
        for (int i = 0; i < paraules.length; i++) {
            paraules[i] = shiftParaula(paraules[i], -i%4);
        }

        paraules = invertirVector(paraules);
        frase = getFrase(paraules);
        frase = replaceLetters(frase, fi, origen);

        

        return frase;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String frase, fraseE, fraseD;

        frase = in.nextLine();

        fraseE = encriptar(frase);
        fraseD = desencriptar(fraseE);

        System.out.println(fraseE);
        System.out.println(fraseD);
       // System.out.println(frase.equals(fraseD));

        in.close();
    }
}