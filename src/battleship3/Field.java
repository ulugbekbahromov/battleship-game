package battleship3;

import java.util.ArrayList;

public class Field {
    private ArrayList[][] field;

    public Field() {
        initField();
    }

    public void initField() {
        field = new ArrayList[10][10];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new ArrayList<>();
                field[i][j].add(CellState.FOG);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder fieldString = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < field.length; i++) {
            fieldString.append(System.lineSeparator()).append((char) ('A' + i));

            for (int j = 0; j < field[0].length; j++) {
                ArrayList currentCell = field[i][j];

                if (currentCell.contains(CellState.HIT)) {
                    fieldString.append(" ").append(CellState.HIT.symbol);
                } else if (currentCell.contains(CellState.MISS)) {
                    fieldString.append(" ").append(CellState.MISS.symbol);
                } else if (currentCell.contains(CellState.FOG)) {
                    fieldString.append(" ").append(CellState.FOG.symbol);
                } else if (currentCell.contains(CellState.YOUR_SHIP)) {
                    fieldString.append(" ").append(CellState.YOUR_SHIP.symbol);
                }
            }
        }

        return fieldString.toString();
    }

    public void setCell(Coordinate coordinate, CellState content) {
        field[coordinate.getRow()][coordinate.getColumn()].add(content);
    }

    public ArrayList<CellState> getCell(Coordinate coordinate) {
        return field[coordinate.getRow()][coordinate.getColumn()];
    }
}