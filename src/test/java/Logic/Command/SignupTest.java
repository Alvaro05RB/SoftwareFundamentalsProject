package Logic.Command;

import Logic.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignupTest {

    @Test
    void testCorrectExecution() {
        Signup signup = new Signup();

        try (MockedStatic<UserController> mockStatic = mockStatic(UserController.class)) {
            UserController mockController = mock(UserController.class);
            mockStatic.when(UserController::getInstance).thenReturn(mockController);

            int result = signup.ejecutar("signup goodUser");
            assertEquals(1, result);
            verify(mockController).SignUp("goodUser");
        }
    }

    @Test
    void testBannedUser() {
        Signup signup = new Signup();
        int result = signup.ejecutar("signup git");
        assertEquals(0, result);
    }

    @Test
    void testInvalidCharacter() {
        Signup signup = new Signup();
        int result = signup.ejecutar("signup user!");
        assertEquals(0, result);
    }

    @Test
    void testWrongCommand() {
        Signup signup = new Signup();
        int result = signup.ejecutar("wrongcommand user");
        assertEquals(0, result);
    }

    @Test
    void testInsufficientArguments() {
        Signup signup = new Signup();
        int result = signup.ejecutar("signup");
        assertEquals(0, result);
    }

    @Test
    void testInvalidUsername() {
        Signup signup = new Signup();

        int result = signup.ejecutar("signup abc");
        assertEquals(0, result);

        result = signup.ejecutar("signup abcdefghijk");
        assertEquals(0, result);
    }

    @Test
    void testGetName(){
        Signup signup = new Signup();
        assertEquals("signup", signup.getName());
    }

    @Test
    void testGetAskParams(){
        Signup signup = new Signup();
        assertEquals(" nombre",signup.getAskedParams());
    }
}