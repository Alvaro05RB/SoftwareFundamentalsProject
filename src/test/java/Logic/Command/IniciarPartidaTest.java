package Logic.Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IniciarPartidaTest {

    @Test
    void testGetName(){
        IniciarPartida iniciar = new IniciarPartida();
        assertEquals("iniciarPartida", iniciar.getName());
    }

    @Test
    void testGetAskParams(){
        IniciarPartida iniciar = new IniciarPartida();
        assertEquals("",iniciar.getAskedParams());
    }

}