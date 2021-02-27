import java.util.ArrayList;


public class Mars {

    int[][] planeta;
    char[] direcoesPossiveis = {'N', 'E', 'S', 'W'};
    
    ArrayList<Sonda> sondas = new ArrayList<Sonda>();

    /*
    Função: adicionaSonda
    Adiciona uma nova sonda no planeta que se encontra na posição passada em coordenadas cartesianas,
    caso a posição e o código da sonda sejam válidas.
    */
    public void adicionaSonda(int coordenadaX, int coordenadaY, char direcaoInicial, int codigo){
        
        int direcao, posX, posY;
        int[] coords;

        coords = Utils.cartesianoToMatriz(coordenadaX, coordenadaY, planeta.length);

        posX = coords[0];
        posY = coords[1];
        
        if(Utils.getSondaById(codigo, sondas).id != -1){
            Utils.print("Já existe uma sonda com esse código.");
            return;
        }

        direcao = Utils.getIndexChar(direcoesPossiveis, direcaoInicial);
        if(direcao < 0){
            Utils.print("Adicione uma direção válida.");
            return;
        }

        if(planeta[posX][posY] == 0){
            planeta[posX][posY] = codigo;
        }
        else{
            Utils.print("Já existe uma sonda nessa posição.");
            return;
        }

        sondas.add(new Sonda(codigo, posX, posY, direcao));
    }

    /*
    Função: processaInput
    Admmistra o input do usuário e decide o próximo passo conforme a direção e o código da sonda passado.
    */
    public void processaInput(char direcao, int codigo){
        
        int[] movimento;
        Sonda novaSonda;
        direcao = Character.toUpperCase(direcao);

        novaSonda = Utils.getSondaById(codigo, sondas);
        if(novaSonda.id == -1){
            Utils.print("Insira um código válido ou adicione uma nova sonda.");
            return;
        }
        // Não processa os movimentos após a sonda ser desativada
        if(!novaSonda.ativa){
            return;
        }
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
    Função: alteraDirecao
    Altera a direção da sonda para esquerda ou para direita e define a nova direção com base na direção anterior da sonda.
    */
    private void alteraDirecao(char comando, Sonda sonda){

        int direcao = sonda.direcao;

        if(comando == 'R'){
            direcao++;
            direcao = direcao % direcoesPossiveis.length;
        }
        else{
            direcao--;
            if(direcao < 0){
                direcao = direcoesPossiveis.length - 1;
            }
        }
        sonda.alteraDirecao(direcao);
    }

    /*
    Função: traduzComando
    Retorno um array de duas posições contendo o deslocamento que deve ser feito em cada um dos eixos com base na direção atual da sonda.
    */
    private int[] traduzComando(Sonda sonda){

        char direcao;
        direcao = direcoesPossiveis[sonda.direcao];

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
    Função: moveSonda
    Define a nova posição da sonda com base em um array de 2 posições passado contendo o deslocamento a ser realizado.
    */
    private void moveSonda(Sonda sonda, int[] delta){

        Sonda sondaPerdida;

        planeta[sonda.posicaoX][sonda.posicaoY] = 0;

        geraNovaPosicao(sonda, delta);

        if(planeta[sonda.posicaoX][sonda.posicaoY] == 0){
            planeta[sonda.posicaoX][sonda.posicaoY] = sonda.id;
        }
        else{
            Utils.print("ERRO");
            Utils.print("COLISÃO IMINENTE!");
            Utils.print("ABORTAR MISSÂO");

            if(Utils.conseguiuEscapar()){
                Utils.print("O PILOTO DESVIOU E SAIU ILESO");

                geraNovaPosicao(sonda, delta);
                planeta[sonda.posicaoX][sonda.posicaoY] = sonda.id;

            }
            else{
                Utils.print("PERDEMOS A COMUNICAÇÃO DA SONDA " + sonda.id + " E DA SONDA " + planeta[sonda.posicaoX][sonda.posicaoY]);
                
                sondaPerdida = Utils.getSondaById(planeta[sonda.posicaoX][sonda.posicaoY], sondas);
                sondaPerdida.desativaSonda();
                sonda.desativaSonda();

                planeta[sonda.posicaoX][sonda.posicaoY] = 0;
            }
        }
    }

    /*
    Função: geraNovaPosicao
    Define uma nova localização dentro do planeta com base na posição anterior e no deslocamento a ser feito.
    */
    private void geraNovaPosicao(Sonda sonda, int[] delta){

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

        sonda.alteraPosicao(novaPosX, novaPosY);
    }

    /*
    Função: mostraPlaneta
    Imprime no console o estado atual do planeta com as sondas posicionadas e informando o código de cada sonda.
    */
    public void mostraPlaneta(){
        Utils.printMatriz(planeta);
    }

    /*
    Função: mostraSondas
    Imprime no Console o status de todas Sondas colocadas no planeta, caso a sonda não esteja desaparecida, imprime as coordenadas e a direção.
    */
    public void mostraSondas(){
        int[] coords;
        for(Sonda sonda : sondas){
            if( sonda.ativa ){
                coords = Utils.matrizToCartesiano(sonda.posicaoX, sonda.posicaoY, planeta.length);
                Utils.print("Sonda : " + sonda.id + " com coordenadas " + coords[0] + " " + coords[1] + " " + direcoesPossiveis[sonda.direcao] );
            }
            else{
                Utils.print("Sonda : " + sonda.id + " desativada.");
            }
        }
    }

    /*
    Função: prepararTerreno
    Ajusta um array 2d conforme a quantidade de linhas e colunas passadas. Esse array será usado como uma base para os movimentos das sondas.
    */
    private void prepararTerreno(int linhas, int colunas){
        planeta = new int[linhas][colunas];
    }

    /*
    Construtor: Mars
    Construtor da classe Mars, exige a quantidade de linhas e colunas do planeta e a quantidade de sondas presentes no planeta.
    */
    public Mars(int linhas, int colunas){
        prepararTerreno(linhas, colunas);
    }

}
