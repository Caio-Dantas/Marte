import java.util.Optional;

public class TraduzInput{

    /*
    Função: novaDirecao
    Retorna um objeto da classe Direção caso o char da direção passado seja válido.
    */
    public Optional<Direcao> novaDirecao(char charDirecao){
        switch(charDirecao){
            case 'N':
                return Optional.of(Direcao.N);
            case 'E':
                return Optional.of(Direcao.E);
            case 'S':
                return Optional.of(Direcao.S);
            case 'W':
                return Optional.of(Direcao.W);
            default:
                return Optional.empty();
        }
    }

    /*
    Função: novoComando
    Retorna um objeto da classe Comando caso o char do comando passado seja válido.
    */
    public Optional<Comandos> novoComando(char charComando){
        switch(charComando){
            case 'L':
                return Optional.of(Comandos.L);
            case 'R':
                return Optional.of(Comandos.R);
            case 'M':
                return Optional.of(Comandos.M);
            default:
                return Optional.empty();
        }
    }
}