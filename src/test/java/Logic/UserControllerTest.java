package Logic;

import Display.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import servidor.ExternalLDAP;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class UserControllerTest {

    @BeforeEach
    void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instance = UserController.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getInstance() {
        UserController instance1 = UserController.getInstance();
        UserController instance2 = UserController.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void setAndGetLoggedUser() {
        UserController uc = UserController.getInstance();
        Usuario user = new Usuario("name", "id", false);
        uc.setLoggedUser(user);
        assertEquals(user, uc.getLoggedUser());
    }

    @Test
    void logout() {
        UserController uc = UserController.getInstance();
        uc.setLoggedUser(new Usuario("test", "logout", false));
        uc.logout();
        assertNull(uc.getLoggedUser());
    }

    @Test
    void loginWhenAlreadyLoggedIn() {
        UserController uc = UserController.getInstance();
        Usuario alreadyLogged = new Usuario("already", "logged", false);
        uc.setLoggedUser(alreadyLogged);
        uc.login();
        assertEquals("logged", uc.getLoggedUser().getId());
    }

    @Test
    void loginWhenUserDoesNotExistInList() {
        UserController uc = UserController.getInstance();
        uc.getUserList().clear();

        try (MockedStatic<ExternalLDAP> ldapMock = mockStatic(ExternalLDAP.class)) {
            ldapMock.when(ExternalLDAP::LoginLDAP).thenReturn("999");

            uc.setLoggedUser(null);
            uc.login();

            assertNull(uc.getLoggedUser());
        }
    }

    @Test
    void loginCorrectExecution() {
        UserController uc = UserController.getInstance();
        uc.getUserList().clear();

        Usuario mockUser = new Usuario("UserExistInList", "001", false);
        uc.getUserList().put("001", mockUser);

        try (MockedStatic<ExternalLDAP> ldapMock = mockStatic(ExternalLDAP.class)) {
            ldapMock.when(ExternalLDAP::LoginLDAP).thenReturn("001");

            uc.setLoggedUser(null);
            uc.login();

            assertEquals(mockUser, uc.getLoggedUser());
        }
    }


    @Test
    void signUpCorrectExecution() {
        UserController uc = UserController.getInstance();
        uc.getUserList().clear();

        try (MockedStatic<ExternalLDAP> mockedLDAP = mockStatic(ExternalLDAP.class)) {
            mockedLDAP.when(ExternalLDAP::LoginLDAP).thenReturn("002");

            uc.SignUp("newUser");

            Usuario result = uc.getLoggedUser();
            assertNotNull(result);
            assertEquals("newUser", result.getNombre());
            assertEquals("002", result.getId());
            assertTrue(uc.getUserList().containsKey("002"));
        }
    }

    @Test
    void signUpWhenUserExistInList() {
        UserController uc = UserController.getInstance();
        uc.getUserList().clear();

        Usuario oldUser = new Usuario("oldUser", "003", false);
        uc.getUserList().put("003", oldUser);

        try (MockedStatic<ExternalLDAP> mockedLDAP = mockStatic(ExternalLDAP.class)) {
            mockedLDAP.when(ExternalLDAP::LoginLDAP).thenReturn("003");

            uc.SignUp("nuevo");

            assertEquals(1, uc.getUserList().size());
            assertEquals(oldUser, uc.getUserList().get("003"));
        }
    }

    @Test
    void getUserList() {
        UserController uc = UserController.getInstance();

        HashMap<String, Usuario> ref1 = uc.getUserList();
        HashMap<String, Usuario> ref2 = uc.getUserList();

        assertSame(ref1, ref2);

        Usuario u = new Usuario("UserList", "004", false);
        ref1.put("004", u);

        assertTrue(ref2.containsKey("004"));
        assertEquals("UserList", ref2.get("004").getNombre());
    }
}