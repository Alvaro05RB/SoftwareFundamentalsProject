package Logic.Command;

import Display.Lector;
import Display.MaquinaWrapper;
import Logic.*;
import State.Persistance;
import es.upm.etsisi.fis.controller.ControladorPartida;
import es.upm.etsisi.fis.model.FactoriaMaquina;
import es.upm.etsisi.fis.model.Maquina;

public class IniciarPartida implements Command{
    private String name = "iniciarPartida";

    @Override
    public int ejecutar(String params) {
        String[] segmented = params.split(";");
        int result = 0;
        if(segmented.length>0 && segmented[0].equals(name)) {
            result = 1;
            ControladorPartida controladorPartida = ControladorPartida.getInstance();
            UserController userController = UserController.getInstance();
            if(userController.getLoggedUser()!=null) {
                Maquina maquina = FactoriaMaquina.creaMaquina(Lector.getInstance().readOption("Seleccione la dificultad ", new String[]{"FACIL", "NORMAL", "DIFICIL"}));
                IJugadorPlus maquinaPlus = new MaquinaWrapper(maquina);
                PartidaActual partida = PartidaActual.getInstance();
                partida.setJugador1(userController.getLoggedUser());
                partida.setJugador2(maquinaPlus);
                controladorPartida.crearPartida(userController.getLoggedUser(), maquina, new Puntuacion(), new Movimiento());
                Persistance.getInstance().guardarPartida("partidas.csv");
            }else{
                System.out.println("Debe iniciar sesión para poder iniciar la partida");
            }
        }
        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAskedParams() {
        return "";
    }
}
