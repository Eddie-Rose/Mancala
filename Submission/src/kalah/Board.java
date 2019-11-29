package kalah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Player, ArrayList<Pit>> _board;
    private ArrayList<Player> _players;

    public Board(ArrayList<Player> players, int numOfHouses, int numOfInitSeed){
        _board = new HashMap<Player, ArrayList<Pit>>();
        initBoard(players, numOfHouses, numOfInitSeed);
    }

    private void initBoard(ArrayList<Player> players, int numOfHouses, int numOfInitSeed){
        _players = players;
        for (Player player : players){
            ArrayList<Pit> playerRowBoard = new ArrayList<Pit>();
            int i = 0;
            while (i < numOfHouses){
                playerRowBoard.add(new House(i, numOfInitSeed));
                i++;

            }

            playerRowBoard.add(new Store(i));
            _board.put(player, playerRowBoard);
        }
    }

    public TurnState pickHouse(Player player, int houseNumber){
        //Adjustment for house number to appropriate index number in Array List
        int pitNumber = houseNumber - 1;
        Player playerTurn = player;
        int seedCount = _board.get(player).get(pitNumber).getSeedCount();
        int maxHouseCount = _board.get(player).size();

        //Case when the chosen house contain no seeds
        if (seedCount == 0){
            return TurnState.EMPTY_HOUSE;
        }

        ((House) _board.get(player).get(pitNumber)).resetSeedCount();

        while (seedCount != 0){
            pitNumber++;
            if ((player.getId() != playerTurn.getId()) && (_board.get(player).get(pitNumber) instanceof Store)) {
                pitNumber = 0;
                player = getNextPlayer(player);
            }
            else if(pitNumber > _board.get(player).size() - 1){
                pitNumber = 0;
                player = getNextPlayer(player);
            }

            if (_board.get(player).get(pitNumber) instanceof Store) {
                if (player.getId() == playerTurn.getId()){
                    System.out.println(_board.get(player).get(pitNumber)._seedCount);
                    _board.get(player).get(pitNumber).incrementSeedCount(1);
                    player.updateScore(_board.get(player).get(pitNumber)._seedCount);
                    seedCount--;
                }
                continue;
            }

            _board.get(player).get(pitNumber).incrementSeedCount(1);
            seedCount--;

        }
        if (player.getId() == playerTurn.getId()) {
            if (_board.get(player).get(pitNumber) instanceof Store){
                return TurnState.EXTRA_TURN;
            }
            else if (_board.get(player).get(pitNumber)._seedCount == 1){
                int captureSeedCount = 1;

                int oppositePitNumber = maxHouseCount - pitNumber - 2;
                Player nextPlayer = getNextPlayer(player);

                if(_board.get(nextPlayer).get(oppositePitNumber).getSeedCount() > 0) {
                    captureSeedCount += _board.get(nextPlayer).get(oppositePitNumber).getSeedCount();
                    ((House) _board.get(player).get(pitNumber)).resetSeedCount();
                    ((House) _board.get(nextPlayer).get(oppositePitNumber)).resetSeedCount();
                    _board.get(player).get(maxHouseCount - 1).incrementSeedCount(captureSeedCount);
                    player.updateScore(_board.get(player).get(maxHouseCount - 1)._seedCount);
                }
            }
        }

        return TurnState.NEXT_PLAYER_TURN;
    }

    public GameState checkMovePossible(Player player){
        for (Pit pit : _board.get(player)){
            if ((pit instanceof House) && (pit.getSeedCount() != 0)){
                return GameState.PLAY;
            }
        }
        return GameState.GAME_FINISH;
    }

    public Map<Player, ArrayList<Pit>> getBoard() {
        return _board;
    }

    private Player getNextPlayer(Player player){
        int index = _players.indexOf(player);
        index++;
        if (index >= _players.size()){
            player = _players.get(0);
        } else {
            player = _players.get(index);
        }
        return player;
    }

    public void moveSeedToStore(){
        for(Player player : _players){
            int seedCount = 0;
            for (Pit pit : _board.get(player)){
                if (pit instanceof House){
                    seedCount += pit._seedCount;
                }
                if (pit instanceof Store){
                    player.updateScore((pit._seedCount) + seedCount );
                }
            }
        }
    }
}
