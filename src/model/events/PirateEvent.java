package model.events;

import model.GameSession;
import model.crew.CrewMember;
import model.crew.Occupation;
import model.crew.StatusEffect;
import model.resources.ResourceType;

public class PirateEvent extends Event{
    public PirateEvent(){
        super("Pirates");
    }

    public String apply(GameSession session){
        return "Pirates demand a toll of 20 credits";
    }

    //payoff and pirates go away
    //*** make this interface????
    public String resolve(GameSession session, boolean pay) {
        if (pay) {
            boolean paid = session.getCargo().spend(ResourceType.CREDITS, 20);
            if (paid) {
                return "You paid 20 credits.";
            }
        }

        //if you didn't have enough credit, it continues to fight
        String prefix = pay ? "You tried to pay, but did not have enough credits.\n" : "";

        //advantage for having security and fighting off pirates
        boolean hasSecurity = session.getCrew().hasOccupation(Occupation.SECURITY);
        double chance = hasSecurity ? 0.7 : 0.3;
        boolean foughtOff = Math.random() < chance;

        if (foughtOff) {
            return prefix + (hasSecurity
                    ? "Security helped fight off the pirates."
                    : "You fought off the pirates.");
        }

        //random chance for getting crew member injured
        CrewMember target = randomLiving(session.getCrew());
        if (target != null) {
            target.setStatus(StatusEffect.INJURED);
            return prefix + target.getName() + " was injured in the fight.";
        }

        //prefix depends on the event
        return prefix + "The pirates left.";
    }

}
