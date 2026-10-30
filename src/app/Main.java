package app;

import model.*;
import model.events.*;
import model.resources.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Expedition (Easy)");
        System.out.println("2. Settler (Medium)");
        System.out.println("3. Mercenary (Hard)");
        int choice = 0;

        //setting up the difficulty. invalid inputs are ignored
        while (choice < 1 || choice > 3) {
            System.out.print("Choose preset (1-3): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Enter a number.");
            }
        }

        GamePreset preset = GamePreset.EXPEDITION;
        if (choice == 2) {
            preset = GamePreset.SETTLER;
        } else if (choice == 3) {
            preset = GamePreset.MERCENARY;
        }

        GameSession session = new GameSession(preset);

        //Game session running with presets
        while (!session.isWon() && !session.isLose()) {
            System.out.println("\n" + session.status());
            boolean atSystem = session.getTravelIndex() == 0;
            if (atSystem) {
                System.out.print("Rest, Travel, or Trade? (r/t/d): ");
            } else {
                System.out.print("Rest or Travel? (r/t): ");
            }

            //invalid inputs are ignored
            String action = "";
            while (true) {
                action = scanner.nextLine().trim().toLowerCase();
                if (action.equals("r") || action.equals("t") || (atSystem && action.equals("d"))) {
                    break;
                }
                System.out.println("Invalid choice. Enter r, t" + (atSystem ? ", or d." : "."));
                System.out.print(atSystem
                        ? "Rest, Travel, or Trade? (r/t/d): "
                        : "Rest or Travel? (r/t): ");
            }

            if (action.equals("r")) {
                session.rest();
                System.out.println("You rested.");
            } else if (action.equals("t")) {
                session.travel();
                System.out.println("You traveled.");
                chanceEvent(session, scanner);
            } else if (action.equals("d")) {
                if (!atSystem) {
                    System.out.println("You can only trade at a star system.");
                } else {
                    trade(session, scanner);
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }

        System.out.println("\n" + session.status());
        if (session.isWon()) {
            System.out.println("You reached Tau Ceti!");
        } else {
            System.out.println("Your expedition failed.");
        }
    }

    //random chance an event happens while traveling
    private static void chanceEvent(GameSession session, Scanner scanner) {
        if (Math.random() > 0.4) {
            return;
        }

        int roll = (int) (Math.random() * 4);
        Event event;
        if (roll == 0) {
            event = new IllnessEvent();
        } else if (roll == 1) {
            event = new BreakdownEvent();
        } else if (roll == 2) {
            event = new AsteroidFieldEvent();
        } else {
            event = new PirateEvent();
        }

        System.out.println(event.apply(session));

        if (event instanceof PirateEvent) {
            System.out.print("Pay 20 credits? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            boolean pay = answer.equals("y");
            System.out.println(((PirateEvent) event).resolve(session, pay));
        }
    }

    //trading for when at outposts
    private static void trade(GameSession session, Scanner scanner) {
        System.out.println("Trade");
        System.out.println("1. Rations  (5 credits per 10)");
        System.out.println("2. Oxygen   (5 credits per 10)");
        System.out.println("3. Fuel     (10 credits per 10)");
        System.out.println("4. Parts    (10 credits per 10)");
        System.out.println("0. Cancel");

        int choice = -1;
        while (choice < 0 || choice > 4) {
            System.out.print("Choose: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Enter a number.");
            }
        }
        if (choice == 0) {
            System.out.println("Trade cancelled.");
            return;
        }

        ResourceType type;
        int cost;
        if (choice == 1) {
            type = ResourceType.RATIONS;
            cost = 5;
        } else if (choice == 2) {
            type = ResourceType.OXYGEN;
            cost = 5;
        } else if (choice == 3) {
            type = ResourceType.FUEL;
            cost = 10;
        } else {
            type = ResourceType.PARTS;
            cost = 10;
        }

        System.out.print("How many units? ");
        int unit = 0;
        if (scanner.hasNextInt()) {
            unit = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
            System.out.println("Invalid amount.");
            return;
        }
        if (unit <= 0) {
            System.out.println("Cancelled.");
            return;
        }

        int totalCost = cost * unit;
        int amount = 10 * unit;
        if (session.getCargo().get(ResourceType.CREDITS) < totalCost) {
            System.out.println("Not enough credits. Need " + totalCost + ".");
            return;
        }
        session.getCargo().spend(ResourceType.CREDITS, totalCost);
        session.getCargo().add(type, amount);
        System.out.println("Bought " + amount + " " + type + " for " + totalCost + " credits.");
    }
}