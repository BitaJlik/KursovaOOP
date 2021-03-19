package oop;

import java.util.Scanner;

import static oop.MainMenu.menu_entity_ch;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static int size = 10;

    static Entity[] entity = new Entity[size];

    public static void main(String[] args) {

        for(int i = 0;i<10;i++)        //---------- Створюємо ліст Об'єктів
            entity[i] = new Spider();
        entity[1] = new Steve(); // Добавляємо Стіва

        System.out.println("\n\nВиберіть :");
        MainMenu.SeeEntity(); // Початковий список
        menu_entity_ch = sc.nextInt();

        MainMenu.menu(entity[menu_entity_ch]);
    }
}