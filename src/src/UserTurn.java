package src;

import java.util.ArrayList;
import java.util.Scanner;


public class UserTurn extends Turn {
    private ArrayList<Position> pos = new ArrayList<>();
    String line;

    public UserTurn(int team) {
        super(team);

    }

    @Override
    public void onTurn(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pos.add(new Position(i, j));
            }
        }
        //if (validturnavailable()) {
            doit(x, y);
        //}

    }

    public void doit(int x, int y) {

            boolean bol = validturn(x, y);
            if (bol){
                Board.Board.protectedPlaceBlock(team, new Position(x, y));
                return;
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

    public void setLine(String line) {
        this.line = line;
    }

    public int getStance(){
        return 0;
    }

    public boolean validturn(int x, int y) {
        Board b = Board.getBoard();
        if (b.protectedPlaceBlock(team, new Position(x, y))) {
            return true;
        } else {
            return false;
        }
    }
}
