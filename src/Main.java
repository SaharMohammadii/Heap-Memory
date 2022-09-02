import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOfHeap = scanner.nextInt();
        int numberOfOrders = scanner.nextInt();
        Heap heap = new Heap(sizeOfHeap);
        String free = "free", malloc = "malloc";
        String order;
        int sizeForFree, sizeForMalloc;

        for (int i = 0; i < numberOfOrders; i++) {
            order=scanner.next();
            if (free.equals(order)) {
                sizeForFree = scanner.nextInt();
                heap.free(sizeForFree);
            } else if (malloc.equals(order)) {
                sizeForMalloc = scanner.nextInt();
                heap.malloc(sizeForMalloc);
            }
        }
    }
}
