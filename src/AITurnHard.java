
import java.util.ArrayList;
import java.util.Collections;

public class AITurnHard extends Turn {

    private static final int squareScores[][] = new int[][]
            {{50, 1, 3, 4, 4, 3, 1, 50},
                    {1, 0, 2, 2, 2, 2, 0, 1},
                    {3, 2, 3, 2, 2, 3, 2, 3},
                    {4, 2, 2, 0, 0, 2, 2, 4},
                    {4, 2, 2, 0, 0, 2, 2, 4},
                    {3, 2, 3, 2, 2, 3, 2, 3},
                    {1, 0, 2, 2, 2, 2, 0, 1},
                    {50, 1, 3, 4, 4, 3, 1,50}};

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
        checkCorners();
        ArrayList<Position> temppos = new ArrayList<>();
        while(pos.size() != 0) {
            if (validturn(0, 0)) {
                temppos.add(pos.get(0));
            }
            pos.remove(0);
        }
        pos = temppos;
        Collections.shuffle(pos);
        doit();
    }

    public void doit() {
        while (pos.size() > 1) {
            if (squareScores[pos.get(1).getX()][pos.get(1).getY()] <= squareScores[pos.get(0).getX()][pos.get(0).getY()]) { //prüfen ob 1 höher als 0
                pos.remove(1);
            } else {
                pos.remove(0);
            }
        }
        Board.Board.protectedPlaceBlock(team, pos.get(0));
    }

    public boolean validturn(int x, int y) {
        Board b = Board.getBoard();
        return b.board[pos.get(0).getX()][pos.get(0).getY()] == -1 && b.castRays(team, pos.get(0), false);
    }

    public int getStance() {
        return 1;
    }

    public void checkCorners(){
        if (Board.getBoard().board[0][0] == team){
            squareScores[1][1] = 4;
        }
        if (Board.getBoard().board[0][7] == team){
            squareScores[1][6] = 4;
        }
        if (Board.getBoard().board[7][0] == team){
            squareScores[6][1] = 4;
        }
        if (Board.getBoard().board[7][7] == team){
            squareScores[6][6] = 4;
        }
    }
}
