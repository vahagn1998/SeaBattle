package School.homework.SeaBattle;


public class Map {
    public static final int SIZE_Y = 8;
    public static final int SIZE_X = 8;

    private char[][] cells = new char[SIZE_Y][SIZE_X];
    private Object[][] cellsFake = new Object[SIZE_Y][SIZE_X];

    public char[][] getCells() {
        return cells;
    }

    public void field() {
        for (int i = 1; i < SIZE_Y; i++) {
            for (int j = 1; j < SIZE_X; j++) {
                cells[i][j] = 'o';
            }
        }
    }

    public void fieldFake() {
        for (int i = 1; i < SIZE_Y; i++) {
            for (int j = 1; j < SIZE_X; j++) {
                cellsFake[i][j] = ('o');
            }
        }

        for (int i = 0; i < SIZE_X; i++) {
            if (i == 0) {
                cellsFake[0][i] = ("  ");
            } else {
                cellsFake[0][i] = (i);
            }
        }

        for (int i = 1; i < SIZE_Y; i++) {
            cellsFake[i][0] = (i + " ");
        }
    }

    public void showMapFake() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(cells[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void setOneDeckShip(int y, int x) {
        cells[y][x] = 'X';
    }

    public void removeOneDeckShip(int[] y, int[] x, int size) {
        for (int j = 0; j < size; j++) {
            cells[y[j]][x[j]] = 'o';
        }
    }

    public int setTwoDeckShip(int y, int x, int r) {
        cells[y][x] = 'X';
        if (r == 0) {
            if (x == 1) {
                cells[y][x + 1] = 'X';
            } else {
                cells[y][x - 1] = 'X';
            }
        } else {
            if (y == 1) {
                cells[y + 1][x] = 'X';
            } else {
                cells[y - 1][x] = 'X';
            }
        }
        return r;
    }

    public void removeTwoDeckShip(int[] y, int[] x, int size, int[] res) {
        for (int j = 0; j < size; j++) {
            cells[y[j]][x[j]] = 'o';
            if (res[j] == 0) {
                if (x[j] == 1) {
                    cells[y[j]][x[j] + 1] = 'o';
                } else {
                    cells[y[j]][x[j] - 1] = 'o';
                }
            } else {
                if (y[j] == 1) {
                    cells[y[j] + 1][x[j]] = 'o';
                } else {
                    cells[y[j] - 1][x[j]] = 'o';
                }
            }
        }

    }

    public void shipShot(int y, int x) {
        if (cells[y][x] == '-' || cells[y][x] == '*') {
            System.out.println("Вы уже стреляли по этой позиции, попробуйте ещё раз.");
        }
        if (cells[y][x] == 'o') {
            cells[y][x] = '*';
            System.out.println("Вы промахнулись.");
        }
        if (cells[y][x] == 'X') {
            if (y != SIZE_Y - 1 && x != SIZE_X - 1) {
                if (cells[y - 1][x] != 'X' && cells[y + 1][x] != 'X' && cells[y][x - 1] != 'X' && cells[y][x + 1] != 'X') {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы потопили корабль.");
                } else {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы ранили корабль.");
                }
            }
            if (y != SIZE_Y - 1 && x == SIZE_X - 1) {
                if (cells[y - 1][x] != 'X' && cells[y + 1][x] != 'X' && cells[y][x - 1] != 'X') {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы потопили корабль.");
                } else {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы ранили корабль.");
                }
            }
            if (y == SIZE_Y - 1 && x != SIZE_X - 1) {
                if (cells[y - 1][x] != 'X' && cells[y][x - 1] != 'X' && cells[y][x + 1] != 'X') {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы потопили корабль.");
                } else {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы ранили корабль.");
                }
            }
            if (y == SIZE_Y - 1 && x == SIZE_X - 1) {
                if (cells[y - 1][x] != 'X' && cells[y][x - 1] != 'X') {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы потопили корабль.");
                } else {
                    cells[y][x] = '-';
                    cellsFake[y][x] = '-';
                    System.out.println("Вы ранили корабль.");
                }
            }
        }
    }
}
