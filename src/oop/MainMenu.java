package oop;

import oop.Entities.Entity;
import oop.Entities.Steve;

import java.util.stream.IntStream;

import static oop.Main.sc;
import static oop.Main.world;

public class MainMenu {
    static int sizeWorld;
    static int sizeCave;
    static int sizeHome;
    public static void menu(){
        update();
        if(sizeHome == 0 && sizeCave == 0 && sizeWorld == 0) {
            System.out.println("В програмі ні одного  об'єкту\nАвтоматичний виклик створення");
            createEntity();
        }
        else {
            if(sizeHome != 0) menu((Entity) world.home.getHome().get(0));
            else if(sizeCave != 0) menu((Entity) world.cave.getCave().get(0));
            else menu((Entity) world.getWorld().get(0));
        }
    }
    public static void menu(Entity obj){
        update();
        if(obj instanceof Steve) {
            if(sizeWorld == 0 && sizeHome == 0 && sizeCave == 0){
                System.out.println("В програмі ні одного  об'єкту\nАвтоматичний виклик створення");
                createEntity();}
            else
                menulistSteve(obj);
        }
        else menulistSpider(obj);
        String menu = sc.next();
        switch (menu){
            case  "1" -> {
                System.out.println(obj);
                System.out.println("Змінити характеристики об'єкта? [1] - Так \t [2] - Ні");
                String ch = sc.next();
                if(ch.equals("1"))changeEntity(obj);
                menu(obj); }
            case   "2" -> changeEntityFromList();
            case   "3" -> createEntity();
            case  "33" -> deleteEntity(obj);
            case   "4" -> seeEntity(obj);
            case   "5" -> moveFromEntity(obj);
            case   "6" -> inventory(obj);
            case   "7" -> dig(obj);
            case  "-1" -> System.out.println("Кінець програми!");
            default -> menu(obj);
        }
    }
    public static void inventory(Entity obj){
        if(obj instanceof Steve){
            System.out.println("====[-1 Назад]================| Inventory |===============[-2 Продати предмети]====");
            if(!(obj.getInv().isEmpty()) ){
                seeInventory(obj);
            }
            else System.out.println("Пусто 0_0");
            String choose = sc.next();
            if(choose.equals("-1")){
                menu(obj);
            }
            else if(choose.equals("-2")){
                sellItem(obj);
            }
            else {
                int chooseItem = -1;
                try{
                    chooseItem = Integer.parseInt(choose);
                }
                catch (NumberFormatException e){
                    System.out.println("Помилка");
                }
                if(chooseItem >= obj.getInv().size()){
                    inventory(obj);
                }
                else if(chooseItem < 0){
                    inventory(obj);
                }
                else {
                    System.out.println("\033[0;36m"+obj.getInv().get(chooseItem)+"\033[0m");
                    inventory(obj);
                }
            }
        }
        menu(obj);
    }
    public static void dig(Entity obj){
        if(obj instanceof Steve) {
            System.out.println("Куди йдемо? [1] - Шахта  [2] - В середину острова");
            String choose = sc.next();
            switch (choose) {
                case "1" -> {
                    world.cave.dig(obj);
                    menu(obj);
                }
                case "2" ->{
                    world.dig(obj);
                    menu(obj);
                }
            }
        }
    }
    public static void sellItem(Entity obj){
        System.out.println("====[-1 Назад]================\033[0;31m Що продати?\033[0m ===============[-2  Продати все    ]====");
        seeInventory(obj);
        String ch = sc.next();
        try{
            int choose = Integer.parseInt(ch);
            if(choose == -1) inventory(obj);

            else if(choose == -2) world.home.sellAll(obj);

            else if(choose >= obj.getInv().size() || choose < 0){
                sellItem(obj);
            }
            else world.home.sell(obj,choose);
        }
        catch (NumberFormatException e){
            System.out.println("Помилка");
            sellItem(obj);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("indxofexp");
        }
        menu(obj);
    }
    public static void seeInventory(Entity obj){
        for(int i = 0;i < obj.getInv().size();++i){
            System.out.println("["+i+"] " + obj.getInv().get(i));
        }
    }
    public static void changeEntityFromList() {
        update();
        System.out.println("Звідки вибрати ?" +
                "\n[1] - Дім\t" +"("+ world.home.getHome().size() +")" +
                "\n[2] - Шахта\t" +"(" + world.cave.getCave().size() + ")" +
                "\n[3] - Світ\t" + "(" + world.getWorld().size() + ")");
        String ch = sc.next();
        switch (ch){
            case "1" -> {
                if(sizeHome == 0){
                    changeEntityFromList();
                }else seeHouse();
                String chose = sc.next();
                try{
                    int pos = Integer.parseInt(chose);
                    menu((Entity) world.home.getHome().get(pos));
                }
                catch (NumberFormatException | IndexOutOfBoundsException e ){
                    System.out.println("Помилка");
                }
            }
            case "2" ->{
                if(sizeCave == 0){
                    changeEntityFromList();
                }else seeCave();
                String chose = sc.next();
                try{
                    int pos = Integer.parseInt(chose);
                    menu((Entity) world.cave.getCave().get(pos));
                }
                catch (NumberFormatException | IndexOutOfBoundsException  e ){
                    System.out.println("Помилка");
                }
            }
            case "3" ->{
                if(sizeWorld == 0){
                    changeEntityFromList();
                }else seeWorld();
                System.out.print("Виберіть:  ");
                String chose = sc.next();
                try{
                    int pos = Integer.parseInt(chose);

                    menu((Entity) world.getWorld().get(pos));
                }
                catch (NumberFormatException | IndexOutOfBoundsException e ){
                    System.out.println("Помилка");
                }
            }
            default ->{
                System.out.println("Не вибрано.");
                menu();
            }
        }
    }
    public static void changeEntity(Entity obj){
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
    public static void createEntity(){
        String what = "1";
        if(!(world.isAllWorldEmpty())) {
            System.out.println("[1] - Створити   чи  [2] - Скопіювати?");
            what = sc.next();
        }
        switch (what){
            case "2" -> copyEntity();
            case "1" ->{
                System.out.println("""
                        Куди створити?
                        [1] - Дім
                        [2] - Шахта
                        [3] - Світ""");
                String where = sc.next();
                System.out.println("""
                        Кого створити?
                        [1] - Стів Початківець
                        [2] - Стів Бувалий
                        [3] - Стів Профі""");
                String choose = sc.next();
                System.out.println("Введіть Ім'я");
                String name = sc.next();
                System.out.println("Введіть Hp");
                double Hp = sc.nextDouble();
                System.out.println("Введіть Dmg");
                double Dmg = sc.nextDouble();
                switch (choose){
                    case "1" ->{
                        switch (where){
                            case "1" -> {
                                Steve temp = new oop.Entities.Steve(name, Hp, Dmg);
                                world.home.addToHome(temp);
                                menu((Entity) world.home.getHome().get(world.home.getHome().lastIndexOf(temp)));
                            }
                            case "2" -> {
                                Steve temp = new oop.Entities.Steve(name, Hp, Dmg);
                                world.cave.addToCave(temp);
                                menu((Entity) world.cave.getCave().get(world.cave.getCave().lastIndexOf(temp)));
                            }
                            case "3" -> {
                                Steve temp = new oop.Entities.Steve(name, Hp, Dmg);
                                world.addToWorld(temp);
                                menu((Entity) world.getWorld().get(world.getWorld().lastIndexOf(temp)));
                            }
                        }
                    }
                    case "2" ->{
                        double chance = Math.round(1 + Math.random() * 5);
                        switch (where){
                            case "1" -> {
                                Entity temp = new oop.Entities.SteveMiddle(name, Hp, Dmg,chance);
                                world.home.addToHome(temp);
                                menu((Entity) world.home.getHome().get(world.home.getHome().lastIndexOf(temp)));
                            }
                            case "2" -> {
                                Entity temp = new oop.Entities.SteveMiddle(name, Hp, Dmg,chance);
                                world.cave.addToCave(temp);
                                menu((Entity) world.cave.getCave().get(world.cave.getCave().lastIndexOf(temp)));
                            }
                            case "3" -> {
                                Entity temp = new oop.Entities.SteveMiddle(name, Hp, Dmg,chance);
                                world.addToWorld(temp);
                                menu((Entity) world.getWorld().get(world.getWorld().lastIndexOf(temp)));

                            }
                        }
                    }
                    case "3" ->{
                        double chance = Math.round(1 + Math.random() * 5);
                        int power = (int) Math.round( Math.random() * 40);
                        switch (where){
                            case "1" -> {
                                Entity temp = new oop.Entities.StevePro(name, Hp, Dmg,chance,power);
                                world.home.addToHome(temp);
                                menu((Entity) world.home.getHome().get(world.home.getHome().lastIndexOf(temp)));
                            }
                            case "2" -> {
                                Entity temp = new oop.Entities.StevePro(name, Hp, Dmg,chance,power);
                                world.cave.addToCave(temp);
                                menu((Entity) world.cave.getCave().get(world.cave.getCave().lastIndexOf(temp)));
                            }
                            case "3" -> {
                                Entity temp = new oop.Entities.StevePro(name, Hp, Dmg,chance,power);
                                world.addToWorld(temp);
                                menu((Entity) world.getWorld().get(world.getWorld().lastIndexOf(temp)));
                            }
                        }
                    }
                    default -> createEntity();
                }
                update();
            }
        }
    }
    public static void moveFromEntity(Entity obj){
        System.out.println("Звідки перемістити ?" +
                "\n[1] - Дім\t" +"("+ world.home.getHome().size() +")" +
                "\n[2] - Шахта\t" +"(" + world.cave.getCave().size() + ")" +
                "\n[3] - Світ\t" + "(" + world.getWorld().size() + ")");
        String choose = sc.next();
        try {
            switch (choose) {
                case "1" -> {
                    System.out.println("Кого переміщуємо?");
                    seeHouse();
                    String p = sc.next();
                    System.out.println("""
                            Куди перемістити?
                            [2] - Шахта
                            [3] - Світ""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh) {
                            case "2" -> {
                                Main.world.cave.addToCave(((Entity) world.home.getHome().get(pos)));
                                ((Entity)world.home.getHome().get(pos)).setPosition("cave");
                                Main.world.home.delToHome(pos);
                            }
                            case "3" -> {
                                Main.world.addToWorld(((Entity) Main.world.home.getHome().get(pos)));
                                ((Entity)world.home.getHome().get(pos)).setPosition("world");
                                Main.world.home.delToHome(pos);
                            }
                        }
                    } catch (NumberFormatException  e) {
                        System.out.println("Не введено параметр!");
                    }
                }
                case "2" -> {
                    System.out.println("Кого переміщуємо?");
                    seeCave();
                    String p = sc.next();
                    System.out.println("""
                            Куди перемістити?
                            [1] - Дім
                            [3] - Світ""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh) {
                            case "1" -> {
                                world.home.addToHome(((Entity) world.cave.getCave().get(pos)));
                                ((Entity)world.cave.getCave().get(pos)).setPosition("home");
                                world.cave.delToCave(pos);
                            }
                            case "3" -> {
                                world.addToWorld(((Entity) world.cave.getCave().get(pos)));
                                ((Entity)world.cave.getCave().get(pos)).setPosition("world");
                                world.cave.delToCave(pos);
                            }
                        }
                    } catch (NumberFormatException  e) {
                        System.out.println("Не введено параметр!");
                    }
                }
                case "3" -> {
                    System.out.println("Кого переміщуємо?");
                    seeWorld();
                    String p = sc.next();
                    System.out.println("""
                            Куди перемістити?
                            [1] - Дім
                            [2] - Шахта""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh) {
                            case "1" -> {
                                world.home.addToHome(((Entity) world.getWorld().get(pos)));
                                ((Entity)world.getWorld().get(pos)).setPosition("home");
                                world.delToWorld(pos);
                            }
                            case "2" -> {
                                world.cave.addToCave(((Entity) world.getWorld().get(pos)));
                                ((Entity)world.getWorld().get(pos)).setPosition("cave");
                                world.delToWorld(pos);
                            }
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Не введено параметр!");
                    }
                }
            }
        }
        catch ( ArrayIndexOutOfBoundsException exception){
            System.out.println("Помилка");
        }
        update();
        menu(obj);
    }
    /*public static void moveFromEntity(Entity obj){
        int check = 0;
        for (int i = 0;i < world.getWorld().size();++i ) {
            if (((Entity) world.getWorld().get(i)).getId() == obj.getId()) {
                check = 1;
                break;
            }
            if (((Entity) world.home.getHome().get(i)).getId() == obj.getId()) {
                check = 2;
                break;
            }
            if (((Entity) world.cave.getCave().get(i)).getId() == obj.getId()) {
                check = 3;
                break;
            }
        }
        switch (check){
            case 1 ->{
                System.out.println("""
                            Куди перемістити?
                            [1] - Дім
                            [2] - Шахта""");
                String wh = sc.next();
                try {
                    switch (wh) {
                        case "1" -> {
                            world.home.addToHome(obj);
                            world.delToWorld(obj);
                        }
                        case "2" -> {
                            world.cave.addToCave(obj);
                            world.delToWorld(obj);
                        }
                        default -> System.out.println("Не вибрано");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не введено параметр!");
                }
            }
            case 2 ->{
                System.out.println("""
                            Куди перемістити?
                            [2] - Шахта
                            [3] - Світ""");
                String wh = sc.next();
                try {
                    switch (wh) {
                        case "2" -> {
                            Main.world.cave.addToCave(obj);
                            Main.world.home.delToHome(obj);
                        }
                        case "3" -> {
                            Main.world.addToWorld(obj);
                            Main.world.home.delToHome(obj);
                            System.out.println("Об'єкт переміщено");
                        }
                        default -> System.out.println("Не вибрано");
                    }
                } catch (NumberFormatException  e) {
                    System.out.println("Не введено параметр!");
                }
            }
            case 3 ->{
                System.out.println("""
                            Куди перемістити?
                            [1] - Дім
                            [3] - Світ""");
                String wh = sc.next();
                try {
                    switch (wh) {
                        case "1" -> {
                            world.home.addToHome(obj);
                            world.cave.delToCave(obj);
                        }
                        case "3" -> {
                            world.addToWorld(obj);
                            world.cave.delToCave(obj);
                        }
                    }
                } catch (NumberFormatException  e) {
                    System.out.println("Не введено параметр!");
                }
            }
            default -> menu(obj);
        }
        menu(obj);
    }*/
    public static void copyEntity(){
        System.out.println("Звідки копіювати ?" +
                "\n[1] - Дім\t" +"("+ world.home.getHome().size() +")" +
                "\n[2] - Шахта\t" +"(" + world.cave.getCave().size() + ")" +
                "\n[3] - Світ\t" + "(" + world.getWorld().size() + ")");
        String choose = sc.next();
        try{
            switch (choose){
                case "1" ->{
                    seeHouse();
                    System.out.print("Кого копіюємо?");
                    String p = sc.next();
                    System.out.println("""
                            Куди ?
                            [1] - Дім
                            [2] - Шахта
                            [3] - Світ""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh){
                            case "1" -> Main.world.home.addToHome(((Entity)Main.world.home.getHome().get(pos)).clone());
                            case "2" -> Main.world.cave.addToCave(((Entity) Main.world.home.getHome().get(pos)).clone());
                            case "3" -> Main.world.addToWorld(((Entity) Main.world.home.getHome().get(pos)).clone());
                        }
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e){
                        System.out.println("Помилка");
                    }
                }
                case "2" ->{
                    seeCave();
                    System.out.print("Кого копіюємо?");
                    String p = sc.next();
                    System.out.println("""
                            Куди ?
                            [1] - Дім
                            [2] - Шахта
                            [3] - Світ""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh){
                            case "1" -> Main.world.home.addToHome(((Entity) world.cave.getCave().get(pos)).clone());
                            case "2" -> Main.world.cave.addToCave(((Entity) world.cave.getCave().get(pos)).clone());
                            case "3" -> Main.world.addToWorld(((Entity) world.cave.getCave().get(pos)).clone());
                        }
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                        System.out.println("Помилка");
                    }
                }
                case "3" ->{
                    seeWorld();
                    System.out.print("Кого копіюємо?");
                    String p = sc.next();
                    System.out.println("""
                            Куди ?
                            [1] - Дім
                            [2] - Шахта
                            [3] - Світ""");
                    String wh = sc.next();
                    try {
                        int pos = Integer.parseInt(p);
                        switch (wh){
                            case "1" -> Main.world.home.addToHome(((Entity) world.getWorld().get(pos)).clone());
                            case "2" -> Main.world.cave.addToCave(((Entity) world.getWorld().get(pos)).clone());
                            case "3" -> Main.world.addToWorld(((Entity)     world.getWorld().get(pos)).clone());
                        }
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                        System.out.println("Помилка");
                    }
                }
            }
        }
        catch (CloneNotSupportedException | IndexOutOfBoundsException | NumberFormatException exception){
            System.out.println("Помилка");
            menu();
        }
        menu();
    }
    public static void deleteEntity(Entity obj){
        System.out.println("Звідки видалити?" +
                "\n[1] - Дім\t" +"("+    world.home.getHome().size() +")" +
                "\n[2] - Шахта\t" +"(" + world.cave.getCave().size() + ")" +
                "\n[3] - Світ\t" + "(" + world.getWorld().size() + ")");
        String where = sc.next();
        switch (where) {
            case "1" -> seeHouse();
            case "2" -> seeCave();
            case "3" -> seeWorld();
            default -> deleteEntity(obj);
        }
        System.out.println("Кого видалити?");
        try{
            int index = Integer.parseInt(sc.next());
            switch (where) {
                case "1" -> world.home.delToHome(index);
                case "2" -> world.cave.delToCave(index);
                case "3" -> world.delToWorld(index);
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Error");
            deleteEntity(obj);
        }
        menu(obj);
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
    public static void deathBatlle(Entity FrstEntity,Entity ScndEntity) {
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
    public static void seeEntity(Entity obj){
        update();
        System.out.println("Що переглянемо?" +
                "\n[1] - Дім\t" +"("+ world.home.getHome().size() +")" +
                "\n[2] - Шахта\t" +"(" + world.cave.getCave().size() + ")" +
                "\n[3] - Світ\t" + "(" + world.getWorld().size() + ")");
        String choose = sc.next();
        switch (choose){
            case "1" -> seeHouse();
            case "2" -> seeCave();
            case "3" -> seeWorld();
        }
        menu(obj);
    }
    public static void seeHouse(){ IntStream.range(0, world.home.getHome().size()).mapToObj(i -> "[" + i + "] " + world.home.getHome().get(i)).forEach(System.out::println); }
    public static void seeCave(){ IntStream.range(0,  world.cave.getCave().size()).mapToObj(i -> "[" + i + "] " + world.cave.getCave().get(i)).forEach(System.out::println); }
    public static void seeWorld(){ IntStream.range(0,     world.getWorld().size()).mapToObj(i -> "[" + i + "] " + world.getWorld().get(i)).forEach(System.out::println); }
    public static void update(){
        sizeHome = world.home.getHome().size();
        sizeCave = world.cave.getCave().size();
        sizeWorld = world.getWorld().size();
        if (sizeWorld == 0) world.clearWorld();
        if(sizeCave == 0) world.cave.clearCave();
        if(sizeHome == 0) world.home.clearHome();
        MainController.size += sizeCave + sizeWorld +sizeHome;
    }
    public static void menulistSpider(Entity obj){
        System.out.println(
                "\nВибрано : " + obj.getName() +
                        "\n [ 1] - Вивести на екран інформацію об'єкта" +
                        "\n [ 2] - Вибрати іншого " +
                        "\n [ 3] - Бій між об'єктами" +
                        "\n [ 4] - Створити нового   " +"[55] - Видалити" +
                        "\n [ 5] - Вивести всіх " +
                        "\n [ 6] - Пересування ");
    }
    public static void menulistSteve(Entity obj){
        System.out.println(
                        "\nВибрано :  " + obj.getName() +
                        "\n [ 1] - Вивести на екран інформацію об'єкта" +
                        "\n [ 2] - Вибрати іншого " +
                        "\n [ 3] - Створити нового   " +"[33] - Видалити" +
                        "\n [ 4] - Вивести всіх " +
                        "\n [ 5] - Пересування " +
                        "\n [ 6] - Інвентар " +
                        "\n [ 7] - Добути ресурси " );
    }
    public static void cls(){ System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n*2\\"); }
}