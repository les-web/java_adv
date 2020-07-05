package oop.controller;

import oop.model.User;
import oop.model.enums.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class InputOutputController {
    /*
     * globalna sciezka bezposrednia do pliku
     */
    private String usersFilePath = "C:\\tarr5_java_adv\\src\\main\\resources\\file\\users.csv";

    public void saveUsersToFile() {

    }

    public void readUsersFromFile() {
        try {
            Scanner scanner = new Scanner(new File(usersFilePath));
            while (scanner.hasNextLine()) {
                String[] userLine = scanner.nextLine().split(";");
                UserControllerTemplate.users.add(new User(userLine[1], userLine[2], userLine[3], userLine[4], userLine[5],
                        userLine[6].equals("MAN") ? Gender.MAN : Gender.WOMAN));

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
