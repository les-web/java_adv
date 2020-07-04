package colections;

import javax.sound.midi.Soundbank;
import java.util.*;

public class JavaCollectionsExample {
    private List<String> names = new ArrayList<>(Arrays.asList("jan", "Ala", "Ola", "Ela", "Ala"));

    public void arrayOperations() {
        try {
            // utworzenie tablicy 10 elementow
            String[] names = new String[10];
            int[] numbers = {1, 2, 3, 4};
            System.out.println("tablica nazw");
            Arrays.stream(names)                            //zamiana tablicy na stream
                    .forEach(s -> System.out.println(s));    // dla kazdego elementu wykonaj predykt (CTRL + Space)
 /*
for (String s : names) {
System.out.println(s)
}
 */
            System.out.println("\nTablica liczb");
            Arrays.stream(numbers).forEach(n -> System.out.print(n + ", "));
            System.out.println("Modyfikacja wartości na indeksie");
            names[5] = "Anna";
            names[names.length - 1] = "Adam";
            Arrays.stream(names).forEach(s -> System.out.print(s + ", "));
            //           names[100] = "Jan";
        } catch (
                ArrayIndexOutOfBoundsException e) {
            System.out.println("Błąd indeksowania");
        }
        System.out.println("\nPoza try - catch");
    }

    /*
    działania na listach
     */
    public void listOperations() {
        // utworzenie pustej listy
//        List<String> names = new ArrayList<>();
        // utworzenie listy z wartościami
        List<Double> params = new ArrayList<>(Arrays.asList(1.2, 1.44, 1.11, 4.6));
        System.out.println(names);
        System.out.println(params);
    }

    public boolean findElem(List<String> names, String name) {
        return names.contains(name);
    }

    public void removeElement(String name) {
//        List<String> names = new ArrayList<>(Arrays.asList("jan", "Ala", "Ola", "Ela", "Ala"));
        System.out.println(names);
        names.remove(name);
        System.out.println(names);
    }

    public void removeAllTheSameElements(String name) {
//        List<String> names = new ArrayList<>(Arrays.asList("jan", "Ala", "Ola", "Ela", "Ala"));
        System.out.println(names);
        names.removeAll(Arrays.asList(name));
        System.out.println(names);
    }

    // zmień pierwszy i ostatni element na Adam w liście names
    public void updateFirstAndLastElement(String name) {
        names.set(0, name);
        names.set(names.size() - 1, name);
    }

    // wypisz wszystkie elementy na parzystych indeksach
    public void getAllElementsWithOddIndex() {
        for (int i = 0; i <= names.size() - 1; i = i + 2) {
            System.out.println(names.get(i));
        }
    }

    /*******************
     ZBIORY
     *******************/
    private Set<String> roles1 = new HashSet<>(Arrays.asList("ADMIN", "USER"));
    private Set<String> roles2 = new HashSet<>(Arrays.asList("USER", "VIEWER", "MODERATOR"));

    // jakie mamy wszystkie role w serwisie
    public Set<String> getAllRoles() {
        // utworzenie pustego podręcznego zbioru z czasem życia w tej metodzie
        //      Set<String> allRoles = new HashSet<>();
        Set<String> allRoles = new TreeSet<>(); // wg alfabetu
        // dodanie ról ze zbiorów
        allRoles.addAll(roles1);
        allRoles.addAll(roles2);
        return allRoles;
    }

    // jakie role powtarzaja sie w zbiorach
    // część wspólna
    public Set<String> getNonUniqueRoles() {
        Set<String> nonUniqueRoles = new TreeSet<>(); // wg alfabetu
        nonUniqueRoles.addAll(roles1);
        nonUniqueRoles.retainAll(roles2);
        return nonUniqueRoles;
    }

    // wszystkie elementy unikatowe
    public Set<String> getUniqueRoles() {
        Set<String> uniqueRoles = getAllRoles();
        uniqueRoles.removeAll(getNonUniqueRoles());

        return uniqueRoles;
    }

    /**************
     * mapy
     * ********************/

    private Map<Integer, String> decimalToRoman = new HashMap<>();
    private Map<String, Integer> romanToDecimal = new HashMap<>();

    public void generateValuesIntoMap() {
        decimalToRoman.put(0, "0");
        decimalToRoman.put(1, "I");
        decimalToRoman.put(2, "II");
        decimalToRoman.put(3, "III");
        decimalToRoman.put(4, "IV");
        decimalToRoman.put(5, "V");
        System.out.println(decimalToRoman);

    }

    public void generateMapRomanToDecimal() {

        for (Integer key : decimalToRoman.keySet()) {
            romanToDecimal.put(decimalToRoman.get(key), key);
        }
        System.out.println(romanToDecimal);

    }

    /****************************************
     kolejki
     *************************************/
    private Deque<String> messages = new ArrayDeque<>();

    public void sendMessage(String message) {
        messages.addLast(message);
        System.out.println(messages);
    }

    public void receiveMessage() {
        try {
            while (!messages.isEmpty()) {

                System.out.println("Odebrana wiadomość " + messages.removeFirst());
                System.out.println("Pozostałe wiadomości " + messages);
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        JavaCollectionsExample ex = new JavaCollectionsExample();
        //      ex.arrayOperations();
//        ex.listOperations();
        //       System.out.println("Wynik " + ex.findElem(new ArrayList<>(Arrays.asList("Jan", "Ala", "Ola", "Ela")), "Ala"));
        //      ex.removeElement("Ala");
        //     ex.removeAllTheSameElements("Ala");
        //       ex.updateFirstAndLastElement("Adam");
//        ex.getAllElementsWithOddIndex();
//        System.out.println("All roles : " + ex.getAllRoles());
//        System.out.println("Non unique roles : " + ex.getNonUniqueRoles());
//        System.out.println("Unique roles : " + ex.getUniqueRoles());
        //       ex.generateValuesIntoMap();
//        ex.generateMapRomanToDecimal();
//        System.out.println("5 dziesiętn to " + ex.decimalToRoman.get(5));
//        System.out.println("II rzymska to " + ex.romanToDecimal.get("II"));

        ex.sendMessage("A");
        ex.sendMessage("B");
        ex.sendMessage("C");
        ex.sendMessage("CcCcCcCCCCCCcccCcCcccc");
        ex.receiveMessage();
    }
}
