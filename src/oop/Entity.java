package oop;

public class Entity {
    private String name;
    private int id ;
    private double hp;
    private double damage;
    //--------------------
    public void Attack(){}
    public void Healing(double heal){}
    public void Death(){}
    public void Sleeping(){}
    //------------------------------ Геттери \ Сеттери
    public String getName() {
        return name;
    }
    public void setName(String name){}
    //-----------------
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //-----------------
    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    //------------------
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return hp == entity.hp &&
                name.equals(entity.name) &&
                id == entity.id &&
                damage == entity.damage;
    }
}