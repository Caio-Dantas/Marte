import java.util.Scanner;

public class Principal{
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); 

        int linhas, colunas, coordX, coordY, ID;
        char direcao;
        String comando = "", movimentacao = "";
        boolean conexaoSondas = true;

        Utils.print("Insira as coordenadas do canto superior direito:");
        colunas = scan.nextInt();
        linhas = scan.nextInt();

        Mars marte = new Mars(colunas, linhas);

        while(conexaoSondas){

            System.out.println("Insira o comando que deseja executar: (adicionar, mover, mostrar, sair)");
            comando = scan.next();
            
            if(comando.equalsIgnoreCase("adicionar")){
                Utils.print("Insira as coordenadas da sonda e a orientação: EX: 1 2 N");
                coordX = scan.nextInt();
                coordY = scan.nextInt();
                direcao = Character.toUpperCase(scan.next().charAt(0));

                Utils.print("Insira o ID da sonda");
                ID = scan.nextInt();

                marte.adicionaSonda(coordX, coordY, direcao, ID);
            }

            else if(comando.equalsIgnoreCase("mover")){
                Utils.print("Insira os ID da sonda que deseja mover:");
                ID = scan.nextInt();

                Utils.print("Insira os comandos de movimentação da sonda:");
                movimentacao = scan.next();

                for(char mov : movimentacao.toCharArray()){
                    marte.processaInput(mov, ID);
                }
            }

            else if(comando.equalsIgnoreCase("mostrar")){
                marte.mostrePlaneta();
            }

            else if(comando.equalsIgnoreCase("sair")){
                Utils.print("Câmbio Desligo");
                conexaoSondas = false;
            }
            
        }   
        scan.close();
    }
}
