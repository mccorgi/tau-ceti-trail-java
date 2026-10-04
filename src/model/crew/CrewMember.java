package model.crew;

//creates an object for each crew member
public class CrewMember {
    private String name;
    private Occupation occupation;
    private int health;
    private StatusEffect status;

    public CrewMember(String name, Occupation occupation){
        this.name = name;
        this.occupation = occupation;
        health = 100;
        status = StatusEffect.HEALTHY;
    }

    //getters for all private fields
    public String getName(){return name;}
    public Occupation getOccupation(){return occupation;}
    public int getHealth(){return health;}
    public StatusEffect getStatus(){return status;}

    //used for quick checks on dead or alive status on crew
    public boolean isAlive(){
        return status != StatusEffect.DEAD && health > 0;
    }


}
