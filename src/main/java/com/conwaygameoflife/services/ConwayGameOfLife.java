package com.conwaygameoflife.services;

import static com.conwaygameoflife.utils.InputParser.addToIntArray;

public class ConwayGameOfLife {
    private int rowSize;
    private int columnSize;
    private int[][] cellList;
    private char[][] matrix;

    public ConwayGameOfLife(int initialRowSize, int initialColumnSize, int[][] initialCellList, char[][] initialMatrix) {
        this.rowSize = initialRowSize;
        this.columnSize = initialColumnSize;
        this.cellList = initialCellList;
        this.matrix = initialMatrix;
    }

    public void playOnce() {
        updateCellListBasedOnNeighbors();
        updateMatrixBasedOnCurrentState();
        implementMatrix();
        printMatrix();
    }

    public void printMatrix() {
        for (var row: matrix) {
            for (var cell: row) {
                System.out.print(cell);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void implementMatrix() {
        matrix = new char[rowSize][columnSize];
        for (var i = 0; i < rowSize; i++) {
            for (var j = 0; j < columnSize; j++) {
                matrix[i][j] = '.';
            }
        }
        for (var cell: cellList) {
            matrix[cell[0]][cell[1]] = 'O';
        }
    }

    private void updateCellListBasedOnNeighbors() {
        cellList = new int[][]{};
        for (var r = 0; r < rowSize; r++) {
            for (var c = 0; c < columnSize; c++) {
                int neighbors = countNeighbors(r, c);
                if ((matrix[r][c] == 'O' && neighbors >= 2 && neighbors <= 3) || (matrix[r][c] == '.' && neighbors == 3)) {
                    int[] cell = new int[2];
                    cell[0] = r;
                    cell[1] = c;
                    cellList = addToIntArray(cellList, cell);
                }
            }
        }
    }

    private void updateMatrixBasedOnCurrentState() {
        if (isCellAtMostTop() && isCellAtMostLeft()) {
            rowSize++;
            columnSize++;
            for (int[] cell : cellList) {
                cell[0]++;
                cell[1]++;
            }
        } else if (isCellAtMostTop() && isCellAtMostRight()) {
            rowSize++;
            columnSize++;
            for (int[] cell : cellList) {
                cell[0]++;
            }
        } else if (isCellAtMostBottom() && isCellAtMostLeft()) {
            rowSize++;
            columnSize++;
            for (int[] cell : cellList) {
                cell[1]++;
            }
        } else if (isCellAtMostBottom() && isCellAtMostRight()) {
            rowSize++;
            columnSize++;
        } else if (isCellAtMostTop()) {
            rowSize++;
            for (int[] cell : cellList) {
                cell[0]++;
            }
        } else if (isCellAtMostBottom()) {
            rowSize++;
        } else if (isCellAtMostLeft()) {
            columnSize++;
            for (int[] cell : cellList) {
                cell[1]++;
            }
        } else if (isCellAtMostRight()) {
            columnSize++;
        }
    }

    private int countNeighbors(int r, int c){
        int neighbors = 0;
        if (r > 0 && c > 0) {
            if (matrix[r-1][c-1] == 'O') {
                neighbors += 1;
            }
        }
        if (r > 0){
            if (matrix[r-1][c] == 'O') {
                neighbors += 1;
            }
        }
        if (r > 0 && c < columnSize-1){
            if (matrix[r-1][c+1] == 'O') {
                neighbors += 1;
            }
        }
        if (c > 0){
            if (matrix[r][c-1] == 'O') {
                neighbors += 1;
            }
        }
        if (c < columnSize-1){
            if (matrix[r][c+1] == 'O') {
                neighbors += 1;
            }
        }
        if (r < rowSize-1 && c > 0){
            if (matrix[r+1][c-1] == 'O') {
                neighbors += 1;
            }
        }
        if (r < rowSize-1){
            if (matrix[r+1][c] == 'O') {
                neighbors += 1;
            }
        }
        if (r < rowSize-1){
            if (c < columnSize-1 && matrix[r+1][c+1] == 'O') {
                neighbors += 1;
            }
        }
        return neighbors;
    }

    private boolean isCellAtMostTop() {
        for (int[] cell : cellList) {
            if (matrix[cell[0]][cell[1]] == 'O' && (cell[0] <= 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCellAtMostBottom() {
        for (int[] cell : cellList) {
            if (matrix[cell[0]][cell[1]] == 'O' && (cell[0] >= rowSize-1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCellAtMostLeft() {
        for (int[] cell : cellList) {
            if (matrix[cell[0]][cell[1]] == 'O' && (cell[1] <= 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCellAtMostRight() {
        for (int[] cell : cellList) {
            if (matrix[cell[0]][cell[1]] == 'O' && (cell[1] >= columnSize-1)) {
                return true;
            }
        }
        return false;
    }
}
