package battleship2;

public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(String coordinate) {
        char letter = coordinate.charAt(0);
        int number = Integer.parseInt(coordinate.replaceFirst(".", ""));

        if (letter < 'A' || letter > 'J' || number < 1 || number > 10) {
            throw new IllegalArgumentException("Illegal coordinate");
        }

        this.row  = letter - 'A';
        this.column = number - 1;
    }

    public Coordinate(int row, int column) {
        if (row < 0 || row > 9 || column < 0 || column > 9) {
            throw new IllegalArgumentException("Illegal coordinate");
        }

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}