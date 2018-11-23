/**
 * @author mmonteiro
 * @project Cifrados
 */

public class Caesar {
    /**
     * @param s
     * @param delta
     * @return String
     *
     * Esta funcion lo que hace es recibir iuna String la cual se ha de codificar, un delta
     * el cual usaremos para codificar. Primero de todo pasamos la string recibida a mayusculas
     * despues definimos el salto que sera el delta % 26 asi solo tendremos un delta de 0 a 26
     * despues comprobamos si la letra es una letra normal (ascii de 65 a 90)
     * en caso contrario ese caracter (espacios, comas, acentos) no se codificaran.
     */
    static String cypher(String s, int delta){
        s = s.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        int salto = delta%26;
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);

            if (c >= 65 && c<=90){
                c = (char) (c+salto);

                if (c > 90){
                    c = (char) (c-26);
                }

                devolver.append(c);
            }else {
                devolver.append(c);
            }
        }

        return devolver.toString();
    }


    /**
     * @param s
     * @param delta
     * @return String
     * Esta funcion lo que hace es recibir iuna String la cual se ha de descodificar, un delta
     * el cual usaremos para descodificarlo. Primero de todo pasamos la string recibida a mayusculas
     * despues definimos el salto que sera el delta % 26 asi solo tendremos un delta de 0 a 26
     * despues comprobamos si la letra es una letra normal (ascii de 65 a 90)
     * en caso contrario ese caracter (espacios, comas, acentos) no se descodificaran ya que
     * no estaran codificados.
     */
    static String decypher(String s, int delta) {
        s = s.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        int salto = delta%26;
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);

            if (c >= 65 && c<=90){
                c = (char) (c-salto);

                if (c < 65){
                    c = (char) (c+26);
                }

                devolver.append(c);
            }else {
                devolver.append(c);
            }
        }

        return devolver.toString();
    }

    /**
     * @param s
     * @param ss
     * @return String
     *
     * Esta funcion recibe dos strings una codificada y otra descodificada pero no son la misma frase
     * entonces deduimos el delta mirando cual es la letra que mas se repite en las dos
     */
    static String magic(String s, String ss) {

        s = s.toUpperCase();
        //Numeros de veces que se repite esa letra
        int E = saberMax(ss, 'E');
        int A = saberMax(ss,'A');

        if (E > A){
            //Si la E es la que mas se repite, entenderemos que el texto esta en español y la usaremos la E
            // para encontrar el delta
            //Buscaremos la letra que mas se repite en la frase cifrada y entenderemos que esa sera la E
            //entonces miraremos la diferencia entre las dos para entender que delta usaremos
            char c = buscar(s);
            int delta = c - 69;

            //Llamaremos a la funcion descifrar pasandole el delta que hemos deduido

            StringBuilder devolver = new StringBuilder(decypher(s,delta));
            return devolver.toString();

        } else {

            //En el caso de que sea A, entenderemos que el texto E en portugues y haremos lo mismo pero
            // deduiendo el delta con la A
            char c = buscar(s);
            int delta = c - 65;
            StringBuilder result = new StringBuilder(decypher(s,delta));
            return result.toString();
        }
    }


    /**
     * @param s
     * @param c
     * @return numero
     *
     * Esta funcion lo que hace es recibir un string y un caracter y te dice cuantas
     * veces se repite ese caracter en la string que hemos pasado.
     */
    public static int saberMax(String s, char c) {

        int  contador = 0;
        s = s.toUpperCase();

        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                contador++;
            }
        }
        return contador;
    }


    /**
     * @param s
     * @return
     *
     * Esta funcion lo que hace es decirnos cual es la letra que mas se repita en una frase
     * la cual se la pasaremos
     */
    public static char buscar(String s){
        char lletra = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.codePointAt(i) < 65 || s.codePointAt(i) > 90) { continue; }
            if (saberMax(s, s.charAt(i)) > saberMax(s, lletra)) {
                lletra = s.charAt(i);
            }
        }
        return lletra;
    }


}

