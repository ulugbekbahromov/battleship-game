package battleship3;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Field field;
    Ship ship;

    public Game(Field field) {
        this.field = field;
        this.ship = new Ship(field);
    }

    public void placeShip(ShipType shipType) {
        System.out.printf("Enter the coordinates of the %s (%d cells):", shipType.name, shipType.length);
        System.out.println();

        String[] coordinates = scanner.nextLine().split(" ");
        Coordinate first = new Coordinate(coordinates[0]);
        Coordinate second = new Coordinate(coordinates[1]);
        while (!canBePlaced(first, second, shipType)) {
            coordinates = scanner.nextLine().split(" ");
            first = new Coordinate(coordinates[0]);
            second = new Coordinate(coordinates[1]);
        }

        ship.placeAbstractShip(first, second);
        System.out.println();
        System.out.println(revealAllShips());
    }

    public boolean canBePlaced(Coordinate first, Coordinate second, ShipType shipType) {
        if (!ship.isLegalForm(first, second)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        } else if (ship.getLength(first, second) != shipType.length) {
            System.out.println("Error! Wrong length of the " + shipType.name + "! Try again:");
            return false;
        } else if (!ship.isLegalRoom(first, second)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }

        return true;
    }

    public void shoot() {
        System.out.println("Take a shot!");

        String coordinate = scanner.nextLine();
        char letter = coordinate.charAt(0);
        int number = Integer.parseInt(coordinate.replaceFirst(".", ""));

        while (letter < 'A' || letter > 'J' || number < 1 || number > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            coordinate = scanner.nextLine();
            letter = coordinate.charAt(0);
            number = Integer.parseInt(coordinate.replaceFirst(".", ""));
        }

        boolean isHit = ship.shoot(new Coordinate(coordinate));
        System.out.println(showShellsOnly());
        System.out.println();

        if (isHit) {
            System.out.println("You hit a ship!");
        } else {
            System.out.println("You missed!");
        }
    }

    public String revealAllShips() {
        StringBuilder fieldString = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < 10; i++) {
            fieldString.append(System.lineSeparator()).append((char) ('A' + i));

            for (int j = 0; j < 10; j++) {
                ArrayList<CellState> currentCell = field.getCell(new Coordinate(i, j));

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

        return fieldString.toString();
    }

    public String showShellsOnly() {
        StringBuilder fieldString = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < 10; i++) {
            fieldString.append(System.lineSeparator()).append((char) ('A' + i));

            for (int j = 0; j < 10; j++) {
                ArrayList<CellState> currentCell = field.getCell(new Coordinate(i, j));

                if (currentCell.contains(CellState.HIT)) {
                    fieldString.append(" ").append(CellState.HIT.symbol);
                } else if (currentCell.contains(CellState.MISS)) {
                    fieldString.append(" ").append(CellState.MISS.symbol);
                } else if (currentCell.contains(CellState.FOG)) {
                    fieldString.append(" ").append(CellState.FOG.symbol);
                }
            }
        }

        return fieldString.toString();
    }
}