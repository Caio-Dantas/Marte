

public enum Direcao {
 
    N{
        public int[] traduzComando(){
            return new int[] {0,1};
        }
        public Direcao right(){
            return Direcao.E;
        }
        public Direcao left(){
            return Direcao.W;
        }
    }, 
    E{
        public int[] traduzComando(){
            return new int[] {1,0};
        }
        public Direcao right(){
            return Direcao.S;
        }
        public Direcao left(){
            return Direcao.N;
        }
    }, 
    S{
        public int[] traduzComando(){
            return new int[] {0,-1};
        }
        public Direcao right(){
            return Direcao.W;
        }
        public Direcao left(){
            return Direcao.E;
        }
    }, 
    W{
        public int[] traduzComando(){
            return new int[] {-1,0};
        }
        public Direcao right(){
            return Direcao.N;
        }
        public Direcao left(){
            return Direcao.S;
        }
    };

    /*
    Função: traduzComando
    Para cada objeto da classe retorna um array com os deslocamento correspondente.
    */
    public abstract int[] traduzComando();

    /*
    Função: right && left
    Para cada objeto da classe retorna o objeto que se encontra a sua 'direita' ou 'esquerda' na rosa dos ventos.
    */
    public abstract Direcao right();
    public abstract Direcao left();

}