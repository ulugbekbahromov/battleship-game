package battleship5;

import java.util.Scanner;

public class ShipPlacer {
    private Field field;
    private Scanner scanner = new Scanner(System.in);
    private Ship aircraftCarrier;
    private Ship battleship;
    private Ship submarine;
    private Ship cruiser;
    private Ship destroyer;

    private boolean isAircraftCarrierSunk = false;
    private boolean isBattleshipSunk = false;
    private boolean isSubmarineSunk = false;
    private boolean isCruiserSunk = false;
    private boolean isDestroyerSunk = false;

    public boolean isSunk(ShipType shipType) {
        if (shipType == ShipType.AIRCRAFT_CARRIER) {
            if (!isAircraftCarrierSunk && battleship.isSunk()) {
                isAircraftCarrierSunk = true;
                return true;
            }

            return false;
        } else if (shipType == ShipType.BATTLESHIP) {
            if (!isBattleshipSunk && aircraftCarrier.isSunk()) {
                isBattleshipSunk = true;
                return true;
            }

            return false;
        } else if (shipType == ShipType.SUBMARINE) {
            if (!isSubmarineSunk && submarine.isSunk()) {
                isSubmarineSunk = true;
                return true;
            }

            return false;
        } else if (shipType == ShipType.CRUISER) {
            if (!isCruiserSunk && cruiser.isSunk()) {
                isCruiserSunk = true;
                return true;
            }

            return false;
        } else if (shipType == ShipType.DESTROYER) {
            if (!isDestroyerSunk && destroyer.isSunk()) {
                isDestroyerSunk = true;
                return true;
            }

            return false;
        } else {
            throw new IllegalArgumentException("There is no such ship");
        }
    }

    public ShipPlacer(Field field) {
        this.field = field;
    }

    public void place(ShipType shipType) {
        System.out.printf("Enter the coordinates of the %s (%d cells):", shipType.name, shipType.length);
        System.out.println();

        String[] coordinates;
        Coordinate first;
        Coordinate second;
        do {
            while ((coordinates = coordinates = scanner.nextLine().split(" ")).length != 2);
            first = new Coordinate(coordinates[0]);
            second = new Coordinate(coordinates[1]);
        } while (coordinates.length != 2 || !canBePlaced(first, second, shipType));

        Ship ship = create(shipType, first, second);
        ship.place();
    }

    public Ship create(ShipType shipType, Coordinate start, Coordinate end) {
        if (shipType == ShipType.AIRCRAFT_CARRIER) {
            aircraftCarrier = new AircraftCarrier(field, start, end);
            return aircraftCarrier;
        } else if (shipType == ShipType.BATTLESHIP) {
            battleship = new Battleship(field, start, end);
            return battleship;
        } else if (shipType == ShipType.SUBMARINE) {
            submarine = new Submarine(field, start, end);
            return submarine;
        } else if (shipType == ShipType.CRUISER) {
            cruiser = new Cruiser(field, start, end);
            return cruiser;
        } else if (shipType == ShipType.DESTROYER) {
            destroyer = new Destroyer(field, start, end);
            return destroyer;
        } else {
            throw new IllegalArgumentException("There is no such ship");
        }
    }

    public boolean canBePlaced(Coordinate first, Coordinate second, ShipType shipType) {
        if (!isLegalForm(first, second)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        } else if (getLength(first, second) != shipType.length) {
            System.out.println("Error! Wrong length of the " + shipType.name + "! Try again:");
            return false;
        } else if (!isLegalRoom(first, second)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }

        return true;
    }

    public int getLength(Coordinate first, Coordinate second) {
        if (first.getRow() == second.getRow()) {
            return Math.abs(first.getColumn() - second.getColumn()) + 1;
        } else if (first.getColumn() == second.getColumn()) {
            return Math.abs(first.getRow() - second.getRow()) + 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isLegalForm(Coordinate first, Coordinate second) {
        if (first.getRow() != second.getRow() && first.getColumn() != second.getColumn()) {
            return false;
        }

        return true;
    }

    public boolean isLegalRoom(Coordinate first, Coordinate second) {
        for (int i = Math.min(first.getRow(), second.getRow()) - 1; i <= Math.max(first.getRow(), second.getRow()) + 1; i++) {
            for (int j = Math.min(first.getColumn(), second.getColumn()) - 1; j <= Math.max(first.getColumn(), second.getColumn()) + 1; j++) {
                if (i >= 0 && i <+ 9 && j >= 0 && j <= 9) {
                    if (field.getCell(new Coordinate(i, j)).contains(CellState.YOUR_SHIP)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isGameFinished() {
        if (
                isAircraftCarrierSunk &&
                        isBattleshipSunk &&
                        isSubmarineSunk &&
                        isCruiserSunk &&
                        isDestroyerSunk
        ) {
            return true;
        }

        return false;
    }
}