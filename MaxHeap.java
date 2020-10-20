
import java.util.*;
public class MaxHeap extends Exception{
    private int heapSize;

    public MaxHeap() {
        
        this.heapSize = 0;
    }

    public int Parent(int i){
        return i / 2;
    }

    public int Left(int i){
        return 2 * i + 1; //We are starting at index 0 so we add 1
    }

    public int Right(int i){
        return 2 * i + 2; //We are starting at index 0 so we add 1
    }

    public void BuildMaxHeap(int[] A){
        heapSize = A.length;
        for (int i = A.length / 2; i >= 0; i--){ // i >= 0 because our index starts at 0
            this.MaxHeapify(A, i);
        }
    }

    public void MaxHeapify(int[] A, int i){
        int l = this.Left(i);
        int r = this.Right(i);
        int largest;

        if (l < heapSize && A[l] > A[i]){ // Changed to < because getting IndexOutOfBounds
            largest = l;
        }
        else {
            largest = i;
        }

        if(r < heapSize && A[r] > A[largest]){ // Changed to < because getting IndexOutOfBounds
            largest = r;
        }
        if(largest != i){
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;

            this.MaxHeapify(A, largest);
        }
    }


    public void Heapsort(int[] A){

        this.BuildMaxHeap(A);
        for (int i = A.length - 1; i > 0; i--){
            int temp = A[0]; //Changed from A[1] to A[0] to account for index = 0
            A[0] = A[i];
            A[i] = temp;
            heapSize = heapSize - 1;
            this.MaxHeapify(A, 0); //Changed from 1 to 0 to account for index = 0
        }
    }

    public int HeapMaximum(int[] A){
        return A[0];
    }

    public int HeapExtractMax(int[] A) throws Exception{
        if (heapSize < 1){
            throw new Exception("Heap Underflow");
        }
        // I am account for index = 0 by changing the 1

        int max = A[0];
        A[0] = A[heapSize - 1];
        heapSize--;

        this.MaxHeapify(A, 0);
        return max;
    }

    public void HeapIncreaseKey(int[] A, int i, int key) throws Exception{
        if (key < A[i]){
            throw new Exception("New Key is Smaller than Current Key");
        }
        A[i] = key;
        while(i > 1 && A[this.Parent(i)] < A[i]){
            int temp = A[i];
            A[i] = A[this.Parent(i)];
            A[this.Parent(i)] = temp;

            i = this.Parent(i);
        }
    }

    public void MaxHeapInsert(int[] A, int key){
        heapSize++;
        A[heapSize - 2] = Integer.MIN_VALUE;
        try {
            this.HeapIncreaseKey(A, heapSize - 2, key);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    

    public static void main(String[] args) {
        int[] A = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};

        MaxHeap heap = new MaxHeap();
        heap.BuildMaxHeap(A);
        try {
            heap.MaxHeapInsert(A, 15);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(Arrays.toString(A));
        
        

        
        
       
    }


}
