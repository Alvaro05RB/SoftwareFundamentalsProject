package State;

import Display.Usuario;
import Logic.PartidaActual;
import Logic.Puntuacion;
import Logic.UserController;
import es.upm.etsisi.fis.model.IPuntuacion;
import es.upm.etsisi.fis.model.IMovimiento;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Persistance {
    private static Persistance instance;

    private Persistance(){}

    public static Persistance getInstance(){
        if(instance==null){
            instance = new Persistance();
        }
        return instance;
    }


    public HashMap<String, Usuario> readUserList(String fichero) {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        File file = new File(fichero);
        try {
            if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                fw.close();
                cargarAdministradores();
                System.out.println("Archivo creado: " + file);
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] datos = line.split(";");

                if (datos.length == 3) {
                    String nombre = datos[0];
                    String id = datos[1];
                    boolean admin = Boolean.parseBoolean(datos[2]);

                    Usuario usuario = new Usuario(nombre, id, admin);
                    usuarios.put(id, usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public List<IPuntuacion> readPuntuacionesUsuario(String id) {
        List<IPuntuacion> result = new ArrayList<>();
        String fichero = "partidas.csv";
        File file = new File(fichero);
        try {
            if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                fw.close();
                System.out.println("Archivo creado: " + file);
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine())!=null) {
                String[] datosGenerales = line.split("/");
                String[] datos = datosGenerales[0].split(";");
                if(datos[0].equals(id)) {
                    IPuntuacion puntuacion = new Puntuacion();
                    puntuacion.setPuntuacion(Long.valueOf(datos[2]));
                    result.add(puntuacion);
                }
                if(datos[3].equals(id)){
                    IPuntuacion puntuacion = new Puntuacion();
                    puntuacion.setPuntuacion(Long.valueOf(datos[5]));
                    result.add(puntuacion);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void guardarUsuarios(String fichero){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichero))) {
            HashMap<String,Usuario> usuarios = UserController.getInstance().getUserList();
            for (Usuario usuario : usuarios.values()) {
                if(usuario.getId()!=null){
                String line = usuario.getNombre() + ";" + usuario.getId() +  ";" + usuario.isAdministrador();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarPartida(String ficheroPartidas){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ficheroPartidas))) {
            PartidaActual partida = PartidaActual.getInstance();
            if(partida.getJugador1() != null) {
                if(!partida.getJugador1().getPuntuaciones().isEmpty() && !partida.getJugador1().getPuntuaciones().isEmpty()) {
                    List<IMovimiento> movimientos1 =partida.getJugador1().getMovimientos();
                    List<IMovimiento> movimientos2 =partida.getJugador2().getMovimientos();
                    StringBuilder line = new StringBuilder(partida.getJugador1().getData()+";"+partida.getJugador2().getData()+"/");
                    for (IMovimiento m : movimientos1) {
                        line.append(m);
                    }
                    line.append("/");
                    for (IMovimiento m : movimientos2) {
                        line.append(m);
                    }
                    bufferedWriter.write(line.toString());
                    bufferedWriter.newLine();
                }
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void cargarAdministradores(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("usuarios.csv"))) {
            String line = "Admin1;f0102b83ff891945a75c51795664024b;true";
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            line = "Admin2;a324ad6ce4ed9a3b54eebfa1f96e9b5c;true";
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
