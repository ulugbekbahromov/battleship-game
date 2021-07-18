package battleship1;

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
        System.out.println(field);
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
        System.out.println(field);
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
}