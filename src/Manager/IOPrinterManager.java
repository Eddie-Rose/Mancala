package Manager;

import kalah.Board;
import com.qualitascorpus.testsupport.IO;
import kalah.Pit;
import kalah.Player;

import java.util.ArrayList;
import java.util.Map;

public abstract class IOPrinterManager {

    protected IO _io;

    protected IOPrinterManager (IO io){
        _io = io;
    }

    protected String getPlayerMove(Player player){
        String message = "Player P" + player.getId() + "'s turn - Specify house number or 'q' to quit: ";
        return _io.readFromKeyboard(message);
    }

    /**
     * Hard coded for a 2 player game
     * Can be refactored to suit a multi-player game with correct specifications
     * @param board
     * @param players
     */
    abstract void printBoard(Board board, ArrayList<Player> players);

    protected void printEmptyHousePicked(){
        _io.println("House is empty. Move again.");
    }

    protected void printGameOver() {
        _io.println("Game over");
    }

    protected void printPlayerScore(ArrayList<Player> players) {
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
