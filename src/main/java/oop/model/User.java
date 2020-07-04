package oop.model;

import oop.model.enums.Gender;
import oop.model.enums.Role;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/****************************************
 * Klasa modelu- klasa determinująca strukturę danych
 *
 *  wzorzec Java Beans
 * 1 prywatne pola klasowe
 * 2 metody dostepowe do pol klasowych - getters setters Alt Ins
 * 3 konstruktory klasy modelu
 * 4 toString Alt Ins
 ************************************** */


public class User {
    // składowa statyczna jedna dla wszysykich instancji klasy
    private static int globalId =1;
    private int userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Gender gender;          // płeć jest typem
    // zbiór ról
    private Set<Role>  roles = new HashSet<>(Arrays.asList(Role.ROLE_USER));
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    private boolean status;
    private boolean removed;

    public User(String name, String lastName, String email, String password, String phone, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.userId = globalId;
        globalId++;
    }

    @Override
    public String toString() {
        return String.format("| %3d | %15s | %15s | %10s | %10s | %9s | %10s | %40s | %25s | %6s | %6s |",
                userId,name,lastName,email,password,phone,
                gender.getGenderName(), roles, registrationDateTime, status, removed)  ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
