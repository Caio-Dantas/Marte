import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class TorreControle {

    private ArrayList<Sonda> sondas = new ArrayList<Sonda>();


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

    public Optional<Sonda> getSondaById(int id){
        for(Sonda sonda : this.sondas){
            if(sonda.getId() == id){
                return Optional.of(sonda);
            }
        }
        return Optional.empty();

    }


    public void moveSonda(Sonda sonda, Plano plano){


        Optional<Sonda> sondaPerdida;
        Optional<Posicao> overridedePos;
        Printer printer = new Printer();

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
            printer.print("ERRO");
            printer.print("COLISÃO IMINENTE!");
            printer.print("ABORTAR MISSÂO");

            if(conseguiuEscapar()){
                printer.print("O PILOTO DESVIOU E SAIU ILESO");

                //geraNovaPosicao(sonda, delta);
                sonda.deslocaSonda();

                //planeta[sonda.getPosX()][sonda.getPosY()] = sonda.getId();
                plano.posicionaSonda(sonda);

            }
            else{
                printer.print("PERDEMOS A COMUNICAÇÃO DA SONDA " + sonda.getId()+ " E DA SONDA " + plano.getIdByPosition(sonda.getPosicao()));
                
                sondaPerdida = getSondaById(plano.getIdByPosition(sonda.getPosicao()));
                if(sondaPerdida.isPresent()){
                    sondaPerdida.get().desativaSonda();
                }
                sonda.desativaSonda();

                //planeta[sonda.getPosX()][sonda.getPosY()] = 0;
                plano.removeSonda(sonda);
            }
        }

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

    public boolean conseguiuEscapar(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    
}
