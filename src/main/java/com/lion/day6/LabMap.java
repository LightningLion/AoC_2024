package com.lion.day6;

import static com.lion.utils.Utils.printMatrix;

public class LabMap {
    private Character[][] map;
    //private Guard guard;
    private int guardRow;
    private int guardColumn;
    private Direction guardDirection;

    public LabMap(Character[][] map) {
        //Guard guard = new Guard();
        this.map = map;
        this.locateGuard();
    }

    private void locateGuard() {
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j< map[0].length; j++){
                switch (map[i][j]){
                    case '^':
                        guardDirection = Direction.UP;
                        guardRow = i;
                        guardColumn = j;
                        break;

                    case '>':
                        guardDirection = Direction.RIGHT;
                        guardRow = i;
                        guardColumn = j;
                        break;

                    case 'v':
                        guardDirection = Direction.DOWN;
                        guardRow = i;
                        guardColumn = j;
                        break;

                    case '<':
                        guardDirection = Direction.LEFT;
                        guardRow = i;
                        guardColumn = j;
                        break;
                }
            }
        }
    }

    public void move(){
        Character spaceAhead;
        switch (guardDirection){
            case UP:
                spaceAhead = map[guardRow - 1][guardColumn];
                if (spaceAhead != '#') {
                    map[guardRow][guardColumn] = 'X';
                    guardRow--;
                    map[guardRow][guardColumn] = '^';
                } else {
                    guardDirection = Direction.RIGHT;
                    move();
                }
                break;

            case RIGHT:
                spaceAhead = map[guardRow][guardColumn + 1];
                if (spaceAhead != '#') {
                    map[guardRow][guardColumn] = 'X';
                    guardColumn++;
                    map[guardRow][guardColumn] = '>';
                } else {
                    guardDirection = Direction.DOWN;
                    move();
                }
                break;

            case DOWN:
                spaceAhead = map[guardRow + 1][guardColumn];
                if (spaceAhead != '#') {
                    map[guardRow][guardColumn] = 'X';
                    guardRow++;
                    map[guardRow][guardColumn] = 'v';
                } else {
                    guardDirection = Direction.LEFT;
                    move();
                }
                break;

            case LEFT:
                spaceAhead = map[guardRow][guardColumn - 1];
                if (spaceAhead != '#') {
                    map[guardRow][guardColumn] = 'X';
                    guardColumn--;
                    map[guardRow][guardColumn] = '<';
                } else {
                    guardDirection = Direction.UP;
                    move();
                }
                break;
        }
    }

    public long countPositions(){
        long result = 0;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] == 'X'){
                    result++;
                }
            }
        }

        return result;
    }

    public void printMap(){
        printMatrix(map);
    }

    public int getGuardRow() {
        return guardRow;
    }

    public int getGuardColumn() {
        return guardColumn;
    }

    public void setMapPosition(int row, int column, char c) {
        this.map[row][column] = c;
    }
}
