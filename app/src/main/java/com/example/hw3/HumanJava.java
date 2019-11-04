package com.example.hw3;

import java.io.Serializable;

public class HumanJava implements Serializable {
    private String name;
    private String hairColor;

    public HumanJava(String name, String hairColor) {
        this.name = name;
        this.hairColor = hairColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
}
