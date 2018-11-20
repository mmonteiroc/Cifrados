
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
        int es = saberMax(ss, 'E');
        int pt = saberMax(ss,'A');

        if (es > pt){
            //If the letter 'E' is the one that appears most that means the text is spanish or catalan
            //then we search the letter that appears most on the encrypted string
            //and then we deduct the ascii number for te letter 'E' to guess the delta.
            char c = find2(s);
            int delta = c - 69;
            //After that we just need to send the string and the delta to our decrypt function.
            StringBuilder result = new StringBuilder(decypher(s,delta));
            return result.toString();

        } else {
            //If the letter 'A' is the one that appears most that means the text is Portuguese
            //we deduct the ascii value for the letter 'A' to guess the delta and do the same procedure than before.
            char c = find2(s);
            int delta = c - 65;
            StringBuilder result = new StringBuilder(decypher(s,delta));
            return result.toString();
        }
    }



    public static int saberMax(String s, char c) {

        //This funcions tells us how many times does a specific letter appears on a text.
        //We save the number of times it appears on a variable called contador.

        int  contador = 0;
        s = s.toUpperCase();

        //We will compare the character c with the letter on the loop
        // if they are the same we add 1 to the result value of the function.
        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                contador++;
            }
        }
        return contador;
    }

    public static char find2(String s){

        //This function tells us which is the most repeated letter on a string.
        char lletra = s.charAt(0);

        for (int i = 0; i < s.length(); i++) {
            //On this case we have to evade spaces and other special characters
            // so we check if the letter on the loop is on the letters range or not.
            if (s.codePointAt(i) < 65 || s.codePointAt(i) > 90) { continue; }

            if (saberMax(s, s.charAt(i)) > saberMax(s, lletra)) {
                lletra = s.charAt(i);
            }
        }
        return lletra;
    }


}
