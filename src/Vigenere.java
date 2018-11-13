
public class Vigenere {

    static String encode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        StringBuilder passwordCompleta = new StringBuilder();
        for (int i = 0,j=0; i < s.length(); i++) {
            if (s.charAt(i) >= 65 && s.charAt(i)<=90){
                //s.charAt(i) >= 65 && s.charAt(i)<=90
                if (j == password.length()) j=0;
                passwordCompleta.append(password.charAt(j));
                j++;

            }else if (s.charAt(i)>=192 && s.charAt(i)<=220){
                if (j == password.length()) j=0;
                passwordCompleta.append(caracterEspecial(password.charAt(j)));
                j++;

            }else {
                passwordCompleta.append(' ');
            }
        }
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 65 && s.charAt(i)<=90){
                newS.append(s.charAt(i));
            }else {
                newS.append(caracterEspecial(s.charAt(i)));
            }
        }

        for (int i = 0; i <newS.length() ; i++) {
            int salto = passwordCompleta.charAt(i)-64;
            char c = newS.charAt(i);

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

    public static char caracterEspecial (char a){
        switch (a){
            case 'Á': case 'À':
                return 'A';
            case 'É': case 'È':
                return 'E';
            case 'Í': case 'Ì':
                return 'I';
            case 'Ó': case 'Ò':
                return 'O';
            case 'Ú': case 'Ù':
                return 'U';

            default:return a;
        }
    }



    static String decode(String s, String password) {
        return "";
    }
}


/*s.charAt(i)!=' ' && s.charAt(i)!= ','&& s.charAt(i)!='\'' &&
                    s.charAt(i)!= '·' && s.charAt(i)!= '%' && s.charAt(i)!= '&' &&
                    s.charAt(i)!= '$' && s.charAt(i)!='(' && s.charAt(i)!=')' &&
                    s.charAt(i)!='@' && s.charAt(i)!='¿' && s.charAt(i)!='?' &&
                    s.charAt(i)!='¡' && s.charAt(i)!='!'*/


