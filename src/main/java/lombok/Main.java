package lombok;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        // sprawdzenie konstruktora
        User userNoArgs = new User();
        User userAllArgs = new User(1, "aaa@wp.pl", "test", LocalDateTime.now(), true, true);
// test toString

        System.out.println(userNoArgs);
        System.out.println(userAllArgs);
    }
}
