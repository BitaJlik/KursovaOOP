package oop;

import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.SteveMiddle;
import oop.Items.Items;
import oop.Structures.World;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static oop.MainMenu.menu_entity_ch;

public class Main  {
    static int SizeMap = 500;
    public static World world = new World(SizeMap);
    public static Scanner sc = new Scanner(System.in);
    static int sizeent ;
    static String input;

    static ArrayList<Entity> entity = new ArrayList<>();
    static ArrayList<Entity> refEntity = new ArrayList<>();

    public static void main(String[] args) throws CloneNotSupportedException {
        launch();

    }
    public static void launch() throws CloneNotSupportedException{
        System.out.println("Скільки об'єктів буде?");
        sizeent = sc.nextInt();
        IntStream.range(0, sizeent).forEach(i -> refEntity.add(i,new Spider("Павук",Math.round(1 +Math.random()*10),2)));
        refEntity.set(1, new oop.Entities.Steve("Гоша",20,4));
        refEntity.add(new SteveMiddle(20,"Макс",30,4));
        Items.initialize();

        for(int i = 0 ;i < refEntity.size();++i) { // Reference Array
            entity.add(i, refEntity.get(i));
        }
        // Others trash
        MainMenu.SeeEntity();
        System.out.println("\n\nВиберіть :");
        safe();
        MainMenu.menu(entity.get(menu_entity_ch));
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