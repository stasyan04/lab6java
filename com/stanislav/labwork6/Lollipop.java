package com.stanislav.labwork6;

public class Lollipop extends Candy {
    public Lollipop(String name, double weight, double sugarContent, double chocolateContent) {
        super(name, weight, sugarContent, chocolateContent);
    }

    @Override
    public String getDescription() {
        return "Льодяник " + getName();
    }
}