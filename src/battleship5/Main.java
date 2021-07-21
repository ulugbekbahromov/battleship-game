package battleship5;


public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 0; i < 2; i++) {
            System.out.printf("Player %d, place your ships to the game field", game.getTurn());
            System.out.println();
            game.printField();
            System.out.println();

            game.placeShip(ShipType.AIRCRAFT_CARRIER);
            game.placeShip(ShipType.BATTLESHIP);
            game.placeShip(ShipType.SUBMARINE);
            game.placeShip(ShipType.CRUISER);
            game.placeShip(ShipType.DESTROYER);
            game.switchPlayer();
        }

        while (!game.isFinished()) {
            System.out.println();
            game.printBothFields();
            System.out.println();
            System.out.printf("Player %d, it's your turn:", game.getTurn());
            System.out.println();
            game.shoot();

            if (!game.isFinished()) {
                game.switchPlayer();
            }
            System.out.println();
        }

        System.out.printf("Player %d, you sank the last ship. You won. Congratulations!", game.getTurn());
    }
}