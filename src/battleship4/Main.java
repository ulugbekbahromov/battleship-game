package battleship4;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.printField();
        System.out.println();

        game.placeShip(ShipType.AIRCRAFT_CARRIER);
        game.placeShip(ShipType.BATTLESHIP);
        game.placeShip(ShipType.SUBMARINE);
        game.placeShip(ShipType.CRUISER);
        game.placeShip(ShipType.DESTROYER);

        System.out.println("\nThe game starts!\n");
        System.out.println();
        System.out.println();
        game.showShellsOnly();
        System.out.println("\nTake a shot!\n");

        while (!game.isFinished()) {
            game.shoot();
            System.out.println();
        }

        game.showShellsOnly();
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}
