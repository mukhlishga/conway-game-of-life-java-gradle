package com.conwaygameoflife.utils;

import com.conwaygameoflife.services.ConwayGameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.lang.Long.parseLong;

public class InputParser {
    public int[][] initialCellList = {};
    public int initialRowSize;
    public int initialColumnSize;

    public InputParser() {
    }

    public void parseInput(String fileName, String timeout) throws InterruptedException {
        parseFileInput(fileName);
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(initialRowSize, initialColumnSize, initialCellList, createInitialMatrix());
        conwayGameOfLife.printMatrix();

        while (true) {
            TimeUnit.MILLISECONDS.sleep(timeout != null ? parseLong(timeout) : 500);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            conwayGameOfLife.playOnce();
        }
    }

    private char[][] createInitialMatrix() {
        char[][] initialMatrix = new char[initialRowSize][initialColumnSize];
        for (var i = 0; i < initialRowSize; i++) {
            for (var j = 0; j < initialColumnSize; j++) {
                initialMatrix[i][j] = '.';
            }
        }
        for (var cell: initialCellList) {
            initialMatrix[cell[0]][cell[1]] = 'O';
        }
        return initialMatrix;
    }

    public void parseFileInput(String filepath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader((filepath)));
            String line;
            try {
                int r = 0;
                while ((line = br.readLine()) != null) {
                    int c = 0;
                    for (String s : line.trim().replaceAll("\\s", "").split("")) {
                        if (s.equalsIgnoreCase("O")) {
                            int[] cell = new int[2];
                            cell[0] = r;
                            cell[1] = c;
                            initialCellList = addToIntArray(initialCellList, cell);
                        }
                        c++;
                        initialColumnSize = Math.max(c, initialColumnSize);
                    }
                    r++;
                    initialRowSize = Math.max(r, initialRowSize);
                }
            } catch (IOException e) {
                System.out.println("Error reading the file input");
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error : File not found in the specified path");
            throw new RuntimeException(e);
        }
    }

    public static int[][] addToIntArray(int[][] oldArray, int[] newElement) {
        int[][] newArray = Arrays.copyOf(oldArray, oldArray.length+1);
        newArray[oldArray.length] = newElement;
        return newArray;
    }
}
