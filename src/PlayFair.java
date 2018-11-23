/**
 * @author mmonteiro
 * @project Cifrados
 */

public class PlayFair {

    /**
     * @param text
     * @param pass
     * @return Frase Cifrada
     *
     * Este algoritmo lo que hace es, recibir una password la cual usaremos para crear
     * una matriz unica que esa matriz se usara para cifrar la frase a cifrar
     *
     * Tanto la password como la frase se limpian de caracteres especiales como acentos, numeros, signos etc
     * Si encontramos un J se Cambiara por una I
     *
     * Una vez hecho eso, comprobamos si hay caracteres repetidos(seguidos) en la frase a cifrar ya que en ese
     * caso habra que poner un caracter sin significado en medio que para nosotros sera la X
     * despues comprobamos si la longitud de la frase (que no tendra ningun espacio) es impar, añadiremos otro
     * caracter sin sentido que para nosotros volvera a ser la X
     *
     * Despues separaremos la frase con espacios en dos letras en dos letras HOLAMUNDO --> HO LA MU ND OX
     * Una vez tengamos hecho esto iremos de dos en dos letras mirando su fila/columna en la matriz
     * Si comparten fila --> se cogera su caracter de la derecha respectivamente y ciclicamente
     * Si comparten columna --> se cogera su caracter de abajo respectivamente y ciclicamente
     * Si no coinciden en ninguno lo que se cogera seran los caracteres de la diagonal opuesta
     * Si tenemos  X y F  como no coinciden ni fila ni columna
     * cogeremos W y Z ya que son de su diagonal opuesta
     *      x   y   z
     *      a   k   q
     *      w   d   f
     *
     */
    public static String encrypt(String text, String pass) {
        StringBuilder devolucion = new StringBuilder();

        String[][]matriz=crearMatriz(pass);
        //Hasta este punto lo que tenemos hecho es la matriz para cifrar rellenada.

        StringBuilder fraseLimpia = new StringBuilder();
        fraseLimpia.append(limpiarFrase(text.toUpperCase()));

        for (int i = 1,j=0; i < fraseLimpia.length(); i++,j++) {
            char caracter1 = fraseLimpia.charAt(i);
            char caracter2 = fraseLimpia.charAt(j);
            if (caracter1 == caracter2){
                fraseLimpia.replace(j+1,i,"X");
            }
        }

        if  (fraseLimpia.length()%2 != 0){
            if (fraseLimpia.charAt(fraseLimpia.length()-1) == 'X'){
                fraseLimpia.append("I");
            }else {
                fraseLimpia.append("X");
            }
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


        int numero = fraseLimpia.length()/2;
        for (int i = 0,j=1,w=0; w < numero; w++, i++,j++) {
            
            int[] filcol = saberFilCol(matriz, fraseAdos.charAt(i));
            int[] filcol1 = saberFilCol(matriz,fraseAdos.charAt(j));

            if  (filcol[0] == filcol1[0]){
                //Si tienen misma fila

                if (filcol[1] >= 4){
                    devolucion.append(matriz[filcol[0]][0]);
                }else {
                    devolucion.append(matriz[filcol[0]][filcol[1]+1]);
                }

                if (filcol1[1]>=4){
                    devolucion.append(matriz[filcol1[0]]    [0]);
                }else {
                    devolucion.append(matriz[filcol1[0]]    [filcol1[1]+1]);
                }



            }else if (filcol[1] == filcol1[1]){
                //Si tienen misma columna
                //Se baja una fila pero no cambia la columna

                if (filcol[0] >= 4){
                    devolucion.append(matriz[0][filcol[1]]);
                }else {
                    devolucion.append(matriz[filcol[0]+1]    [filcol[1]]);
                }

                if (filcol1[0] >= 4){
                    devolucion.append(matriz[0][filcol1[1]]);
                }else {
                    devolucion.append(matriz[filcol1[0]+1]   [filcol1[1]]);
                }


            }else {
                //Si no tienen misma fila ni misma columna

                devolucion.append(matriz[filcol[0]][filcol1[1]]);
                devolucion.append(matriz[filcol1[0]][filcol[1]]);

            }

            
            i+=2;
            j+=2;


            devolucion.append(" ");
        }

        devolucion.deleteCharAt(devolucion.length()-1);

        return devolucion.toString();
    }


    /**
     * @param text
     * @param pass
     * @return frase descifrada
     * Este algoritmo lo que hace es, recibir una password la cual usaremos para crear
     * una matriz unica que esa matriz se usara para cifrar la frase a cifrar
     *
     * La password  se limpia de caracteres especiales como acentos, numeros, signos etc
     * Si encontramos un J se Cambiara por una I
     *
     * Iremos de dos en dos letras mirando su fila/columna en la matriz
     * Si comparten fila --> se cogera su caracter de la derecha respectivamente y ciclicamente
     * Si comparten columna --> se cogera su caracter de abajo respectivamente y ciclicamente
     * Si no coinciden en ninguno lo que se cogera seran los caracteres de la diagonal opuesta
     * Si tenemos  X y F  como no coinciden ni fila ni columna
     * cogeremos W y Z ya que son de su diagonal opuesta
     *      x   y   z
     *      a   k   q
     *      w   d   f
     *
     */
    public static String decrypt(String text, String pass) {
        StringBuilder devolucion = new StringBuilder();
        String[][]matriz=crearMatriz(pass);
        //Hasta este punto lo que tenemos hecho es la matriz para cifrar rellenada.



        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i)!=' '){
                newText.append(text.charAt(i));
            }
        }


        for (int i = 0,j=1,x=0; x< newText.length()/2; x++, i++, j++) {
            int[] filcol = saberFilCol(matriz,text.charAt(i));
            int[] filcol1 = saberFilCol(matriz,text.charAt(j));

            if (filcol[0] == filcol1[0]){
                //misma fila
                //Primera letra
                if (filcol[1] == 0){
                    devolucion.append(matriz[filcol[0]][4]);
                }else {
                    devolucion.append(matriz[filcol[0]][filcol[1]-1]);
                }
                //Segunda letra
                if (filcol1[1] == 0){
                    devolucion.append(matriz[filcol1[0]][4]);
                }else {
                    devolucion.append(matriz[filcol1[0]][filcol1[1]-1]);
                }


            }else if (filcol[1] == filcol1[1]){
                //misma columna

                //Primera letra
                if (filcol[0]  == 0){
                    devolucion.append(matriz[4][filcol[1]]);
                }else {
                    devolucion.append(matriz[filcol[0]-1][filcol[1]]);
                }

                //Segunda letra
                if (filcol1[0]  == 0){
                    devolucion.append(matriz[4][filcol1[1]]);
                }else {
                    devolucion.append(matriz[filcol1[0]-1][filcol1[1]]);
                }


            }else {
                //No coincide ninguna
                devolucion.append(matriz[filcol[0]][filcol1[1]]);
                devolucion.append(matriz[filcol1[0]][filcol[1]]);

            }

            i+=2;
            j+=2;

            devolucion.append(" ");
        }
        devolucion.deleteCharAt(devolucion.length()-1);

        char c1 = devolucion.charAt(devolucion.length()-2);
        char c2 = devolucion.charAt(devolucion.length()-1);
        int pos1 = devolucion.length()-1;
        int pos2 =devolucion.length();

        if (c1 == c2){
            devolucion.replace(pos1,pos2,"X");
        }

        return devolucion.toString();
    }


    //Esta funcion lo que hace es crearnos una matriz para cifrar usando la password que nosotros le pasemos
    public static String[][] crearMatriz(String pass){
        pass = pass.toUpperCase();
        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y", "Z"};
        StringBuilder passwordCorrecta = new StringBuilder();
        StringBuilder passLimpia = new StringBuilder();

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
                if (i < 0){
                    i++;
                }
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
        return matriz;
    }





    //Esta funcion nos dice en que fila y en que columna
    // esta esa letra que hemos pasado en la matriz para cifrar
    public static int[] saberFilCol(String[][] matriz, char c){

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                StringBuilder cc = new StringBuilder();
                cc.append(c);
                if (cc.toString().equals(matriz[i][j])  ){
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
            if  (frase.charAt(i) == 'J'){
                devolver.append('I');
            }else if (frase.charAt(i) >= 65 && frase.charAt(i)<=90){
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
            case 'Ç':
                return 'C';

            default:return a;
        }
    }


}
