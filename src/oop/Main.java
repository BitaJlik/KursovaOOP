package oop;

import oop.Items.Items;

import java.util.Scanner;
import java.util.stream.IntStream;

import static oop.MainMenu.menu_entity_ch;

public class Main {

    public static Home home = new Home(); // Adding Home to game
    static int SizeMap = 500;
    static Scanner sc = new Scanner(System.in);
    static int size = 10;
    static Entity[] entity = new Entity[size];
    static String input;

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> entity[i] = new Spider());          //\---------- Making list Entity
        entity[1] = new Steve("Гоша",20,2,50,24,10); //\-------------- Adding Steve in Entity
        Items.initialize();
        System.out.println(Items.items.get(1).getDigseconds());
        // Others trash
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