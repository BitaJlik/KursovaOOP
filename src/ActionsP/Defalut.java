package ActionsP;

import Entities.Entity;
import Item.Items;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface Defalut {
    //--------------------
    default void Attack(Entity entity){}
    default void Healing(double heal){}
    default void Death(){}
    default void kamikaze(){}
    default void open(BufferedReader bufferedReader){}
    default void save( FileWriter fileWriter ) throws IOException { }
    //------------------------------
    //---------------------(ONLY STEVE)-------------------------------\\
    default double getMoney(){return 0;}
    default void setMoney(double money){}
    default double getChance(){return 1;}
    default void setChance(double chance){}
    default void addInv(Items item){ }
    default void delInv(int index){ }
    default ArrayList<Items> getInv(){ return null; }
    default void eat(Items item){}
    default void setProcess(){ }
    default int getProcess(){return 0;}
    default void clearProcess(){}
    //--------------------------------------------------------
}
