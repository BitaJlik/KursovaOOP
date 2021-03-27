package oop;

import oop.Items.Items;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static oop.MainMenu.menu_entity_ch;

public class Main  {

    public static Home home = new Home(); // Adding Home to game
    static int SizeMap = 500;
    static Scanner sc = new Scanner(System.in);
    static int size ;
    static String input;

    static ArrayList<Entity> entity = new ArrayList<>();
    static ArrayList<Entity> refEntity = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Скільки об'єктів буде?");
        size = sc.nextInt();

        IntStream.range(0, size).forEach(i -> refEntity.add(i,new Spider("Павук",Math.round(Math.random()*10),2)));     //\---------- Making list Entity
        refEntity.set(1, new Steve("Гоша",20,2,50,24,10)); //\-------------- Adding Steve in Entity
        Items.initialize();
        for(int i = 0 ;i < refEntity.size();++i){ // Reference Array
            entity.add(i,refEntity.get(i));
        }
        // Others trash
        MainMenu.SeeEntity();
        System.out.println("\n\nВиберіть :");
        safe();
        MainMenu.menu(entity.get(menu_entity_ch));
        System.out.println(refEntity.get(1));
    }
    public static void safe(){
        try {
            input = sc.next();
            menu_entity_ch = Integer.parseInt(input);
            if(menu_entity_ch < 0|| menu_entity_ch >= entity.size()){
                System.out.println("\u001B[31m"+ "Введення некоректне!!!\n" + "\u001B[0m");
                safe();
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31m"+ "Введення некоректне!!!\n" + "\u001B[0m");
            safe();
        }
    }
}