
import java.util.ArrayList;

public class Board {
    public static Board Board;

    int[][] board;
    int[] turnAmount;
    Turn whiteTurn, blackTurn;
    int currentTeam;
    private ArrayList<Position> checkposition;

    public Board(Turn whiteTurn, Turn blackTurn) {
        this.whiteTurn = whiteTurn;
        this.blackTurn = blackTurn;
        board = new int[8][8];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = -1;
            }
        }

        turnAmount = new int[]{0, 0};
        currentTeam = 0;
        Board = this;
        placeBlock(0, new Position(3, 3));
        placeBlock(0, new Position(4, 4));
        placeBlock(1, new Position(3, 4));
        placeBlock(1, new Position(4, 3));

        checkposition = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkposition.add(new Position(i, j));
            }
        }

    }

    public boolean validturnavailable(int teamint) {
        for (Position p : checkposition) {
            if (board[p.getX()][p.getY()] == -1 && castRays(teamint, p, false)) {
                return true;
            }
        }
        return false;
    }

    public String getWinner() {
        int white = countStones(0);
        int black = countStones(1);

        if (black > white) {
            return "Der Gewinner ist Spieler 2 mit " + black + " Steinen!";
        } else if (white > black) {
            return "Der Gewinner ist Spieler 1 mit " + white + " Steinen!";
        } else {
            return "Unentschieden!";
        }
    }

    public String getWinnerATM() {
        int white = countStones(0);
        int black = countStones(1);

        if (black > white) {
            return "Der Gewinner wäre Spieler 2 mit " + black + " Steinen gewesen!";
        } else if (white > black) {
            return "Der Gewinner wäre Spieler 1 mit " + white + " Steinen gewesen!";
        } else {
            return "Es wäre ein Unentschieden gewesen!";
        }
    }

    public int countStones(int team){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0 && team == 0) {
                    count++;
                } else if (board[i][j] == 1 && team == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void onTurn(int x, int y, int team) {
        if (team == 0) {
            whiteTurn.onTurn(x, y);
        } else {
            blackTurn.onTurn(x, y);
        }
    }

    public void setCurrentTeam(int i){
        currentTeam = i;
    }

    private boolean placeBlock(int team, Position position) {
        if (position.valid() && position.getX() < board.length && position.getY() < board[position.getY()].length) {
            board[position.getX()][position.getY()] = team;
            turnAmount[team]++;
            return true;
        }
        return false;
    }


    public boolean protectedPlaceBlock(int team, Position position) {
        if (board[position.getX()][position.getY()] == -1 && castRays(team, position, false)) {
            castRays(team, position, true);
            return placeBlock(team, position);
        }
        return false;
    }

    private boolean replaceBlock(int team, Position position) {
        if (board[position.getX()][position.getY()] == (1 - team)) return placeBlock(team, position);
        return false;
    }

    public boolean castRays(int team, Position position, boolean replace) {
        ArrayList<Position> replaces = new ArrayList<>();

        replaces.addAll(castRay(team, position, new Position(1, 1)));
        replaces.addAll(castRay(team, position, new Position(0, 1)));
        replaces.addAll(castRay(team, position, new Position(-1, 1)));
        replaces.addAll(castRay(team, position, new Position(1, 0)));
        replaces.addAll(castRay(team, position, new Position(0, 0)));
        replaces.addAll(castRay(team, position, new Position(-1, 0)));
        replaces.addAll(castRay(team, position, new Position(1, -1)));
        replaces.addAll(castRay(team, position, new Position(0, -1)));
        replaces.addAll(castRay(team, position, new Position(-1, -1)));
        if (replaces.size() <= 0) return false;
        else {
            if (replace) replaces.forEach((p -> replaceBlock(team, p)));
            return true;
        }
    }

    private ArrayList<Position> castRay(int team, Position position, Position gradient) {
        ArrayList<Position> replaceBlocks = new ArrayList<>();
        Position gradPos = new Position(position.getX(), position.getY());


        for (int i = 0; i < 64; i++) {
            gradPos = gradPos.addGradient(gradient);
            if (gradPos.valid() && gradPos.getX() < 8 && gradPos.getY() < 8) {
                if (board[gradPos.getX()][gradPos.getY()] == -1) {
                    break;
                } else if (board[gradPos.getX()][gradPos.getY()] == team){
                    return replaceBlocks;
                }
                else if (board[gradPos.getX()][gradPos.getY()] == (1 - team)){
                    replaceBlocks.add(gradPos);
                }
            } else {
                break;
            }
        }

        return new ArrayList();
    }


    public int getstate(int i, int j) {
        return board[i][j];
    }

    public int getturnamount(int team){
        return turnAmount[team];
    }

    public static Board getBoard() {
        return Board;
    }

}
