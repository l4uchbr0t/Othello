package othello.system;
import java.util.ArrayList;

public class Board {
    public static Board Board;

    int[][] board;
    int[] turnAmount;
    Turn whiteTurn, blackTurn;


    public Board (UserTurn whiteTurn, UserTurn blackTurn) {
        this.whiteTurn = whiteTurn;
        this.blackTurn = blackTurn;
        board = new int[8][8];
        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = -1;
            }
        }

        turnAmount = new int[] {0, 0};

        Board = this;
        placeBlock(0, new Position(3, 3));
        placeBlock(0, new Position(4, 4));
        placeBlock(1, new Position(3, 4));
        placeBlock(1, new Position(4, 3));
        gameloop();
    }

    private void gameloop() {
        print();
        whiteTurn.onTurn();
        this.print();
        blackTurn.onTurn();
        gameloop();
    }

    private boolean placeBlock(int team,Position position) {
        if(position.valid() && position.getX() < board.length && position.getY() < board[position.getY()].length) {
            board[position.getX()][position.getY()] = team;
            turnAmount[team]++;
            return true;
        }
        return false;
    }

    public boolean protectedPlaceBlock(int team, Position position){
        if(board[position.getX()][position.getY()] == -1 && castRays(team, position, false))  {
            castRays(team, position, true);
            return placeBlock(team, position);
        }
        return false;
    }

    public boolean tryprotectedPlaceBlock(int team, Position position){
        if(board[position.getX()][position.getY()] == -1 && castRays(team, position, false))  {
            castRays(team, position, true);
            return true;
        }
        return false;
    }

    private boolean replaceBlock(int team,Position position) {
        if(board[position.getX()][position.getY()] == (1 - team)) return placeBlock(team, position);
        return false;
    }

    private boolean castRays(int team,Position position, boolean replace){
        ArrayList<Position> replaces = new ArrayList<>();

        replaces.addAll(castRay(team, position,new Position(1, 1)));
        replaces.addAll(castRay(team, position,new Position(0, 1)));
        replaces.addAll(castRay(team, position,new Position(-1, 1)));
        replaces.addAll(castRay(team, position,new Position(1, 0)));
        replaces.addAll(castRay(team, position,new Position(0, 0)));
        replaces.addAll(castRay(team, position,new Position(-1,0)));
        replaces.addAll(castRay(team, position,new Position(1, -1)));
        replaces.addAll(castRay(team, position,new Position(0, -1)));
        replaces.addAll(castRay(team, position,new Position(-1, -1)));
        if(replaces.size() <= 0) return false;
        else {
            if(replace) replaces.forEach((p -> replaceBlock(team, p)));
            return true;
        }
    }

    private ArrayList<Position> castRay(int team,Position position,Position gradient) {
        ArrayList<Position> replaceBlocks = new ArrayList<>();
        Position gradPos = new Position(position.getX(), position.getY());


        while(true) {
            gradPos = gradPos.addGradient(gradient);
            if (gradPos.valid() && gradPos.getX() < 8 && gradPos.getY() < 8) {
                if(board[gradPos.getX()][gradPos.getY()] == -1) {
                    break;
                }
                else if(board[gradPos.getX()][gradPos.getY()] == team) return replaceBlocks;
                else if(board[gradPos.getX()][gradPos.getY()] == (1 - team)) replaceBlocks.add(gradPos);
            }else break;
        }

        return new ArrayList();
    }

    private void print() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int x = 0; x < board.length; x++) {
            System.out.print(x + " ");
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == -1)
                    System.out.print("- ");
                else
                    System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

    public Board getBoard(){
        return board;
    }

    public static void main(String[] args) {
        new Board(new UserTurn(0),new AuTurn(1));
    }
}
