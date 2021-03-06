package oop.controller;

import oop.model.User;
import oop.model.enums.Gender;
import oop.model.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/********************************************
 * interfejs czyli szblon wymagań dla klasy go implementującej
 *
 ********************************************/
public interface UserControllerTemplate {
    // metody abstrakcyjne
    // nie possiadające ciała
    // sygnatura metody
    // [typ zwracanej metody / void] [nazwa metody] (argumenty / bez) ;

    List<User> users = new ArrayList<>(
            Arrays.asList(
 //                           Gender.WOMAN)
            )
    );

    // rejestracja
    void registerUser(User user);

    // logowanie
    boolean loginUser(String email, String password);

    // wyszukiwanie użytkownika
    User findUserById(int userId);

    // zmiana hasła
    void updateUserPassword(int userId, String newPassword);

    // usunięcie użykownika
    void deleteUserById(int userId);

    // add / drop role
    void updateRole(int userId, Set<Role> newRoles);

    // wypisanie wszystkich użytkowników
    List<User> findAllUsers();

    // wypisanie użytkowników posortowanych po emailu
    List<User> findAllUsersOrderByEmail(boolean asc);

}
