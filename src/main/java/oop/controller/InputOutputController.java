package oop.controller;

import oop.model.User;
import oop.model.enums.Gender;
import oop.model.enums.Role;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class InputOutputController {
    /*
     * globalna sciezka bezposrednia do pliku
     */
    private String usersFilePath = "C:\\tarr5_java_adv\\src\\main\\resources\\file\\users.csv";


    public void saveUsersToFile() {
        try {
            PrintWriter pw = new PrintWriter(new File(usersFilePath));
            for (User user : UserControllerTemplate.users) {
                pw.println(String.format(
                        "%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        user.getUserId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(),
                        user.getPhone(), user.getGender(), user.getRoles(), user.getRegistrationDateTime(), user.isStatus(),
                        user.isRemoved()
                ));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readUsersFromFile() {
        try {
            Scanner scanner = new Scanner(new File(usersFilePath));
            while (scanner.hasNextLine()) {
                String[] userLine = scanner.nextLine().split(";");
                String[] rolesName = userLine[7].replace("[","").replace("]","").split(", ");
                Set <Role> roles = new HashSet<>();
                for (String role : rolesName){
                    roles.add(Role.valueOf(role));
                }
                // mapowanie obiektu na typu data
                LocalDateTime registrationDateTime = LocalDateTime.of(
                        Integer.valueOf(userLine[8].substring(0,4)),
                        Integer.valueOf(userLine[8].substring(5,7)),
                        Integer.valueOf(userLine[8].substring(8,10)),
                        Integer.valueOf(userLine[8].substring(11,13)),
                        Integer.valueOf(userLine[8].substring(14,16)),
                        Integer.valueOf(userLine[8].substring(17,19))
                        );
                UserControllerTemplate.users.add(
                        new User(Integer.valueOf(userLine[0]),
                                userLine[1], userLine[2], userLine[3],
                                userLine[4], userLine[5],
                        userLine[6].equals("MAN") ? Gender.MAN : Gender.WOMAN,
                        roles,
                        registrationDateTime,
                        Boolean.valueOf(userLine[9]),
                        Boolean.valueOf(userLine[10])));

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
