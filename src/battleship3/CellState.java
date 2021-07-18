package battleship3;

public enum CellState {
    FOG('~'),
    YOUR_SHIP('O'),
    HIT('X'),
    MISS('M');

    public char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}