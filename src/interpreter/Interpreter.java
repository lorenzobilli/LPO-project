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
            program.accept(new TypeChecker());
            program.accept(new Evaluator());
            System.out.flush();
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

    private static void fromFile(String inputFileName) {
        try (Tokenizer tokenizer = new Tokenizer(new FileReader(inputFileName))) {
            Parser parser = new Parser(tokenizer);
            Prog program = parser.parseProgram();
            program.accept(new TypeChecker());
            program.accept(new Evaluator());
            System.out.flush();
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

    private static void toFile(String outputFileName) {
        System.out.print(prompt);
        try {
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFileName))));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        try (Tokenizer tokenizer = new Tokenizer(new InputStreamReader(System.in))) {
            Parser parser = new Parser(tokenizer);
            Prog program = parser.parseProgram();
            program.accept(new TypeChecker());
            program.accept(new Evaluator());
            System.out.flush();
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

    private static void fromFileToFile(String inputFileName, String outputFileName) {
        try {
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFileName))));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        try (Tokenizer tokenizer = new Tokenizer(new FileReader(inputFileName))) {
            Parser parser = new Parser(tokenizer);
            Prog program = parser.parseProgram();
            program.accept(new TypeChecker());
            program.accept(new Evaluator());
            System.out.flush();
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
}
