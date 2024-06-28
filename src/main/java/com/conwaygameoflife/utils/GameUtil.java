package com.conwaygameoflife.utils;

import com.conwaygameoflife.services.ConwayGameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Long.parseLong;

public class GameUtil {
    public int[][] initialCellList = {};
    public int initialRowSize;
    public int initialColumnSize;

    public GameUtil() {
    }

    public void play(String fileName, String timeout) throws InterruptedException {
        parseFileInput(fileName);

        char[][] initialMatrix = MatrixUtil.implementMatrix(initialRowSize, initialColumnSize, initialCellList);
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(initialRowSize, initialColumnSize, initialCellList, initialMatrix);

        clearScreen();
        MatrixUtil.printMatrix(initialMatrix);
        delay(timeout);

        while (true) {
            char[][] nextMatrix = conwayGameOfLife.playOnce();

            clearScreen();
            MatrixUtil.printMatrix(nextMatrix);
            delay(timeout);
        }
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
                            initialCellList = MatrixUtil.addToIntArray(initialCellList, cell);
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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void delay(String timeout) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(timeout != null ? parseLong(timeout) : 500);
    }
}
