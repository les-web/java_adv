package exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadWriteExceptionHandler {
    private static String path =
            Paths.get("").toAbsolutePath().toString() +
                    "\\src\\main\\java\\exceptions\\data.txt";
    private static File file = new File(path);

    public static void appendDataToFile() {
        FileWriter fileWriter = null;
        while (true) {
            try {
                fileWriter = new FileWriter(file, true);
                Scanner scanner = new Scanner(System.in);

                System.out.println("Wprowadz liczbe (Q - wyjście) ");
                String data = scanner.nextLine();
                if (data.toUpperCase().equals("Q")) {
                    //              fileWriter.close();
                    break;
                }
                String s;
                double number = Double.valueOf(data);
                if (number == 0) {
                    throw new ArithmeticException();

                }
                if (number == 13) {
                    throw new MyException();
                }


                fileWriter.append(String.valueOf(number) + "\n");
//
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Blad pliku");
            } catch (InputMismatchException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Blad typu danych");
            } catch (ArithmeticException e) {
                System.out.println("Wprowadzona liczba nie moze byc 0");
            } catch (MyException e){
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readDataFromFile() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        appendDataToFile();
        readDataFromFile();
    }

}
