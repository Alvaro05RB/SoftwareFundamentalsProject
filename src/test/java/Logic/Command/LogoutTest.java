package Logic.Command;

import Display.Usuario;
import Logic.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogoutTest {
    @Test
    void testCorrectExecution() {
        Logout logout = new Logout();

        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            Usuario mockU = mock(Usuario.class);
            UserController mockUC = mock(UserController.class);

            when(mockUC.getLoggedUser()).thenReturn(mockU);
            mockStatic.when(UserController::getInstance).thenReturn(mockUC);

            int result = logout.ejecutar("logout");
            assertEquals(1, result);
            verify(mockUC).logout();
        }
    }

    @Test
    void testNullLoggedUser(){
        Logout logout = new Logout();
        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            UserController mockUC = mock(UserController.class);

            when(mockUC.getLoggedUser()).thenReturn(null);
            mockStatic.when(UserController::getInstance).thenReturn(mockUC);

            int result = logout.ejecutar("logout");
            assertEquals(1, result);
            verify((mockUC), never()).logout();
        }
    }

    @Test
    void testWrongCommand() {
        Logout logout = new Logout();
        int result = logout.ejecutar("signup");
        assertEquals(0, result);
    }

    @Test
    void testEmptyParams() {
        Logout logout = new Logout();
        int result = logout.ejecutar("");
        assertEquals(0, result);
    }

    @Test
    void testGetName(){
        Logout logout = new Logout();
        assertEquals("logout", logout.getName());
    }

    @Test
    void testGetAskParams(){
        Logout logout = new Logout();
        assertEquals("",logout.getAskedParams());
    }
}