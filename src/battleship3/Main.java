package battleship3;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
        Game game = new Game(field);

        System.out.println(field);
        System.out.println();

        game.placeShip(ShipType.AIRCRAFT_CARRIER);
        game.placeShip(ShipType.BATTLESHIP);
        game.placeShip(ShipType.SUBMARINE);
        game.placeShip(ShipType.CRUISER);
        game.placeShip(ShipType.DESTROYER);

        System.out.println("\nThe game starts!\n");
        System.out.println(field);
        System.out.println();
        game.shoot();
        System.out.println();

        System.out.println(game.revealAllShips());

    }
}
