package model.events;

import model.GameSession;
import model.crew.CrewMember;
import model.crew.StatusEffect;
import model.resources.ResourceType;

//random breakdown ship event
public class BreakdownEvent extends Event{
    public BreakdownEvent(){
        super("Breakdown");
    }

    public String apply(GameSession session) {
        boolean spent = session.getCargo().spend(ResourceType.PARTS, 5);
        if (spent) {
            return "The ship broke down. Used 5 parts to repair it.";
        }

        session.getCargo().spend(ResourceType.FUEL, 5);
        session.getCargo().spend(ResourceType.OXYGEN, 5);

        if (Math.random() < 0.5) {
            CrewMember target = randomLiving(session.getCrew());
            if (target != null) {
                target.setStatus(StatusEffect.INJURED);
                return "The ship broke down and you had no parts. "
                        + target.getName() + " was injured. Lost 5 fuel and 5 oxygen fixing the ship.";
            }
        }
        return "The ship broke down and you had no parts. Lost 5 fuel and 5 oxygen fixing the ship.";
    }
}
