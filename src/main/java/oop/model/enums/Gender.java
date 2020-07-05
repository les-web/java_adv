package oop.model.enums;

public enum Gender {
    MAN("mężczyzna"), WOMAN("kobieta");


    private final String genderName;

    public String getGenderName() {
        return genderName;
    }

    Gender(String genderName) {         // konstruktor typu wyliczeniowego

        this.genderName = genderName;
    }

}
