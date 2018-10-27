
import java.util.ArrayList;
import java.util.Scanner;


public class UserTurn extends Turn {
    private ArrayList<Position> pos = new ArrayList<>();

    public UserTurn(int team) {
        super(team);

    }

    @Override
    public void onTurn(int x, int y) {
        if(validturn(x,y)){
            Board.Board.protectedPlaceBlock(team, new Position(x, y));
        }
    }

    public int getStance(){
        return 0;
    }

    public boolean validturn(int x, int y) {
        Board b = Board.getBoard();
        return b.protectedPlaceBlock(team, new Position(x, y));
    }
}
