package model.events;

import model.GameSession;
import model.crew.CrewMember;
import model.crew.StatusEffect;

//random illness event
public class IllnessEvent extends Event{
    public IllnessEvent(){
        super("Illness");
    }

    public String apply(GameSession session){
        CrewMember target = randomLiving(session.getCrew());
        if(target == null){
            return noEffect();
        }
        target.setStatus(StatusEffect.SICK);
        return target.getName() + " fell ill.";
    }
}
