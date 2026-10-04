package model.resources;

import java.util.EnumMap;
import java.util.Map;

//Holds the list of all resources and values in a Map
//Key is enum ResourceType and value is int
public class Cargo {
    private final Map<ResourceType, Integer> resources = new EnumMap<>(ResourceType.class);

    public Cargo(){
        for (ResourceType type : ResourceType.values()){
            resources.put(type, 0);
        }
    }

    public int get(ResourceType type){
        return resources.get(type);
    }

    public void add(ResourceType type, int amount){
        if(amount < 0){
            return;
        }
        resources.put(type, resources.get(type) + amount);
    }

    //returns false if lacking resources or amount to use is negative
    public boolean spend(ResourceType type, int amount){
        if(amount < 0 || resources.get(type) < amount){
            return false;
        }
        resources.put(type, resources.get(type) - amount);
        return true;
    }
}
