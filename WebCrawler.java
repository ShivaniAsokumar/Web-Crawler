import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Searches the web for URL's based on user inputed keywords.
 * @author Shivani Asokumar
 */
public class WebCrawler {

    private String url;
    private String keyword;
    private Set<String> urls = new HashSet<String>();

    private static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    private Document htmlDocument;
    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }


    /**
     * Creates a Web Crawler based on keyword.
     * @param aKeyword A string that the user inputs.
     */
    WebCrawler(String aKeyword) {
        keyword = aKeyword;
        url = "https://google.com/search?q=" + aKeyword + "&num=80";
    }


    /**
     * Starts the Web Crawler.
     */
    public void search() {
        String currentUrl = url;
        crawl(currentUrl);
        boolean success = searchForWord(keyword);
        if (success) {
            System.out.println(String.format("**Success** Word %s found at %s", keyword, currentUrl));
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", urls.size()));

        System.out.println(" \nHere are the first 30 URL links: \n");

    }

    
    /** 
     * Uses pattern and matcher to extract the domain name of a URL.
     * @param url The URL that is given.
     * @return String A String that is the domain name of a URL.
     */
    public String getDomainName(String url) {
        matcher = patternDomainName.matcher(url);
        if (url.startsWith("/url?q=https://")) {
            return url.substring(15, 42);
        }
        return url.substring(14, 42);
    }

    
    /** 
     * Gets a Set of URL's based on the keyword that the user inputed.
     * @return Set<String> A Set containing 30 URL's.
     */
    public Set<String> getUrls() {
        
        int count = 0;
        Set<String> result = new HashSet<>(); // Initializing HashSet
        
        Iterator<String> it = this.urls.iterator(); // Iterator helps traverse the given set.
        while (it.hasNext() && count < 30){
            result.add(it.next());
            count++;
        }
        return result; // HashSet with 30 URLs.
    }

    
    /** 
     * Calculates the total PageRank score for each URL and stores it.
     * @param set A Set containing the 30 URL's.
     * @return HashMap<String, Integer> A HashMap with each URL storing its PageRank score.
     */
    public PageRank[] CalculatePageRank(Set<String> set){

        PageRank[] pageRankMapper = new PageRank[30]; 
        int count = 0; 
        for (String url : set){
            
            // Generate random integer for PageRank score.
            Random rand = new Random();

            // These are the 4 scores for EACH url.
            int score1 = rand.nextInt(100) + 1; 
            int score2 = rand.nextInt(100) + 1;
            int score3 = rand.nextInt(100) + 1;
            int score4 = rand.nextInt(100) + 1;

            int sum = score1 + score2 + score3 + score4;

            // Create PageRank object with url and sum
            PageRank pageRank = new PageRank(score1, score2, score3, score4, url, sum);
            pageRankMapper[count] = pageRank;

            count++;
        }

        return pageRankMapper;
        
    }

    
    /** 
     * Crawls the links and puts them in a Set to keep. 
     * Makes an HTTP request for a web page based on a given URL.
     * @param url The given URL.
     * @return boolean based on weather the connection is successful. 
     */
    public boolean crawl(String url) {
        try {

        Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
        final Document htmlDocument = connection.timeout(5000).get();
        this.htmlDocument = htmlDocument;

        if (connection.response().statusCode() == 200) {
            System.out.println("\n**Visiting** Received web page at " + url);
        }

        if (!connection.response().contentType().contains("text/html")) {
            System.out.println("**Failure** Retrieved something other than HTML");
            return false;
        }

        Elements linksOnPage = htmlDocument.select("a[href]");
        System.out.println("Found (" + linksOnPage.size() + ") links");

        for (Element link : linksOnPage) {
            String temp = link.attr("href");
            if (temp.startsWith("/url?q=http")) {
                this.urls.add(getDomainName(temp));
            }
        }
        return true;
        } catch (IOException ioe) {
        return false;
        }
    }
    
    /** 
     * Checks if the website contains the keyword.
     * @param searchWord The keyword to be searched.
     * @return boolean determining weather keyword is in webpage.
     */
    public boolean searchForWord(String searchWord) {
        if (this.htmlDocument == null) {
            System.out.println("Error!");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }


    /**
     * Stores the first 20 out of the 30 PageRank objects in a queue.
     * @return An array containing the 20 PageRank objects.
     */
    public PageRank[] StoreURLsInQueue(){
        MaxHeap heap = new MaxHeap();
        PageRank[] pageRank = this.CalculatePageRank(this.getUrls()); // Returns array of 30 PageRanks.
        heap.Heapsort(pageRank);
        PageRank[] result = new PageRank[20]; // Initialize array to store the 20 PageRanks.
        for(int i = 0; i < result.length; i++){
            PageRank p = pageRank[i]; // Transfers the objects over.
            result[i] = p;
        }

        return result;
    }

    /**
     * Prints the given PageRank array in reverse order. 
     * @param pageRank An Array of PageRank objects.
     */
    public void PrintPageRankReverseOrder(PageRank[] pageRank){
        int count = 1;
        Collections.reverse(Arrays.asList(pageRank)); // Riverses a given array.

        //Traverses the array.
        for(PageRank p : pageRank){
            System.out.println(count + ". " + p.getURL() + ", Page Rank Score: " + p.getSum());
            count++;
        }
    }

    /**
     * Prints the given PageRank array in regular order
     * @param pageRank An array of PageRannk objects.
     */
    public void PrintPageRank(PageRank[] pageRank){
        int count = 1;

        // Traverses the array.
        for(PageRank p : pageRank){
            System.out.println(count + ". " + p.getURL() + ", Page Rank Score: " + p.getSum());
            count++;
        }
    }
    
    /** 
     * Prints out a given Set.
     * @param set A Set containing URL's.
     */
    public void PrintSet(Set<String> set){
        int count = 1;
        
        // Traverses the set
        for(String url : set){
            System.out.println(count + ". " + url); //Print this way makes it easier to see the elements of the set.
            count++;
        }
    }

    
    

}

