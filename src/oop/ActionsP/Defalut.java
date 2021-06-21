package oop.ActionsP;

public interface Defalut {
    //--------------------
    default void Attack(){}
    default void Healing(double heal){}
    default void Death(){}
    //default void Sleeping(){}
    //------------------------------
    default void moveUp(){ }
    default void moveDown(){ }
    default void moveLeft(){ }
    default void moveRight(){ }
}
