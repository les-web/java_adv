package oop.controller;

import oop.model.User;
import oop.model.enums.Role;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/****************************
 *  Klasa controllera -
 *  odpowiedzialna za obsługę i implementację
 *  logiki biznesowej aplikacji
 *
 ******************************/
public class UserController implements UserControllerTemplate {
    private String passwordEncoder(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // operacja szyfrowania zwraca tablicę liczb naturalnych
            byte[] passwordHash = md.digest(password.getBytes());
            // zapisanie tablicy liczb w typie String
            String passwordHashTxt = "";
            for (byte digit : passwordHash) {
                passwordHashTxt += String.format("%x", digit);
            }
            return passwordHashTxt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void registerUser(User user) {
        // kodowanie hasła
        user.setPassword(passwordEncoder(user.getPassword()));
        users.add(user);
        System.out.println("Dodano nowego użytkownika, jego email : " + user.getEmail());
    }

    @Override
    public boolean loginUser(String email, String password) {
        for (User user : users) {
            // porownanie emaila i hashow haseł
            if (user.getEmail().equals(email) && user.getPassword().equals(passwordEncoder(password))) {
                System.out.println("Zalogowano użytkownika " + user.getEmail());
                return true;
            }
        }
        return false;
    }

    // ---------------------------
    @Override
    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                System.out.println("Znaleziono użytkownika " + user);
                return user;

            }
        }
        System.out.println("nie znaleziono użytkownika o id " + userId);
        return null;
    }

    @Override
    public void updateUserPassword(int userId, String newPassword) {
        // pobranie uzytkownika na podstawie hasła
        User user = findUserById(userId);
        if (user != null) {
            // zmiana hasła
            user.setPassword(passwordEncoder(newPassword));
            System.out.println("Zmieniono hasło ");
        } else {
            System.out.println("Nie zmieniono hasła");
        }
    }

    //----------------------------------
    @Override
    public void deleteUserById(int userId) {
        if (findUserById(userId) == null) {
            System.out.println("Nie ma użytkownika o id " + userId);

        } else {
            for (User user : users) {
                if (user.getUserId() == userId) {
                    users.remove(user);
                    System.out.println("Usunieto użytkownika + " + user.getEmail());
                    break;
                }
            }
        }


    }

    // zadanie domowe
    @Override
    public void updateRole(int userId, Set<Role> newRoles) {
        if (findUserById(userId) != null) {
            User user = findUserById(userId);
            user.setRoles(newRoles);
        } else {
            System.out.println("Nie ma uzytkownika o id :" + userId);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public List<User> findAllUsersOrderByEmail(boolean asc) {
        // zamiana List na Stream
        if (asc) {
            return users.stream()
                    .sorted(Comparator.comparing(user -> user.getEmail()))
                    .collect(Collectors.toList());
        } else {
            return users.stream()
                    .sorted(Comparator.comparing(User::getEmail).reversed())
                    .collect(Collectors.toList());
        }

    }
}
