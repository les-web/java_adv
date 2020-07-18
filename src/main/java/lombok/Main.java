package lombok;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        // sprawdzenie konstruktora
        User userNoArgs = new User();
        User userAllArgs = new User(1, "aaa@wp.pl", "test", LocalDateTime.now(), true, true);


        System.out.println(userNoArgs);
        System.out.println(userAllArgs);
        userNoArgs.setPassword("ala");
        userAllArgs.setPassword("janek");
        userAllArgs.setStatus(false);
        userAllArgs.setRegistrationDateTime(LocalDateTime.of(1999, 10, 10, 10, 10, 10));
        System.out.println(userNoArgs);
        System.out.println(userAllArgs);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy'r.' '('HH:mm')'");
        System.out.println("Data rejestracji " + dtf.format(userAllArgs.getRegistrationDateTime()));

        System.out.println("Taski");
        Task task = new Task();
        Task task1 = new Task("nauka javy", "programowanie obiektowe", false, userAllArgs);
        System.out.println(task);
        System.out.println(task1);


    }
}
