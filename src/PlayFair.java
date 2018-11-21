/**
 * @author mmonteiro
 * @project Cifrados
 */
public class PlayFair {

    public static void main(String[] args) {
        encrypt("hola mundo","holaaao  ekaleessxwq!");
    }


    public static String encrypt(String text, String pass) {
        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y", "Z"};
        StringBuilder passwordCorrecta = new StringBuilder();

        StringBuilder fraseLimpia = new StringBuilder();
        pass = pass.toUpperCase();
        // Aqui lo que hacemos es comprobar si lleva algun caracter especial la passwrd, en el caso de
        // que sea asi, se eliminan los caracteres especiales
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) >= 65 && pass.charAt(i)<=90){
                fraseLimpia.append(pass.charAt(i));
            }else{
                char c = caracterEspecial(pass.charAt(i));
                if (c>= 65 && c<=90){
                    fraseLimpia.append(c);
                }
            }
        }

        //Aqui hacemos que la password no tenga ningun caracter repetido
        //EX: ARANYA = ARNY
        for (int i = 0; i < fraseLimpia.length(); i++) {
            StringBuilder c = new StringBuilder() ;
            c.append(fraseLimpia.charAt(i));
            if (passwordCorrecta.toString().contains(c.toString())){
                continue;
            }else {
                passwordCorrecta.append(fraseLimpia.charAt(i));
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

        return "";
    }

    public static String decrypt(String text, String pass) {


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
