
public class Mars {

    int[][] planeta;
    Sonda[] sondas;
    int sondasColocadas = 0;
    char[] direcoesPossiveis = {'N', 'E', 'S', 'W'};
    int[] codigosSondas;

    public void mostrePlaneta(){
        Utils.printMatriz(planeta);
    }

    public void adicionaJogador(int posY, int posX, char direcaoInicial, int codigo){
        
        int direcao, posXCartesiano;

        direcao = Utils.getIndexChar(direcoesPossiveis, direcaoInicial);
        
        // Adaptação de uma abordagem matricial para uma abordagem cartesiana
        posXCartesiano = planeta.length - posX - 1;

        sondas[sondasColocadas] = new Sonda(codigo, posXCartesiano, posY, direcao);
        planeta[posXCartesiano][posY] = codigo;
        codigosSondas[sondasColocadas] = codigo;
        sondasColocadas++;
    }

    public void processaInput(char direcao, int codigo){
        
        int pos = Utils.getIndexInt(codigosSondas, codigo);
        int[] movimento;
        Sonda novaSonda = sondas[pos];
        if(direcao == 'L' || direcao == 'R'){
            alteraDirecao(direcao, novaSonda);
        }
        else if(direcao == 'M'){
            movimento = traduzOrdem(novaSonda);
            moveSonda(novaSonda, movimento);
        }
    }

    public int[] traduzOrdem(Sonda sonda){
        int indexDirecao;
        char direcao;

        indexDirecao = sonda.direcao;
        direcao = direcoesPossiveis[indexDirecao];

        switch(direcao){
            case 'N':
                return new int[] {-1,0};
            case 'E':
                return new int[] {0,1};
            case 'S':
                return new int[] {1,0};
            case 'W':
                return new int[] {0,-1};
            default:
                return new int[] {0,0};
        }
        
    }

    public void alteraDirecao(char novaDirecao, Sonda sonda){

        int direcao = sonda.direcao;

        if(novaDirecao == 'R'){
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

    public void moveSonda(Sonda sonda, int[] delta){
        int novaPosX, novaPosY;

        novaPosX = sonda.posicaoX + delta[0];
        novaPosY = sonda.posicaoY + delta[1];

        // Caso ultrapasse para esquerda move para a última coluna
        if(novaPosY < 0){
            novaPosY = planeta[0].length - 1;
        }
        // Caso ultrapasse para direita move para a primeira coluna
        else if(novaPosY > planeta[0].length - 1){
            novaPosY = 0;
        }
        // Caso ultrapasse para cima move para a última linha
        if(novaPosX < 0){
            novaPosX = planeta.length - 1;
        }
        // Caso ultrapasse para baixo move para a primeria linha
        if(novaPosX > planeta.length - 1){
            novaPosX = 0;
        }


        planeta[sonda.posicaoX][sonda.posicaoY] = 0;
        planeta[novaPosX][novaPosY] = sonda.id;

        sonda.posicaoX = novaPosX;
        sonda.posicaoY = novaPosY;

    }


    public Mars(int linhas, int colunas, int quantidadeSondas){
        sondas = new Sonda[quantidadeSondas];
        codigosSondas = new int[quantidadeSondas];
        prepararTerreno(linhas, colunas);
    }

}
