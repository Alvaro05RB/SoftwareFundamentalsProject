import Display.Lector;
import Logic.Command.*;
import Logic.UserController;
import es.upm.etsisi.fis.controller.ControladorPartida;

import java.util.ArrayList;

public class App {
    public static void main (String[] args){
        //Controladores
        ControladorPartida controladorPartida = ControladorPartida.getInstance(Lector.getInstance().getScanner());
        UserController userController = UserController.getInstance();

        //Comandos
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Exit());
        commands.add(new IniciarPartida());
        commands.add(new Login());
        commands.add(new Logout());
        commands.add(new Signup());
        commands.add(new VerPuntuaciones());

        Invoker invoker = new Invoker(commands);

        invoker.executeCommand();
    }
}
