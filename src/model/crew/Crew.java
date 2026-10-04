package model.crew;

import java.util.ArrayList;
import java.util.List;


//creates the crew with array of CrewMember

public class Crew {
    private final List<CrewMember> members = new ArrayList<>();

    public void add(CrewMember member){
        members.add(member);
    }

    public List<CrewMember> getMembers(){
        return members;
    }

    //Keeps track of the number of crew alive
    public int getAliveCount(){
        int count = 0;
        for(CrewMember member : members){
            if(member.isAlive()){
                count++;
            }
        }
        return count;
    }

    //checks if crew has member with correct job for events
    public boolean hasOccupation(Occupation job){
        for(CrewMember member : members){
            if(member.isAlive() && member.getOccupation() == job){
                return true;
            }
        }
        return false;
    }

    //get the details of CrewMember that has the correct job for events
    public CrewMember memberWithOccupation(Occupation job){
        for(CrewMember member : members){
            if(member.isAlive() && member.getOccupation() == job){
                return member;
            }
        }
        return null;
    }
}
