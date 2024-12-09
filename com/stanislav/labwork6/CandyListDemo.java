package com.stanislav.labwork6;

import java.util.ArrayList;
import java.util.List;

public class CandyListDemo {
    public static void main(String[] args) {
        CandyList candyListEmpty = new CandyList();
        System.out.println("Порожній список: size = " + candyListEmpty.size());

        Candy oneCandy = new ChocolateCandy("SingleChoco", 20, 30, 50);
        CandyList candyListOne = new CandyList(oneCandy);
        System.out.println("Список з одним елементом:");
        for (Candy c : candyListOne) {
            System.out.println(c);
        }

        List<Candy> candyCollection = new ArrayList<>();
        candyCollection.add(new CaramelCandy("Caramella", 15, 40, 0));
        candyCollection.add(new Lollipop("FruityPop", 10, 35, 0));
        candyCollection.add(new ChocolateCandy("ChocoDelight", 25, 20, 70));

        CandyList candyListFromCollection = new CandyList(candyCollection);
        System.out.println("\nСписок з колекції:");
        for (Candy c : candyListFromCollection) {
            System.out.println(c);
        }

        candyListFromCollection.add(new CaramelCandy("Toffee", 17, 45, 0));
        System.out.println("\nПісля додавання Toffee:");
        for (Candy c : candyListFromCollection) {
            System.out.println(c);
        }

        candyListFromCollection.add(1, new ChocolateCandy("DarkChoco", 25, 20, 80));
        System.out.println("\nПісля вставки DarkChoco на індекс 1:");
        for (Candy c : candyListFromCollection) {
            System.out.println(c);
        }

        candyListFromCollection.remove(0);
        System.out.println("\nПісля видалення елемента на індексі 0:");
        for (Candy c : candyListFromCollection) {
            System.out.println(c);
        }

        boolean hasDarkChoco = candyListFromCollection.contains(new ChocolateCandy("DarkChoco", 25, 20, 80));
        System.out.println("Список містить DarkChoco? " + hasDarkChoco);

        candyListFromCollection.set(0, new Lollipop("BerryPop", 12, 33, 0));
        System.out.println("\nПісля set(0, BerryPop):");
        for (Candy c : candyListFromCollection) {
            System.out.println(c);
        }

        candyListFromCollection.clear();
        System.out.println("\nПісля clear: size = " + candyListFromCollection.size());
    }
}