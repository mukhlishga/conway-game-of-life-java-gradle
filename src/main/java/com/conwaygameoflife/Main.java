package com.conwaygameoflife;

import com.conwaygameoflife.utils.InputParser;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InputParser parser = new InputParser();

        switch (args.length) {
            case 0:
                System.out.println("Please provide the input file name");
                break;
            case 1:
                parser.parseInput(args[0], null);
                break;
            case 2:
                parser.parseInput(args[0], args[1]);
                break;
            default:
                System.out.println("Invalid input in main");
                break;
        }
    }
}
