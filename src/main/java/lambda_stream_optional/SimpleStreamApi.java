package lambda_stream_optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

public class SimpleStreamApi {
    List<Double> numbers = new ArrayList<>(Arrays.asList(13.11, 16.66, 7.0, 33.43, 9.999999));

    // metoda wypisujaca liczby jedna pod druga
    public void getAllNumbers() {
        numbers.stream().forEach(number -> System.out.println(number));
    }

    // metoda wypisujaca wszystkie liczby oddzielone ';'
    public void getAllNumbersCSV(String separator) {
        System.out.println(numbers.stream().
                map(number -> String.valueOf(number))
                .collect(Collectors.joining(separator)));

    }

    // metoda wypisująca liczby zaokrąglone do 2 miejsc po przecinku
    public void getAllRoundedNumbers(int precision) {
        try {
            numbers.stream().forEach(number -> System.out.printf("%." + precision + "f\t", number));
        } catch (UnknownFormatConversionException e) {
            System.out.println("Błędny zakres precyzji przy zaokraglaniu");
        }
    }
// metoda zwracajaca sume wszystkich liczb w liscie

    public double getSumOfAllNumbers() {
        return numbers.stream().mapToDouble(value -> Double.valueOf(value)).sum();
    }

    public double getMin() {
        if (numbers.stream().mapToDouble(value -> Double.valueOf(value)).min().isPresent()) {
            return numbers.stream()
                    .mapToDouble(value -> Double.valueOf(value))
                    .min()
                    .getAsDouble();
        } else {
            System.out.println("Lista jest pusta");
            return 0.0;
        }

    }

    public double getMax() {
        if (numbers.stream().mapToDouble(value -> Double.valueOf(value)).max().isPresent()) {
            return numbers.stream().
                    mapToDouble(value -> Double.valueOf(value))
                    .max()
                    .getAsDouble();
        } else {
            System.out.println("Lista jest pusta");
            return 0.0;
        }
    }

    // srenia
    public double getAvg() {
        if (numbers.stream().mapToDouble(value -> Double.valueOf(value)).average().isPresent()) {
            return numbers.stream().mapToDouble(value -> Double.valueOf(value)).average().getAsDouble();
        } else {
            System.out.println("Lista pusta");
            return 0.0;
        }
    }

    // metoda wypisujaca wieksze liczby niz argument
    public void getNumbersGreatherThan(double treshold) {
        numbers.stream().filter(number -> number > treshold).forEach(number -> System.out.print(number + " "));
    }

    public static void main(String[] args) {
        SimpleStreamApi ssa = new SimpleStreamApi();
        ssa.getAllNumbers();
        ssa.getAllNumbersCSV(" --;-- ");
        ssa.getAllRoundedNumbers(2);
        System.out.println("\nSuma liczb :" + ssa.getSumOfAllNumbers());
        System.out.println(" MIN : " + ssa.getMin());
        System.out.println(" MAX : " + ssa.getMax());
        System.out.println(" AVG :" + ssa.getAvg());
        ssa.getNumbersGreatherThan(8.0);
    }

}
