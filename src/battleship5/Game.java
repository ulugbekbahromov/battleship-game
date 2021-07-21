package battleship5;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Field field1 = new Field();
    private final ShipPlacer shipPlacer1 = new ShipPlacer(field1);
    private final Field field2 = new Field();
    private final ShipPlacer shipPlacer2 = new ShipPlacer(field2);
    private int turn = 1;
    private Field currentField = field1;
    private ShipPlacer currentShipPlacer = shipPlacer1;

    public void switchPlayer() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        switchTurn();
    }

    public void switchTurn() {
        if (currentField == field1) {
            currentField = field2;
        } else if (currentField == field2) {
            currentField = field1;
        }

        if (currentShipPlacer == shipPlacer1) {
            currentShipPlacer = shipPlacer2;
        } else if (currentShipPlacer == shipPlacer2) {
            currentShipPlacer = shipPlacer1;
        }

        if (turn == 1) {
            turn = 2;
        } else if (turn == 2) {
            turn = 1;
        }
    }

    public int getTurn() {
        return turn;
    }

    public void placeShip(ShipType shipType) {
        currentShipPlacer.place(shipType);
        revealAllShips();
    }

    public void shoot() {
        String coord = scanner.nextLine();
        char letter = coord.charAt(0);
        int number = Integer.parseInt(coord.replaceFirst(".", ""));

        while (letter < 'A' || letter > 'J' || number < 1 || number > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            coord = scanner.nextLine();
            letter = coord.charAt(0);
            number = Integer.parseInt(coord.replaceFirst(".", ""));
        }
        Coordinate coordinate = new Coordinate(coord);

        switchTurn();

        boolean isHit;
        if (currentField.getCell(coordinate).contains(CellState.YOUR_SHIP)) {
            currentField.getCell(coordinate).add(CellState.HIT);
            isHit = true;
        } else {
            currentField.getCell(coordinate).add(CellState.MISS);
            isHit = false;
        }

        if (isHit) {
            if (
                    currentShipPlacer.isSunk(ShipType.AIRCRAFT_CARRIER) ||
                            currentShipPlacer.isSunk(ShipType.BATTLESHIP) ||
                            currentShipPlacer.isSunk(ShipType.SUBMARINE) ||
                            currentShipPlacer.isSunk(ShipType.CRUISER) ||
                            currentShipPlacer.isSunk(ShipType.DESTROYER)
            ) {
                System.out.println("You sank a ship!");
            } else {
                System.out.println("You hit a ship!");
            }
        } else {
            System.out.println("You missed!");
        }

        switchTurn();
    }

    public void revealAllShips() {
        StringBuilder fieldString = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < 10; i++) {
            fieldString.append(System.lineSeparator()).append((char) ('A' + i));

            for (int j = 0; j < 10; j++) {
                ArrayList<CellState> currentCell = currentField.getCell(new Coordinate(i, j));

                if (currentCell.contains(CellState.HIT)) {
                    fieldString.append(" ").append(CellState.HIT.symbol);
                } else if (currentCell.contains(CellState.MISS)) {
                    fieldString.append(" ").append(CellState.MISS.symbol);
                } else if (currentCell.contains(CellState.YOUR_SHIP)) {
                    fieldString.append(" ").append(CellState.YOUR_SHIP.symbol);
                } else if (currentCell.contains(CellState.FOG)) {
                    fieldString.append(" ").append(CellState.FOG.symbol);
                }
            }
        }

        System.out.println(fieldString);
    }

    public void printField() {
        System.out.println(currentField);
    }

    public void printBothFields() {
        switchTurn();
        printField();
        switchTurn();
        System.out.println("---------------------");
        revealAllShips();
    }

    public boolean isFinished() {
        if (currentShipPlacer.isGameFinished()) {
            return true;
        }

        switchTurn();

        if (currentShipPlacer.isGameFinished()) {
            return true;
        }

        switchTurn();

        return false;
    }
}