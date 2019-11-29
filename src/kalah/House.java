package kalah;

public class House extends Pit {

    public House (int index, int initSeed){
        super(index, initSeed);
    }

    public void resetSeedCount(){
        _seedCount = 0;
    }
}
