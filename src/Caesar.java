
public class Caesar {
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

    static String magic(String s, String ss) {
        s = s.toUpperCase();
        ss = ss.toUpperCase();


        int[] contador = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if (j != i){
                    if (c == s.charAt(j)){
                        contador[i]++;
                    }
                }
            }
        }

        int [] ordenado = ordenarArrayDoble(contador);
        int repeticionMax = ordenado[ordenado.length - 1];
        char caracterRepetidoS = saberMax(repeticionMax, s);

        int [] contador1 = new int[ss.length()];
        for (int i = 0; i < ss.length(); i++) {
            char c = (char) ss.charAt(i);
            for (int j = 0; j < ss.length(); j++) {
                if (j!= i){
                    if (c==ss.charAt(j)){
                        contador1[i]++;
                    }
                }
            }

        }
        int [] ordenado1 = ordenarArrayDoble(contador1);
        int repeticionMax1 = ordenado1[ordenado.length - 1];
        char caracterRepetidoSS = saberMax(repeticionMax1, ss);

        int delta = caracterRepetidoSS-caracterRepetidoS;


        String devolver = decypher(s, delta);
        return devolver;
    }


    public static char saberMax(int repeticionMax, String s){
        for (int i = 0; i < s.length(); i++) {
            if (repeticionMax == s.charAt(i)){
                return s.charAt(i);
            }
        }
        return ' ';
    }




    public static int[] ordenarArrayDoble(int[] array) {

        for (int i=array.length, x=0; i>x; i--, x++) {

            for (int j = 1; j < i; j++) {
                if (array[j - 1] > array[j]) {
                    int Swap1 = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = Swap1;
                }
            }
            for (int k = (i-1) ; k > x ; k--) {
                if (array[k-1] > array[k]){
                    int swap2 = array[k];
                    array[k] = array[k-1];
                    array[k-1] = swap2;
                }
            }

        }
        return array;
    }


}
