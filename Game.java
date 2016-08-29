package School.homework.SeaBattle;

import java.util.Random;

public class Game {
    Map map = new Map();
    Ship ship = new Ship();
    Player player = new Player();
    int removeCash;
    char[][] cells = map.getCells();

    //Однопалубный массив
    int[] oneDeckShipX = ship.getOneDeckShipX();
    int[] oneDeckShipY = ship.getOneDeckShipY();

    //Двухпалубный массив
    int[] twoDeckShipX = ship.getTwoDeckShipX();
    int[] twoDeckShipY = ship.getTwoDeckShipY();
    int[] resRandomFull = new int[ship.SIZE_TWO_DECK_SHIP];

    // Игра
    public void game() {
        fillingField();
        cycleInstallPositionShip();
        cycleGame();
    }

    // Циклы установки кораблей
    public void cycleInstallPositionShip() {
        cycleTwoDeckShip();
        cycleOneDeckShip();
    }

    public void cycleOneDeckShip() {
        cyclePositionOneShip();
        for (int i = 0; i < ship.SIZE_ONE_DECK_SHIP; i++) {
            checkOnOthersOneDeckShip(oneDeckShipY[i], oneDeckShipX[i]);
        }
    }

    public void cycleTwoDeckShip() {
        cyclePositionTwoShip();
        for (int i = 0; i < ship.SIZE_TWO_DECK_SHIP; i++) {
            checkOnOthersTwoDeckShip(twoDeckShipY[i], twoDeckShipX[i], resRandomFull[i]);
        }
    }

    // Установка и проверка однопалубников
    public void cyclePositionOneShip() {
        for (int i = 0; i < ship.SIZE_ONE_DECK_SHIP; i++) {
            oneDeckShipX[i] = ship.oneDeckShipX(map.SIZE_X);
            oneDeckShipY[i] = ship.oneDeckShipY(map.SIZE_Y);
            checkOneDeckShip(i);
            map.setOneDeckShip(oneDeckShipY[i], oneDeckShipX[i]);
        }
    }

    public void checkOneDeckShip(int i) {
        while (returnResultCheckPositionOneShip(i)) {
            oneDeckShipX[i] = ship.oneDeckShipX(map.SIZE_X);
            oneDeckShipY[i] = ship.oneDeckShipY(map.SIZE_Y);
        }
    }

    public boolean returnResultCheckPositionOneShip(int i) {
        boolean result = false;
        if (cells[oneDeckShipY[i]][oneDeckShipX[i]] == 'X') {
            result = true;
        }
        return result;
    }

    public void checkOnOthersOneDeckShip(int shipY, int shipX) {
        while (true) {
            if (shipY == map.SIZE_Y - 1 && shipX != map.SIZE_X - 1) {
                if ('X' == cells[shipY][shipX - 1]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY - 1][shipX]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY][shipX + 1]) {
                    reinstallOneDeckShip();
                }
                break;
            } else if (shipX == map.SIZE_X - 1 && shipY != map.SIZE_Y - 1) {
                if ('X' == cells[shipY][shipX - 1]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY - 1][shipX]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY + 1][shipX]) {
                    reinstallOneDeckShip();
                }
                break;
            } else if (shipX == map.SIZE_X - 1 && shipY == map.SIZE_Y - 1) {
                if ('X' == cells[shipY][shipX - 1]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY - 1][shipX]) {
                    reinstallOneDeckShip();
                }
                break;
            } else if (shipX != map.SIZE_X - 1 && shipY != map.SIZE_Y - 1) {
                if ('X' == cells[shipY][shipX - 1]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY - 1][shipX]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY + 1][shipX]) {
                    reinstallOneDeckShip();
                }
                if ('X' == cells[shipY][shipX + 1]) {
                    reinstallOneDeckShip();
                }
                break;
            }
        }
    }

    public void reinstallOneDeckShip() {
        if (removeCash < 1) {
            map.removeOneDeckShip(oneDeckShipY, oneDeckShipX, ship.SIZE_ONE_DECK_SHIP);
            cycleOneDeckShip();
        }
        removeCash++;
    }

    // Установка и проверка двухпалубников
    public void cyclePositionTwoShip() {
        for (int i = 0; checkAndReturnValueCyclePositionTwoShip(); i++) {
            Random rand = new Random();
            resRandomFull[i] = rand.nextInt(2);
            twoDeckShipX[i] = ship.twoDeckShipX(map.SIZE_X);
            twoDeckShipY[i] = ship.twoDeckShipY(map.SIZE_Y);
            checkTwoDeckShip(i);
            checkOnSecondDeckForTwoDeckShip(twoDeckShipY[i], twoDeckShipX[i], i);
            map.setTwoDeckShip(twoDeckShipY[i], twoDeckShipX[i], resRandomFull[i]);
        }
    }

    public void checkTwoDeckShip(int i) {
        while (returnResultCheckPositionTwoShip(i)) {
            twoDeckShipX[i] = ship.twoDeckShipX(map.SIZE_X);
            twoDeckShipY[i] = ship.twoDeckShipY(map.SIZE_Y);
        }
    }

    public boolean returnResultCheckPositionTwoShip(int i) {
        boolean result = false;
        if (cells[twoDeckShipY[i]][twoDeckShipX[i]] == 'X') {
            result = true;
        }
        if (twoDeckShipY[i] != map.SIZE_Y - 1) {
            if (cells[twoDeckShipY[i] + 1][twoDeckShipX[i]] == 'X') {
                result = true;
            }
        } else if (twoDeckShipX[i] != map.SIZE_X - 1) {
            if (cells[twoDeckShipY[i]][twoDeckShipX[i] + 1] == 'X') {
                result = true;
            }
        } else {
            if (cells[twoDeckShipY[i] - 1][twoDeckShipX[i]] == 'X') {
                result = true;
            }
            if (cells[twoDeckShipY[i]][twoDeckShipX[i] - 1] == 'X') {
                result = true;
            }
        }
        return result;
    }

    public void checkOnSecondDeckForTwoDeckShip(int y, int x, int i) {
        if (resRandomFull[i] == 0) {
            if (x == 1) {
                if (cells[y][x + 1] == 'X') {
                    map.removeTwoDeckShip(twoDeckShipY, twoDeckShipX, i, resRandomFull);
                    cyclePositionTwoShip();

                }
            } else {
                if (cells[y][x - 1] == 'X') {
                    map.removeTwoDeckShip(twoDeckShipY, twoDeckShipX, i, resRandomFull);
                    cyclePositionTwoShip();
                }
            }
        } else {
            if (y == 1) {
                if (cells[y + 1][x] == 'X') {
                    map.removeTwoDeckShip(twoDeckShipY, twoDeckShipX, i, resRandomFull);
                    cyclePositionTwoShip();
                }
            } else {
                if (cells[y - 1][x] == 'X') {
                    map.removeTwoDeckShip(twoDeckShipY, twoDeckShipX, i, resRandomFull);
                    cyclePositionTwoShip();
                }
            }
        }
    }

    public boolean checkAndReturnValueCyclePositionTwoShip() {
        int counter = 1;
        boolean checkStrength = true;
        for (char[] cell : cells) {
            for (char c : cell) {
                if (counter <= ship.SIZE_TWO_DECK_SHIP * 2 /*+ ship.SIZE_ONE_DECK_SHIP*/) {
                    if (c == 'X') {
                        if (counter == ship.SIZE_TWO_DECK_SHIP * 2 /*+ ship.SIZE_ONE_DECK_SHIP*/) {
                            checkStrength = false;
                        }
                        counter++;
                    }
                }
            }
        }
        return checkStrength;
    }

    public void checkOnOthersTwoDeckShip(int shipY, int shipX, int res) {
        while (true) {
            if (shipY == map.SIZE_Y - 1 && shipX != map.SIZE_X - 1) {
                if (res == 0) {
                    if (shipX == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    }
                } else {
                    if (shipY == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    }
                }
                break;
            } else if (shipX == map.SIZE_X - 1 && shipY != map.SIZE_Y - 1) {
                if (res == 0) {
                    if (shipX == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    }
                } else {
                    if (shipY == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    }
                }
                break;
            } else if (shipX == map.SIZE_X - 1 && shipY == map.SIZE_Y - 1) {
                if (res == 0) {
                    if (shipX == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    }
                } else {
                    if (shipY == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                    }
                }
                break;
            } else if (shipX != map.SIZE_X - 1 && shipY != map.SIZE_Y - 1) {
                if (res == 0) {
                    if (shipX == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    }
                } else {
                    if (shipY == 1) {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY - 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    } else {
                        if ('X' == cells[shipY][shipX - 1]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY + 1][shipX]) {
                            reinstallTwoDeckShip();
                        }
                        if ('X' == cells[shipY][shipX + 1]) {
                            reinstallTwoDeckShip();
                        }
                    }
                }
                break;
            }
        }
    }

    public void reinstallTwoDeckShip() {
        if (removeCash < 1) {
            map.removeTwoDeckShip(twoDeckShipY, twoDeckShipX, ship.SIZE_TWO_DECK_SHIP, resRandomFull);
            cycleTwoDeckShip();
        }
        removeCash++;
    }

    // Установка и показ поля
    public void fillingField() {
        map.field();
        map.fieldFake();
        map.showMapFake();
    }

    // Удар по выбранной позиции игроком
    public void playerPosition() {
        int yPosition = player.yPosition(map.SIZE_Y);
        int xPosition = player.xPosition(map.SIZE_X);
        map.sunkShip(yPosition, xPosition);
    }

    // Закончена ли игра или нет?
    public boolean isGameOver() {
        for (char[] cell : map.getCells()) {
            for (char c : cell) {
                if (c == 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    // Цикл игры
    public void cycleGame() {
        do {
            playerPosition();
            map.showMapFake();
        } while (!(isGameOver()));
        System.out.println("Вы потопили все корабли. Поздравляем.");
    }
}
