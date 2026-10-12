package model;

import model.resources.Cargo;
import model.resources.ResourceType;
import model.GamePreset;

public class LoadoutPreset {
    public static Cargo create(GamePreset preset){
        Cargo loadout = new Cargo();

        if (preset == GamePreset.EXPEDITION) {
            loadout.add(ResourceType.RATIONS, 100);
            loadout.add(ResourceType.FUEL, 100);
            loadout.add(ResourceType.OXYGEN, 100);
            loadout.add(ResourceType.PARTS, 25);
            loadout.add(ResourceType.CREDITS, 200);
        } else if (preset == GamePreset.SETTLER) {
            loadout.add(ResourceType.RATIONS, 75);
            loadout.add(ResourceType.FUEL, 75);
            loadout.add(ResourceType.OXYGEN, 75);
            loadout.add(ResourceType.PARTS, 15);
            loadout.add(ResourceType.CREDITS, 100);
        } else if (preset == GamePreset.MERCENARY) {
            loadout.add(ResourceType.RATIONS, 50);
            loadout.add(ResourceType.FUEL, 50);
            loadout.add(ResourceType.OXYGEN, 50);
            loadout.add(ResourceType.PARTS, 7);
            loadout.add(ResourceType.CREDITS, 50);
        }
        return loadout;
    }
}
