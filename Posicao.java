public class Posicao {

    private int x;
    private int y;

    /*
    Construtor: Posicao
    Cada objeto da classe Posicao é composto por dois atributos, um inteiro x e um y.
    */
    public Posicao(int x, int y){
        this.x = x;
        this.y = y;
    }

    
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    /*
    Função: novaPosicao
    Gera uma nova posição com base na posição atual e em uma determinada direção.
    */
    public Posicao novaPosicao(Direcao direcao){
        
        int novaPosX, novaPosY;
        int [] delta = direcao.traduzComando();

        novaPosX = this.x + delta[0];
        novaPosY = this.y+ delta[1];

        return new Posicao(novaPosX, novaPosY);
    }
    
}
