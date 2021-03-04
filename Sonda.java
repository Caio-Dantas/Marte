public class Sonda {
    private int id;
    private Posicao posicao;
    private Direcao direcao;
    private boolean ativa = true;

    /*
    Encapsulamento do atributo 'ativa'
    */
    public void desativaSonda(){
        this.ativa = false;
    }

    public boolean isAtiva(){
        return this.ativa;
    }

    /*
    Encapsulamento do 'posicao'
    */
    public void alteraPosicao(Posicao posicao){
        this.posicao = posicao;
    }

    public int getPosX(){
        return this.posicao.getX();
    }

    public int getPosY(){
        return this.posicao.getY();
    }

    public Posicao getPosicao(){
        return this.posicao;
    }

    /*
    Encapsulamento do atributo 'direcao'
    */
    public Direcao getDirecao(){
        return this.direcao;
    }

    public void deslocaRight(){
        this.direcao = this.direcao.right();
    }

    public void deslocaLeft(){
        this.direcao = this.direcao.left();
    }

    /*
    Encapsulamento do atributo 'id'
    */
    public int getId(){
        return this.id;
    }


    /*
    Função: deslocaSonda
    Aplica o deslocamento na sonda
    */
    public void deslocaSonda(){
        this.posicao = this.posicao.novaPosicao(this.direcao);
    }

    /*
    Construtor: Sonda
    Construtor da classe Sonda, exige um ID, as coordenadas iniciais e a direção de uma nova Sonda.
    */
    public Sonda(int id, Posicao posicao, Direcao direcao){
        this.id = id;
        this.posicao = posicao;
        this.direcao = direcao;
    }   

}
