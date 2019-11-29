package kalah;

public abstract class Pit {

    protected int _index;
    protected int _seedCount;

    protected Pit() {}

    protected Pit(int index, int initSeedCount){
        _index = index;
        _seedCount = initSeedCount;
    }

    public int getSeedCount(){
        return _seedCount;
    }

    protected void incrementSeedCount(int i){
        _seedCount += i;
    }
}
