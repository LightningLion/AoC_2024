package com.lion.day6;

import java.io.File;
import java.util.List;

import static com.lion.utils.Utils.convertToMatrix;
import static com.lion.utils.Utils.getStringsFromFile;

public class Part1 {
    private static final String SAMPLE_PATH = "src/main/java/com/lion/day6/sample.txt";
    private static final String INPUT_PATH = "src/main/java/com/lion/day6/input.txt";

    public static void main(String[] args) {
        //File file = new File(SAMPLE_PATH);
        File file = new File(INPUT_PATH);

        List<String> inputs = getStringsFromFile(file);
        Character[][] matrixMap = convertToMatrix(inputs);

        LabMap map = new LabMap(matrixMap);

        try {
            while(true){
                map.move();
                //map.printMap();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            //Getting out of the map
            map.setMapPosition(map.getGuardRow(), map.getGuardColumn(), 'X');
        }

        map.printMap();
        System.out.printf("Total of occupied spaces is: %d.%n", map.countPositions());

    }
}
