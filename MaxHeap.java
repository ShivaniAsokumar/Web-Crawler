
import java.util.*;
public class MaxHeap extends PageRank{
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

    public void BuildMaxHeap(PageRank[] A){
        heapSize = A.length;
        for (int i = A.length / 2; i >= 0; i--){ // i >= 0 because our index starts at 0
            this.MaxHeapify(A, i);
        }
    }


    public void MaxHeapify(PageRank[] A, int i){
        int l = this.Left(i);
        int r = this.Right(i);
        int largest;

        if (l < heapSize && A[l].getSum() > A[i].getSum()){ // Changed to < because getting IndexOutOfBounds
            largest = l;
        }
        else {
            largest = i;
        }

        if(r < heapSize && A[r].getSum() > A[largest].getSum()){ // Changed to < because getting IndexOutOfBounds
            largest = r;
        }
        if(largest != i){
            int temp = A[i].getSum();
            A[i].setSum(A[largest].getSum());
            A[largest].setSum(temp);

            this.MaxHeapify(A, largest);
        }
    }

    public void Heapsort(PageRank[] A){

        this.BuildMaxHeap(A);
        for (int i = A.length - 1; i > 0; i--){
            int temp = A[0].getSum(); //Changed from A[1] to A[0] to account for index = 0
            A[0].setSum(A[i].getSum());
            A[i].setSum(temp);
            heapSize = heapSize - 1;
            this.MaxHeapify(A, 0); //Changed from 1 to 0 to account for index = 0
        }
    }

    public int HeapMaximum(int[] A){
        return A[0];
    }

    public PageRank HeapExtractMax(PageRank[] A) throws Exception{
        if (heapSize < 1){
            throw new Exception("Heap Underflow");
        }
        // I am account for index = 0 by changing the 1

        int max = A[0].getSum();
        A[0].setSum(A[heapSize - 1].getSum());
        heapSize--;

        this.MaxHeapify(A, 0);
        // return max 
        return A[0]; // ! Not sure if we are supposed to return the max score or the max object
        
    }

    public void HeapIncreaseKey(PageRank[] A, int i, int key) throws Exception{
        if (key < A[i].getSum()){
            throw new Exception("New Key is Smaller than Current Key");
        }
        A[i].setSum(key);
        while(i > 1 && A[this.Parent(i)].getSum() < A[i].getSum()){
            int temp = A[i].getSum();
            A[i].setSum(A[this.Parent(i)].getSum());
            A[this.Parent(i)].setSum(temp);

            i = this.Parent(i);
        }
    }

    // ! When inserting, it doesn't add element. It just increases key of last element
    public void MaxHeapInsert(PageRank[] A, int key){
        heapSize++;
        A[heapSize - 2].setSum(Integer.MIN_VALUE);
        try {
            this.HeapIncreaseKey(A, heapSize - 2, key);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }


    /**
     * TODO: Questions
     * * For MaxHeapInsert, my method is only increasing the key of the last element. It isn't adding the element. Is it supposed to add the element?
     * * What do you mean my commment at the end of lines?
     * * For HeapExtractMax, are we returning the sum or are we returning the URL only? I am returning a PageRank object has both. Is that okay?
     * * For the write-up are we supposed to include the screenshots input and output for EVERY method?
     * * For the files to turn in you have, we have to turn in both jar files and java files?
     */
    

}
