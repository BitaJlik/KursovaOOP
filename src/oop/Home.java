package oop;

import oop.Items.Items;

public class Home {
    private double x;
    private double y;
    private final double hp = 3;
//----------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //----------------------------------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
//----------------------------
    public void Start(Entity entity) {
        if (entity.getClass() == Steve.class) {
            Healing(entity);
        }
        else{
            entity.setHp(entity.getHp() - hp);
        }
    }
//-----------------------------
    private void Healing(Entity entity){ entity.setHp(entity.getHp() + hp); }
    //-----------------------
    public  void Sell(int choose, Entity entity){
        if(Items.items.get(choose).getId()!=-1){
            if(Items.items.get(choose).getAmount() <= 0 ){
                System.out.println("\u001B[35m"+"Немає в наявності!"+"\u001B[0m");
                Progress.Waiting(1500);
                MainMenu.cls();
            }
            else {
                System.out.println("Введіть кількість: ");
                String amo = MainMenu.sc.next();
                int amount = 1;
                try {
                    amount = Integer.parseInt(amo);
                    System.out.println("\u001B[33m"+"Продано " + Items.items.get(choose).getName()+"\u001B[0m");
                    entity.setMoney(entity.getMoney() + Items.items.get(choose).getPrice() * amount);
                    Items.items.get(choose).setAmount(-1 * amount);
                }
                catch (NumberFormatException e){
                    System.out.println("Неправильне введення!");
                    Sell(choose,entity);
                }

            }
        }
    }

}
