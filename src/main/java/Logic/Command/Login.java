package Logic.Command;

import Logic.UserController;

public class Login implements Command{
    private String name = "login";
    @Override
    public int ejecutar(String params) {
        String[] segmented = params.split(";");
        int result = 0;
        if (segmented.length>0 && segmented[0].equals(name)) {
            result = 1;
            UserController userController = UserController.getInstance();
            userController.login();
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
