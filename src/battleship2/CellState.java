package battleship2;

public enum CellState {
    FOG('~'),
    YOUR_SHIP('O'),
    HIT('X'),
    MISS('M');

    protected char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}