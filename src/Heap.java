public class Heap {
    Chunk headChunk;
    Chunk currentChunk;
    Bin headBin;
    Bin currentBin;
    int numberOfChunks;// it contains the headChunk.

    public Heap(int sizeOfHeadChunk) {
        headChunk = new Chunk(sizeOfHeadChunk);
        numberOfChunks = 1;
        headBin = new Bin(8);  // making Bin lists.
        currentBin = headBin;

        for (int i = 2; i <= 32; i++) {
            currentBin.setBinNext(new Bin(i * 8));
            currentBin = currentBin.getBinNext();
        }
    }

    public void malloc(int size) {
        if (headChunk.size<size){
            System.out.println("wanted size is more than size of Heap!!");
            System.out.println("-------------------------");

        }else {
            Chunk lastChunk;
            Chunk helper = searchInTheBin(size);
            if (helper != null) { //if the counter of bin (size) is more than zero, return chunk.
                lastChunk = helper;
                if (lastChunk.next == null) {
                    lastChunk.prev.next = lastChunk;
                    headChunk.size -= size;
                } else {
                    lastChunk.next.prev = lastChunk;
                    lastChunk.prev.next = lastChunk;
                    headChunk.size -= size;
                }
            } else {
                lastChunk = new Chunk(size);
                lastChunk.next = headChunk.next;
                lastChunk.prev = headChunk;
                if (headChunk.next != null)//in shart baraye ine ke field prev e chunk e baad head ,eshare kone be lastchunk(chunk jadid)
                    headChunk.next.prev = lastChunk;//8 ro prev 16 mikone
                headChunk.next = lastChunk;
                headChunk.size -= size;
            }
            numberOfChunks++;
            print();
        }
    }
    public void free(int size) {
        boolean isChunkFound = false;
        if (isHeapEmpty()) {
            System.out.println("the Heap is empty!\n" +
                    "there's no chunk to get free.");
        } else {
            currentChunk = headChunk.next;
            while (!isChunkFound && currentChunk.next != null) {
                if (currentChunk.size == size) {
                    if (currentChunk.prev == headChunk) {
                        headChunk.next = currentChunk.next;
                        currentChunk.next.prev = headChunk;
                        numberOfChunks--;
                        headChunk.size += size;
                        isChunkFound = true;
                    }
                    else {
                        currentChunk.prev.next = currentChunk.next;
                        currentChunk.next.prev = currentChunk.prev;
                        numberOfChunks--;
                        headChunk.size += size;
                        isChunkFound = true;
                        mallocInBin(currentChunk);
                    }
                } else {
                    currentChunk = currentChunk.next;
                }
            }
            if (currentChunk.next == null) {// if chunk doesn't found and we arrive the end of the heap.
                if (currentChunk.size == size && numberOfChunks == 2) { // if there's just one chunk and it has the wanted size.
                    headChunk.size += size;                             // it's 2 because it contains the head chunk.
                    headChunk.next = null;
                    numberOfChunks--;
                } else {
                    if (currentChunk.size == size && numberOfChunks > 2) {// it's 2 because it contains the head chunk.
                        currentChunk.prev.next = null;
                        numberOfChunks--;
                        headChunk.size += size;
                        mallocInBin(currentChunk);
                    } else {
                        System.out.println("no size " + size + " chunk found!");
                    }
                }
            }
        }
        print();
    }

    public boolean isHeapEmpty() {
        return (headChunk.next == null);
    }

    public void print() {
        currentChunk = headChunk.next;
        System.out.println("\nRemaining Memory : " + headChunk.size);
        for (int i = 0; i < numberOfChunks - 1; i++) {
            System.out.print("- " + currentChunk.size + " ");
            currentChunk = currentChunk.next;
        }
        System.out.println();
        Bin item = headBin;
        while (item.getBinNext() != null) {
            if (item.getCounter() != 0) {
                System.out.println("bin " + item.getSize() / 8 + " (" + item.getSize() + "): " + item.getCounter());
            }
            item = item.getBinNext();
        }
        System.out.println("\n-------------------------");
    }
    // Sahar's codes

    public Chunk searchInTheBin(int size) {   //if there's any chunk,returns that. else returns NULL.
        Bin item = headBin;
        boolean isBinFound = false;
        while (!isBinFound) {
            if (item.getSize() == size) {
                if (item.getCounter() == 1) {
                    Chunk one = item.getChunkNext();
                    item.setChunkNext(null);
                    item.setCounter(0);
                    return one;
                } else if (item.getCounter() > 1) {
                    Chunk travers = item.getChunkNext();
                    while (travers.bNext.bNext != null) {
                        travers = travers.bNext;
                    }
                    Chunk last = travers.bNext;
                    travers.bNext = null;
                    item.setCounter(item.getCounter() - 1);
                    return last;
                }
                isBinFound = true;
            } else
                item = item.getBinNext();
        }
        return null;
    }

    public void mallocInBin(Chunk chunk) {
        Bin item = headBin;
        boolean isBinFound = false;
        while (!isBinFound) {
            if (chunk.size == item.getSize()) {
                if (item.getCounter() == 0) {
                    item.setChunkNext(chunk);
                    chunk.binPrev = item;
                } else {
                    Chunk travers = item.getChunkNext();
                    while (travers.bNext != null) {
                        travers = travers.bNext;
                    }
                    travers.bNext = chunk;
                    chunk.bPrev = travers;
                }
                item.setCounter(item.getCounter() + 1);
                isBinFound = true;
            } else {
                item = item.getBinNext();
            }
        }
    }
}
