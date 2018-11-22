/**
 * @author mmonteiro
 * @project Cifrados
 */

public class Vigenere {
    /**
     * @param frase   Es la frase que hemos de codificar
     * @param password Es la password que usaremos para codificar la frase
     * @return Es la frase que devolveremos una vez cifrada
     *
     * Lo que hacemos es pasar de primeras la frase y la password a mayusculas
     * despues las limpiamos quitando acentos, caracteres especiales etc
     *
     * Una vez tenemos las dos frases limpias, lo que haremos es que si la password es mas corta que la frase que hemos de recibir
     * la repetiremos una y otra vez hasta que la password sea de la misma longitud que la frase a cifrar
     * EX: LICEU -->  LICEULICEULICEULICEULICEULICE
     *
     * Despues iremos caracter a caracter mirando la difrencia entre ese caracter y el de la password y esa diferencia definira el salto que tendremos que usar
     * para cifrar ese caracter. Lo que estamos haciendo es un cesar pero cada caracter tiene un delta distinto.
     */
    static String encode(String frase, String password) {
        password = password.toUpperCase();
        frase = frase.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        StringBuilder passwordCompleta = new StringBuilder(crearPasswd(password,frase));

        StringBuilder newS = new StringBuilder();
        //Limpiamos la frase sin acentos
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) >= 65 && frase.charAt(i)<=90){
                newS.append(frase.charAt(i));
            }else {
                newS.append(caracterEspecial(frase.charAt(i)));
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


    /**
     * @param FraseAdescifrar
     * @param password
     * @return
     *
     *
     * Lo que hacemos es pasar de primeras la frase y la password a mayusculas
     * despues las limpiamos quitando acentos, caracteres especiales etc
     *
     * Una vez tenemos las dos frases limpias, lo que haremos es que si la password es mas corta que la frase que hemos de recibir
     * la repetiremos una y otra vez hasta que la password sea de la misma longitud que la frase a cifrar
     * EX: LICEU -->  LICEULICEULICEULICEULICEULICE
     *
     * Despues iremos caracter a caracter mirando la difrencia entre ese caracter y el de la password y esa diferencia definira el salto que tendremos que usar
     * para cifrar ese caracter. Lo que estamos haciendo es un descifrado cesar pero cada caracter tiene un delta distinto.
     */
    static String decode(String FraseAdescifrar, String password) {
        password = password.toUpperCase();
        FraseAdescifrar = FraseAdescifrar.toUpperCase();
        StringBuilder devolver = new StringBuilder();
        StringBuilder passwordCompleta = new StringBuilder(crearPasswd(password,FraseAdescifrar));
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < FraseAdescifrar.length(); i++) {
            if (FraseAdescifrar.charAt(i) >= 65 && FraseAdescifrar.charAt(i)<=90){
                newS.append(FraseAdescifrar.charAt(i));
            }else {
                newS.append(caracterEspecial(FraseAdescifrar.charAt(i)));
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

    /**
     * @param passwd
     * @param s
     * @return te devuelve una frase que sera la passwd de la longitud deseada (la long la define la frase a cifrar)
     *
     * siempre y cuando en la frase hay un caracter normal (A-Z) añadiremos una letra de la pass original ciclicamente
     * una vez acabemos la frase original, volveremos a empezar
     */
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