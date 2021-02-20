import java.util.Random;

public class Utils {
    /*
        Exibe o parâmetro passado no console.
    */
    static void print(String output){
        System.out.println(output);
    }

    static int getIndexChar(char[] array, char key){
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
                System.out.print(formatarPrint(matriz[i][j], matriz[i].length));
            }
            print("");
        }
        print("---------------------------");
    }

    static String formatarPrint(int elemento, int tamanho){
        return String.format("%1$-" + tamanho + "s", elemento);
    }

    static boolean conseguiuEscapar(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

}
