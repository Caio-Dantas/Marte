public class Principal{
    /*
        Exibe o parâmetro passado no console.
    */
    static void print(String output){
        System.out.println(output);
    }

    /*
        Retorna um valor String lido no console.
    */
    static String getInput(){
        return System.console().readLine();
    }

    public static void main(String[] args) {

        int linhas = 5;
        int numSondas = 3;
            
        Mars marte = new Mars(linhas,linhas,numSondas);
        marte.adicionaJogador(0,1, 'N', 5);
        marte.mostrePlaneta();
        marte.processaInput('M', 5);  
        marte.mostrePlaneta();
    }
}
