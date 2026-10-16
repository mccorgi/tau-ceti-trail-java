package app;

import model.*;

public class Main {
    public static void main(String[] args){
        GameSession session = new GameSession(GamePreset.EXPEDITION);
        System.out.println(session.status());
        session.rest();
        System.out.println(session.status());

        while(!session.isLose()){
            session.rest();
            System.out.println(session.status());
        }

    }
}
