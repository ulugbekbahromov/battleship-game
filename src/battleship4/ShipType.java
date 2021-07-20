package battleship4;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    protected final String name;
    protected final int length;
    private Coordinate first;
    private Coordinate second;

    ShipType(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public void setCoordinates(Coordinate first, Coordinate second) {
        if (
                first.getRow() != second.getRow() && first.getColumn() != second.getColumn() ||
                        first.getRow() == second.getRow() && first.getColumn() == second.getColumn()
        ) {
            throw new IllegalArgumentException();
        }

        if (first.getRow() < second.getRow() || first.getColumn() < second.getColumn()) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    public boolean isSank(Field field) {
        for (int i = first.getRow(); i <= second.getRow(); i++) {
            for (int j = first.getColumn(); j <= second.getColumn(); j++) {
                if (!field.getCell(new Coordinate(i, j)).contains(CellState.HIT)) {
                    return false;
                }
            }
        }

        return true;
    }
}