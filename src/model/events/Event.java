package model.events;

import model.GameSession;
import model.crew.Crew;
import model.crew.CrewMember;

import java.util.ArrayList;
import java.util.List;

//parent class for events
public abstract class Event {
    private final String name;

    public Event(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract String apply(GameSession session);

    //creates array of living crew and picks one at random
    protected CrewMember randomLiving(Crew crew){
        List<CrewMember> living = new ArrayList<>();
        for (CrewMember x : crew.getMembers()){
            if (x.isAlive()){
                living.add(x);
            }
        }
        if(living.isEmpty()){
            return null;
        }
        return living.get((int) (Math.random() * living.size()));
    }

    protected String noEffect(){
        return "Nothing happened.";
    }
}
