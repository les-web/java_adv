package pizzaExercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PizzaController {

    // podaje cenę pizzy na podstawie składowych
    public int calculatePizzaPrice(Pizza pizza) {
        return pizza.getIngredients().stream()
                .mapToInt(p -> p.getPrice())
                .sum();
    }

    // metoda sprawdzająca tylko ostre pizze
    public List<Pizza> getAllSpicy() {
        return Arrays.stream(Pizza.values()).filter(pizza -> pizza.getIngredients().stream()
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
    }
}
