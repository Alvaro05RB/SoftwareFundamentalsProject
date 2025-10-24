package Logic;

import es.upm.etsisi.fis.model.IJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PartidaActualTest {

    private IJugadorPlus jugador1;
    private IJugadorPlus jugador2;

    @BeforeEach
    void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        jugador1 = mock(IJugadorPlus.class);
        jugador2 = mock(IJugadorPlus.class);

        Field instance = PartidaActual.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    void getInstance() {
        PartidaActual instance1 = PartidaActual.getInstance();
        PartidaActual instance2 = PartidaActual.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void setAndGetJugador1() {
        PartidaActual partidaActual = PartidaActual.getInstance();
        partidaActual.setJugador1(jugador1);
        assertEquals(jugador1, partidaActual.getJugador1());
    }

    @Test
    void setAndGetJugador2() {
        PartidaActual partidaActual = PartidaActual.getInstance();
        partidaActual.setJugador2(jugador2);
        assertEquals(jugador2, partidaActual.getJugador2());
    }
}