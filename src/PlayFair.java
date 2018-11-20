
public class PlayFair {

    public static void main(String[] args) {
        encrypt("hola mundo","holaaaoekalee");
    }
    public static String decrypt(String text, String pass) {


        return "";
    }

    public static String encrypt(String text, String pass) {
        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "i", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y", "Z"};
        StringBuilder passwordCorrecta = new StringBuilder();


        //Aqui hacemos que la password no tenga ningun caracter repetido
        //EX: ARANYA = ARNY
        for (int i = 0; i < pass.length(); i++) {
            StringBuilder c = new StringBuilder() ;
            c.append(pass.charAt(i));
            if (passwordCorrecta.toString().contains(c.toString())){
                continue;
            }else {
                passwordCorrecta.append(pass.charAt(i));
            }
        }

        //Pasamos todos los caracteres a mayusculas
        StringBuilder passwdMAY = new StringBuilder(passwordCorrecta.toString().toUpperCase());

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
                if (matriz[i][j]!=null){

                }
            }
        }


        Transposition.printarMatrix(matriz,"matrix");

        return "";
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
