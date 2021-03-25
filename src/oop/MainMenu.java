package oop;

import java.util.Scanner;

import static oop.Items.Items.items;
import static oop.Main.*;

public class MainMenu {

    static int menu_entity_ch ;
    static int entity_ch;
    static int entity_ch2;
    static Scanner sc = new Scanner(System.in);

    public static void menu(Entity obj)  {
        System.out.println(
                "\nВибрано : " + obj.getName() +
                        "\n [ 0] - Поспати (+3 Hp)  " +
                        "\n [ 1] - Вивести на екран інформацію " +
                        "\n [ 2] - Змінити параметри " +
                        "\n [ 3] - Вибрати іншого " +
                        "\n [ 4] - Бій між об'єктами" +
                        "\n [44] - Бій між об'єктами "+ "\u001B[31m" + "насмерть " + "\u001B[0m" +
                        "\n [ 5] - Створити нового " +
                        "\n [ 6] - Вивести всіх " +
                        "\n [ 7] - Пересування " +
                        "\n [ 8] - Переглянути предмети " +
                        "\n [ 9] - Добути предмети " +
                        "\n [-1] - Завершення програми\n");
        System.out.print("Що робимо? ");
        String choose = sc.next();
        switch (choose) {
//------------------------------------------ Print
            case "1" -> {
                System.out.println(obj);
                menu(obj);
            }
//------------------------------------------ Changing parameters in chosen entity
            case "2" -> {
                System.out.println("Що змінюємо? \nName(1),Id(2),Hp(3),Damage(dmg,4)");
                String choose_edit = sc.next();
                switch (choose_edit) {
                    case "name", "1" -> {
                        System.out.println("Введіть нову назву " + obj);
                        String name = sc.next();
                        obj.setName(name);
                    }
                    case "id", "2" -> {
                        System.out.println("Введіть новий Id:");
                        int id = sc.nextInt();
                        obj.setId(id);
                    }
                    case "hp", "3" -> {
                        System.out.println("Введіть кількість HitPoints: ");
                        double hp = sc.nextDouble();
                        obj.setHp(hp);
                    }
                    case "damage", "dmg", "4" -> {
                        System.out.println("Введіть величину урону: ");
                        double damage = sc.nextDouble();
                        obj.setDamage(damage);
                    }
                }
                menu(obj);
            }
//------------------------------------------ Changing Entity from list
            case "3" -> {
                SeeEntity();
                ChangeEntity();
            }
//------------------------------------------ Batlle
            case "4" -> {
                System.out.println("Виберіть створіння для бою : ");
                entity_ch2 = sc.nextInt();
                if (obj.equals(entity[entity_ch2])) {
                    System.out.println("\u001B[31m" + "Створіння само з собою не може проводити бої!" + "\u001B[0m");
                    menu(obj);
                }
                else if (obj.getId() == -1 || entity[entity_ch2].getId() == -1) {
                    System.out.println("Одне з створінь мертве");
                    menu(obj);
                }
                else
                    Batlle(obj, entity[entity_ch2]);
                menu(obj);
            }
//------------------------------------------ DeathBatlle
            case "44" -> {
                System.out.println("Виберіть  героя для бою : ");
                entity_ch = sc.nextInt();
                entity_ch2 = sc.nextInt();
                if (entity_ch == entity_ch2) {
                    System.out.println("\u001B[31m" + "Створіння само з собою не може проводити бої!" + "\u001B[0m");
                    menu(obj);
                } else if (entity[entity_ch].getId() == -1 || entity[entity_ch2].getId() == -1) {
                    System.out.println("Одне з створінь мертве");
                    menu(entity[menu_entity_ch]);
                } else Deathbatlle(entity[entity_ch], entity[entity_ch2]);
            }
//------------------------------------------ Creating new entity
            case "5" -> CreateEntity();
//------------------------------------------ List all entities
            case "6" -> {
                SeeEntity();
                menu(obj);
            }
//------------------------------------------ Moving
            case "7" -> {
                System.out.println("Куди йдемо?");
                String where = sc.next();
                System.out.println("Наскільки ми йдемо?");
                double movestep = sc.nextDouble();
                if(movestep >= SizeMap){
                    System.out.println("Він знаходиться на крайній точці!");
                    menu(obj);
                }
                else if(movestep + obj.getX() >= SizeMap){
                    System.out.println("Він знаходиться на крайній точці!");
                    menu(obj);
                }
                else if(movestep + obj.getY() >= SizeMap){
                    System.out.println("Він знаходиться на крайній точці!");
                    menu(obj);
                }
                else {
                    switch (where) {
                        case "Вгору","вгору"   -> obj.moveUp();
                        case "Вниз","вниз"     -> obj.moveDown();
                        case "Вліво","вліво"   -> obj.moveLeft();
                        case "Вправо","вправо" -> obj.moveRight();
                    }
                    menu(obj);
                }
            }
//------------------------------------------  Inventory
            case "8" -> Invetory(obj);
//------------------------------------------ Digging item
            case "9" ->{
                dig(obj);
                menu(obj);
            }
//------------------------------------------ Sleep
            case "0" -> {
                obj.Sleeping();
                menu(obj);
            }
//------------------------------------------ Default
            default -> {
                for (int i = 0; i < 50; ++i) System.out.println();
            }
        }
    }
    public static void dig(Entity obj){
        if(obj.getClass() == Steve.class ) {
            double id = Math.random() * items.size();
            for (oop.Items.Items item : items) {
                if (item.getId() == Math.round(id)) {
                    System.out.println("Добули " + item.getName());
                    item.setAmount(1);
                }
            }
        }
        else System.out.println("Це павук!");
    }
    public static void Invetory(Entity obj){
        if(obj.getClass() == Steve.class ) {
            SeeItems(obj);
            System.out.println("\n\n[-2] - Продати предмет \t [-1] - Вихід");
            String itemchoose = sc.next();
            if(itemchoose.equalsIgnoreCase("-2")){
                SellItem(obj);
            }
            else if(itemchoose.equalsIgnoreCase("-1")){
                menu(obj);
            }
            else {
                cls();
                System.out.println(items.get(Integer.parseInt(itemchoose)));
                Invetory(obj);
            }
        }
    }
    public static void SeeItems(Entity obj){
        if(obj.getClass() == Steve.class ) {
            int check=0,empty =0;
            for(int i = 0;i<items.size();++i){
                if(items.get(i).getAmount() <= 0){
                    empty = 1;
                }
                else{
                System.out.println( "[" + i + "]"+items.get(i).getName() + " \tКількість: "+ items.get(i).getAmount());
                check = 1;
                }
            }
            if(empty == 1 && check != 1){
                System.out.println("\nТут пусто 0_0");
            }
        }
    }
    public static void SellItem(Entity obj){
        if(obj.getClass() == Steve.class ) {
            System.out.println("Що продати?\t [-1] Назад");
            int chooseinv = sc.nextInt();
            if (chooseinv == -1) {
                cls();
                Invetory(obj);
            } else
                home.Sell(chooseinv, obj); // Trying to use seller with objects
            menu(obj);
        }
    }
    public static void SeeEntity(){ // Screen all Entities
        for(int i = 0;i < size;i++){
            if(entity[i].getId() == -1) {
                System.out.print("\u001B[31m" +"[" + i + "] " + entity[i].getName());
                System.out.print( " - Мертвий \n" + "\u001B[0m");
            }
            else  {
                System.out.println("|" + i + "| " + entity[i].getName());
            }
        }
    }
    public static void ChangeEntity(){ //---------- Changing Entity from list
        System.out.println("Виберіть: ");
        menu_entity_ch = sc.nextInt();
        if(entity[menu_entity_ch].getId() == -1){
            System.out.println("\u001B[31m" +"Об'єкт мертвий"+ "\u001B[0m" );
            ChangeEntity();
        }
        else menu(entity[menu_entity_ch]);
    }
    public static void CreateEntity(){ // I think its logic?
        String name;
        double hp, dmg;
        System.out.println("Введіть Name: ");
        name = sc.next();
        System.out.println("Введіть Hp: ");
        hp = sc.nextDouble();
        System.out.println("Введіть Damage:");
        dmg = sc.nextDouble();
        System.out.println("Виберіть місце для створення");
        SeeEntity();
        int choosen = sc.nextInt();
        if(choosen > size){
            System.out.println("\u001B[31m" + "Місце перевищує розмір!\n" + "\u001B[0m");
            menu(entity[0]);
        }
        else{
            System.out.println("Кого створити?\n1 - Spider\t 2- Steve");
            String side = sc.next();
            switch (side) {
                case "1" -> { // Side Spiders
                    entity[choosen] = new Spider(name, hp, dmg);
                    System.out.println("Створено новий Об'єкт" + entity[choosen].getName());
                }
                case "2" -> { // Side Steves
                    entity[choosen] = new Steve(name, hp, dmg);
                    System.out.println("Створено новий Об'єкт");
                }
                default -> { // If user inputted other
                    System.out.println("Створення відмінено\n");
                    menu(entity[0]);
                }
            }
            menu(entity[choosen]);
        }
    }
    public static void Batlle(Entity FrstEntity,Entity ScndEntity){
        FrstEntity.Attack();
        FrstEntity.setHp(FrstEntity.getHp() - ScndEntity.getDamage());

        ScndEntity.Attack();
        ScndEntity.setHp(ScndEntity.getHp() - FrstEntity.getDamage());
        System.out.println(ScndEntity);
        System.out.println(FrstEntity);
        if(FrstEntity.getHp() <= 0 && ScndEntity.getHp() <= 0){
            FrstEntity.Death();
            ScndEntity.Death();
            System.out.println("Нічия!Обидва померли!");
        }
        else if(FrstEntity.getHp() <= 0){
            ScndEntity.Healing(FrstEntity.getDamage()*2);
            FrstEntity.Death();
            System.out.println(ScndEntity.getName() + " Перемагає!");
            menu(ScndEntity);
        }
        else if(ScndEntity.getHp() <= 0){
            FrstEntity.Healing(ScndEntity.getDamage()*2);
            ScndEntity.Death();
            System.out.println(FrstEntity.getName() + " Перемагає!");
            menu(FrstEntity);
        }
        else if(FrstEntity.getHp() == ScndEntity.getHp()){
            System.out.println("Нічия!");
        }
    }
    public static void Deathbatlle(Entity FrstEntity,Entity ScndEntity){
        while(FrstEntity.getId() != -1 || ScndEntity.getId() != -1){
            if(FrstEntity.getId() == -1){
                break;
            }
            else if(ScndEntity.getId() == -1){
                break;
            }
            else {
                FrstEntity.Attack();
                FrstEntity.setHp(FrstEntity.getHp() - ScndEntity.getDamage());
                ScndEntity.Attack();
                ScndEntity.setHp(ScndEntity.getHp() - FrstEntity.getDamage());
                System.out.println(ScndEntity);
                System.out.println(FrstEntity);
                if(FrstEntity.getHp() <= 0 && ScndEntity.getHp() <= 0){
                    FrstEntity.Death();
                    ScndEntity.Death();
                    System.out.println("Нічия!Обидва померли!");
                }
                else if(FrstEntity.getHp() <= 0){
                    ScndEntity.Healing(FrstEntity.getDamage()*2);
                    FrstEntity.Death();
                    System.out.println(ScndEntity.getName() + " Перемагає!");
                }
                else if(ScndEntity.getHp() <= 0){
                    FrstEntity.Healing(ScndEntity.getDamage()*2);
                    ScndEntity.Death();
                    System.out.println(FrstEntity.getName() + " Перемагає!");
                }
                else if(FrstEntity.getHp() == ScndEntity.getHp()){
                    System.out.println("Нічия!");
                }
            }
        }
        menu(FrstEntity);
    }
    public static void cls(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
