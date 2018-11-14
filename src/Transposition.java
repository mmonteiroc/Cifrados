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
        StringBuilder devolver = new StringBuilder();
        int columnas = dim, filas = s.length()/columnas;
        double x = (double) s.length()/columnas;
        if (x>filas){
            filas++;
        }
        
        String [][] matrix = crearMatrix(filas,columnas,s);
        
        String [][] matrizSaberNulls = transponerMatriz(matrix);

        String[][] newMatrix = new String[matrix.length][matrix[0].length];
        for (int i = 0, n=0; i < newMatrix.length; i++) {
            for (int j = 0, y=0; j < newMatrix[0].length; j++) {

                if (matrizSaberNulls[i][j]!=null){
                    newMatrix[i][j] = matrix[n][y];
                    y++;
                }
            }
            n++;
        }

        
        

        return devolver.toString();
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

    public static String[][] transponerMatriz(String[][] matriz) {
        //Transposicion de matriz
        //Matriz original a[10][20]
        //Matriz nueva    b[20][10]
        int longitudH = matriz.length,longitudV = matriz[0].length;
        String[][] matrizNueva=new String[longitudV][longitudH];
        for (int i = 0; i < longitudH; i++) {
            for (int j = 0; j < longitudV; j++) {
                matrizNueva[j][i] = matriz[i][j];
            }
        }
        return matrizNueva;
    }



}
