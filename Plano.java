import java.util.Optional;

public class Plano {
    
    private int[][] posicoes;

    /*
    Contrutor: Plano
    Cria um plano de duas dimensões com a quantidade de linhas e colunas passadas.
    */
    public Plano(int linhas, int colunas){
        this.posicoes = new int[linhas][colunas];
    }

    /*  
    Função: getOverridePosicao
    Caso a posição passada ultrapasse os limites do plano então aplica a regra escolhida para o tratamento,
    as sondas são movidas para a extremidade oposta do limite ultrapassado.
    */
    public Optional<Posicao> getOverridePosicao(Posicao posicao){
        int novaPosX, novaPosY;
        boolean precisaOverride = false;
        
        novaPosX = posicao.getX();
        novaPosY = posicao.getY();

        // Caso ultrapasse para esquerda move para a última coluna
        if(novaPosY < 0){
            novaPosY = this.posicoes[0].length - 1;
            precisaOverride = true;
        }
        // Caso ultrapasse para direita move para a primeira coluna
        else if(novaPosY > this.posicoes[0].length - 1){
            novaPosY = 0;
            precisaOverride = true;
        }
        // Caso ultrapasse para cima move para a última linha
        if(novaPosX < 0){
            novaPosX = this.posicoes.length - 1;
            precisaOverride = true;
        }
        // Caso ultrapasse para baixo move para a primeria linha
        if(novaPosX > this.posicoes.length - 1){
            novaPosX = 0;
            precisaOverride = true;
        }

        if(precisaOverride){
            return Optional.of(new Posicao(novaPosX, novaPosY));
        }
        else{
            return Optional.empty();
        }
        
    }

    /*
    Função: removeSonda
    Remove determinada sonda da posição atual dela no plano.
    */
    public void removeSonda(Sonda sonda){
        Posicao posMatriz = cartesianoToMatriz(sonda.getPosicao());
        this.posicoes[posMatriz.getX()][posMatriz.getY()] = 0;
    }

    /*
    Função: posicionaSonda
    Posiciona determinada sonda dentro do plano em sua respectiva posição.
    */
    public boolean posicionaSonda(Sonda sonda){
        Posicao posMatriz = cartesianoToMatriz(sonda.getPosicao());

        if(this.posicoes[posMatriz.getX()][posMatriz.getY()] == 0){
            this.posicoes[posMatriz.getX()][posMatriz.getY()] = sonda.getId();
            return true;
        }
        return false;

    }
    
    /*
    Função: getIdByPosition
    Retorna o ID que se localiza em determinada posição.
    */
    public int getIdByPosition(Posicao posicao){
        Posicao posMatriz = cartesianoToMatriz(posicao);
        return posicoes[posMatriz.getX()][posMatriz.getY()];
    }

    /*
    Função: cartesianoToMatriz
    Dado uma posicao em coordenadas cartesianas converte para coordenadas de uma matriz.
    */
    public Posicao cartesianoToMatriz(Posicao posicao){
        int posXMatriz, posYMatriz;

        posXMatriz = this.posicoes.length - posicao.getY() - 1;
        posYMatriz = posicao.getX();

        return new Posicao(posXMatriz, posYMatriz);
    }

    /*
    Função: exibeMapa
    Imprime no console o status atual das posições das sondas.
    */
    public void exibeMapa(){
        Printer printer = new Printer();
        printer.printMatriz(this.posicoes);
    }

}
