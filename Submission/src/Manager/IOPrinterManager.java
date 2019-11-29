package Manager;

import kalah.Board;
import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Pit;
import kalah.Player;

import java.util.ArrayList;
import java.util.Map;

public class IOPrinterManager {

    private IO _io;

    public IOPrinterManager (IO io){
        _io = io;
    }

    public String getPlayerMove(Player player){
        String message = "Player P" + player.getId() + "'s turn - Specify house number or 'q' to quit: ";
        return _io.readFromKeyboard(message);
    }

    /**
     * Hard coded for a 2 player game
     * Can be refactored to suit a multi-player game with correct specifications
     * @param board
     * @param players
     */
    public void printBoard(Board board, ArrayList<Player> players){

        Map<Player, ArrayList<Pit>> gameBoard = board.getBoard();
        ArrayList<Pit> gameBoardPlayerRow;

        gameBoardPlayerRow = gameBoard.get(players.get(1));


        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        _io.print("| P2 |");

        for (int i = 6 ; i > 0 ; i--){
            _io.print(" " + i + "[" + String.format("%2d",(gameBoardPlayerRow.get(i - 1)).getSeedCount()) + "] |");
        }

        _io.println(" " + String.format("%2d",(gameBoard.get(players.get(0)).get(gameBoardPlayerRow.size() - 1)).getSeedCount()) + " |");
        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        gameBoardPlayerRow = gameBoard.get(players.get(0));
        _io.print("| " + String.format("%2d",(gameBoard.get(players.get(1)).get(gameBoardPlayerRow.size() - 1)).getSeedCount()) + " |");

        for (int i = 1 ; i <= 6 ; i++){
            _io.print(" " + i + "[" + String.format("%2d",(gameBoardPlayerRow.get(i - 1).getSeedCount())) + "] |");
        }

        _io.println(" P1 |");
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");

    }

    public void printEmptyHousePicked(){
        _io.println("House is empty. Move again.");
    }

    public void printGameOver() {
        _io.println("Game over");
    }

    public void printPlayerScore(ArrayList<Player> players) {
        Player bestPlayer = null;
        int bestScore = 0;

        for (Player player : players){
            _io.println( "\tplayer " + player.getId() + ":" + player.getScore());
            if (player.getScore() > bestScore) {
                bestPlayer = player;
                bestScore = player.getScore();
            }
            else if (player.getScore() == bestScore){
                bestPlayer = null;
            }
        }

        if (bestPlayer == null){
            _io.println("A tie!");
        }
        else {
            _io.println("Player " + bestPlayer.getId() + " wins!");
        }
    }

}
