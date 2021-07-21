package battleship5;

public enum CellState {
    FOG('~'),
    YOUR_SHIP('O'),
    HIT('X'),
    MISS('M');

    public final char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}