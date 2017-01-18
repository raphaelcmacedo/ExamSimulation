package answers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplementaryPairs {
	public static int countComplementaryPairs(int[]A, int K){
		if(!isInputValid(A)){
			return 0;
		}
		
		//Isolate each value found on the array and counts how many times this value appears
		//This way we handle repeated values if any.
		//The key of the map is the value found in the array
		//The value stored on the map is the number of occurrences that we found of this specific number
		Map<Integer, Integer> values = new HashMap<Integer, Integer>();
		for(int key : A){
			int occurrences = 1;
			if(values.containsKey(key)){//This value is repeated in the array
				occurrences = values.get(key) + 1;	
			}
			values.put(key, occurrences);
		}
		
		//Now we run for each value found on the array
		//calculate the value that we need to reach K. For example if the value found is 1 and K is 10 we need 10-1 = 9
		//We need to consider how many times this value appears on the array.
		//We also need to handle the counterpart already evaluated assuming that the pairs = {9,1} and {1,9} are the same pair.
		//In order to do this we have a List of already evaluated counterparts. 
		//We don't need to add the keys for this array because it already passed on the loop
		List<Integer> evaluatedCounterparts = new ArrayList<Integer>(); 
		int result = 0;
		for(int key : values.keySet()){
			if(evaluatedCounterparts.contains(key)){
				continue;
			}
			
			int keyOccurrences = values.get(key);
			int counterpart = K - key;
			
			if(values.containsKey(counterpart)){
				int counterpartOccurrences = values.get(counterpart);
				result += keyOccurrences * counterpartOccurrences;
				evaluatedCounterparts.add(counterpart);
			}
		}
		
		return result;
	}
	
	//Validation method
	public static boolean isInputValid(int[] A)
	{
		//Validates if the input is not null
		if(A == null || A.length == 0) {
			return false;
		}
		
		return true;
	}
}
