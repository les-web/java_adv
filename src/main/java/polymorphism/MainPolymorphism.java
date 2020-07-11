package polymorphism;

public class MainPolymorphism {
    public static void hasMoreRooms(Company c1, Company c2) {
        if (c1.getRoomsQuantity() > c2.getRoomsQuantity()) {
            System.out.println("Firma " + c1.getCompanyName() + "posiada wiecej pokoi");
        } else {
            System.out.println("Firma " + c2.getCompanyName() + "posiada wiecej pokoi");
        }
        if (c1.getRoomsQuantity() == c2.getRoomsQuantity()) {
            System.out.println("Obie mają taką samą ilość pokoi");
        }


    }

    public static void main(String[] args) {

        Object openSpaceObject = new OpenSpace(
                "AAAA", 3, 1, "XXXXX", 100);

        Company openSpace1 = new OpenSpace(
                "X", 10, 1, "A", 10);
        System.out.println("reprezentant klasy " + openSpace1.getClass().getName());
        System.out.println(openSpaceObject);
        System.out.println(openSpace1);
        hasMoreRooms(openSpace1, (Company) openSpaceObject);  // castowanie - konwersja na typ Company
        hasMoreRooms(new Company("TTT", 100), openSpace1);

    }
}
