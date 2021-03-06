package pizzaExercises;

import lambda_stream_optional.InMemoryData;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class PizzaController {

    // podaje cenę pizzy na podstawie składowych
    public int calculatePizzaPrice(Pizza pizza) {
        return pizza.getIngredients()
                .stream()
                .mapToInt(p -> p.getPrice())
                .sum();
    }

    // metoda sprawdzająca tylko ostre pizze
    public List<Pizza> getAllSpicy() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream()
                        .anyMatch(ingredient -> ingredient.isSpicy()))
                .collect(Collectors.toList());
    }

    // znajdz najtańszą ostrą

    public Pizza findCheapestSpicy() {
        Optional<Pizza> pizzaOpt = Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().anyMatch(ingredient -> ingredient.isSpicy()))
                .sorted(Comparator.comparing(pizza -> calculatePizzaPrice(pizza)))
                .findFirst();
        if (pizzaOpt.isPresent()) {
            return pizzaOpt.get();
        } else {
            System.out.println("Brak danych do pobrania");
            return null;
        }
    }

    // znajdz najdrozszą wegetarianska
    public Pizza findMostExpensiveVegetarian() {
        Optional<Pizza> pizzaOpt = Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().noneMatch(ingredient -> ingredient.isMeat()))
                .sorted(Comparator.comparing(this::calculatePizzaPrice).reversed())
                .findFirst();
        if (pizzaOpt.isPresent()) {
            return pizzaOpt.get();
        } else {
            System.out.println("Brak danych do pobrania");
            return null;
        }
    }

    // wypisanie cennika
    public void getAllPizzasWithPrices() {
        Arrays.stream(Pizza.values())
                .forEach(pizza -> System.out.println(
                        pizza.getName()
                                + " - " + calculatePizzaPrice(pizza)
                                + " PLN")
                );
    }

    // zwraca ilość skłądników mięsnych
    public long countMeatIngredients(Pizza pizza) {
        return pizza.getIngredients().stream().filter(Ingredient::isMeat).count();
    }

    // zwraca miesne posortowane po ilości składników miesnych
    public List<Pizza> iLikeMeat() {
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().anyMatch(Ingredient::isMeat))
                .sorted(Comparator.comparing(this::countMeatIngredients).reversed())
                .collect(Collectors.toList());
    }

    // metoda grupująca pizze po cenie

    public Map<Integer, List<Pizza>> groupByPrice() {
        return Arrays.stream(Pizza.values()).collect(Collectors.groupingBy(pizza -> calculatePizzaPrice(pizza)));

    }

    // grupowanie po poziomie ostrości
    public Map<Boolean, List<Pizza>> groupBySpicy() {
        return Arrays.stream(Pizza.values()).collect(Collectors.groupingBy(pizza -> pizza.getIngredients()
                .stream().anyMatch(ingredient -> ingredient.isSpicy())));

    }

    // po ilosci skladnikow
    public Map<Integer, List<Pizza>> gropuByIngredientsSize() {
        return Arrays.stream(Pizza.values())
                .collect(Collectors.groupingBy(pizza -> pizza.getIngredients()
                        .size()));

    }

    // zwraca string String formatedMenu() -metoda zwracająca string w postaci
    // nazwa_pizzy: składnik1, składnik2, składnik3 -cena,
    // kolejne pizzęoddzielone znakiem nowej linii.
    // pizza menu : nazwa (składniki) - cena zł
    public String formattedMenu() {
        Random random = new Random();
        int randomIndex = random.nextInt(Pizza.values().length);
        Pizza pizzaOfTheDay = Pizza.values()[randomIndex];
        return Arrays.stream(Pizza.values())
                .map(pizza -> String.format("%15s (%-90s) %5s %4s - %5.2f zł %1s",
                        pizza.getName(),
                        pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining(", ")),
                        pizza.getIngredients().stream().anyMatch(ingredient -> ingredient.isSpicy()) ?
                                "ostra" : "",
                        pizza.getIngredients().stream().noneMatch(Ingredient::isMeat) ? "wege" : "",
                        pizza.equals(pizzaOfTheDay) ?
                                Double.valueOf(calculatePizzaPrice(pizza)) * 0.5 :
                                Double.valueOf(calculatePizzaPrice(pizza))
                        ,
                        pizza.equals(pizzaOfTheDay) ? "*" : ""
                        )
                )
                // posortowane wg nazwy
                .sorted(Comparator.comparing(pizza -> pizza.trim().toString()))

                // posortowane wg ceny

                //                .sorted(Comparator.comparing(pizza -> calculatePizzaPrice(pizza))

                //         .sorted(Comparator.comparing(this::calculatePizzaPrice).reversed())


                .collect(Collectors.joining("\n"));
    }

    public String formattedMenuOrderByName() {
        Random random = new Random();
        int randomIndex = random.nextInt(Pizza.values().length);
        Pizza pizzaOfTheDay = Pizza.values()[randomIndex];
        return Arrays.stream(Pizza.values())
                .sorted(Comparator.comparing(Pizza::getName))
                .map(pizza -> String.format("%15s (%-90s) %5s %4s - %5.2f zł %1s",
                        pizza.getName(),
                        pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining(", ")),
                        pizza.getIngredients().stream().anyMatch(ingredient -> ingredient.isSpicy()) ?
                                "ostra" : "",
                        pizza.getIngredients().stream().noneMatch(Ingredient::isMeat) ? "wege" : "",
                        pizza.equals(pizzaOfTheDay) ?
                                Double.valueOf(calculatePizzaPrice(pizza)) * 0.5 :
                                Double.valueOf(calculatePizzaPrice(pizza))
                        ,
                        pizza.equals(pizzaOfTheDay) ? "*" : ""
                        )
                )
                .collect(Collectors.joining("\n"));
    }

    public double calculatePizzaPriceWithDiscount(Pizza pizza, Pizza pizzaOfTheDay) {
        return pizza.equals(pizzaOfTheDay) ? calculatePizzaPrice(pizza) * 0.5 : calculatePizzaPrice(pizza);
    }


    public String formattedMenuOrderByPrice() {
        Random random = new Random();
        int randomIndex = random.nextInt(Pizza.values().length);
        Pizza pizzaOfTheDay = Pizza.values()[randomIndex];

        return Arrays.stream(Pizza.values())

                .sorted(Comparator.comparing(pizza -> calculatePizzaPriceWithDiscount(pizza, pizzaOfTheDay)))
                .map(pizza -> String.format("%15s (%-90s) %5s %4s - %5.2f zł %1s",
                        pizza.getName(),
                        pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining(", ")),
                        pizza.getIngredients().stream().anyMatch(ingredient -> ingredient.isSpicy()) ?
                                "ostra" : "",
                        pizza.getIngredients().stream().noneMatch(Ingredient::isMeat) ? "wege" : "",
                        pizza.equals(pizzaOfTheDay) ?
                                Double.valueOf(calculatePizzaPrice(pizza)) * 0.5 :
                                Double.valueOf(calculatePizzaPrice(pizza))
                        ,
                        pizza.equals(pizzaOfTheDay) ? "*" : ""
                        )
                )
                .collect(Collectors.joining("\n"));
    }

    public int calculateIngredientsSize(Pizza pizza) {
        return pizza.getIngredients().size();
    }

    public String formattedMenuOrderByNumberOfIngedients() {
        Random random = new Random();
        int randomIndex = random.nextInt(Pizza.values().length);
        Pizza pizzaOfTheDay = Pizza.values()[randomIndex];

        return Arrays.stream(Pizza.values())

                .sorted(Comparator.comparing(this::calculateIngredientsSize).reversed())
                .map(pizza -> String.format("%15s (%-90s) %5s %4s - %5.2f zł %1s",
                        pizza.getName(),
                        pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining(", ")),
                        pizza.getIngredients().stream().anyMatch(ingredient -> ingredient.isSpicy()) ?
                                "ostra" : "",
                        pizza.getIngredients().stream().noneMatch(Ingredient::isMeat) ? "wege" : "",
                        pizza.equals(pizzaOfTheDay) ?
                                Double.valueOf(calculatePizzaPrice(pizza)) * 0.5 :
                                Double.valueOf(calculatePizzaPrice(pizza))
                        ,
                        pizza.equals(pizzaOfTheDay) ? "*" : ""
                        )
                )
                .collect(Collectors.joining("\n"));
    }


    public static void main(String[] args) {
        PizzaController pc = new PizzaController();
        System.out.println("Cena " + pc.calculatePizzaPrice(Pizza.CARUSO));
        System.out.println("Ostre ");
        pc.getAllSpicy().forEach(System.out::println);
        System.out.println("Najtansza ostra : ");
        System.out.println(pc.findCheapestSpicy());
        pc.getAllPizzasWithPrices();
        System.out.println("Najdroższa wegetariańska");
        System.out.println(pc.findMostExpensiveVegetarian());
        System.out.println("Posortowane po ilosci skladnikow - tylko miesne");
        pc.iLikeMeat().forEach(pizza -> System.out.println(pizza + " " + pc.countMeatIngredients(pizza)));
        System.out.println("Grupowanie po cenach");
        System.out.println();
        pc.groupByPrice().forEach((price, pizzas) -> System.out.println(price + " - " + pizzas));
        System.out.println("Grupowanie po ostrości true/false");

        pc.groupBySpicy().forEach((price, pizzas) -> System.out.println(price + " - " + pizzas));
        System.out.println("Grupowanie po ilości składników");
        pc.gropuByIngredientsSize().forEach((price, pizzas) -> System.out.println(price + " - " + pizzas));
        System.out.println("--------------- Pełne menu wraz z pizzą dnia -------------------");
        System.out.println(pc.formattedMenu());
        System.out.println("--------------- Pełne menu posortowane wraz z pizzą dnia -------------------");
        System.out.println(pc.formattedMenuOrderByName());
        System.out.println("--------------- Pełne menu posortowane po cenie  -------------------");
        System.out.println(pc.formattedMenuOrderByPrice());
        System.out.println("--------------- Pełne menu posortowane po ilości składników  -------------------");
        System.out.println(pc.formattedMenuOrderByNumberOfIngedients());
    }
}
