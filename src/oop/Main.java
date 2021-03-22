package oop;

import java.util.Scanner;

import static oop.MainMenu.menu_entity_ch;
import oop.Home;

public class Main {
    static int SizeMap = 500;
    static Scanner sc = new Scanner(System.in);
    static int size = 10;
    static Entity[] entity = new Entity[size];
    public static void main(String[] args) {

        for(int i = 0;i<10;i++)        //---------- Створюємо ліст Об'єктів
            entity[i] = new Spider();


        entity[1] = new Steve("Гоша",20,2,50,24,10); // Добавляємо Стіва
        System.out.println("\n\nВиберіть :");

        MainMenu.SeeEntity(); // Початковий список
        menu_entity_ch = sc.nextInt();

        MainMenu.menu(entity[menu_entity_ch]);
    }
}