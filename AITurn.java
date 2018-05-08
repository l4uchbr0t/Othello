package othello.system;

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

    private ArrayList<Position> pos;
    
    
    
    public AITurn(int team) {
        super(team);
        for (int i = 0, i < 9, i++) {
            for(int j = 0, j < 9, j++){
                pos.add(new Position(i, j));
            }
        }
    }


    @Override
    public void onTurn() {
        Board b = board.getBoard();
        for (Position p: pos) {
            if(b.castRays)
        }
    }
}
