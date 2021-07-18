package battleship1;

public class Field {
    private CellState[][] field;

    public Field() {
        initField();
    }

    public void initField() {
        field = new CellState[10][10];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = CellState.FOG;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder fieldString = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < field.length; i++) {
            fieldString.append(System.lineSeparator()).append((char) ('A' + i));
            for (int j = 0; j < field[0].length; j++) {
                fieldString.append(" ").append(field[i][j].symbol);
            }
        }

        return fieldString.toString();
    }

    public void setCell(int row, int column, CellState content) {
        field[row][column] = content;
    }

    public CellState getCell(int row, int column) {
        return field[row][column];
    }
}