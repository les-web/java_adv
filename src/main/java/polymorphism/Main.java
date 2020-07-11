package polymorphism;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        //                     os1.getOpenSpaceNo(), os1.getOpenSpaceName(), os1.getOpenSpaceCapacity());
        Company company = new Company("ABC", 10);
        OpenSpace os1 = new OpenSpace("QWERTY", 10, 1, "Londyn", 100);
//        System.out.println(company);
        OpenSpace os2 = new OpenSpace(os1.getCompanyName(), os1.calculateFreeRooms(), 2, "Rzym", 10);
        OpenSpace os3 = new OpenSpace(os1.getCompanyName(), os2.calculateFreeRooms(), 3, "Paryż", 25);
        System.out.println(os1);

        System.out.println(os2);
        System.out.println(os3);
    }
};




