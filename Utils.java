public class Utils {
    /*
        Exibe o parâmetro passado no console.
    */
    static void print(String output){
        System.out.println(output);
    }

    /*
        Retorna um valor String lido no console.
    */
    static String getInput(){
        return System.console().readLine();
    }

    static int getIndexChar(char[] array, char key){
        for(int i =0; i < array.length; i++){
            if(array[i] == key){
                return i;
            }
        }
        return -1;
    }

    static int getIndexInt(int[] array, int key){
        for(int i =0; i < array.length; i++){
            if(array[i] == key){
                return i;
            }
        }
        return -1;
    }

    static void printMatriz(int[][] matriz){
        print("---------------------------");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            print("");
        }
        print("---------------------------");
    }
}
