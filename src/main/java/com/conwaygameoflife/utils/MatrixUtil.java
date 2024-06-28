package com.conwaygameoflife.utils;

import java.util.Arrays;

public class MatrixUtil {

    public static char[][] implementMatrix(int rowSize, int columnSize, int[][] cellList) {
        char[][] matrix = new char[rowSize][columnSize];
        for (var i = 0; i < rowSize; i++) {
            for (var j = 0; j < columnSize; j++) {
                matrix[i][j] = '.';
            }
        }
        for (var cell: cellList) {
            matrix[cell[0]][cell[1]] = 'O';
        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (var row: matrix) {
            for (var cell: row) {
                System.out.print(cell);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int[][] addToIntArray(int[][] oldArray, int[] newElement) {
        int[][] newArray = Arrays.copyOf(oldArray, oldArray.length+1);
        newArray[oldArray.length] = newElement;
        return newArray;
    }
}
