package Display;
import java.util.Scanner;

public class Lector {
    private static  Lector instance;
    private final Scanner scanner = new Scanner(System.in);
    private Lector(){}
    public static Lector getInstance(){
        if (instance == null){
            instance = new Lector();
        }
        return instance;
    }

    public String readLine(String texto){
        System.out.print(texto+": ");
        return scanner.nextLine();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int readInt(String texto, int limit){
        boolean validInput = false;
        int result = 0;
        do {
            System.out.println(texto + ": ");
            String input = scanner.nextLine();
            if (input.matches("\\d+")){
                result = Integer.parseInt(input);
                if(result>limit){
                    System.out.println("Porfavor ingrese un entero válido (menor que "+limit+")");
                }else {
                    validInput = true;
                }
            }else{
                System.out.println("Porfavor ingrese un entero positivo");
            }
        }while (!validInput);
        return result;
    }
    public boolean readConfirmation(String texto){
        boolean result = false;
        boolean valid = false;
        do{
            System.out.println(texto+"(s/n): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("s")) {
                result = true;
                valid = true;
            } else if (input.equalsIgnoreCase("n")) {
                valid = true;
            }else{
                System.out.println("Porfavor ingrese el carácter los caracteres s ó n");
            }
        }while(!valid);
        return result;
    }
    public String readOption(String texto, String[] opciones){
        String result = "";
        boolean valid = false;
        do{
            System.out.print("\n"+texto + "(");
            for(String s : opciones){
                System.out.print(s+",");
            }
            System.out.print("): ");
            result = scanner.nextLine();
            int i = 0;
            while (i<opciones.length && !valid){
                valid = opciones[i].equals(result);
                i++;
            }
            if(!valid){
                System.out.println("Seleccione una opción válida (recuerde las mayusclas)");
            }
        }while (!valid);
        return result;
    }

}
