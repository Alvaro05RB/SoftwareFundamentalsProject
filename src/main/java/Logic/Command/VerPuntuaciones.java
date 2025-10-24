package Logic.Command;

import Logic.UserController;

public class VerPuntuaciones implements Command{
    private String name = "verPuntuaciones";
    @Override
    public int ejecutar(String params) {
        String[] segmented = params.split(";");
        int result = 0;
        if (segmented.length>0 && segmented[0].equals(name) && UserController.getInstance().getLoggedUser()!=null ) {
            result = 1;
            UserController.getInstance().getLoggedUser().verPuntuaciones();
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