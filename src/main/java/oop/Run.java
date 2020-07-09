package oop;

import oop.controller.InputOutputController;
import oop.controller.UserController;
import oop.controller.UserControllerTemplate;
import oop.model.User;
import oop.model.enums.Gender;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Run extends InputOutputController {
    public static void main(String[] args) {
   /*
        User u1 = new User(
                "Adam",
                "Kowalski",
                "aa@wp.pl",
                "akak",
                "123456789",
                Gender.MAN);

        User u2 = new User(
                "Jan",
                "Wiśniak",
                "cdp.pl",
                "janek",
                "123456789",
                Gender.MAN);
        User u3 = new User(
                "Anna",
                "Wiśniewska",
                "aa@wp.pl",
                "lelek",
                "123456789",
                Gender.WOMAN);
*/
        //      System.out.println(u1);
        //      System.out.println(u2);
        //      System.out.println(u3);

        UserController uc = new UserController();
        Scanner scanner = new Scanner(System.in);

        //     List<User> users = UserControllerTemplate.users;
        Run run = new Run();
        run.readUsersFromFile();

        while (true) {
            System.out.println(" Co chcesz zrobić \n1. Rejestracja \n2.Lista użytkowników \n3 Logowanie \n4 Zmiana hasła \nQ Wyjście");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("1")) {
                System.out.println("podaj imię : ");
                String name = scanner.nextLine();
                System.out.println("podaj nazwisako : ");
                String lastName = scanner.nextLine();

                System.out.println("podaj email : ");
                String email = scanner.nextLine();
                /*
                * regex for email validation
                * source: https://emailregex.com/
                * General Email Regex (RFC 5322 Official Standard)
                (?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])
                *
                 */
                String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                // String emailPattern = "^\\S{1,}[@]\\S{1,}$";
                // wersja pierwotna regex z zajęć
                if (!Pattern.matches(emailPattern, email)) {
                    System.out.println("Błędny adres email");
                    continue;
                }

                System.out.println("podaj hasło : ");
                String password = scanner.nextLine();

                System.out.println("podaj płeć M/K : ");

                String genderInput = scanner.nextLine().toUpperCase();
                String genderPattern = "^[MK]{1}$";
                if (!Pattern.matches(genderPattern, genderInput)) {
                    System.out.println("Wprowadzono złą płeć");
                    continue;
                }
                Gender gender = genderInput.equals("M") ? Gender.MAN : Gender.WOMAN;
                System.out.println("podaj numer telefonu (000-000-000) ");
                String phone = scanner.nextLine();
                // walidacj na podstawie regex
                String phonePattern = "^[0-9]{3}(-[0-9]{3}){2}$";
                if (!Pattern.matches(phonePattern, phone)) {
                    System.out.println("Błędny numer telefonu");
                    continue;

                }
                uc.registerUser(new User(name, lastName, email, password, phone, gender));
                System.out.println("Dodano użytrkownika z mailem " + email);

            } else if (choice.equals("2")) {
                uc.findAllUsers().forEach(user -> System.out.println(user));
            } else if (choice.equals("3")) {
                System.out.println("Podaj email");
                String email = scanner.nextLine();
                System.out.println("Podaj hasło");
                String password = scanner.nextLine();
                uc.loginUser(email, password);


            } else if (choice.equals("4")) {
                try {
                    System.out.println("Podaj id: ");
                    int userId = Integer.valueOf(scanner.nextLine());
                    System.out.println("Podaj nowe hasło");
                    String newPassword = scanner.nextLine();
                    uc.updateUserPassword(userId, newPassword);


                } catch (InputMismatchException e) {
                    System.out.println("Błędny id");
                }
            } else if (choice.equals("Q")) {
                System.out.println("Wyjście ");
                run.saveUsersToFile();
                break;
            } else {
                System.out.println("Błędny wybór");
            }
        }
        ;

        //      for (User user : UserControllerTemplate.users) {
        //          System.out.println(user);


        //      }

    }
}
