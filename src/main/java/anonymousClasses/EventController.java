package anonymousClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface EventController {
    public static final String eventName ="Mój event";
    LocalDateTime eventDate =LocalDateTime.of(2020,10,10,11,11);

    public abstract void printEvent ();

    public static void formattedEventDate(){
        System.out.printf(" Data wydarzenia: %s w dniu : %s",
                eventName,
                eventDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:ss")));
    }
}
