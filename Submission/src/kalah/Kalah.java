package kalah;

import Manager.GameManager;
import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	//variables to be changed to suit the type of game
	int _numOfPlayers = 2;
	int _numOfHousesPerPlayer = 6;
	int _initSeedCount = 4;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {

		GameManager game = new GameManager(_numOfPlayers, _numOfHousesPerPlayer, _initSeedCount, io);
		game.startGame();
		game.endGame();
	}
}