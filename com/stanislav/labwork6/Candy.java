package com.stanislav.labwork6;

import java.util.Objects;

public abstract class Candy {
    private String name;
    private double weight;
    private double sugarContent;
    private double chocolateContent;

    public Candy(String name, double weight, double sugarContent, double chocolateContent) {
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.chocolateContent = chocolateContent;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return name + " [вага: " + weight + " г, цукор: " + sugarContent +
                ", шоколад: " + chocolateContent + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candy)) return false;
        Candy candy = (Candy) o;
        return Double.compare(candy.weight, weight) == 0 &&
                Double.compare(candy.sugarContent, sugarContent) == 0 &&
                Double.compare(candy.chocolateContent, chocolateContent) == 0 &&
                Objects.equals(name, candy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, sugarContent, chocolateContent);
    }
}