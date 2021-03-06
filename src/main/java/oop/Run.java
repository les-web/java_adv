package oop;

import oop.controller.InputOutputController;
import oop.controller.UserController;
import oop.controller.UserControllerTemplate;
import oop.model.User;
import oop.model.enums.Gender;
import oop.model.enums.Role;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.regex.Pattern;

public class Run extends InputOutputController {
    public static void main(String[] args) {

        UserController uc = new UserController();
        Scanner scanner = new Scanner(System.in);

        //     List<User> users = UserControllerTemplate.users;
        Run run = new Run();
        run.readUsersFromFile();

        while (true) {

            System.out.println(
                    " Co chcesz zrobić :" +
                            "\n--------------- " +
                            "\n1.Rejestracja " +
                            "\n2.Lista użytkowników " +
                            "\n3.Logowanie " +
                            "\n4.Zmiana hasła " +
                            "\n5.Usuń użytkownika " +
                            "\n6.Lista użytkowników posortowanych wg email " +
                            "\n7.Przypisz role do użytkownika " +

                            "\nQ Wyjście" +
                            "\n-------------- ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "1": {
                    System.out.println("podaj imię : ");
                    String name = scanner.nextLine();
                    System.out.println("podaj nazwisko : ");
                    String lastName = scanner.nextLine();

                    System.out.println("podaj email : ");
                    String email = scanner.nextLine();
                    //
                    //regex for email validation
                    //source: https://emailregex.com/
                    //General Email Regex (RFC 5322 Official Standard)
                    // (?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])

                    String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                    // String emailPattern = "^\\S{1,}[@]\\S{1,}$";
                    // wersja pierwotna regex z zajęć
                    if (!Pattern.matches(emailPattern, email)) {
                        System.out.println("Błędny adres email");
                        break;
                    }

                    System.out.println("podaj hasło : ");
                    String password = scanner.nextLine();

                    System.out.println("podaj płeć M/K : ");
                    String genderInput = scanner.nextLine().toUpperCase();
                    String genderPattern = "^[MK]{1}$";
                    if (!Pattern.matches(genderPattern, genderInput)) {
                        System.out.println("Wprowadzono złą płeć");
                        break;
                    }
                    Gender gender = genderInput.equals("M") ? Gender.MAN : Gender.WOMAN;

                    System.out.println("podaj numer telefonu (000-000-000) ");
                    String phone = scanner.nextLine();
                    // walidacj na podstawie regex
                    String phonePattern = "^[0-9]{3}(-[0-9]{3}){2}$";
                    if (!Pattern.matches(phonePattern, phone)) {
                        System.out.println("Błędny numer telefonu");
                        break;
                    }
                    uc.registerUser(new User(name, lastName, email, password, phone, gender));
                    System.out.println("Dodano użytkownika z mailem " + email);
                    break;
                }

                case "2": {
                    uc.findAllUsers().forEach(user -> System.out.println(user));
                    break;
                }

                case "3": {
                    System.out.println("Podaj email");
                    String email = scanner.nextLine();
                    System.out.println("Podaj hasło");
                    String password = scanner.nextLine();
                    uc.loginUser(email, password);
                    break;
                }

                case "4": {
                    try {
                        System.out.println("Podaj id: ");
                        int userId = Integer.valueOf(scanner.nextLine());
                        System.out.println("Podaj nowe hasło");
                        String newPassword = scanner.nextLine();
                        uc.updateUserPassword(userId, newPassword);
                    } catch (InputMismatchException e) {
                        System.out.println("Błędny id");
                    }
                    break;
                }
                case "5": {
                    try {
                        System.out.println("Podaj id użytkownika do usunięcia: ");
                        int userId = Integer.valueOf(scanner.nextLine());
                        uc.deleteUserById(userId);
                    } catch (InputMismatchException e) {
                        System.out.println("Błędny id");
                    }
                    break;
                }
                case "6": {
                    System.out.println("Czy chcesz posortować rosnąco/malejąco ASC/DESC");
                    boolean asc = true;
                    String decision = scanner.nextLine();
                    if (decision.toUpperCase().equals("DESC")) {
                        asc = false;
                    }
                    uc.findAllUsersOrderByEmail(asc).forEach(user -> System.out.println(user));
                    break;
                }
                case "7": {
                    try {
                        System.out.println("Podaj id użytkownika do zmiany ról: ");
                        int userId = Integer.valueOf(scanner.nextLine());
                        // wybór ról
                        Set<Role> roles = new HashSet<>();
                        if (uc.findUserById(userId) == null) {
                            break;
                        }
                        while (true) {
                            System.out.println("Wybierz role (Q - koniec) : ");
                            Arrays.stream(Role.values()).forEach(role -> System.out.println(role.ordinal() +
                                    ". " + role));
                            String decision = scanner.nextLine();

                            if (decision.equals("0")) {
                                roles.add(Role.ROLE_USER);
                            } else if (decision.equals("1")) {
                                roles.add(Role.ROLE_ADMIN);
                            } else if (decision.equals("2")) {
                                roles.add(Role.ROLE_VIEWER);
                            } else if (decision.toUpperCase().equals("Q")) {
                                System.out.println("Zaktualizowano zbior rol");
                                break;

                            } else {
                                System.out.println("Bledny wobor");
                            }
                        }
                        uc.updateRole(userId, roles);
                    } catch (InputMismatchException e) {
                        System.out.println("Błędny id");
                    }

                }

                default: {
                    if (!choice.equals("Q")) {
                        System.out.println("Zły wybór!");
                        break;
                    }
                }
            } // end case

            if (choice.equals("Q")) {
                System.out.println("Wyjście ");
                run.saveUsersToFile();
                break;
            }
        } // end while
    }
}
/*
wersja z klasy - z ciagiem if then else

        while (true) {
            System.out.println(" Co chcesz zrobić \n1.Rejestracja \n2.Lista użytkowników \n3 Logowanie \n4 Zmiana hasła \nQ Wyjście");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("1")) {
                System.out.println("podaj imię : ");
                String name = scanner.nextLine();
                System.out.println("podaj nazwisko : ");
                String lastName = scanner.nextLine();

                System.out.println("podaj email : ");
                String email = scanner.nextLine();
                //
                //regex for email validation
                //source: https://emailregex.com/
                //General Email Regex (RFC 5322 Official Standard)
                // (?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])

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
                System.out.println("Dodano użytkownika z mailem " + email);

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

    }

}
*/