import java.util.ArrayList;


public class Mars {

    int[][] planeta;
    char[] direcoesPossiveis = {'N', 'E', 'S', 'W'};
    
    ArrayList<Integer> codigosSondas = new ArrayList<Integer>();
    ArrayList<Sonda> sondas = new ArrayList<Sonda>();

    /*
    Função: mostrePlaneta
    Imprime no console o estado atual do planeta com as sondas posicionadas e informando o código de cada sonda.
    */
    public void mostrePlaneta(){
        Utils.printMatriz(planeta);
    }

    /*
    Função: adicionaSonda
    Adiciona uma nova sonda no planeta que se encontra na posição passada em coordenadas cartesianas,
    caso a posição e o código da sonda sejam válidas.
    */
    public void adicionaSonda(int coordenadaX, int coordenadaY, char direcaoInicial, int codigo){
        
        int posX, posY, direcao, posXCartesiano;

        posX = coordenadaY;
        posY = coordenadaX;

        // Adaptação de uma abordagem matricial para uma abordagem cartesiana
        posXCartesiano = planeta.length - posX - 1;
        
        if(codigosSondas.indexOf(codigo) >= 0){
            Utils.print("Já existe uma sonda com esse código.");
            return;
        }

        direcao = Utils.getIndexChar(direcoesPossiveis, direcaoInicial);
        if(direcao < 0){
            Utils.print("Adicione uma direção válida.");
            return;
        }

        if(planeta[posXCartesiano][posY] == 0){
            planeta[posXCartesiano][posY] = codigo;
        }
        else{
            Utils.print("Já existe uma sonda nessa posição.");
            return;
        }

        sondas.add(new Sonda(codigo, posXCartesiano, posY, direcao));
        codigosSondas.add(codigo);
    }

    /*
    Função: processaInput
    Admmistra o input do usuário e decide o próximo passo conforme a direção e o código da sonda passado.
    */
    public void processaInput(char direcao, int codigo){
        
        int[] movimento;
        int pos;
        Sonda novaSonda;

        pos = codigosSondas.indexOf(codigo);
        if(pos < 0){
            Utils.print("Insira um código válido ou adicione uma nova sonda.");
            return;
        }
        novaSonda = sondas.get(pos);
        if(direcao == 'L' || direcao == 'R'){
            alteraDirecao(direcao, novaSonda);
        }
        else if(direcao == 'M'){
            movimento = traduzComando(novaSonda);
            moveSonda(novaSonda, movimento);
        }
        else{
            Utils.print("Insira uma direção válida.");
        }
    }

    /*
    Função: traduzComando
    Retorno um array de duas posições contendo o deslocamento que deve ser feito em cada um dos eixos com base na direção atual da sonda.
    */
    public int[] traduzComando(Sonda sonda){

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

    /*
    Função: alteraDirecao
    Altera a direção da sonda para esquerda ou para direita e define a nova direção com base na direção anterior da sonda.
    */
    public void alteraDirecao(char comando, Sonda sonda){

        int direcao = sonda.direcao;

        if(comando == 'R'){
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

    /*
    Função: prepararTerreno
    Ajusta um array 2d conforme a quantidade de linhas e colunas passadas. Esse array será usado como uma base para os movimentos das sondas.
    */
    public void prepararTerreno(int linhas, int colunas){
        planeta = new int[linhas][colunas];
    }

    /*
    Função: moveSonda
    Define a nova posição da sonda com base em um array de 2 posições passado contendo o deslocamento a ser realizado.
    */
    public void moveSonda(Sonda sonda, int[] delta){

        // A sonda sai da posição anterior
        planeta[sonda.posicaoX][sonda.posicaoY] = 0;

        geraNovaPosicao(sonda, delta);

        if(planeta[sonda.posicaoX][sonda.posicaoY] == 0){
            planeta[sonda.posicaoX][sonda.posicaoY] = sonda.id;
        }
        else{
            Utils.print("ERRO");
            Utils.print("HOUVE UMA COLISÃO!");
            Utils.print("ABORTAR MISSÂO");
            if(Utils.conseguiuEscapar()){
                Utils.print("O PILOTO DESVIOU E SAIU ILESO");
                geraNovaPosicao(sonda, delta);
                planeta[sonda.posicaoX][sonda.posicaoY] = sonda.id;
            }
            else{
                Utils.print("PERDEMOS A COMUNICAÇÃO DA SONDA " + sonda.id + " E DA SONDA " + planeta[sonda.posicaoX][sonda.posicaoY]);
                planeta[sonda.posicaoX][sonda.posicaoY] = 0;
            }
        }
    }

    /*
    Função: geraNovaPosicao
    Define uma nova localização dentro do planeta com base na posição anterior e no deslocamento a ser feito.
    */
    public void geraNovaPosicao(Sonda sonda, int[] delta){

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

        sonda.posicaoX = novaPosX;
        sonda.posicaoY = novaPosY;
    }

    /*
    Construtor: Mars
    Construtor da classe Mars, exige a quantidade de linhas e colunas do planeta e a quantidade de sondas presentes no planeta.
    */
    public Mars(int linhas, int colunas){
        prepararTerreno(linhas, colunas);
    }

}
