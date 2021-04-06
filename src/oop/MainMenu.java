package oop;

import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Structures.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static oop.Items.Items.items;
import static oop.Main.*;

public class MainMenu {

    static int menu_entity_ch ;
    static int ch2;
    static Scanner sc = new Scanner(System.in);

    public static void menu(Entity obj) throws CloneNotSupportedException {
        if(obj.getClass() == Spider.class){
            menulistSpider(obj);
        }
        if(obj instanceof Steve){
            menulistSteve(obj);
        }
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
                System.out.println("Режим бою:\n [1] - Звичайний [2] - " + "\u001B[31m" + "Насмерть" + "\u001B[m");
                String choosebatlle = sc.next();
                switch (choosebatlle) {
                    case "2" -> {
                        System.out.println("Виберіть створіння для бою : ");
                        String ch = sc.next();
                        try {
                            ch2 = Integer.parseInt(ch);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31m" + "Некоректне введеня!" + "\u001B[0m");
                            Progress.Waiting(1000);
                            cls();
                            menu(obj);
                        }
                        if (ch2 >= entity.size() || ch2 < 0) {
                            menu(obj);
                        } else {

                            if (obj.equals(entity.get(ch2))) {
                                System.out.println("\u001B[31m" + "Створіння само з собою не може проводити бої!" + "\u001B[0m");
                                menu(obj);
                            } else if (obj.getId() == -1 || entity.get(ch2).getId() == -1) {
                                System.out.println("Одне з створінь мертве");
                                menu(obj);
                            } else
                                Deathbatlle(obj, entity.get(ch2));
                        }
                    }
                    case "1" -> {
                        System.out.println("Виберіть створіння для бою : ");
                        String ch = sc.next();
                        try {
                            ch2 = Integer.parseInt(ch);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31m" + "Некоректне введеня!" + "\u001B[0m");
                            Progress.Waiting(1000);
                            cls();
                            menu(obj);
                        }
                        if (ch2 >= entity.size() || ch2 < 0) {
                            menu(obj);
                        } else {

                            if (obj.equals(entity.get(ch2))) {
                                System.out.println("\u001B[31m" + "Створіння само з собою не може проводити бої!" + "\u001B[0m");
                                menu(obj);
                            } else if (obj.getId() == -1 || entity.get(ch2).getId() == -1) {
                                System.out.println("Одне з створінь мертве");
                                menu(obj);
                            } else
                                Batlle(obj, entity.get(ch2));
                        }
                    }
                    default -> System.out.println("Нічого не введено");
                }
                menu(obj);
            }
//------------------------------------------ Creating new entity
            case "5" -> CreateEntity();
//------------------------------------------ Delete entity
            case "55" -> DeleteEntity();
//------------------------------------------ List all entities
            case "6" -> {
                SeeEntity();
                menu(obj);
            }
//------------------------------------------ Moving
            case "7" -> {
                System.out.println("Куди йдемо?");
                String where = sc.next();
               if( obj.getX() + obj.getSpeed() >= SizeMap){
                    System.out.println("Він знаходиться на крайній точці!");
                    menu(obj);
                }
                else if( obj.getY() + obj.getSpeed() >= SizeMap){
                    System.out.println("Він знаходиться на крайній точці!");
                    menu(obj);
                }
                else {
                    switch (where) {
                        case "Вгору","вгору"   -> obj.moveUp();
                        case "Вниз","вниз"     -> obj.moveDown();
                        case "Вліво","вліво"   -> obj.moveLeft();
                        case "Вправо","вправо" -> obj.moveRight();
                        default -> System.out.println("Не вибрано");
                    }
                    menu(obj);
                }
            }
//------------------------------------------  Inventory
            case "8" -> Invetory(obj);
//------------------------------------------ Digging item
            case "9" ->{
                System.out.println("Куди йдемо? [1] - Шахта \t[2] - Всередину острова");
                String side = sc.next();
                switch (side) {
                    case "1" -> World.cave.Dig(obj);
                    case "2" -> world.SeekFood(obj);
                    case "-1" -> menu(obj);
                    default -> System.out.println("Не введено параметр");
                }
                menu(obj);
            }
//------------------------------------------
            case "0" -> {
                System.out.println("[1] - День \t[2] - Ніч");
                String time = sc.next();
                if(time.equals("1")) world.Day();
                else if(time.equals("2")) world.Night();
                menu(obj);
            }
//------------------------------------------ Exit
            case "-1" ->{}
//------------------------------------------ Binary Search by new obj
            case "000" ->{
                Collections.sort(entity);
                System.out.println("Введіть ім'я шуканого");
                System.out.println(Collections.binarySearch(entity,
                        new Spider(sc.next(), 2, 2)) );
                menu(obj);
            }
//------------------------------------------ Making competition
            case "01" ->{
                ArrayList<Entity> fstGroup = new ArrayList<>();
                ArrayList<Entity> scndGroup = new ArrayList<>();
                if(entity.size()%2 == 0) {
                    for (int i = 0; i < entity.size() / 2; ++i) {
                        fstGroup.add(entity.get(i).clone());
                    }
                    for (int i = entity.size()/2; i < entity.size(); ++i) {
                        scndGroup.add(entity.get(i).clone());
                    }
                    for(int i = 0;i<fstGroup.size();++i){
                        Batlle(fstGroup.get(i),scndGroup.get(i));
                    }
                    double fstSumHp = 0;
                    double scndSumHp = 0;
                    for(int i =0;i<fstGroup.size();++i){
                        fstSumHp += fstGroup.get(i).getHp();
                        scndSumHp += scndGroup.get(i).getHp();
                    }
                    if(fstSumHp > scndSumHp){
                        System.out.println("\u001B[32m"+ "\n\nКоманда 1 Виграла!\t" + fstSumHp + " Cума hp" + "\u001B[0m");
                        for (Entity value : fstGroup) { System.out.println(value); }
                    }
                    else if(scndSumHp > fstSumHp){
                        System.out.println("\u001B[32m" + "\n\nКоманда 2 Виграла!\t"+ scndSumHp + " Cума hp"+ "\u001B[0m");
                        for (Entity value : scndGroup) { System.out.println(value); }
                    }
                    else System.out.println("Нічия!");
                }
                else System.out.println("Неможливо поділити на дві рівні групи!");
                menu(obj);
            }
//----------------------------------------- Divide by 2 groups,and choose for deleting in entity
            case "02" ->{
                ArrayList<Entity> fstGroup = new ArrayList<>();
                ArrayList<Entity> scndGroup = new ArrayList<>();
                if(entity.size()%2 == 0) {
                    for (int i = 0; i < entity.size() / 2; ++i) {
                        fstGroup.add(entity.get(i).clone());
                    }
                    for (int i = entity.size()/2; i < entity.size(); ++i) {
                        scndGroup.add(entity.get(i).clone());
                    }
                }
                else System.out.println("Неможливо поділити на дві рівні групи!");
                System.out.println("Яку групу видалити? [1] - Першу половину [2] - другу половину");
                String chose = sc.next();
                switch (chose) {
                    case "1":
                        for (int i = 0; i < entity.size(); ++i) {
                            for (int j = 0; j < fstGroup.size(); ++i) {
                                if (entity.get(i).equals(fstGroup.get(j))) {
                                    entity.remove(i);
                                }
                            }
                        }
                        break;
                    case "2":
                        for (int i = 0; i < entity.size(); ++i) {
                            for (int j = 0; j < scndGroup.size(); ++i) {
                                if (entity.get(i).equals(scndGroup.get(j))) {
                                    entity.remove(i);
                                }
                            }
                        }
                        break;
                }
            }
//------------------------------------------ Default
            default -> {
                System.out.println("\u001B[31m"+"Не введено параметр "+"\u001B[0m");
                Progress.Waiting(1000);
                cls();
                menu(obj);
            }
        }
    }

    public static void Invetory(Entity obj) throws CloneNotSupportedException {
        if(obj instanceof Steve ) { //---------- ONLY STEVE-------\\

            SeeItems(obj);
            System.out.println("\n\n[-2] - Продати предмет \t [-1] - Вихід");
            String itemchoose = sc.next();
            switch (itemchoose) {
                case "-2" -> SellItem(obj);
                case "-1" -> menu(obj);
                default -> {
                    try {
                        if(Integer.parseInt(itemchoose) >= items.size() || Integer.parseInt(itemchoose) < -2){
                            System.out.println("\u001B[31m" + "Некоректне введеня!"+ "\u001B[0m");
                            Progress.Waiting(1000);
                            cls();
                        }
                        else {
                            cls();
                            System.out.println(items.get(Integer.parseInt(itemchoose)));
                        }
                        Invetory(obj);
                    }catch (NumberFormatException e){
                        System.out.println("\u001B[31m" + "Неправильне введення!" + "\u001B[0m");
                        Progress.Waiting(2000);
                        cls();
                        Invetory(obj);
                    }
                }
            }
        }
    }
    public static void SeeItems(Entity obj){
        if(obj instanceof Steve) {
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
                System.out.println("\u001B[32m" + "\nТут пусто 0_0" + "\u001B[0m");
            }
        }
    }
    public static void SellItem(Entity obj) throws CloneNotSupportedException {
        if(obj instanceof Steve ) {
            System.out.println("Що продати?\t [-1] Назад");
            int chooseinv = sc.nextInt();
            if(chooseinv < -1 || chooseinv >= items.size()){
                System.out.println("\u001B[31m" + "Некоректне введеня!"+ "\u001B[0m");
                Progress.Waiting(1000);
                cls();
            }
            else {
                if (chooseinv == -1) {
                    cls();
                    Invetory(obj);
                } else
                    World.home.Sell(chooseinv, obj); // Trying to use seller with objects
            }
            Invetory(obj);
        }
    }
    public static void SeeEntity(){ // Screen all Entities
        for(int i = 0;i < entity.size();i++){
            if(entity.get(i).getId() == -1) {
                System.out.print("\u001B[31m" +"[" + i + "] " + entity.get(i).getName());
                System.out.print( " - Мертвий \n" + "\u001B[0m");
            }
            else  {
                System.out.println("|" + i + "| " + entity.get(i).getName());
                //System.out.println( "|" + i + "| " + entity.get(i));
            }
        }
    }
    public static void ChangeEntity() throws CloneNotSupportedException { //---------- Changing Entity from list
        System.out.println("Виберіть: ");
        String menu_entity = sc.next();
        try{
            menu_entity_ch = Integer.parseInt(menu_entity);
        }
        catch (NumberFormatException e){
            System.out.println("\u001B[31m"+"Введено неправильне місце"+"\u001B[0m");
            Progress.Waiting(1000);
            ChangeEntity();
        }
        if(menu_entity_ch > entity.size()|| menu_entity_ch < 0){
            System.out.println("Неравильний параметр!");
            ChangeEntity();
        }
        else {
            if(entity.get(menu_entity_ch).getId() == -1){
                System.out.println("\u001B[31m" +"Об'єкт мертвий"+ "\u001B[0m" );
                 ChangeEntity();
            }
            else menu(entity.get(menu_entity_ch));
        }
    }
    public static void CreateEntity() throws CloneNotSupportedException { // I think its logic?
        System.out.println("[1] - Створити чи [2] - Скопіювати?");
        String ch = sc.next();
        if(ch.equals("1")){
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
            if (choosen > sizeent) {
                System.out.println("\u001B[31m" + "Місце перевищує розмір!\n" + "\u001B[0m");
                menu(entity.get(0));
            } else {
                System.out.println("Кого створити?\n1 - Spider\t 2- Steve");
                String side = sc.next();
                switch (side) {
                    case "1" -> { // Side Spiders
                        entity.add(choosen + 1, new Spider(name, hp, dmg));
                        System.out.println("Створено новий Об'єкт" + entity.get(choosen).getName());
                    }
                    case "2" -> { // Side Steves
                        entity.add(choosen + 1, new Steve(name, hp, dmg));
                        System.out.println("Створено новий Об'єкт");
                    }
                    default -> { // If user inputted other
                        System.out.println("Створення відмінено\n");
                        menu(entity.get(0));
                    }
                }
                menu(entity.get(choosen));
            }
        }
        else if(ch.equals("2")){
            System.out.println("Кого скопіювати?");
            SeeEntity();
            int chose = sc.nextInt();
            entity.add(entity.get(chose).clone());
            menu(entity.get(chose));
        }

    }
    public static void DeleteEntity(){
        System.out.println("Кого видалити?");
        SeeEntity();
        String ch = sc.next();
        try {
            int choose = Integer.parseInt(ch);
            if (choose < entity.size() && choose >= 0) {
            System.out.println("Видалено " + entity.get(choose).getName());
            entity.remove(choose);
            ChangeEntity();
            }
        }
        catch (NumberFormatException | CloneNotSupportedException e){
            System.out.println("\u001B[31m" + "Неправильне введення!" + "\u001B[0m");
            Progress.Waiting(2000);
            cls();
            DeleteEntity();
        }
    }
    public static void Batlle(Entity FrstEntity,Entity ScndEntity) {
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
    public static void Deathbatlle(Entity FrstEntity,Entity ScndEntity) {
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
    }
    // public static void sort(Entity obj) throws CloneNotSupportedException {
    //     System.out.println("Сортування:\n [1] - Comparable [2] - Анонімним класом(Only Hp) ");
    //     String chooseSort = sc.next();
    //     if(chooseSort.equals("1")){
    //         System.out.println("Відсортувати за:\n [1] - Hp [2] - Ім'ям");
    //         chooseSort = sc.next();
    //         if(chooseSort.equals("1")){
    //             entity.sort(Spider::compareTo);
    //             SeeEntity();
    //             menu(obj);
    //         }
    //         else if(chooseSort.equals("2")){
    //             entity.sort(Steve::compareTo);
    //             SeeEntity();
    //             menu(obj);
    //         }

    //     }
    //     else if(chooseSort.equals("2")) {
    //         System.out.println("Сортувати за:  [1] - Зростанням [2] - Спаданням");
    //         chooseSort = sc.next();
    //         if (chooseSort.equals("1")) {

    //             entity.sort(new Comparator<Entity>() {
    //                 @Override
    //                 public int compare(Entity o1, Entity o2) {
    //                     int a = (int) o1.getHp();
    //                     int b = (int) o2.getHp();
    //                     return a > b ? 1 : a == b ? 30 : -1;
    //                 }
    //             });
    //         } else if (chooseSort.equals("2")) {
    //             entity.sort(new Comparator<Entity>() {
    //                 @Override
    //                 public int compare(Entity o1, Entity o2) {
    //                     int a = (int) o1.getHp();
    //                     int b = (int) o2.getHp();
    //                     return a < b ? 1 : a == b ? 30 : -1;
    //                 }
    //             });
    //         }
    //         SeeEntity();
    //     }
    // }
    public static void menulistSpider(Entity obj){
        System.out.println(
                "\nВибрано : " + obj.getName() +
                        "\n [00] - Сортування  " +
                        "\n [ 1] - Вивести на екран інформацію об'єкта" +
                        "\n [ 2] - Змінити параметри " +
                        "\n [ 3] - Вибрати іншого " +
                        "\n [ 4] - Бій між об'єктами" +
                        "\n [ 5] - Створити нового   " +"[55] - Видалити" +
                        "\n [ 6] - Вивести всіх " +
                        "\n [ 7] - Пересування " +
                        "\n [-1] - Завершення програми\n");
    } public static void menulistSteve(Entity obj){
        System.out.println(
                "\nВибрано : " + obj.getName() +
                        "\n [00] - Сортування  " +
                        "\n [ 1] - Вивести на екран інформацію об'єкта" +
                        "\n [ 2] - Змінити параметри " +
                        "\n [ 3] - Вибрати іншого " +
                        "\n [ 4] - Бій між об'єктами" +
                        "\n [ 5] - Створити нового   " +"[55] - Видалити" +
                        "\n [ 6] - Вивести всіх " +
                        "\n [ 7] - Пересування " +
                        "\n [ 8] - Переглянути предмети " +
                        "\n [ 9] - Добути предмети " +
                        "\n [-1] - Завершення програми\n");
    }
    public static void cls(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}