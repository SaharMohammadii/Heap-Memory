public class Chunk {
    int size;
    Chunk next;
    Chunk prev;
    Chunk bNext; //in the bin
    Chunk bPrev;
    Bin binPrev; // just for relating the first Chunk to bin.
    public Chunk(int size){
        this.size=size;
        next=null;
        prev=null;
        bNext=null;
        bPrev=null;
        binPrev=null;
    }
}
