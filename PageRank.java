
/**
 * This class builds a PageRank object and its methods.
 * @author Shivani Asokumar
 */
public class PageRank implements Comparable<PageRank>{
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private String url;
    private int sum;

    /**
     * Constructs the PageRank object with the given parameters.
     * @param score1 The first PageRank score.
     * @param score2 The second PageRank score.
     * @param score3 The third PageRank score.
     * @param score4 The fourth PageRank score.
     * @param url The given url.
     * @param sum The sum of the four PageRank scores.
     */
    public PageRank(int score1, int score2, int score3, int score4, String url, int sum){
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.url = url;
        this.sum = sum;
    }

    /**
     * Gets the first score.
     * @return the first score.
     */
    public int getScore1(){
        return this.score1;
    }

    /**
     * Gets the second score.
     * @return the second score.
     */
    public int getScore2(){
        return this.score2;
    }

    /**
     * Gets the third score.
     * @return the third score.
     */
    public int getScore3(){
        return this.score3;
    }

    /**
     * Gets the fourth score.
     * @return the fourth score.
     */
    public int getScore4(){
        return this.score4;
    }

    /**
     * Gets the URL.
     * @return The URL.
     */
    public String getURL(){
        return this.url;
    }

    /**
     * Gets the Sum.
     * @return the Sum.
     */
    public int getSum(){
        return this.sum;
    }

    /**
     * Sets the URL to a new URL
     * @param newURL The URL that replaces the old one.
     */
    public void setURL(String newURL){
        this.url = newURL;
    }

    /**
     * Sets the Sum to a new Sum.
     * @param newSum The Sum that replaces the old one.
     */
    public void setSum(int newSum){
        this.sum = newSum;
    }

    /**
     * Compares two PageRank objects based on Sum first then URL.
     * @param that is the second PageRank object.
     * @return An int that detemines the order of placement of the two objects.
     */
    public int compareTo(PageRank that){
        if(this.sum != that.sum){
            return Integer.compare(this.sum, that.sum);
        }
        return this.url.compareTo(that.url);
    }

    /**
     * Gets the unique hash code.
     * @return The unique hash code.
     */
    public int hashCode(){
        return this.getURL().hashCode();
    }

    /**
     * Determines if two PageRank objects are equal. 
     * @param other Is from the Object class.
     * @return A boolen that determines if two PageRank objects are equal.
     */
    public boolean equals(Object other){
        PageRank that = (PageRank) other;
        if(this.getSum() == that.getSum()){
            return this.getURL() == that.getURL();
        } 
        return this.getSum() == that.getSum();
    }
    
}
