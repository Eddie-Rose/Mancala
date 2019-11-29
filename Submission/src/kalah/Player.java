package kalah;

public class Player {

    private int _id;
    private int _score;

    public Player(){}

    public Player (int id){
        _id = id;
        _score = 0;
    }

    public int getId(){
        return _id;
    }

    public void updateScore(int score){
        _score = score;
    }

    public int getScore(){
        return _score;
    }
}
