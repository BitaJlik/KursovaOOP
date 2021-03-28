package oop;

import java.util.Comparator;

public class EntityHp implements Comparator<Entity> {
    @Override
    public int compare(Entity entity,Entity entity1){
        return entity.compareTo(entity1);
    }
}
