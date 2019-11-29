package Manager;

import com.qualitascorpus.testsupport.IO;
import kalah.Board;
import kalah.Pit;
import kalah.Player;

import java.util.ArrayList;
import java.util.Map;

public class HorizontalPrinter extends IOPrinterManager {
    protected HorizontalPrinter(IO io) {
        super(io);
    }

    @Override
    void printBoard(Board board, ArrayList<Player> players) {
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


}
