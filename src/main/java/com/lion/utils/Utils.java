package com.lion.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getStringsFromFile(File file){
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

        return inputs;
    }

    public static Character[][] convertToMatrix(List<String> inputs){
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
}
