package src;

import java.util.ArrayList;
import java.util.Scanner;


public class UserTurn extends Turn {

    Scanner input;
    private ArrayList<Position> pos = new ArrayList<>();

    public UserTurn(int team) {
        super(team);
        input = new Scanner(System.in);
    }

    @Override
    public void onTurn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pos.add(new Position(i, j));
            }
        }
        if (validturnavailable()) {
            doit();
        }

    }

    public void doit() {
        while (true) { //Nicht gut
            System.out.print(String.valueOf(team) + ": ");
            String line = input.nextLine(); //Eingabe für Spieler
            boolean bol = Board.Board.protectedPlaceBlock(team, new Position(Integer.parseInt(line.substring(1, 2)), Integer.parseInt(line.substring(0, 1))));
            if (bol) break;
        }
    }

    public boolean validturnavailable() {
        Board b = Board.getBoard();
        while (true) {
            if (b.protectedPlaceBlock(team, pos.get(0))) {
                return true;
            } else {
                if (pos.size() == 0) {
                    return false;
                } else {
                    pos.remove(0);
                }
            }
        }
    }
}
