import java.util.*;
/**
 * This class is used to run all of the methods in this project.
 * @author Shivani Asokumar
 */
public class WebCrawlerRunner{
    
    public static void main(String[] args) {
        // Enter Keyword
        System.out.println("Please Enter the Keyword: ");
        Scanner scan = new Scanner(System.in);
        String keyword = scan.nextLine();

    
        // Set up WebCrawler
        System.out.println("Would you like to view the 30 URLs? (Y): ");
        WebCrawler crawler = new WebCrawler(keyword);
        crawler.search();
        String viewURLs = scan.nextLine();
        Set<String> urls = crawler.getUrls();

        // Allow users to see 30 URLs.
        if(viewURLs.equals("Y") || viewURLs.equals("y") || viewURLs.equals("Yes") || viewURLs.equals("yes")){
            crawler.PrintSet(urls);
        }
        
        // Allow users to view the 4 PageRank scores
        System.out.println("Would you like to view the 4 PageRank scores? (Y): ");
        String viewPageRankScores = scan.nextLine();
        PageRank[] p = crawler.CalculatePageRank(urls);

        if(viewPageRankScores.equals("Y") || viewPageRankScores.equals("y") || viewPageRankScores.equals("Yes") || viewPageRankScores.equals("yes")){
            for(PageRank pageRank : p){
                System.out.println("=============");
                System.out.println(pageRank.getURL());
                System.out.println("Score 1: " + pageRank.getScore1());
                System.out.println("Score 2: " + pageRank.getScore2());
                System.out.println("Score 3: " + pageRank.getScore3());
                System.out.println("Score 4: " + pageRank.getScore4());
            }
        }
        MaxHeap heap = new MaxHeap();
        

        // Allow users to view the sorted PageRank
        System.out.println("Would you like to view the PageRank in sorted order? (Y): ");
        String sortedPageRank = scan.nextLine();
        if(sortedPageRank.equals("Y") || sortedPageRank.equals("y") || sortedPageRank.equals("Yes") || sortedPageRank.equals("yes")){
            System.out.println("=======Sorted Order=======");
            heap.Heapsort(p);
            crawler.PrintPageRankReverseOrder(p);
        }

        PageRank[] r = crawler.StoreURLsInQueue(p);
        System.out.println();
        System.out.println("======Priority Queue With 20 URLs");
        crawler.PrintPageRank(r);

        // Builds Max Heap
        System.out.println("======Builds Max Heap======");
        System.out.println();
        heap.BuildMaxHeap(r);
        crawler.PrintPageRank(r);
        System.out.println();

        // MaxHeapify
        System.out.println("======MaxHeapify======");
        System.out.println();
        heap.MaxHeapify(r, 10);
        crawler.PrintPageRank(r);
        System.out.println();


        // Heap Maximum
        System.out.println("======Heap Maximum=====");
        System.out.println();
        System.out.println(heap.HeapMaximum(r).getURL() + ": " + heap.HeapMaximum(r).getSum());
        System.out.println();

        
        
         // Max Heap Insert
         System.out.println("======Heap Insert=====");
         System.out.println();

         System.out.println("(Inserting WebURL to queue) Please enter URL: ");
         String url = scan.nextLine();
         System.out.println("(Inserting WebURL to queue) Please enter Sum: ");
         int sum = scan.nextInt();
         scan.nextLine();
         PageRank rank = new PageRank(20,20,20,20,url, sum);
         heap.MaxHeapInsert(r, rank);
        crawler.PrintPageRank(r);
        

        // Heap Extract Max
        System.out.println("Would you like to Extract Max Number from queue? (Y): ");
        
        String extractMaxAnswer = scan.nextLine();
        if(extractMaxAnswer.equals("Y") || extractMaxAnswer.equals("y") || extractMaxAnswer.equals("Yes") || extractMaxAnswer.equals("yes")){
            System.out.println("======Queue Extract Max=====");
            System.out.println();
        
            try{
                PageRank extractMax = heap.HeapExtractMax(r);
                System.out.println(extractMax.getURL() + ": " + extractMax.getSum());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }


        // Heap Increase Key
        System.out.println("======Queue Increase Key=====");
        System.out.println();

        System.out.println("(Increasing Key) Please enter index (1-19): ");
        int index = scan.nextInt();
        scan.nextLine();
        System.out.println("(Increasing Key) Please enter URL: ");
        String increaseKeyURL = scan.nextLine();
        System.out.println("(Increasing Key) Please enter Sum: ");
        int increaseKeySum = scan.nextInt();


        PageRank increaseKey = new PageRank(0,0,0,0,increaseKeyURL,increaseKeySum);

        try{
            heap.HeapIncreaseKey(r, index, increaseKey);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        crawler.PrintPageRank(r);
        System.out.println();
        
        // HeapSort
        System.out.println("======HeapSort=======");
        System.out.println();
        heap.Heapsort(r);
        crawler.PrintPageRank(r);
        System.out.println();


        scan.close();
    }
}
