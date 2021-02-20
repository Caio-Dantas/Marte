public class Principal{
    
    public static void main(String[] args) {

        int linhas = 5;
        int numSondas = 3;
            
        Mars marte = new Mars(linhas,linhas,numSondas);
        marte.adicionaJogador(1,0, 'S', 5);
        marte.mostrePlaneta();
        marte.processaInput('M', 5);  
        marte.mostrePlaneta();
    }
}
