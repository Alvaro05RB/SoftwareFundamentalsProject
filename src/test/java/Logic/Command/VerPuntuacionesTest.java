package Logic.Command;

import Display.Usuario;
import Logic.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerPuntuacionesTest {

    @Test
    void testCorrectExecution(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            Usuario mockU = mock(Usuario.class);
            UserController mockUC = mock(UserController.class);

            when(mockUC.getLoggedUser()).thenReturn(mockU);
            mockStatic.when(UserController::getInstance).thenReturn(mockUC);

            int result = verPuntuaciones.ejecutar("verPuntuaciones");
            assertEquals(1, result);
            verify(mockU).verPuntuaciones();
        }
    }

    @Test
    void testWrongCommand(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        int result = verPuntuaciones.ejecutar("wrongcommand");
        assertEquals(0, result);
    }

    @Test
    void testEmptyParams(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        int result = verPuntuaciones.ejecutar("");
        assertEquals(0, result);
    }

    @Test
    void testNullLoggedUser(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            UserController mockUC = mock(UserController.class);

            when(mockUC.getLoggedUser()).thenReturn(null);
            mockStatic.when(UserController::getInstance).thenReturn(mockUC);

            int result = verPuntuaciones.ejecutar("verPuntuaciones");
            assertEquals(0, result);
        }
    }

    @Test
    void testGetName(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        assertEquals("verPuntuaciones", verPuntuaciones.getName());
    }

    @Test
    void testGetAskParams(){
        VerPuntuaciones verPuntuaciones = new VerPuntuaciones();
        assertEquals("",verPuntuaciones.getAskedParams());
    }
}