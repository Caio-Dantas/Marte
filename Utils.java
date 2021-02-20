import java.util.Random;

public class Utils {
    /*
    Função: print
    Exibe o parâmetro passado no console.
    */
    static void print(String output){
        System.out.println(output);
    }

    /*
    Função: getIndexChar
    Retorna o index em que um determinado item está localizado dentro de um array de char.
    */
    static int getIndexChar(char[] array, char item){
        for(int i =0; i < array.length; i++){
            if(array[i] == item){
                return i;
            }
        }
        return -1;
    }

    /*
    Função: printMatriz
    Imprime no console uma dada matriz ajustando o espaçamento conforme a quantidade de coluna(largura).
    */
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

    /*
    Função: formatarPrint
    Retorna uma String formatada com base nos parâmetros passados.
    */
    static String formatarPrint(int elemento, int tamanho){
        return String.format("%1$-" + tamanho + "s", elemento);
    }

    /*
    Função: conseguiuEscapar
    Retorna um valor booleano aleatório;
    */
    static boolean conseguiuEscapar(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

}
