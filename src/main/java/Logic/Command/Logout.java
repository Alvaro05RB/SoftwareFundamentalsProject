package Logic.Command;

import Logic.UserController;

public class Logout implements Command{
    private String name = "logout";
    @Override
    public int ejecutar(String params) {
        String[] segmented = params.split(";");
        int result = 0;
        if(segmented.length>0 && segmented[0].equals(name)) {
            result = 1;
            UserController userController = UserController.getInstance();
            if(userController.getLoggedUser()!=null) {
                userController.logout();
                System.out.println("Sesión cerrada correctamente.");
            }else{
                System.out.println("No es posible cerrar sesión sin haberla iniciado primero");
            }
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
