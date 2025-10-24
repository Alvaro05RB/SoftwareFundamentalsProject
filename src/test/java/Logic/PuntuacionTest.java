package Logic;

import es.upm.etsisi.fis.model.IPuntuacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntuacionTest {

    @Test
    void testClonePuntuacion() {
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setPuntuacion(200);
        puntuacion.setPartidaId(99L);

        IPuntuacion clon = puntuacion.clonePuntuacion();

        assertEquals(puntuacion.getPuntos(), clon.getPuntos());
        assertEquals(puntuacion.getId(), ((Puntuacion) clon).getId());
    }

    @Test
    void setAndGetPuntuacion() {
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setPuntuacion(100);
        assertEquals(100, puntuacion.getPuntos());
    }

    @Test
    void setAndGetPartidaId() {
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setPartidaId(999L);
        assertEquals(999L,puntuacion.getId());
    }

}