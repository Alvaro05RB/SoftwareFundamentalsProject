package Display;

import Logic.IJugadorPlus;
import Logic.UserController;
import State.Persistance;
import es.upm.etsisi.fis.model.IMovimiento;
import es.upm.etsisi.fis.model.IPuntuacion;
import es.upm.etsisi.fis.model.TBarcoAccionComplementaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Usuario implements IJugadorPlus {

    private String nombre;
    private String id;
    private boolean administrador;

    private List<IPuntuacion> puntuaciones;

    private List<IMovimiento> movimientos;

    public Usuario(String nombre,String id, Boolean administrador){
        this.nombre = nombre;
        this.id = id;
        this.administrador = administrador;
        this.movimientos = new ArrayList<>();
        this.puntuaciones = Persistance.getInstance().readPuntuacionesUsuario(id);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void addMovimiento(IMovimiento movimiento){
        movimientos.add(movimiento);
    }

    public void addPuntuacion(IPuntuacion puntuacion){
        puntuaciones.add(puntuacion);
    }

    @Override
    public List<IMovimiento> getMovimientos() {
        return movimientos;
    }

    @Override
    public List<IPuntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    @Override
    public boolean aceptarAccionComplementaria(TBarcoAccionComplementaria tBarcoAccionComplementaria, int i) {
        boolean result = false;
        if(i>0){
            Lector lector = Lector.getInstance();
            result = lector.readConfirmation("¿Desea utilizar la habilidad del barco "+tBarcoAccionComplementaria+"?");
        }
        return result;
    }

    @Override
    public int[] realizaTurno(char[][] chars) {
        Lector lector = Lector.getInstance();
        int[] ataque = new int[2];
        ataque[0] = lector.readInt("Seleccione la fila",9);
        ataque[1] = lector.readInt("Seleccione la columna",9);
        return ataque;
    }

    public void verPuntuaciones(){
        if(isAdministrador())
            verPuntuacionesAdmin();
        else verPuntuacionesNoAdmin();
    }

    private void verPuntuacionesAdmin(){
        HashMap<String,Usuario> usuarios = UserController.getInstance().getUserList();
        for(Usuario u : usuarios.values()){
            System.out.println(u.nombre);
            System.out.println("[");
            for (IPuntuacion p : u.getPuntuaciones()){
                System.out.println(p.getPuntos());
            }
            System.out.println("]");
        }
    }

    private void verPuntuacionesNoAdmin(){
        System.out.println(nombre);
        System.out.println("[");
        for (IPuntuacion p : getPuntuaciones()){
            System.out.println(p.getPuntos());
        }
        System.out.println("]");
    }
    @Override
    public String getData(){
        return id+";"+nombre+";"+puntuaciones.get(puntuaciones.size()-1).getPuntos();
    }
}
