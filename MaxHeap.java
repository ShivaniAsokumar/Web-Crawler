
/**
 * This class builds a MaxHeap and contains its various methods.
 * @author Shivani Asokumar
 */
public class MaxHeap{

    private int heapSize;

    /**
     * Constructs a MaxHeap with a heapSize of 0.
     */
    public MaxHeap() {
        this.heapSize = 0;
    }

    /**
     * Gets the index of the Parent in the heap.
     * @param i The given index.
     * @return The index of the Parent in the heap.
     */
    public int Parent(int i){
        return i / 2;
    }

    /**
     * Gets the index of the Left node in the heap.
     * @param i The given index.
     * @return The index of the Left node in the heap.
     */
    public int Left(int i){
        return 2 * i + 1; //We are starting at index 0 so we add 1
    }

    /**
     * Gets the index of the Right node in the heap.
     * @param i The given index.
     * @return The index of the Right node in the heap.
     */
    public int Right(int i){
        return 2 * i + 2; //We are starting at index 0 so we add 1
    }

    /**
     * Builds a MaxHeap with a given PageRank array.
     * @param A The given PageRank array.
     */
    public void BuildMaxHeap(PageRank[] A){
        heapSize = A.length - 1;
        
        for (int i = A.length - 1 / 2; i >= 0; i--){ // i >= 0 because our index starts at 0
            this.MaxHeapify(A, i);
        }
    }

    /**
     * Makes the heap follow the rules of a MaxHeap.
     * @param A The given PageRank array.
     * @param i The given index where we start to MaxHeapify.
     */
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
            PageRank temp = A[i]; //Swaps A[i] and A[largest]
            A[i] = A[largest];
            A[largest] = temp;

            this.MaxHeapify(A, largest); // Maxifies largest.
        }
    }


    /**
     * Sorts the Heap in ascending order.
     * @param A The given PageRank array
     */
    public void Heapsort(PageRank[] A){

        this.BuildMaxHeap(A);
        for (int i = A.length - 1; i > 0; i--){
            PageRank temp = A[0]; // Swaps A[0] and A[i]
            A[0] = A[i];
            A[i] = temp;
            heapSize = heapSize - 1;
            this.MaxHeapify(A, 0); //Changed from 1 to 0 to account for index = 0
        }
    }

    /**
     * Gets the maximum element in the heap.
     * @param A The given PageRank array.
     * @return The maximum element in the heap.
     */
    public PageRank HeapMaximum(PageRank[] A){
        return A[0]; // Returns the root element since its the max.
    }

    /**
     * Gets the maximum element in the heap and removes it from the heap.
     * @param A The given PageRank array.
     * @return The maximum element in the heap.
     * @throws Exception
     */
    public PageRank HeapExtractMax(PageRank[] A) throws Exception{
        if (heapSize < 1){ // If the heapSize is less than 1, throws Exception
            throw new Exception("Heap Underflow");
        }

        // Swaps A[0] and A[heapSize]
        PageRank max = A[0];
        A[0] = A[heapSize];
        heapSize--; 

        // Maxifies the root element
        this.MaxHeapify(A, 0);
        return max; 
        
    }


    /**
     * Increases the key of a certain URL in PageRank array.
     * @param A The given PageRank array.
     * @param i The given index.
     * @param key The given PageRank object, which has both URL and Sum.
     * @throws Exception
     */
    public void HeapIncreaseKey(PageRank[] A, int i, PageRank key) throws Exception{
        if (key.getSum() < A[i].getSum()){ // If the give key is smaller than original key, throws Exception.
            throw new Exception("New Key is Smaller than Current Key");
        }
        
        A[i].setSum(key.getSum()); // Sets the new sum.
        A[i].setURL(key.getURL()); //Sets the new URL.

        while(i > 0 && A[this.Parent(i)].getSum() < A[i].getSum()){
            PageRank temp = A[i]; // Swaps A[i] and A[this.Parent(i)]
            A[i] = A[this.Parent(i)];
            A[this.Parent(i)] = temp;

            i = this.Parent(i);
        }
    } 

    /**
     * Inserts a given PageRank object into the PageRank array.
     * @param A The given PageRank array.
     * @param key The given PageRank object.
     */
    public void MaxHeapInsert(PageRank[] A, PageRank key){
        heapSize = A.length - 1;
        A[heapSize].setSum(Integer.MIN_VALUE);
        try {
            this.HeapIncreaseKey(A, heapSize, key); // Increases the key value for the last element.
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
