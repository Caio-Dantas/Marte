public class Sonda {
    int id;
    int posicaoX;
    int posicaoY;
    int direcao;
    boolean ativa = true;

    public void desativaSonda(){
        ativa = false;
    }

    public void alteraPosicao(int coordX, int coordY){
        posicaoX = coordX;
        posicaoY = coordY;
    }
    
    public void alteraDirecao(int novaDirecao){
        direcao = novaDirecao;
    }

    /*
    Construtor: Sonda
    Construtor da classe Sonda, exige um ID, as coordenadas iniciais e a direção de uma nova Sonda.
    */
    public Sonda(int idNovo, int posicaoXInicial, int posicaoYInicial, int direcaoInicial){
        id = idNovo;
        posicaoX = posicaoXInicial;
        posicaoY = posicaoYInicial;
        direcao = direcaoInicial;
    }

}
