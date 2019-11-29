package kalah;

public class Pit {

    private int _index;
    protected int _seedCount;

    public Pit() {}

    public Pit(int index, int initSeedCount){
        _index = index;
        _seedCount = initSeedCount;
    }

    public int getSeedCount(){
        return _seedCount;
    }

    public void incrementSeedCount(int i){
        _seedCount += i;
    }
}
