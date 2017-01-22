package answers;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TopPhrases {

    private static final int LIMIT = 100000;
    private static final String DELIMITER = "\\|";
    
    //Complexity: O(n^2)
    public static Map<String, Integer> getTopPhrases() {
    	
    	//Create the InputStream to the specific file and a Map that will store all the phrases and the occurencies of each one
    	InputStream inputStream = TopPhrases.class.getResourceAsStream("phrases.txt");
        Map<String, Integer> topPhrases = new LinkedHashMap<String, Integer>();

        try {
        	//Consume the file with a BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            //Read every line of the file.
            while( (line=bufferedReader.readLine()) != null ) {
            	//One line may have many phrases, this loop will read each one and store in the map
            	String[] linePhrases = line.split(DELIMITER);
                for (String phrase : linePhrases){
                    int amount = 1;
                    String key = phrase.trim();
                	if (topPhrases.containsKey(key)) {
                        amount = topPhrases.get(key) + 1;
                    } 
                	topPhrases.put(key, amount);
                }
            }
            
            //Since it's the top 10,000 phrases, we have limited the order of the Map
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