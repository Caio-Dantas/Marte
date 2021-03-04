import java.util.Optional;


public class Planeta {

    private TorreControle torre;
    private Plano plano;

    /*
    Função: adicionaSonda
    Adiciona uma nova sonda no planeta que se encontra na posição passada em coordenadas cartesianas
    */
    public void adicionaSonda(Posicao posicao, Direcao direcao, int codigo){
        
        Sonda sonda;

        sonda = new Sonda(codigo, posicao, direcao);
        torre.adicionaSonda(sonda, plano);

    }

    /*
    Função: processaInput
    Admmistra o input do usuário e decide o próximo passo conforme a direção e o código da sonda passado.
    */
    public void processaInput(Comandos comando, int codigo){
        
        Optional<Sonda> optSonda;
        Sonda sonda;

        optSonda = torre.getSondaById(codigo);

        // Não processa os movimentos após a sonda ser desativada
        if(!optSonda.isPresent() || !optSonda.get().isAtiva()){
            return;
        }
        sonda = optSonda.get();
        comando.aplicaComando(torre, sonda, plano);
    }

    /*
    Função: mostraPlaneta
    Imprime no console o estado atual do planeta com as sondas posicionadas e informando o código de cada sonda.
    */
    public void mostraPlaneta(){
        plano.exibeMapa();
    }

    /*
    Função: mostraSondas
    Imprime no Console o status de todas Sondas colocadas no planeta, caso a sonda não esteja desaparecida, imprime as coordenadas e a direção.
    */
    public void mostraSondas(){
        torre.mostraSondas();
    }

    /*
    Construtor: Planeta
    Construtor da classe Planeta, exige a quantidade de linhas e colunas do planeta.
    */
    public Planeta(int linhas, int colunas){
        plano = new Plano(linhas, colunas);
        torre = new TorreControle();
    }

}
