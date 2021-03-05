import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;

public class Tests {

    @Test
    public void testaMudancaDirecao(){
        assertEquals(Direcao.E, Direcao.N.right());
        assertEquals(Direcao.S, Direcao.E.right());
        assertEquals(Direcao.W, Direcao.S.right());
        assertEquals(Direcao.N, Direcao.W.right());

        assertEquals(Direcao.W, Direcao.N.left());
        assertEquals(Direcao.S, Direcao.W.left());
        assertEquals(Direcao.E, Direcao.S.left());
        assertEquals(Direcao.N, Direcao.E.left());
    }

    @Test
    public void testaNovaPosicao(){
        Posicao posTest;
        
        posTest = new Posicao(0, 0).novaPosicao(Direcao.N);
        assertArrayEquals(new int[] {0, 1}, new int[] {posTest.getX(), posTest.getY()});

        posTest = new Posicao(0, 0).novaPosicao(Direcao.S);
        assertArrayEquals(new int[] {0, -1}, new int[] {posTest.getX(), posTest.getY()});
        
        posTest = new Posicao(5, 5).novaPosicao(Direcao.E);
        assertArrayEquals(new int[] {6, 5}, new int[] {posTest.getX(), posTest.getY()});

        posTest = new Posicao(0, 3).novaPosicao(Direcao.W);
        assertArrayEquals(new int[] {-1, 3}, new int[] {posTest.getX(), posTest.getY()});

        posTest = new Posicao(10, 0).novaPosicao(Direcao.W);
        assertArrayEquals(new int[] {9, 0}, new int[] {posTest.getX(), posTest.getY()});
        
    }

    @Test
    public void testaTradutorDirecao(){
        TraduzInput tradutor = new TraduzInput();
        assertEquals(Direcao.N, tradutor.novaDirecao('N').get());
        assertEquals(Direcao.E, tradutor.novaDirecao('E').get());
        assertEquals(Direcao.S, tradutor.novaDirecao('S').get());
        assertEquals(Direcao.W, tradutor.novaDirecao('W').get());

        assertEquals(Optional.empty(), tradutor.novaDirecao('A'));
        assertEquals(Optional.empty(), tradutor.novaDirecao('L'));
        assertEquals(Optional.empty(), tradutor.novaDirecao('O'));
        assertEquals(Optional.empty(), tradutor.novaDirecao('Z'));
    }
}
