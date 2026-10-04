package model;

import model.crew.Crew;
import model.crew.CrewMember;
import model.crew.Occupation;


//sets up what crew is setup based on which mode was selected
public class CrewPreset {
    public static Crew create(GamePreset preset){
        Crew crew = new Crew();

        if(preset == GamePreset.EXPEDITION){
            crew.add(new CrewMember("Cpt. Maverick", Occupation.PILOT));
            crew.add(new CrewMember("Lt. Einstein", Occupation.ENGINEER));
            crew.add(new CrewMember("Dr. Hippocrates", Occupation.MEDIC));
            crew.add(new CrewMember("Sgt. Payne", Occupation.SECURITY));
            crew.add(new CrewMember("Dr. Einstein", Occupation.SCIENTIST));
        }else if(preset == GamePreset.SETTLER){
            crew.add(new CrewMember("Otto Pilot", Occupation.PILOT));
            crew.add(new CrewMember("Anne Jean", Occupation.ENGINEER));
            crew.add(new CrewMember("Courtney Kit", Occupation.MEDIC));
            crew.add(new CrewMember("Adam Bomb", Occupation.SCIENTIST));
        }else if(preset == GamePreset.MERCENARY){
            crew.add(new CrewMember("Mefly", Occupation.PILOT));
            crew.add(new CrewMember("Yufix", Occupation.ENGINEER));
            crew.add(new CrewMember("Ishot", Occupation.SECURITY));
        }
        return crew;
    }

}
