
public class Vigenere {


    static String encode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        StringBuilder passwordCompleta = new StringBuilder(crearPasswd(password,s));

        StringBuilder newS = new StringBuilder();
        //Limpiamos la frase sin acentos
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 65 && s.charAt(i)<=90){
                newS.append(s.charAt(i));
            }else {
                newS.append(caracterEspecial(s.charAt(i)));
            }
        }
        StringBuilder newPasswd = new StringBuilder();
        for (int i = 0; i < passwordCompleta.length(); i++) {
            if (passwordCompleta.charAt(i)>=65 &&passwordCompleta.charAt(i)<=90){
                newPasswd.append(passwordCompleta.charAt(i));
            }else{
                newPasswd.append(caracterEspecial(passwordCompleta.charAt(i)));
            }
        }


        for (int i = 0; i <newS.length() ; i++) {
            int salto = newPasswd.charAt(i)-64;
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
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        StringBuilder passwordCompleta = new StringBuilder(crearPasswd(password,s));
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

    public static String crearPasswd (String passwd,String s){
        StringBuilder passwordCompleta = new StringBuilder();

        for (int i = 0,j=0; i < s.length(); i++) {

            //Si el caracter se ha de cifrar
            if ((s.charAt(i) >= 65 && s.charAt(i)<=90) || (s.charAt(i)>=192 && s.charAt(i)<=220)){

                //s.charAt(i) >= 65 && s.charAt(i)<=90
                if (j == passwd.length()) j=0;
                if  (passwd.charAt(j) == 181 || passwd.charAt(j) == 144 || passwd.charAt(j) ==214||
                        passwd.charAt(j) ==224 || passwd.charAt(j) ==233 || passwd.charAt(j) == 183 ||
                        passwd.charAt(j) ==212 || passwd.charAt(j) ==227 ){

                    passwordCompleta.append(caracterEspecial(passwd.charAt(j)));

                }else {
                    passwordCompleta.append(passwd.charAt(j));
                }
                j++;

                //Si el caracter no se ha de cifrar
            }else {
                passwordCompleta.append(' ');
            }
        }
        return passwordCompleta.toString();
    }

}