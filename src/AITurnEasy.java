
import java.util.ArrayList;
import java.util.Collections;

public class AITurnEasy extends Turn {

    private ArrayList<Position> pos = new ArrayList<>();

    public AITurnEasy(int team) {
        super(team);
    }

    @Override
    public void onTurn(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pos.add(new Position(i, j));
            }
        }
        ArrayList<Position> temppos = new ArrayList<>();
        while(pos.size() != 0) {
            if (validturn(0, 0)) {
                temppos.add(pos.get(0));
            }
            pos.remove(0);
        }
        pos = temppos;
        Collections.shuffle(pos);
        Board.Board.protectedPlaceBlock(team, pos.get(0));
    }

    public boolean validturn(int x, int y) {
        Board b = Board.getBoard();
        return b.board[pos.get(0).getX()][pos.get(0).getY()] == -1 && b.castRays(team, pos.get(0), false);
    }

    public int getStance() {
        return 1;
    }
}
