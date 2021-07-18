package battleship3;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    protected final String name;
    protected final int length;

    ShipType(String name, int length) {
        this.name = name;
        this.length = length;
    }
}