package Logic;

import es.upm.etsisi.fis.model.IMovimiento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MovimientoTest {

    @Test
    void testCloneMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setPartidaId(987L);
        movimiento.setFila(1);
        movimiento.setColumna(2);
        movimiento.setTime(5);

        IMovimiento clon = movimiento.cloneMovimiento();

        assertEquals(movimiento.getPartidaId(), ((Movimiento) clon).getPartidaId());
        assertEquals(movimiento.getFila(), ((Movimiento) clon).getFila());
        assertEquals(movimiento.getColumna(), ((Movimiento) clon).getColumna());
        assertEquals(movimiento.getTime(), ((Movimiento) clon).getTime());
    }

    @Test
    void setAndGetPartidaId() {
        Movimiento movimiento = new Movimiento();
        movimiento.setPartidaId(99L);
        assertEquals(99, movimiento.getPartidaId());
    }

    @Test
    void setAndGetFila() {
        Movimiento movimiento = new Movimiento();
        movimiento.setFila(3);
        assertEquals(3, movimiento.getFila());
    }

    @Test
    void setAndGetColumna() {
        Movimiento movimiento = new Movimiento();
        movimiento.setColumna(4);
        assertEquals(4, movimiento.getColumna());
    }

    @Test
    void setAndGetTime() {
        Movimiento movimiento = new Movimiento();
        movimiento.setTime(10);
        assertEquals(10, movimiento.getTime());
    }
}