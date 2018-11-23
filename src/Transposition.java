/**
 * @author mmonteiro
 * @project Cifrados
 */

public class Transposition {
    /**
     * @param s
     * @param dim
     * @return Devuelve un Stirng Cifrado
     *
     * Esta funcion recibe un String a cifrar y una dimension de la matriz, la dimension (DIM)
     * Definira el numero de columnas de la matriz
     * Deduiremos cuantas filas necesitaremos dividiendo la longitud del String entre la dimension que nos pasen
     *
     * Rellenaremos posicion a posicion la matriz caracter a caracter de nuestra string en horizontal fila por fila
     * Una vez la tengamos rellenada iremos columna por columna leyendo verticalmente y asi tendremos la frase cifrada
     *
     */
    static String cypher(String s, int dim) {
        StringBuilder devolver = new StringBuilder();
        int nColumnas = dim, nFilas = s.length()/nColumnas;
        double x = (double) s.length()/nColumnas;

        //Si la fila es decimal, añadiremos una fila mas ya que no podemos tener una fila y media
        if (x>nFilas){
            nFilas++;
        }

        String [][] matrix = crearMatrix(nFilas,nColumnas,s);
        String[][] newMatrix = new  String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                newMatrix[j][i] = matrix[i][j];

            }
        }

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length; j++) {

                if (newMatrix[i][j]!= null){
                    devolver.append(newMatrix[i][j]);
                }
            }
        }

        return devolver.toString();
    }


    /**
     * @param Frase
     * @param clave
     * @return devolvemos una String cifrada
     *
     * Esta funcion recibe un String a cifrar y una clave, esa clave (su longitud) definira la dimension de la matriz la cual
     * definira el numero de columnas de la matriz
     * Deduiremos cuantas filas necesitaremos dividiendo la longitud del Frase a cifrar entre la dimension que nos pasen
     *
     * Rellenaremos posicion a posicion la matriz caracter a caracter de nuestra string en horizontal fila por fila
     * Una vez la tengamos rellenada reordenaremos la matriz (moviendo sus columnas) por orden alfabetico de la clave e
     * iremos columna por columna leyendo verticalmente y asi tendremos la frase cifrada
     *
     */
    static String cypher(String Frase, String clave) {

        StringBuilder devolucion = new StringBuilder();
        int columnas = clave.length(), filas = Frase.length()/columnas;
        double x =(double) Frase.length()/columnas;
        if (x>filas){
            filas++;
        }

        String[][] matriz = new String[filas][columnas];
        //** <-- NULLS
        /*
            L   I   C   E   U           PASSWORD
            A   V   U   I
            F   A       U   N
            B   O   N       D
            I   A   *   *   *

         */


        //Aqui rellenamos la matriz con la frase a cifrar
        int ñ = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (ñ < Frase.length()){
                    StringBuilder c = new StringBuilder();
                    c.append(Frase.charAt(ñ));
                    matriz[i][j] = c.toString();
                    c.delete(0,c.length()-1);
                    ñ++;
                }
            }
        }

        int [] letras = new int[clave.length()];
        StringBuilder passwordOrdenada = new StringBuilder();

        for (int i = 0; i < letras.length; i++) {
            letras[i] = clave.charAt(i);
        }
        int[] letrasOrdenadas = ordenarArrayDoble(letras);
        char a;
        for (int i = 0; i < letrasOrdenadas.length; i++) {
            a = (char) letrasOrdenadas[i];
            passwordOrdenada.append(a);
        }


        //Aqui lo que hacemos es ordenarla con la clave
        StringBuilder key1 = new StringBuilder();
        key1.append(clave);

        for (int i = 0; i <passwordOrdenada.length() ; i++) {

            int posicion = key1.toString().indexOf(passwordOrdenada.charAt(i));

            key1.replace(posicion,posicion+1,"+");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[j][posicion] != null){
                    devolucion.append(matriz[j][posicion]);
                }
            }
        }
        return devolucion.toString();
    }


    /**
     * @param FraseCifrada
     * @param dimension
     * @return Frase descifrada
     *
     * Esta funcion recibe dos paramtros, uno que es la frase cifrada que queremos descifrar,
     * y otro que es la dimension que se ha usado para cifrarla.
     * Lo primero que haremos sera deduir si necesitaremos añadir algun null o no
     * Despues deduiremos el numero de filas que usaremos para la matriz
     * Crearemos la matriz y por el final añadiremos los nulls que sabemos que necesitaremos
     * Despues iremos verticalmente escribiendo en la matriz la frase que nos han pasado y para
     * descifrarla la leeremos horizontalmente linea a linea
     */
    static String decypher(String FraseCifrada, int dimension) {

        //Contador que nos dira cuntos espacios tenemos
        int cont = 0;
        int contador = 0;

        //Comprobaremos si necesitaremos caracteres especiales para los nulls
        while ((FraseCifrada.length() + cont) % dimension != 0){
            cont++;
        }

        //Miramos si es decimal, y si lo es lo incrementamos hacia arriba
        double conDecimales = (double) FraseCifrada.length()/dimension;
        int filas = FraseCifrada.length()/dimension;
        if (conDecimales>filas){
            filas++;
        }

        char[][] matrix = new char[filas][dimension];
        StringBuilder devolver = new StringBuilder();

        //donde tenemos nulls ponemos un '*'
        if (cont > 0) {
            for (int i = matrix.length; i > 0; i--) {
                int x = matrix[0].length -1;
                for (int j = cont; j > 0; j--) {
                    matrix[i - 1][x] = '*';
                    x--;
                }
                break;
            }
        }

        //Insertamos los caracteres del string uno a uno saltandonos las posiciones de los '*'
        //Los insertaremos verticalmente columna por columna
        for (int i = 0; i < matrix[0].length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][i] == '*'){continue;}
                matrix[k][i] = FraseCifrada.charAt(contador);
                contador++;
            }
        }

        //Añadimos los caracteres de la matriz verticalmente a nuestra string
        // que retornaremos ignorando los asteriscos
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '*'){ continue;}
                devolver.append(matrix[i][j]);
            }
        }

        return devolver.toString();
    }



    /**
     * @param FraseCifrada
     * @param key
     * @return devolveremos una string la cual sera la frase descifrada
     *
     * Lo primero que haremos sera deduir el numero de columnas, fillas y nulls que tendremos que añadir
     * despues crearemos la matriz vacia con ese num de filas y columnas
     *
     * Una vez creada, rellenaremos por el final los nulls que necesitemos,
     * ordenaremos la matriz con la passwd y rellenaremos verticalmente evitando los nulls
     * con la string cifrada que nos han pasado
     * Despues volveremos a ordenarla pero esta vez con el orden original de la clave y leeremos
     * horizontalmente para recibir la frase descifrada
     */
    static String decypher(String FraseCifrada, String key) {
        StringBuilder devolucion = new StringBuilder();
        int columnas = key.length(), filas = FraseCifrada.length()/columnas;
        double x =(double) FraseCifrada.length()/columnas;
        if (x>filas){
            filas++;
        }
        int numNulls = (filas*columnas) - FraseCifrada.length() ;

        String [][] matriz = new String[filas][columnas];
        if (numNulls>0){
            int p = columnas-1;
            for (int i = numNulls; i >0 ; i--) {
                matriz[filas-1][p]="*";
                p--;
            }
        }

        //Aqui lo que hacemos es ordenar la password
        int [] letras = new int[key.length()];
        StringBuilder passwordOrdenada = new StringBuilder();

        for (int i = 0; i < letras.length; i++) {
            letras[i] = key.charAt(i);
        }
        int[] letrasOrdenadas = ordenarArrayDoble(letras);
        char a;
        for (int i = 0; i < letrasOrdenadas.length; i++) {
            a = (char) letrasOrdenadas[i];
            passwordOrdenada.append(a);
        }

        String[][] mat1 = new String[filas][columnas];
        StringBuilder key1 = new StringBuilder(key.toString());

        int[] indices = new int[passwordOrdenada.length()];
        for (int i = 0; i < passwordOrdenada.length(); i++) {
            int posi = key1.toString().indexOf(passwordOrdenada.charAt(i));
            key1.replace(posi,posi+1,"%");

            mat1[filas-1][i] = matriz[filas-1][posi];

            indices[i] = posi;

        }

        //Restablecemos la key a la por defecto
        key1.delete(0,key1.length());
        key1.append(key);

        int contador = 0;
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                if (contador < FraseCifrada.length()){
                    if (mat1[j][i]!="*"){
                        StringBuilder c = new StringBuilder();
                        c.append(FraseCifrada.charAt(contador));
                        mat1[j][i] = c.toString();
                        c.delete(0,c.length()-1);
                    }else {
                        continue;
                    }
                    contador++;
                }
            }
        }

        StringBuilder guiones = new StringBuilder();
        for (int i = 0; i < passwordOrdenada.length(); i++) {
            guiones.append("-");
        }

        String [][] mat2 = new String[filas][columnas];


        for (int i = 0; i < indices.length; i++) {

            int posi = indices[i];
            for (int j = 0; j < filas; j++) {
                mat2[j][posi]= mat1[j][i];
            }

        }

        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                if (mat2[i][j]!="*"){
                    devolucion.append(mat2[i][j]);
                }
            }
        }
        return devolucion.toString();
    }



    //Funciones adicionales que usan nuestro algoritmo

    //Esta funcion lo que hace es crearnos una matrix
    //Rellenada por la string que le pasemos
    public static String[][] crearMatrix(int nFilas, int nCol, String s){

        int contador = 0;
        String[][] matrix = new String[nFilas][nCol];
        for (int i = 0; i < nFilas; i++) {
            for (int j = 0; j < nCol; j++) {
                if (contador == s.length()){
                    continue;
                }
                StringBuilder c = new StringBuilder();
                c.append(s.charAt(contador));
                matrix[i][j] = c.toString();
                c.delete(0,c.length()-1);
                contador++;
            }
        }
        return matrix;
    }


    //Esta funcion recibe un array de INT y te los ordena de menor a mayor
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
