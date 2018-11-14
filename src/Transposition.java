
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





        return "";
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

        printarMatrix(matrix,"");
        return result.toString();
    }

    static String decypher(String s, String key) {

        return "";

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


    public static void printarMatrix(char[][] mat, String n){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j]!='*'){
                    System.out.print(mat[i][j]);
                }else {
                    System.out.print("+");
                }
            }
            System.out.println();
        }
        System.out.println(n+"\n");

    }





}
