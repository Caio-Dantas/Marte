import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

public class Tests {
    
    @Test
    public void testaConversao(){
        assertArrayEquals(new int[] {0,4}, Utils.matrizToCartesiano(0, 0, 5));
        assertArrayEquals(new int[] {5,0}, Utils.matrizToCartesiano(5, 5, 6));
        assertArrayEquals(new int[] {1,1}, Utils.matrizToCartesiano(2, 1, 4));
        assertArrayEquals(new int[] {0,0}, Utils.matrizToCartesiano(9, 0, 10));
        assertArrayEquals(new int[] {0,0}, Utils.matrizToCartesiano(4, 0, 5));
        assertArrayEquals(new int[] {2,2}, Utils.matrizToCartesiano(2, 2, 5));
        assertArrayEquals(new int[] {10,10}, Utils.matrizToCartesiano(0, 10, 11));
    }

    /*
    Teste: testaDeslocamento
    Verifica a posição da sonda após a movimentação, utiliza as coordenadas em formato CARTESIANO.
    */
    @Test
    public void testaDeslocamento(){

        assertArrayEquals(new Object[] {0,1, 'N'}, preparaTeste(0, 0, 4, 4, 'N', "M"));
        assertArrayEquals(new Object[] {0,9, 'S'}, preparaTeste(0, 10, 10, 10, 'S', "M"));
        assertArrayEquals(new Object[] {0,0, 'N'}, preparaTeste(0, 0, 4, 0, 'N', "M"));
        assertArrayEquals(new Object[] {1,0, 'W'}, preparaTeste(0, 0, 4, 0, 'E', "MLL"));
        assertArrayEquals(new Object[] {4,4, 'E'}, preparaTeste(4, 4, 4, 4, 'E', "RRLL"));
        assertArrayEquals(new Object[] {1,3, 'N'}, preparaTeste(1, 2, 5, 5, 'N', "LMLMLMLMM"));
        assertArrayEquals(new Object[] {5,1, 'E'}, preparaTeste(3, 3, 5, 5, 'E', "MMRMMRMRRM"));
        assertArrayEquals(new Object[] {0,0, 'E'}, preparaTeste(5, 5, 5, 5, 'N', "MRM"));
        assertArrayEquals(new Object[] {3,5, 'N'}, preparaTeste(0, 0, 3, 5, 'S', "LLMMMMRMMMLM"));
        assertArrayEquals(new Object[] {1,2, 'S'}, preparaTeste(0, 0, 1, 4, 'W', "LMMLMRM"));
        assertArrayEquals(new Object[] {4,4, 'N'}, preparaTeste(4, 4, 5, 5, 'N', "RMMMMMML"));
    }


    /*
    Função: preparaTeste
    Retorna um array de objetos contendo informação sobre a posição de uma dada sonda após seguir os comandos passados, a função é usada para
    montar um array para ser usado no teste "testaDeslocamento"
    */
    public Object[] preparaTeste(int posX, int posY, int posXLimite, int posYLimite, char direcaoIncial, String comando){
        Sonda sondaTeste;
        int[] posFinal;
        Mars marte = new Mars(posYLimite + 1, posXLimite + 1);

        marte.adicionaSonda(posX, posY, direcaoIncial, 1);
        for( char charComando : comando.toCharArray()){
            marte.processaInput(charComando, 1);
        }
        sondaTeste = marte.sondas.get(0);
        posFinal = Utils.matrizToCartesiano(sondaTeste.posicaoX, sondaTeste.posicaoY, posYLimite + 1);

        return new Object[] {posFinal[0], posFinal[1], marte.direcoesPossiveis[sondaTeste.direcao] };
    }
}
