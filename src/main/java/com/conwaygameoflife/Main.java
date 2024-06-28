package com.conwaygameoflife;

import com.conwaygameoflife.utils.GameUtil;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameUtil gameUtil = new GameUtil();

        switch (args.length) {
            case 0:
                System.out.println("Please provide the input file name");
                break;
            case 1:
                gameUtil.play(args[0], null);
                break;
            case 2:
                gameUtil.play(args[0], args[1]);
                break;
            default:
                System.out.println("Invalid input in main");
                break;
        }
    }
}
