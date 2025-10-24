package Logic.Command;

import State.Persistance;

public class Exit implements Command{
    private String name = "exit";
    @Override
    public int ejecutar(String params) {
        String[] segemnted = params.split(";");
        int result = 0;
        if(segemnted.length>0 && segemnted[0].equals(name)) {
            result = 1;
            Persistance persistance = Persistance.getInstance();
            persistance.guardarUsuarios("usuarios.csv");
            System.exit(0);
        }
        return result;
    }
    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getAskedParams() {
        return "";
    }
}
