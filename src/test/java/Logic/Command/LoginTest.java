package Logic.Command;

import Logic.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginTest {

    @Test
    void testCorrectExecution() {
        Login login = new Login();
        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            UserController mockUC = mock(UserController.class);
            mockStatic.when(UserController::getInstance).thenReturn(mockUC);

            int result = login.ejecutar("login");
            assertEquals(1, result);
            verify(mockUC).login();
        }
    }

    @Test
    void testWrongCommand() {
        Login login = new Login();
        int result = login.ejecutar("signup");
        assertEquals(0, result);
    }

    @Test
    void testEmptyParams() {
        Login login = new Login();
        int result = login.ejecutar("");
        assertEquals(0, result);
    }

    @Test
    void testGetName(){
        Login login = new Login();
        assertEquals("login", login.getName());
    }

    @Test
    void testGetAskParams(){
        Login login = new Login();
        assertEquals("",login.getAskedParams());
    }

}