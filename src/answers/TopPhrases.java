package answers;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TopPhrases {

    //private static final Logger logger = Logger.getLogger(TopPhrases.class.getName());

    private static final int LIMIT = 100000;
    private static final String DELIMITER = "\\|";
    
    public static Map<String, Integer> getTopPhrases() {

    	InputStream inputStream = TopPhrases.class.getResourceAsStream("phrases.txt");
        Map<String, Integer> topPhrases = new LinkedHashMap<String, Integer>();

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            //Read every line of the file.
            while( (line=bufferedReader.readLine()) != null ) {
                String[] linePhrases = line.split(DELIMITER);
                //One line may have many phrases, this loop will read each one and store in the map
                for (String phrase : linePhrases){
                    int amount = 1;
                    String key = phrase.trim();
                	if (topPhrases.containsKey(key)) {
                        amount = topPhrases.get(key) + 1;
                    } 
                	topPhrases.put(key, amount);
                }
            }
            
            //Since it's the top 10,000 phrases, we have limited the order
            topPhrases = topPhrases.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1,v2)->v1, LinkedHashMap::new));

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        } catch (IOException e) {
        	System.out.println("IO Exception. " + e.getMessage());
        }

        return topPhrases;
    }
}