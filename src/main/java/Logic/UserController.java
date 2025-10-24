package Logic;

import Display.Usuario;
import State.Persistance;
import servidor.ExternalLDAP;

import java.util.HashMap;

public class UserController {
    private static UserController instance;
    private Usuario loggedUser;
    private HashMap<String, Usuario> userList;

    private UserController(){
        loggedUser = null;
        userList = Persistance.getInstance().readUserList("usuarios.csv");
    }

    public static UserController getInstance(){
        if (instance == null){
            instance = new UserController();
        }
        return instance;
    }
    public void setLoggedUser(Usuario user){
        loggedUser = user;
    }

    public Usuario getLoggedUser() {
        return loggedUser;
    }

    public void logout(){
        loggedUser = null;
    }

    public void login(){
        if(getLoggedUser()==null){
            String usuario = ExternalLDAP.LoginLDAP();
            if(userList.containsKey(usuario)){
                System.out.println("Login correcto");
                loggedUser = userList.get(usuario);
            }
            else System.out.println("Error en login");
        }
        else System.out.println("Ya hay un usuario con la sesión iniciada, cierre sesión antes");
    }

    public void SignUp(String nombre){
        String usuarioId = ExternalLDAP.LoginLDAP();
        if(!userList.containsKey(usuarioId)){
            userList.put(usuarioId,new Usuario(nombre,usuarioId,false));
            setLoggedUser(userList.get(usuarioId));
            System.out.println("Sign up correcto, login hecho");
        }else{
            System.out.println("Error en sign up");
        }
    }

    public HashMap<String,Usuario>getUserList(){
        return userList;
    }

}
