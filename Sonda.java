public class Sonda {
    private int id;
    private int posicaoX;
    private int posicaoY;
    private int direcao;
    private boolean ativa = true;

    /*
    Encapsulamento do atributo 'ativa'
    */
    public void desativaSonda(){
        ativa = false;
    }

    public boolean isAtiva(){
        return ativa;
    }

    /*
    Encapsulamento dos atributos 'posicaoX' e 'posicaoY'
    */
    public void alteraPosicao(int coordX, int coordY){
        posicaoX = coordX;
        posicaoY = coordY;
    }

    public int getPosX(){
        return posicaoX;
    }

    public int getPosY(){
        return posicaoY;
    }

    /*
    Encapsulamento do atributo 'direcao'
    */
    public void alteraDirecao(int novaDirecao){
        direcao = novaDirecao;
    }

    public int getDirecao(){
        return direcao;
    }

    /*
    Encapsulamento do atributo 'id'
    */
    public int getId(){
        return id;
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
