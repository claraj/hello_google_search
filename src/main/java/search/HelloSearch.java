package search;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.util.List;


/**
 * Created by clara on 11/29/17.
 */
public class HelloSearch {
    
    private static String KEY = System.getenv("KEY");
    private static String CX = System.getenv("CX");   //todo set env variables
    
    private static Customsearch customsearch;
    
    public static void main(String[] args) throws Exception{
    
    
        Customsearch.Builder builder = new Customsearch.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null).setApplicationName("Hello Search");
            
            customsearch = builder.build();
        
        // do search.
        
        Customsearch.Cse.List futureListOfResults = customsearch.cse().list("President of France");
        
        futureListOfResults.setQ("President of France");
        futureListOfResults.setKey(KEY);
        futureListOfResults.setCx(CX);
       futureListOfResults.setSearchType("image");  // Enable image search at https://cse.google.com
        futureListOfResults.setImgType("news");
        
        Search results = futureListOfResults.execute();
        
        List<Result> resultList = results.getItems();
        
        for (Result r : resultList) {
            System.out.println(r);
            System.out.println(r.getImage().getThumbnailLink());
            
        }
        
        
        
        
    }
}
