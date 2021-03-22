package oop;

import java.util.Scanner;

import static oop.Main.entity;
import static oop.Main.size;
import static oop.Main.SizeMap;
public class MainMenu {

    static int menu_entity_ch;
    static int entity_ch;
    static int entity_ch2;
    static Scanner sc = new Scanner(System.in);

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
                        "\n [-1] - Завершення програми\n");
        System.out.print("Що робимо? ");
        String choose = sc.next();
        switch (choose) {
// Print Вибраного Обєкта
            case "1" -> {
                System.out.println(obj);
                menu(obj);
            }
// Зміна параметрів обраного Об'єкта
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
// Зміна Об'єкта
            case "3" -> {
                SeeEntity();
                ChangeEntity();
            }
// Бій
            case "4" -> {
                System.out.println("Виберіть два створіння для бою : ");
                entity_ch = sc.nextInt();
                entity_ch2 = sc.nextInt();
                if (entity[entity_ch].equals(entity[entity_ch2])) {
                    System.out.println("\u001B[31m" + "Створіння само з собою не може проводити бої!" + "\u001B[0m");
                    menu(obj);
                }
                else if (entity[entity_ch].getId() == -1 || entity[entity_ch2].getId() == -1) {
                    System.out.println("Одне з створінь мертве");
                    menu(entity[menu_entity_ch]);
                }
                else Batlle(entity[entity_ch], entity[entity_ch2]);{
                    menu(obj);
                }
            }
// Бій насмерть
            case "44" -> {
                System.out.println("Виберіть два героя для бою : ");
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
// Створення Нового Об'єкта
            case "5" -> CreateEntity();
// Вивести всіх на екран
            case "6" -> {
                SeeEntity();
                menu(obj);
            }
// Пересування
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
// Спати
            case "0" -> {
                obj.Sleeping();
                menu(obj);
            }
// Дефолт
            default -> System.out.println();
        }
    }
    public static void SeeEntity(){ // Вивод всіх об'єктів
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
    public static void ChangeEntity(){ // Зміна Об'єкта
        System.out.println("Виберіть: ");
        menu_entity_ch = sc.nextInt();
        if(entity[menu_entity_ch].getId() == -1){
            System.out.println("\u001B[31m" +"Об'єкт мертвий"+ "\u001B[0m" );
            ChangeEntity();
        }
        else menu(entity[menu_entity_ch]);
    }
    public static void CreateEntity(){
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
            int side = sc.nextInt();
            switch (side) {
                case 1 -> {
                    entity[choosen] = new Spider(name, hp, dmg);
                    System.out.println("Створено новий Об'єкт" + entity[choosen].getName());
                }
                case 2 -> {
                    entity[choosen] = new Steve(name, hp, dmg);
                    System.out.println("Створено новий Об'єкт");
                }
                default -> {
                    System.out.println("Створення відмінено\n");
                    menu(entity[0]);
                }
            }
            menu(entity[choosen]);
        }
    }

}
