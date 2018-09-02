package src;

import java.util.ArrayList;

public class AITurnHard extends Turn {

    private static final int squareScores[][] = new int[][]
            {{50, 1, 5, 4, 4, 5, 1, 50},
                    {1, 0, 2, 2, 2, 2, 0, 1},
                    {5, 2, 2, 2, 2, 2, 2, 5},
                    {4, 2, 2, 0, 0, 2, 2, 4},
                    {4, 2, 2, 0, 0, 2, 2, 4},
                    {5, 2, 2, 2, 2, 2, 2, 5},
                    {1, 0, 2, 2, 2, 2, 0, 1},
                    {50, 1, 5, 4, 4, 5, 1, 50}};

    private ArrayList<Position> pos = new ArrayList<>();


    public AITurnHard(int team) {
        super(team);
    }


    @Override
    public void onTurn(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pos.add(new Position(i, j));
            }
        }

        doit();

    }

    public void doit() {
        if (pos.size() != 0) {
            if (validturn(0, 0)) {
                while (pos.size() > 1) {
                    if (validturn2(0, 0)) {
                        if (squareScores[pos.get(1).getX()][pos.get(1).getY()] <= squareScores[pos.get(0).getX()][pos.get(0).getY()]) { //prüfen ob 1 höher als 0
                            pos.remove(1);
                        } else {
                            pos.remove(0);
                        }
                    } else {
                        pos.remove(1);
                    }
                }
                Board.Board.protectedPlaceBlock(team, pos.get(0));
            } else {
                pos.remove(0);

            }
            doit();
        }
    }

    public boolean validturn(int x, int y){
        Board b = Board.getBoard();
        if (b.board[pos.get(0).getX()][pos.get(0).getY()] == -1 && b.castRays(team, pos.get(0), false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validturn2(int x, int y) {
        Board b = Board.getBoard();
        if (b.board[pos.get(1).getX()][pos.get(1).getY()] == -1 && b.castRays(team, pos.get(1), false)) {
            return true;
        } else {
            return false;
        }
    }

    public int getStance() {
        return 1;
    }
}
