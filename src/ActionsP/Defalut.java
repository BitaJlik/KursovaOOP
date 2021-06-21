package ActionsP;

import Entities.Entity;

public interface Defalut {
    //--------------------
    default void Attack(Entity entity){}
    default void Healing(double heal){}
    default void Death(){}
    default void kamikaze(){

    }
    //------------------------------
}
