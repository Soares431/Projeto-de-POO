package System;

import Models.Player;

import java.util.Stack;

public class Controller {

    public static void main(String[] args) {

        String name = "Kujikun";
        String occupation = "Conhecedor de TI";
        int level = 0;
        double xp = 0;
        int network = 0;

        Player player = new Player(name, occupation, level, xp, network);
    }
}
