
/**
 * @author mmonteiro
 * @project Cifrados
 */
public class PlayFair {

    public static void main(String[] args) {
        encrypt("hola, soy mdigúuágel, como estas?","murcielago");
    }


    public static String encrypt(String text, String pass) {
        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y", "Z"};
        StringBuilder passwordCorrecta = new StringBuilder();
        StringBuilder passLimpia = new StringBuilder();
        pass = pass.toUpperCase();
        StringBuilder devolucion = new StringBuilder();

        // Aqui lo que hacemos es comprobar si lleva algun caracter especial la passwrd, en el caso de
        // que sea asi, se eliminan los caracteres especiales

        passLimpia.append(limpiarFrase(pass));

        //Aqui hacemos que la password no tenga ningun caracter repetido
        //EX: ARANYA = ARNY
        for (int i = 0; i < passLimpia.length(); i++) {
            StringBuilder c = new StringBuilder() ;
            c.append(passLimpia.charAt(i));
            if (passwordCorrecta.toString().contains(c.toString())){
                continue;
            }else {
                passwordCorrecta.append(passLimpia.charAt(i));
            }
        }

        //Pasamos todos los caracteres a mayusculas
        StringBuilder passwdMAY = new StringBuilder(passwordCorrecta.toString());

        String[][] matriz = new String[5][5];
        int filasIniciales = passwdMAY.length()/5;
        double x = (double)  passwdMAY.length()/5;
        if (x>filasIniciales){
            filasIniciales++;
        }

        int cont = 0;
        for (int i = 0; i < filasIniciales; i++) {
            for (int j = 0; j < 5; j++) {
                if (cont < passwdMAY.length()){
                    StringBuilder c = new StringBuilder();
                    c.append(passwdMAY.charAt(cont));
                    matriz[i][j] = c.toString();
                    cont++;
                }
            }
        }

        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j]==null){

                    if  (passwdMAY.toString().contains(letras[contador])){
                        //Si contiene esa letra, no la escribimos y saltamos a la siguiente iteracion del bucle
                        contador++;
                        i--;
                        j--;
                        continue;
                    }else {
                        //Si no contiene esa letra escribimos en la matriz esa letra
                        matriz[i][j] = letras[contador];
                    }

                    contador++;
                }
            }
        }
        Transposition.printarMatrix(matriz,"matrix");
        //Hasta este punto lo que tenemos hecho es la matriz para cifrar rellenada.

        StringBuilder fraseLimpia = new StringBuilder();
        fraseLimpia.append(limpiarFrase(text.toUpperCase()));
        System.out.println(fraseLimpia.toString()+"\nfrase limpia");

        for (int i = 1,j=0; i < fraseLimpia.length(); i++,j++) {
            char caracter1 = fraseLimpia.charAt(i);
            char caracter2 = fraseLimpia.charAt(j);
            if (caracter1 == caracter2){
                fraseLimpia.replace(j+1,i,"X");
            }
        }

        if  (fraseLimpia.length()%2 != 0){
            fraseLimpia.append("X");
        }

        StringBuilder fraseAdos = new StringBuilder();

        //Esto lo que hace es separarnos la frase de dos letras en dos letras separando
        // cada dos lestras con un espacio.
        for (int i = 0,q=0; i < fraseLimpia.length(); i++,q++) {
            if (q<2){
                fraseAdos.append(fraseLimpia.charAt(i));
            }else {
                q=0;
                fraseAdos.append(" "+fraseLimpia.charAt(i));
            }
        }

        System.out.println(fraseAdos.toString()+"\nfrase a dos");

        for (int i = 0,j=1; i < fraseLimpia.length()/2; i++,j++) {
            
            int[] filcol = saberFilCol(matriz, fraseAdos.charAt(i));
            int[] filcol1 = saberFilCol(matriz,fraseAdos.charAt(j));


            
            i+=2;
            j+=2;
        }



        return devolucion.toString();
    }

    public static String decrypt(String text, String pass) {


        return "";
    }





    public static int[] saberFilCol(String[][] matriz, char c){

        int [] devo = new int[2];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                StringBuilder cc = new StringBuilder();
                cc.append(c);
                if (cc.toString() == matriz[i][j]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }




    //Funciones para limpiar las frases
    public static String  limpiarFrase(String frase){
        StringBuilder devolver = new StringBuilder();
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) >= 65 && frase.charAt(i)<=90){
                devolver.append(frase.charAt(i));
            }else{
                char c = caracterEspecial(frase.charAt(i));
                if (c>= 65 && c<=90){
                    devolver.append(c);
                }
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


}
