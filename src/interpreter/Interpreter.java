package interpreter;

import common.ast.Prog;
import evaluator.Evaluator;
import parser.Parser;
import parser.ScannerException;
import parser.Tokenizer;
import typechecker.TypeChecker;

import java.io.*;

/**
 * Interpreter class
 */
public class Interpreter {

    private static final String prompt = ">: ";
    private static BufferedReader inputReader;
    private static BufferedWriter outputWriter;

    public static void main(String[] args) {
        switch (args.length) {
            case 0:
                interactive();
                break;
            case 1:
                fromFile(args[0]);
                break;
            case 2:
                if (!args[0].equals("-o")) {
                    throw new IllegalArgumentException("Wrong argument(s) given");
                }
                toFile(args[1]);
                break;
            case 3:
                if (!args[0].equals("-o")) {
                    throw new IllegalArgumentException("Wrong arguments(s) given");
                }
                fromFileToFile(args[2], args[1]);
                break;
            default:
                throw new IllegalArgumentException("Wrong argument(s) given");
        }
    }

    private static void interactive() {
        System.out.print(prompt);
        try (Tokenizer tokenizer = new Tokenizer(new InputStreamReader(System.in))) {
            Parser parser = new Parser(tokenizer);
            Prog program = parser.parseProgram();
            System.out.println("Program correctly parsed");
            System.out.println(program);
            program.accept(new TypeChecker());
            System.out.println("Program statically correct");
            program.accept(new Evaluator());
            System.out.println("Program dinamically correct");
        } catch (ScannerException e) {
            String skipped = e.getSkipped();
            if (skipped != null) {
                System.err.println(e.getMessage() + e.getSkipped());
            } else {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void _interactive() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        try {
            String input = inputReader.readLine();
            System.out.println(input);
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
    }

    private static void fromFile(String inputFileName) {
        try (Tokenizer tokenizer = new Tokenizer(new FileReader(inputFileName))) {
            Parser parser = new Parser(tokenizer);
            Prog program = parser.parseProgram();
            System.out.println("Program correctly parsed");
            System.out.println(program);
            program.accept(new TypeChecker());
            System.out.println("Program statically correct");
            program.accept(new Evaluator());
            System.out.println("Program dinamically correct");
        } catch (ScannerException e) {
            String skipped = e.getSkipped();
            if (skipped != null) {
                System.err.println(e.getMessage() + e.getSkipped());
            } else {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void _fromFile(String inputFileName) {
        try {
            inputReader = new BufferedReader(new FileReader(inputFileName));
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
        try {
            String line;
            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
    }

    private static void toFile(String outputFileName) {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        try {
            outputWriter = new BufferedWriter(new FileWriter(outputFileName));
            outputWriter.write(inputReader.readLine());
            outputWriter.newLine();
            outputWriter.flush();
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
    }

    private static void fromFileToFile(String inputFileName, String outputFileName) {
        try {
            inputReader = new BufferedReader(new FileReader(inputFileName));
            outputWriter = new BufferedWriter(new FileWriter(outputFileName));
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
        try {
            String line;
            while ((line = inputReader.readLine()) != null) {
                outputWriter.write(line);
                outputWriter.newLine();
                outputWriter.flush();
            }
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        }
    }
}
