package com.lion.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    private static final String SAMPLE_PATH = "src/main/java/com/lion/day4/sample.txt";
    private static final String INPUT_PATH = "src/main/java/com/lion/day4/input.txt";

    public static void main(String[] args) {
        //File file = new File(SAMPLE_PATH);
        File file = new File(INPUT_PATH);

        List<String> inputs = getStringsFromFile(file);

        Character[][] charMatrix = convertToMatrix(inputs);

        long result = processCharMatrix(charMatrix);
        System.out.printf("Found MAS cross %d times.", result);
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
        /*
            Cross-MAS
            [M][_][M]
            [_][A][_]
            [S][_][S]

            In order to locate the MAS crosses we search for the A and
            check the surrouding vertexes

            Since A is at the center of a 3 by 3 square the first and last
            rows can be skipped

            Once an A is located there are two conditions to have a valid cross:
                -There are 2 M and 2 S letters
                -The letters at opposing vertexes have to be different to avoid
                incorrect matches such as
                [M][_][S]
                [_][A][_]
                [S][_][M]
         */
        long totalCount = 0L;

        for (int i = 1; i < charMatrix.length - 1; i++) { //rows
            for (int j = 0; j < charMatrix[0].length; j++) { //columns
                if (charMatrix[i][j] == 'A'){ //locate center of cross
                    totalCount += searchCross(charMatrix, i, j) ? 1 : 0;
                }
            }
        }

        return totalCount;
    }

    private static boolean searchCross(Character[][] charMatrix, int i, int j) {
        List<Character> crossChars = new ArrayList<>();
        boolean found = false;

        try {
            //Check opposing vertexes are different
            if ((charMatrix[i-1][j-1] != charMatrix[i+1][j+1])
                && (charMatrix[i-1][j+1] != charMatrix[i+1][j-1])) {

                //Make a list with the chars in the vertexes
                crossChars.add(charMatrix[i - 1][j - 1]);
                crossChars.add(charMatrix[i - 1][j + 1]);
                crossChars.add(charMatrix[i + 1][j - 1]);
                crossChars.add(charMatrix[i + 1][j + 1]);
            }
        } catch (Exception ignored) {

        }

        //Check for condition 2: 2 'M' and 2 'S' characters
        if (crossChars.stream().filter(c -> c.equals('M')).count() == 2
        && crossChars.stream().filter(c -> c.equals('S')).count() == 2) {
            //Print the located square
            System.out.print(charMatrix[i-1][j-1]); System.out.print(" "); System.out.println(charMatrix[i-1][j+1]);
            System.out.print(" "); System.out.print(charMatrix[i][j]); System.out.println(" ");
            System.out.print(charMatrix[i+1][j-1]); System.out.print(" "); System.out.println(charMatrix[i+1][j+1]);

            found = true;
        }

        return found;
    }

}
