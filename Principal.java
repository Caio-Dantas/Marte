public class Principal{
    
    public static void main(String[] args) {

        int linhas = 5;
            
        Mars marte = new Mars(linhas,linhas);
        marte.adicionaSonda(1,0, 'S', 5);
        marte.adicionaSonda(0,0, 'E', 7);
        marte.processaInput('M', 7);
        marte.mostrePlaneta();
    }
}
