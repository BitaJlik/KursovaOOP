package oop;

import java.util.Scanner;

import static oop.MainMenu.menu_entity_ch;

public class Main {
    static int SizeMap = 500;
    static Scanner sc = new Scanner(System.in);
    static int size = 10;
    static Entity[] entity = new Entity[size];
    static String input;
    public static void main(String[] args) {
        for(int i = 0;i<10;i++)        //---------- Створюємо ліст Об'єктів
            entity[i] = new Spider();
        entity[1] = new Steve("Гоша",20,2,50,24,10); // Добавляємо Стіва
        MainMenu.SeeEntity();
        System.out.println("\n\nВиберіть :");
        safe();
        MainMenu.menu(entity[menu_entity_ch]);
    }
    public static void safe(){
        try {
            input = sc.next();
            menu_entity_ch = Integer.parseInt(input);
            if(menu_entity_ch < 0|| menu_entity_ch > size){
                System.out.println("\u001B[31m"+ "Введення некоректне!!!\n" + "\u001B[0m");
                safe();
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31m"+ "Введення некоректне!!!\n" + "\u001B[0m");
            safe();
        }
    }
}