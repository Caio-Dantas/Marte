import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class TorreControle {

    private ArrayList<Sonda> sondas = new ArrayList<Sonda>();

    /*
    Função: adicionaSonda
    Adiciona uma nova sonda em um plano, além de adicionar no array da torre de controle.
    */
    public boolean adicionaSonda(Sonda novaSonda, Plano plano){
        Printer printer = new Printer();
        for(Sonda sonda : this.sondas){
            if(sonda.getId() == novaSonda.getId()){
                printer.print("Já existe uma sonda com esse código.");
                return false;
            }
        }
        if(!plano.posicionaSonda(novaSonda)){
            printer.print("Já existe uma sonda nessa posição.");
            return false;
        }
        this.sondas.add(novaSonda);
        return true;
    }

    /*
    Função: moveSonda
    Aplica o deslocamento e o processamento caso ocorra uma colisão entre sondas.
    */
    public void moveSonda(Sonda sonda, Plano plano){

        Optional<Posicao> overridedePos;

        //Retira a sonda da posição anterior
        plano.removeSonda(sonda);

        //Gera a nova posição da sonda
        sonda.deslocaSonda();

        //Aplica as verificações em cima da nova posição
        overridedePos = plano.getOverridePosicao(sonda.getPosicao());
        if(overridedePos.isPresent()){
            sonda.alteraPosicao(overridedePos.get());
        }

        if(!plano.posicionaSonda(sonda)){
            processaColisao(sonda, plano);
        }

    }

    /*
    Função: processaColisao
    Dada um sonda em posição de colisão, processa a resolução da colisão.
    */
    public void processaColisao(Sonda sonda, Plano plano){
        Printer printer = new Printer();
        Random rand = new Random();
        boolean conseguiuEscapar = rand.nextBoolean();
        Optional<Sonda> sondaPerdida;


        printer.print("ERRO");
        printer.print("COLISÃO IMINENTE!");
        printer.print("ABORTAR MISSÂO");    
            
        if(conseguiuEscapar){
            printer.print("O PILOTO DESVIOU E SAIU ILESO");
            sonda.deslocaSonda();
            plano.posicionaSonda(sonda);
        }
        else{
            printer.print("PERDEMOS A COMUNICAÇÃO DA SONDA " + sonda.getId()+ " E DA SONDA " + plano.getIdByPosition(sonda.getPosicao()));
            sondaPerdida = getSondaById(plano.getIdByPosition(sonda.getPosicao()));
            if(sondaPerdida.isPresent()){
                sondaPerdida.get().desativaSonda();
            }
            sonda.desativaSonda();
            plano.removeSonda(sonda);
        }
    }

    /*
    Função: getSondaById
    Dado um ID retorna a sonda com o respectivo ID, caso exista.
    */
    public Optional<Sonda> getSondaById(int id){
        for(Sonda sonda : this.sondas){
            if(sonda.getId() == id){
                return Optional.of(sonda);
            }
        }
        return Optional.empty();

    }

    /*
    Função: mostraSondas
    Imprime no Console o status de todas Sondas colocadas no planeta, caso a sonda não esteja desaparecida, imprime as coordenadas e a direção.
    */
    public void mostraSondas(){
        Printer printer = new Printer();
        for(Sonda sonda : this.sondas){
            if( sonda.isAtiva() ){
                printer.print("Sonda : " + sonda.getId() + " com coordenadas " + sonda.getPosX() + " " + sonda.getPosY() + " " + sonda.getDirecao().toString() );
            }
            else{
                printer.print("Sonda : " + sonda.getId() + " desativada.");
            }
        }
    }
    
}
