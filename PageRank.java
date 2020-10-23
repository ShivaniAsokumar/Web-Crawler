
public class PageRank implements Comparable<PageRank>{
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private String url;
    private int sum;

    public PageRank(int score1, int score2, int score3, int score4, String url, int sum){
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.url = url;
        this.sum = sum;
    }

    public PageRank(){
        //Empty Constructor
    }

    public int getScore1(){
        return this.score1;
    }

    public int getScore2(){
        return this.score2;
    }

    public int getScore3(){
        return this.score3;
    }

    public int getScore4(){
        return this.score4;
    }

    public String getURL(){
        return this.url;
    }

    public int getSum(){
        return this.sum;
    }

    public void setURL(String newURL){
        this.url = newURL;
    }

    public void setSum(int newSum){
        this.sum = newSum;
    }

    public int compareTo(PageRank that){
        if(this.sum != that.sum){
            return Integer.compare(this.sum, that.sum);
        }
        return this.url.compareTo(that.url);
    }

    // ! Include hashCode and equals method





    public static void main(String[] args) {
        // String keyword = "Hello";
        // WebCrawler crawler = new WebCrawler(keyword);
        // crawler.search();
        // crawler.PrintSet(crawler.getUrls());

        // MaxHeap heap = new MaxHeap();
        // int[] A = {12,4,5,8,3,93};
        // heap.BuildMaxHeap(A);
        // System.out.println(Arrays.toString(A));
    }
}
