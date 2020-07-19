package anonymousClasses;

// anonimowa klasa
// nie posiada nazwy
// mozna utworzyc tylko jedna instancje

public class Main {
    public static void main(String[] args) {
        Object object = new EventController() {
            @Override
            public void printEvent() {
                System.out.println(" -- nowe wydarzenie -- ");
                EventController.formattedEventDate();
            }
        };
        ((EventController) object).printEvent();
    }
}
