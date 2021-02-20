import java.util.Arrays;

public class Mars {

    int[][] planeta;
    Sonda[] sondas;
    int sondasColocadas = 0;
    char[] direcoesPossiveis = {'N', 'L', 'S', 'O'};
    int[] codigosSondas;

    public void mostrePlaneta(){
        System.out.println("---------------------------");
        for (int i = 0; i < planeta.length; i++) {
            for (int j = 0; j < planeta[i].length; j++) {
                System.out.print(planeta[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void adicionaJogador(int posX, int posY, char direcaoInicial, int codigo){
        
        
        //sondas[sondasColocadas - 1] = sondasColocadas;
        int direcao = Arrays.binarySearch(direcoesPossiveis, direcaoInicial);

        sondas[sondasColocadas] = new Sonda(codigo, posX, posY, direcao);

        planeta[posX][posY] = sondas[sondasColocadas].id;
        codigosSondas[sondasColocadas] = codigo;
        
        
        sondasColocadas++;
    }

    public void processaInput(char direcao, int codigo){
        int pos = Arrays.binarySearch(codigosSondas, codigo);
        Sonda novaSonda = sondas[pos];
        if(direcao == 'E' || direcao == 'D'){
            alteraDirecao(direcao, novaSonda);
        }
        else if(direcao == 'F'){

        }
    }


    public void alteraDirecao(char novaDirecao, Sonda sonda){
        int direcao = sonda.direcao;
        if(novaDirecao == 'D'){
            direcao++;
            direcao = direcao % 4;
        }
        else{
            direcao--;
            if(direcao == -1){
                direcao = 3;
            }
        }
        sonda.direcao = direcao;
    }

    public void prepararTerreno(int linhas, int colunas){
        planeta = new int[linhas][colunas];
    }

    public void moveSonda(int indiceSonda, int posXAnterior, int posYAnterior, int posXNova, int posYNova){
        planeta[posXAnterior][posYAnterior] = 0;
        planeta[posXNova][posYNova] = sondas[indiceSonda].id;
    }

    public Mars(int linhas, int colunas, int quantidadeSondas){
        sondas = new Sonda[quantidadeSondas];
        codigosSondas = new int[quantidadeSondas];
        prepararTerreno(linhas, colunas);
    }

}
