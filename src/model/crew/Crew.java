package model.crew;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    private final List<CrewMember> members = new ArrayList<>();

    public void add(CrewMember member){
        members.add(member);
    }

    public List<CrewMember> getMembers(){
        return members;
    }
}
