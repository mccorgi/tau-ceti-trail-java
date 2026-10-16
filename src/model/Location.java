package model;

//key locations with 5 locations
//ALPHA_CENTAURI will be midpoint in case of expansion
//if expanding fully, add description to each location, custom trade
public enum Location {
    SOL("Sol"),
    EPSILON_ERIDANI("Epsilon Eridani"),
    ALPHA_CENTAURI("Alpha Centauri"),
    BARNARDS_STAR("Barnard's Star"),
    TAU_CETI("Tau Ceti");

    private final String displayName;

    Location(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}