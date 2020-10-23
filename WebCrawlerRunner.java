import java.util.*;
public class WebCrawlerRunner{
    
    public static void main(String[] args) {
        // Enter Keyword
        System.out.println("Please Enter the Keyword: ");
        Scanner scan = new Scanner(System.in);
        String keyword = scan.nextLine();

        // Set up WebCrawler
        System.out.println("Would you like to view the 30 URLs? (Y): ");
        String viewURLs = scan.nextLine();
        WebCrawler crawler = new WebCrawler(keyword);
        crawler.search();
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
        PageRank rank = new PageRank(20,20,20,20,"DummyURL", 600);

        // Allow users to view the sorted PageRank
        System.out.println("Would you like to view the PageRank in sorted order? (Y): ");
        String sortedPageRank = scan.nextLine();
        if(sortedPageRank.equals("Y") || sortedPageRank.equals("y") || sortedPageRank.equals("Yes") || sortedPageRank.equals("yes")){
            // heap.Heapsort(p);
            crawler.PrintPageRank(p);
        }


        

        // // Builds Max Heap
        System.out.println("======Builds Max Heap======");
        System.out.println();
        heap.BuildMaxHeap(p);
        crawler.PrintPageRank(p);
        System.out.println();

        // // MaxHeapify
        // System.out.println("======MaxHeapify======");
        // System.out.println();
        // heap.MaxHeapify(p, 10);
        // crawler.PrintPageRank(p);
        // System.out.println();


        // // Heap Maximum
        // System.out.println("======Heap Maximum=====");
        // System.out.println();
        // System.out.println(heap.HeapMaximum(p).getURL() + ": " + heap.HeapMaximum(p).getSum());
        // System.out.println();

        // Heap Extract Max
        // System.out.println("======Heap Extract Max=====");
        // System.out.println();
        
        // try{
        //     PageRank extractMax = heap.HeapExtractMax(p);
        //     System.out.println(extractMax.getURL() + ": " + extractMax.getSum());
        // } catch(Exception e){
        //     System.out.println(e.getMessage());
        // }
        // crawler.PrintPageRank(p);
        // System.out.println();

        // HeapSort
        // System.out.println("======HeapSort=======");
        // System.out.println();
        // heap.Heapsort(p);
        // crawler.PrintPageRank(p);
        // System.out.println();


        // Heap Increase Key
        // System.out.println("======Heap Increase Key=====");
        // System.out.println();

        // try{
        //     heap.HeapIncreaseKey(p, 0, 600);
        // } catch (Exception e){
        //     System.out.println(e.getMessage());
        // }
        // crawler.PrintPageRank(p);
        // System.out.println();

        // Max Heap Insert
        System.out.println("======Heap Insert Key=====");
        System.out.println();
        heap.MaxHeapInsert(p, rank);
        crawler.PrintPageRank(p);
        

        scan.close();
    }
}
