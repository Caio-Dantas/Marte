public class Principal{
    
    public static void main(String[] args) {

        int linhas = 5;
            
        Mars marte = new Mars(linhas,linhas);
        marte.adicionaSonda(1,0, 'S', 5);
        marte.mostrePlaneta();
        marte.processaInput('M', 5);  
        marte.mostrePlaneta();
    }
}
