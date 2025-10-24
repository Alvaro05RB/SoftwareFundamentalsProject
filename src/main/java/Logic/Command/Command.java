package Logic.Command;

public interface Command {
    public int ejecutar(String params);
    public String getName();
    public String getAskedParams();
}
