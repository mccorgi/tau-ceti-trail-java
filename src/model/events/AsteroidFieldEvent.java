package model.events;

import model.GameSession;
import model.crew.CrewMember;
import model.crew.Occupation;
import model.crew.StatusEffect;
import model.resources.ResourceType;


//random event that needs pilot alive to pass or chance of breakdown
public class AsteroidFieldEvent extends Event {

    public AsteroidFieldEvent() {
        super("Asteroid Field");
    }

    @Override
    public String apply(GameSession session) {
        if (session.getCrew().hasOccupation(Occupation.PILOT)) {
            return "Your pilot navigated the asteroid field safely.";
        }

        if (Math.random() < 0.5) {
            boolean spent = session.getCargo().spend(ResourceType.PARTS, 5);
            if (spent) {
                return "Asteroids damaged the ship. Used 5 parts to repair it.";
            }
            session.getCargo().spend(ResourceType.FUEL, 5);
            CrewMember target = randomLiving(session.getCrew());
            if (target != null && Math.random() < 0.5) {
                target.setStatus(StatusEffect.INJURED);
                return "Asteroids damaged the ship. No parts left. "
                        + target.getName() + " was injured. Lost 5 fuel.";
            }
            return "Asteroids damaged the ship. No parts left. Lost 5 fuel.";
        }
        return "You scraped through the asteroid field.";
    }
}