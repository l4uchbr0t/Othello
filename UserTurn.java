package othello.system;

import java.util.Scanner;


public class UserTurn extends Turn{

    Scanner input;

    public UserTurn(int team) {
        super(team);
        input = new Scanner(System.in);
    }

    @Override
    public void onTurn() {
        while (true) { //Nicht gut
            System.out.print(String.valueOf(team) + ": ");
            String line = input.nextLine(); //Eingabe für Spieler
            boolean bol = Board.Board.protectedPlaceBlock(team, new Position(Integer.parseInt(line.substring(1,2)), Integer.parseInt(line.substring(0,1))));
            if(bol) break;
        }
    }
}
