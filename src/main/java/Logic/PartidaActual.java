package Logic;


public class PartidaActual {
    private static PartidaActual instance;
    private IJugadorPlus jugador1;
    private IJugadorPlus jugador2;

    private PartidaActual(){
        jugador1 = null;
        jugador2 = null;
    }
    public static PartidaActual getInstance(){
        if(instance == null){
            instance = new PartidaActual();
        }
        return instance;
    }

    public void setJugador1(IJugadorPlus jugador1) {
        this.jugador1 = jugador1;
    }
    public void setJugador2(IJugadorPlus jugador2){
        this.jugador2 = jugador2;
    }
    public IJugadorPlus getJugador1(){
        return jugador1;
    }
    public IJugadorPlus getJugador2(){
        return jugador2;
    }
}
