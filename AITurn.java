package othello.system;
import java.util.ArrayList;

public class AITurn extends Turn {

    private static final int squareScores[][] = new int[][]
                    {{50, -1, 5, 2, 2, 5, -1, 50},
                    {-1, -10, 1, 1, 1, 1, -10, -1},
                    {5, 1, 1, 1, 1, 1, 1, 5},
                    {2, 1, 1, 0, 0, 1, 1, 2},
                    {2, 1, 1, 0, 0, 1, 1, 2},
                    {5, 1, 1, 1, 1, 1, 1, 5},
                    {-1, -10, 1, 1, 1, 1, -10, -1},
                    {50, -1, 5, 2, 2, 5, -1, 50}};

    private ArrayList<Position> pos = new ArrayList<Position>();
    
    
    
    public AITurn(int team) {
        super(team);
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                pos.add(new Position(i, j));
            }
        }
    }


    @Override
    public void onTurn() {
        Board b = Board.getBoard();
        if(b.tryProtectedPlaceBlock(team, pos.get(0))){      
            while (pos.get(1) != null) {
                if (b.tryProtectedPlaceBlock(team, pos.get(1))) {
                    if (squareScores[pos.get(1).getX()][pos.get(1).getY()] <= squareScores[pos.get(0).getX()][pos.get(0).getY()]) { //prüfen ob 1 höher als 0
                        pos.remove(1);
                    }else {
                        pos.remove(0);
                    }
                }
            }
            Board.Board.protectedPlaceBlock(team, pos.get(0));
        }else{
            pos.remove(0);
            onTurn();
        }
    }
}
