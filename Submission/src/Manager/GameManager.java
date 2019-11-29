package Manager;

import com.qualitascorpus.testsupport.IO;
import exception.EmptyInputException;
import exception.QuitException;
import kalah.*;

import java.util.ArrayList;

public class GameManager {

    private int _numOfPlayers;
    private int _numOfHouses;
    private int _numOfInitSeedsPit;

    private IOPrinterManager _printerManager;
    private InputManager _inputManager;
    private IO _io;

    private Board _gameBoard;
    private ArrayList<Player> _players;
    private Player _playerTurn;
    private GameState _gameState;

    public GameManager(){ }

    /**
     * Sets the game parameters and starts up a new game
     * @param numOfPlayers
     * @param numOfHouses
     * @param numOfInitSeed
     * @param io
     */
    public GameManager(int numOfPlayers, int numOfHouses, int numOfInitSeed, IO io){
        _numOfPlayers = numOfPlayers;
        _numOfHouses = numOfHouses;
        _numOfInitSeedsPit = numOfInitSeed;
        _io = io;

        newGame();
    }

    /**
     * Sets up the board and all the managers
     */
    public void newGame(){
        initialisePlayers();
        _gameBoard = new Board(_players, _numOfHouses, _numOfInitSeedsPit);
        _printerManager = new IOPrinterManager(_io);
        _inputManager = new InputManager();
        _gameState = GameState.PLAY;

    }

    /**
     * Main game logic when the game is on going.
     */
    public void startGame(){
        _printerManager.printBoard(_gameBoard, _players);

        String input;
        int houseNumber;
        TurnState turnState;

        //Loops until quit or game over
        while (_gameState == GameState.PLAY){
            input = _printerManager.getPlayerMove(_playerTurn);
            try {
                _inputManager.verifyInput(input);
            } catch (QuitException e) {
                _gameState = GameState.QUIT;
                break;
            } catch (EmptyInputException e){
                continue;
            } catch (NumberFormatException e){
                continue;
            }

            houseNumber = Integer.parseInt(input);

            //Player turn, analyse the game board for the next possible move
            turnState = _gameBoard.pickHouse(_playerTurn, houseNumber);
            analyseTurnState(turnState);
            _gameState = _gameBoard.checkMovePossible(_playerTurn);

        }
    }

    private void analyseTurnState(TurnState turnState){
        switch (turnState){
            case EMPTY_HOUSE:
                _printerManager.printEmptyHousePicked();
                _printerManager.printBoard(_gameBoard, _players);
                break;

            case NEXT_PLAYER_TURN:

                int index = _players.indexOf(_playerTurn);
                index++;

                if (index >= _numOfPlayers){
                    index = 0;
                }

                _playerTurn = _players.get(index);
                _printerManager.printBoard(_gameBoard, _players);
                break;

            case EXTRA_TURN:
                _printerManager.printBoard(_gameBoard, _players);
                break;
        }
    }

    public void endGame(){
        switch(_gameState){
            case GAME_FINISH:
                _gameBoard.moveSeedToStore();
                _printerManager.printGameOver();
                _printerManager.printBoard(_gameBoard, _players);
                _printerManager.printPlayerScore(_players);
                break;

            case QUIT:
                _printerManager.printGameOver();
                _printerManager.printBoard(_gameBoard, _players);
                break;
        }
    }

    private void initialisePlayers(){

        _players = new ArrayList<Player>();
        for (int i = 1; i <= _numOfPlayers; i++){
            _players.add(new Player(i));
        }

        _playerTurn = _players.get(0);
    }



}
