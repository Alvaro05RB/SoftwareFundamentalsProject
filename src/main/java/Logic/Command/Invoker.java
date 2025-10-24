package Logic.Command;

import Display.Lector;
import java.util.List;

public class Invoker {
    private List<Command> commands;
    public Invoker(List<Command> commands){
        this.commands = commands;
    }
    public void executeCommand(){
        Lector lector = Lector.getInstance();
        do{
            int executedCommands = 0;
            readCommands();
            String input = lector.readLine("Ingrese el comando");
            for(Command c : commands){
                executedCommands += c.ejecutar(input);
            }
            if(executedCommands == 0){
                System.out.println("Ningún comando ejecutado");
            }
        }while (true);
    }

    private void readCommands(){
        for(int i = 0; i<commands.size();i++){
            System.out.println(i+1+"." + commands.get(i).getName() + commands.get(i).getAskedParams());
        }
    }
}
