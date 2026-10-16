package model;

import model.resources.Cargo;
import model.resources.ResourceType;

//sets up amount of resources based on difficulty
public class CargoPreset {
    public static Cargo create(GamePreset preset){
        Cargo cargo = new Cargo();

        if (preset == GamePreset.EXPEDITION) {
            cargo.add(ResourceType.RATIONS, 100);
            cargo.add(ResourceType.FUEL, 100);
            cargo.add(ResourceType.OXYGEN, 100);
            cargo.add(ResourceType.PARTS, 25);
            cargo.add(ResourceType.CREDITS, 200);
        } else if (preset == GamePreset.SETTLER) {
            cargo.add(ResourceType.RATIONS, 75);
            cargo.add(ResourceType.FUEL, 75);
            cargo.add(ResourceType.OXYGEN, 75);
            cargo.add(ResourceType.PARTS, 15);
            cargo.add(ResourceType.CREDITS, 100);
        } else if (preset == GamePreset.MERCENARY) {
            cargo.add(ResourceType.RATIONS, 50);
            cargo.add(ResourceType.FUEL, 50);
            cargo.add(ResourceType.OXYGEN, 50);
            cargo.add(ResourceType.PARTS, 7);
            cargo.add(ResourceType.CREDITS, 50);
        }
        return cargo;
    }
}
