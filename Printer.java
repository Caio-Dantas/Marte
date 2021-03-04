public class Printer {
    /*
    Função: print
    Exibe o parâmetro passado no console.
    */
    public void print(String output){
        System.out.println(output);
    }

    /*
    Função: printMatriz
    Imprime no console uma dada matriz ajustando o espaçamento conforme a quantidade de coluna(largura).
    */
    public void printMatriz(int[][] matriz){
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
    public String formatarPrint(int elemento, int tamanho){
        return String.format("%1$-" + tamanho + "s", elemento);
    }

}
