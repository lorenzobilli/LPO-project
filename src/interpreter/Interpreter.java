package interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interpreter class
 */
public class Interpreter {

    private static BufferedReader inputReader;

    public static void main(String[] args) {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        switch (args.length) {
            case 0:
                interactive();
                break;
            default:
                throw new IllegalArgumentException("Wrong argument(s) given");
        }
    }

    private static void interactive() {
        System.out.print(">: ");
        try {
            String input = inputReader.readLine();
            System.out.println(input);
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
    }
}
