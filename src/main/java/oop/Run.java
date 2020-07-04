package oop;

import oop.model.User;
import oop.model.enums.Gender;

public class Run {
    public static void main(String[] args) {
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

        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
    }
}
