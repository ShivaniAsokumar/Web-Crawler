import java.util.*;
public class PageRank implements Comparable<PageRank>{
    private String url;
    private int score;

    public PageRank(String url, int score){
        this.url = url;
        this.score = score;
    }

    public String getURL(){
        return this.url;
    }

    public int getScore(){
        return this.score;
    }

    public void setURL(String newURL){
        this.url = newURL;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    public int compareTo(PageRank that){
        if(this.score != that.score){
            return Integer.compare(this.score, that.score);
        }
        return this.url.compareTo(that.url);
    }





    public static void main(String[] args) {
        String keyword = "Hello";
        WebCrawler crawler = new WebCrawler(keyword);
        crawler.search();
        crawler.PrintSet(crawler.getUrls());

        MaxHeap heap = new MaxHeap();
        int[] A = {12,4,5,8,3,93};
        heap.BuildMaxHeap(A);
        System.out.println(Arrays.toString(A));
    }
}
