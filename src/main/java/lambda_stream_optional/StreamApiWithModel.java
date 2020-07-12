package lambda_stream_optional;

import oop.model.User;
import oop.model.enums.Gender;
import oop.model.enums.Role;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiWithModel {

    // wypisywanie zawartość listy Users z InMemoryData
    public void getAllUsers() {
        InMemoryData.users.stream().forEach(System.out::println);
    }

    // wypisywanie listy uzytkownikow posortowanych po dacie rejestracji malejaco
    public void getAllUsersOrderByRegistrationDateDesc() {
        InMemoryData.users.stream()
                .sorted(Comparator.comparing(User::getRegistrationDateTime).reversed())
                .forEach(System.out::println);
    }

    // metoda do logowania
    public boolean loginUser(String email, String password) {
        return InMemoryData.users.stream()
                .anyMatch(user -> user.getEmail().equals(email) &&
                        user.getPassword().equals(password)
                        && user.isStatus() && !user.isRemoved());
    }

    // liczba kobiet
    public long countAllWomen() {
        return InMemoryData.users.stream()
                .filter(user -> user.getGender().equals(Gender.WOMAN))
                .count();
    }

    //  metoda zwracajaca liste uzytkownikow o podanej roli
    public List<User> getAllUsersContainsRole(Role role) {
        return InMemoryData.users.stream().filter(user -> user.getRoles()
                .contains(role))
                .collect(Collectors.toList());
    }

    // grupowanie uzytkownikow po zbiorach roli
    public Map<Set<Role>, List<User>> groupUsersByRoleSet() {
        return InMemoryData.users.stream().collect(Collectors.groupingBy(user -> user.getRoles()));

    }

    //
    public Map<Role, List<User>> userRoleMapper() {
        Map<Role, List<User>> userRoleMap = new HashMap<>();

        for (Role role : Role.values()) {
            List<User> groupingUsers = new ArrayList<>();

            for (User user : InMemoryData.users) {
                if (user.getRoles().contains(role)) {
                    groupingUsers.add(user);
                    userRoleMap.put(role, groupingUsers);

                }

            }
        }

        return userRoleMap;
    }

    // grupowanie uzytkownikow po roli
    public Map<Set<Role>, List<User>> groupUsersByRole() {
        return InMemoryData.users.stream()
                .collect(Collectors.groupingBy(user -> user.getRoles()));

    }


    public static void main(String[] args) {
        StreamApiWithModel sapi = new StreamApiWithModel();
        sapi.getAllUsers();
        System.out.println("----------------------------------------");
        sapi.getAllUsersOrderByRegistrationDateDesc();
        System.out.println("----------------------------------------");
        System.out.println("Wynik logowania " + sapi.loginUser("ak@ak.pl", "ak"));
        System.out.println("----------------------------------------");
        System.out.println("Ilość kobiet " + sapi.countAllWomen());
        System.out.println("----------------------------------------");
        sapi.getAllUsersContainsRole(Role.ROLE_ADMIN).forEach(System.out::println);
        System.out.println("----------------------------------------");
        sapi.getAllUsersContainsRole(Role.ROLE_USER).forEach(System.out::println);
        System.out.println("----------------------------------------");
        sapi.groupUsersByRoleSet().forEach((roles, users) -> System.out.printf("%30s | %30s\n", roles, users));
        System.out.println("----------------------------------------");
        sapi.groupUsersByRole().forEach((roles, users) -> System.out.printf("%30s | %30s\n", roles, users));
        System.out.println("----------------------------------------");
        System.out.println(sapi.userRoleMapper().get(Role.ROLE_ADMIN));
        System.out.println("----------------------------------------");
        System.out.println(sapi.userRoleMapper().get(Role.ROLE_USER));


    }
}