import java.util.Optional;
import java.util.Scanner;

public class Principal{

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); 
        Printer printer = new Printer();
        TraduzInput tradutor = new TraduzInput();

        Optional<Comandos> optComando;
        Optional<Direcao> direcao;
        Posicao pos;
        Planeta marte;

        int linhas, colunas, coordX, coordY, ID;
        char charDirecao;
        String comando = "", movimentacao = "";
        boolean conexaoSondas = true;

        printer.print("Insira as coordenadas do canto superior direito:");

        while(!scan.hasNextInt()){
            scan.nextLine();
            printer.print("Insira um número válido.");
        }
        linhas = scan.nextInt();

        while(!scan.hasNextInt()){
            scan.nextLine();
            printer.print("Insira um número válido.");
        }
        colunas = scan.nextInt();

        marte = new Planeta(colunas + 1, linhas + 1);

        while(conexaoSondas){

            System.out.println("Insira o comando que deseja executar: (adicionar, mover, mapa, relatório, sair)");
            comando = scan.next();
            
            if(comando.equalsIgnoreCase("adicionar")){
                printer.print("Insira as coordenadas da sonda e a orientação: EX: 1 2 N");
                coordX = scan.nextInt();
                coordY = scan.nextInt();
                charDirecao = Character.toUpperCase(scan.next().charAt(0));

                direcao = tradutor.novaDirecao(charDirecao);
                if(!direcao.isPresent()){
                    printer.print("Direção Inválida");
                }
                else{
    
                    printer.print("Insira o ID da sonda");
                    ID = scan.nextInt();
                    pos = new Posicao(coordX, coordY);
    
                    marte.adicionaSonda(pos, direcao.get(), ID);

                }
            }

            else if(comando.equalsIgnoreCase("mover")){
                printer.print("Insira os ID da sonda que deseja mover:");
                ID = scan.nextInt();

                printer.print("Insira os comandos de movimentação da sonda:");
                movimentacao = scan.next();

                for(char mov : movimentacao.toCharArray()){
                    optComando = tradutor.novoComando(mov);
                    if(optComando.isPresent()){
                        marte.processaInput(optComando.get(), ID);
                    }
                    else{
                        printer.print("Insira uma direção válida.");
                    }
                }
            }

            else if(comando.equalsIgnoreCase("mapa")){
                marte.mostraPlaneta();
            }

            else if(comando.equalsIgnoreCase("relatório") || comando.equalsIgnoreCase("relatorio")){
                marte.mostraSondas();
            }

            else if(comando.equalsIgnoreCase("sair")){
                printer.print("Câmbio Desligo");
                conexaoSondas = false;
            }
            
        }   
        scan.close();
    }
}
