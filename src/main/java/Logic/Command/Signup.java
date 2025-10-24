package Logic.Command;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import Logic.UserController;

public class Signup implements Command{
    private String name = "signup";
    @Override
    public int ejecutar(String params) {
        String[] commandText = params.split(" ");
        int result = 0;
        if(commandText.length == 2 && commandText[0].equals(getName())) {
            if(usuarioCorrecto("list.json",commandText[1])){
                result = 1;
                UserController userController = UserController.getInstance();
                userController.SignUp(commandText[1]);
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
        return " nombre";
    }

    private boolean estaEnListaNegra(String fichero, String usuario){
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode jsonArray = mapper.readTree(new File(fichero));

            for (JsonNode node : jsonArray) {
                if (node.asText().equals(usuario)) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return false;
        }
    }

    private boolean usuarioCorrecto(String fichero, String usuario){
        return !estaEnListaNegra(fichero,usuario) &&
                usuario.length()>3 &&
                usuario.length() <10 &&
                usuario.matches("^[a-zA-Z0-9]+$");
    }

}
