package com.lion.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    private static final String SAMPLE_PATH = "src/main/java/com/lion/day4/sample.txt";
    private static final String INPUT_PATH = "src/main/java/com/lion/day4/input.txt";

    public static void main(String[] args) {
        //File file = new File(SAMPLE_PATH);
        File file = new File(INPUT_PATH);

        List<String> inputs = getStringsFromFile(file);

        Character[][] charMatrix = convertToMatrix(inputs);

        long result = processCharMatrix(charMatrix);
        System.out.printf("Found XMAS word %d times.", result);
    }

    private static List<String> getStringsFromFile(File file) {
        List<String> inputs = new ArrayList<>();
        String line;

        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return  inputs;

    }

    private static Character[][] convertToMatrix(List<String> inputs){
        Character[][] charMatrix = new Character[inputs.size()][inputs.get(1).length()];

        System.out.println("Building matrix:");
        for (int i = 0; i < inputs.size(); i++){
            for (int j = 0; j < inputs.get(i).length(); j++){
                charMatrix[i][j] = inputs.get(i).charAt(j);
                System.out.print(charMatrix[i][j]);
            }
            System.out.println();
        }

        return charMatrix;
    }

    private static long processCharMatrix(Character[][] charMatrix) {
        long totalCount = 0L;
        //matrix.lenght = rows
        //matrix[a][b].length = columns

        //long countForward = searchForward(charMatrix);
        //ong countBackwards = searchBackwards(charMatrix);
        //long countDown = searchDown(charMatrix);

        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[0].length; j++) {
                if (charMatrix[i][j] == 'X'){
                    totalCount += searchForward(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchBackwards(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchDown(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchUp(charMatrix, i, j) ? 1 : 0;

                    totalCount += searchDiagonalLeftUp(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchDiagonalRightUp(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchDiagonalLeftDown(charMatrix, i, j) ? 1 : 0;
                    totalCount += searchDiagonalRightDown(charMatrix, i, j) ? 1 : 0;
                }
            }
        }

        //totalCount = countForward + countBackwards + countDown;

        return totalCount;
    }

    private static boolean searchForward(Character[][] charMatrix, int i, int j) {
        boolean found = false;

        try {
            if (charMatrix[i][j] == 'X'
                && charMatrix[i][j + 1] == 'M'
                && charMatrix[i][j + 2] == 'A'
                && charMatrix[i][j + 3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchBackwards(Character[][] charMatrix, int i, int j) {
        boolean found = false;
        try {
            if (charMatrix[i][j] == 'X'
                && charMatrix[i][j - 1] == 'M'
                && charMatrix[i][j - 2] == 'A'
                && charMatrix[i][j - 3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchDown(Character[][] charMatrix, int i, int j) {
        boolean found = false;

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i+1][j] == 'M'
                    && charMatrix[i+2][j] == 'A'
                    && charMatrix[i+3][j] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchUp(Character[][] charMatrix, int i, int j){
        boolean found = false;

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i-1][j] == 'M'
                    && charMatrix[i-2][j] == 'A'
                    && charMatrix[i-3][j] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchDiagonalLeftUp(Character[][] charMatrix, int i, int j) {
        boolean found = false;
        /*
        [S][ ][ ][ ]
        [ ][M][ ][ ]
        [ ][ ][A][ ]
        [ ][ ][ ][X]
         */

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i-1][j-1] == 'M'
                    && charMatrix[i-2][j-2] == 'A'
                    && charMatrix[i-3][j-3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchDiagonalLeftDown(Character[][] charMatrix, int i, int j) {
        boolean found = false;
        /*
        [ ][ ][ ][X]
        [ ][ ][M][ ]
        [ ][A][ ][ ]
        [S][ ][ ][ ]
         */

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i+1][j-1] == 'M'
                    && charMatrix[i+2][j-2] == 'A'
                    && charMatrix[i+3][j-3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchDiagonalRightUp(Character[][] charMatrix, int i, int j) {
        boolean found = false;
        /*
        [ ][ ][ ][S]
        [ ][ ][A][ ]
        [ ][M][ ][ ]
        [X][ ][ ][ ]
         */

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i-1][j+1] == 'M'
                    && charMatrix[i-2][j+2] == 'A'
                    && charMatrix[i-3][j+3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

    private static boolean searchDiagonalRightDown(Character[][] charMatrix, int i, int j) {
        boolean found = false;
        /*
        [X][ ][ ][ ]
        [ ][M][ ][ ]
        [ ][ ][A][ ]
        [ ][ ][ ][S]
         */

        try {
            if (charMatrix[i][j] == 'X'
                    && charMatrix[i+1][j+1] == 'M'
                    && charMatrix[i+2][j+2] == 'A'
                    && charMatrix[i+3][j+3] == 'S'
            ) {
                found = true;
            }
        } catch (Exception ignored) {

        }

        return found;
    }

}
