
public enum Comandos {
    R{
        public void aplicaComando(TorreControle torre, Sonda sonda, Plano plano){
            sonda.deslocaRight();
        }
    }, 
    L{
        public void aplicaComando(TorreControle torre, Sonda sonda, Plano plano){
            sonda.deslocaLeft();
        }
    },
    M{
        public void aplicaComando(TorreControle torre, Sonda sonda, Plano plano){
            torre.moveSonda(sonda, plano);
        }  
    };

    /*
    Função: aplicaComando
    Para cada possível comando passado (L, R, M) aplica as movimentações necessárias: rotação ou deslocamento.
    */
    public abstract void aplicaComando(TorreControle torre, Sonda sonda, Plano plano);
}
