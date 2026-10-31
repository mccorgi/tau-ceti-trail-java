package model;

import model.crew.*;
import model.resources.*;
import java.util.Arrays;
import java.util.List;

//sets up game session based off difficulty chosen
//Holds one instance of crew, cargo, and route progress
public class GameSession {
    private final Crew crew;
    private final Cargo cargo;

    //create own class if expanding game
    private final List<Location> stops = Arrays.asList(
            Location.SOL,
            Location.EPSILON_ERIDANI,
            Location.ALPHA_CENTAURI,
            Location.BARNARDS_STAR,
            Location.TAU_CETI
    );

    private int locationIndex = 0;
    private int travelIndex = 0;
    private final int TRAVEL_PER = 10;

    public GameSession(GamePreset preset){
        this.crew = CrewPreset.create(preset);
        this.cargo = CargoPreset.create(preset);
    }

    public String status() {
        return "Crew alive: " + crew.getAliveCount()
                + "\nRations: " + cargo.get(ResourceType.RATIONS)
                + "\nFuel: " + cargo.get(ResourceType.FUEL)
                + "\nOxygen: " + cargo.get(ResourceType.OXYGEN)
                + "\nParts: " + cargo.get(ResourceType.PARTS)
                + "\nCredits: " + cargo.get(ResourceType.CREDITS)
                + "\nLocation: " + stops.get(locationIndex).getDisplayName()
                +"\nStep: " + travelIndex + "/" + TRAVEL_PER;
    }


    //rest will spend rations and oxygen per turn
    //*****need to create system for chance of recovery
    public void rest(){
        int alive = crew.getAliveCount();
        cargo.spend(ResourceType.RATIONS, alive);
        cargo.spend(ResourceType.OXYGEN, alive);

        //rest heals sick and injured when resting
        if (crew.hasOccupation(Occupation.MEDIC)) {
            for (CrewMember member : crew.getMembers()) {
                if (member.isAlive() &&
                        (member.getStatus() == StatusEffect.SICK ||
                                member.getStatus() == StatusEffect.INJURED)) {
                    member.setStatus(StatusEffect.HEALTHY);
                }
            }
        }
    }

    //travel will spend rations, oxygen, fuel per turn
    public void travel(){
        int alive = crew.getAliveCount();
        cargo.spend(ResourceType.RATIONS, alive);
        cargo.spend(ResourceType.OXYGEN, alive);
        cargo.spend(ResourceType.FUEL, 5);

        //ticks down for when injured crew dies
        for (CrewMember c : crew.getMembers()) {
            if (c.isAlive()) {
                c.tickStatus();
            }
        }
        if(locationIndex >=  stops.size() - 1){
            return;
        }

        travelIndex ++;
        if(travelIndex >= TRAVEL_PER){
            travelIndex = 0;
            locationIndex++;
        }
    }

    //game over implmentation
    public boolean isLose() {
        return crew.getAliveCount() == 0
                || cargo.get(ResourceType.RATIONS) == 0
                || cargo.get(ResourceType.OXYGEN) == 0
                || cargo.get(ResourceType.FUEL) == 0;
    }

    // win condition by reaching final location with 1 crew alive
    public boolean isWon(){
        return locationIndex == stops.size() - 1 && crew.getAliveCount() > 0;
    }

    public Crew getCrew() {
        return crew;
    }

    public Cargo getCargo(){
        return cargo;
    }

    public int getTravelIndex() {
        return travelIndex;
    }
}
