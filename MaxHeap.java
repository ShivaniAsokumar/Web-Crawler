
public class MaxHeap extends Exception{
    private int[] A;
    private int heapSize;
    public MaxHeap(int[] A) {
        this.A = A;
        this.heapSize = 0;
    }

    public int Parent(int i){
        return i / 2;
    }

    public int Left(int i){
        return 2 * i;
    }

    public int Right(int i){
        return 2 * i + 1;
    }

    public void BuildMaxHeap(int[] A){
        heapSize = A.length;
        for (int i = A.length / 2; i >= 1; i--){
            this.MaxHeapify(A, i);
        }
    }

    public void MaxHeapify(int[] A, int i){
        int l = this.Left(i);
        int r = this.Right(i);
        int largest;

        if (l <= heapSize && A[l] > A[i]){ //If error, check A.length. Supposed to be A.heap-size
            largest = l;
        }
        else {
            largest = i;
        }

        if(r <= heapSize && A[r] > A[largest]){
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
        for (int i = A.length; i >= 2; i--){
            int temp = A[1];
            A[1] = A[i];
            A[i] = temp;
            heapSize = heapSize - 1;
            this.MaxHeapify(A, 1);
        }
    }

    public int HeapMaximum(int[] A){
        return A[1];
    }

    public int HeapExtractMax(int[] A) throws Exception{
        if (heapSize < 1){
            throw new Exception("Heap Underflow");
        }

        int max = A[1];
        A[1] = A[heapSize];
        heapSize -= 1;

        this.MaxHeapify(A, 1);
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
        heapSize += 1;
        A[heapSize] = Integer.MIN_VALUE;
        try {
            this.HeapIncreaseKey(A, heapSize, key);
        } catch (Exception e){
            System.out.println("Something wrong with HeapIncreaseKey");
        }
        
    }


}
