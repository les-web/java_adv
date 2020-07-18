package exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyException extends Exception{
    public MyException (){
        System.out.println("Jestem w konstruktorze : MyException");
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("Moja obsluga wyjatku");
    }
    public String  getExceptionDateTime (){
        return DateTimeFormatter.ofPattern("dd.MM.yyyy'r.' '('HH:mm:ss'").format(LocalDateTime.now());
    }
}
