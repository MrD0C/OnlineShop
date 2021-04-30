package com.example.onlineshop.model;

public enum Gender {
    MAN("Мужской"),
    WOMAN("Женский");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public boolean isSame(String gender) {
        return this.gender.equals(gender);
    }

    public String getGender() {
        return gender;
    }
}
