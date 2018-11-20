public class Transposition {


    static String cypher(String s, int dim) {

        StringBuilder devolver = new StringBuilder();
        int nColumnas = dim, nFilas = s.length()/nColumnas;
        double x = (double) s.length()/nColumnas;

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

    static String cypher(String s, String key) {

        StringBuilder devolucion = new StringBuilder();
        int columnas = key.length(), filas = s.length()/columnas;
        double x =(double) s.length()/columnas;
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

        int ñ = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (ñ < s.length()){
                    StringBuilder c = new StringBuilder();
                    c.append(s.charAt(ñ));
                    matriz[i][j] = c.toString();
                    c.delete(0,c.length()-1);
                    ñ++;
                }
            }
        }

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


        StringBuilder key1 = new StringBuilder();
        key1.append(key);
        System.out.println(key1.toString());
        for (int i = 0; i <passwordOrdenada.length() ; i++) {

            int posicion = key1.toString().indexOf(passwordOrdenada.charAt(i));

            key1.replace(posicion,posicion+1,"+");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[j][posicion] != null){
                    devolucion.append(matriz[j][posicion]);
                }
            }



        }

        printarMatrix(matriz,"matriz con key");

        return devolucion.toString();
    }


    static String decypher(String s, int dim) {

        //Contador que nos dira cuntos espacios tenemos
        int cont = 0;
        int contador = 0;

        //Comprobaremos si necesitaremos caracteres especiales para los nulls
        while ((s.length() + cont) % dim != 0){
            cont++;
        }

        //Miramos si es decimal, y si lo es lo incrementamos hacia arriba
        double conDecimales = (double) s.length()/dim;
        int row = s.length()/dim;
        if (conDecimales>row){
            row++;
        }



        char[][] matrix = new char[row][dim];
        StringBuilder result = new StringBuilder();

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
        for (int i = 0; i < matrix[0].length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][i] == '*'){continue;}
                matrix[k][i] = s.charAt(contador);
                contador++;
            }
        }

        //Añadimos los caracteres de la matriz verticalmente ignorando los asteriscos
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '*'){ continue;}
                result.append(matrix[i][j]);
            }
        }

        return result.toString();
    }

    static String decypher(String s, String key) {
        StringBuilder devolucion = new StringBuilder();
        int columnas = key.length(), filas = s.length()/columnas;
        double x =(double) s.length()/columnas;
        if (x>filas){
            filas++;
        }
        int numNulls = (filas*columnas) - s.length() ;

        System.out.println("\n\n\n\n\n"+columnas+"col\n"+filas+"fil\n"+numNulls+"nulls");

        String [][] matriz = new String[filas][columnas];
        if (numNulls>0){
            System.out.println(numNulls);
            int p = columnas-1;
            for (int i = numNulls; i >0 ; i--) {
                matriz[filas-1][p]="*";
                p--;
            }
            printarMatrix(matriz,"bac");
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

        System.out.println(passwordOrdenada);
        printarMatrix(mat1,"Matriz ordenada solo nulls");
        //Restablecemos la key a la por defecto
        key1.delete(0,key1.length());
        key1.append(key);



        int contador = 0;
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                if (contador < s.length()){
                    if (mat1[j][i]!="*"){
                        StringBuilder c = new StringBuilder();
                        c.append(s.charAt(contador));
                        mat1[j][i] = c.toString();
                        c.delete(0,c.length()-1);
                    }else {
                        continue;
                    }
                    contador++;
                }
            }
        }

        System.out.println(passwordOrdenada);
        StringBuilder guiones = new StringBuilder();
        for (int i = 0; i < passwordOrdenada.length(); i++) {
            guiones.append("-");
        }
        System.out.println(guiones);
        printarMatrix(mat1,"Matriz Ordenada y rellenada");

        String [][] mat2 = new String[filas][columnas];


        for (int i = 0; i < indices.length; i++) {

            int posi = indices[i];
            for (int j = 0; j < filas; j++) {
                mat2[j][posi]= mat1[j][i];
            }

        }




        System.out.println(key);
        printarMatrix(mat2,"matriz 2");

        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                if (mat2[i][j]!="*"){
                    devolucion.append(mat2[i][j]);
                }
            }
        }


        return devolucion.toString();
    }









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


    public static void printarMatrix(String[][] mat, String n){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j]!=null){
                    System.out.print(mat[i][j]);
                }else {
                    System.out.print("+");
                }
            }
            System.out.println();
        }
        System.out.println(n+"\n");

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
