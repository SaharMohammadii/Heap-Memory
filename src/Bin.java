public class Bin {
    private int size;
    private int counter;
    private Bin binNext;
    private Chunk chunkNext;

    public Bin(int size){
        this.size = size;
        this.counter = 0;
        chunkNext = null;
        binNext = null;
    }

    public Bin getBinNext() {
        return binNext;
    }

    public void setBinNext(Bin binNext) {
        this.binNext = binNext;
    }

    public int getSize() {
        return size;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Chunk getChunkNext() {
        return chunkNext;
    }

    public void setChunkNext(Chunk chunkNext) {
        this.chunkNext = chunkNext;
    }
}
