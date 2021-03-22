package oop;

public class Home {
    public void checkSide(Entity entity){
        if(entity.getId() == 1)
            System.out.println("Это поук");
        else if (entity.getId() == 2)
            System.out.println("Это гондон");
    }
}
