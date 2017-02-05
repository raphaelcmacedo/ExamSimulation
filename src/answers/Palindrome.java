package answers;

/**
 * Question
 * 
 * Write an efficient algorithm to check if a string is a palindrome. 
 * A string is a palindrome if the string matches the reverse of string. Example: 1221 is a palindrome but not 1121.
 *
 */

public class Palindrome {

	//Complexity: O(n)
	public static boolean isPalindrome(String text){
		
		if(!isInputValid(text)){
			return false;
		}
		
		//Declare the first and last index of the string in order to compare them to each interaction, 
		//so that the first index always increases and the last always decreases. 
		//That way I can get each pair correctly 
		int startIndex = 0;
		int endIndex = text.length() - 1;
		
		while (startIndex < endIndex) {
			//Separate the values obtained from this interaction and compare the chars. 
			char startChar = text.charAt(startIndex);
			char endChar = text.charAt(endIndex);
			if (startChar != endChar) {
				return false;
			}
			startIndex++;
			endIndex--;
		}
		//If all the pairs of the string have been traversed without finding any difference, 
		//the value entered is Palindrome
		return true;
	}
	
	//Validation method
	public static boolean isInputValid(String text)
	{
		//Validates if the input is not null
		if(text == null || text.isEmpty()) {
			return false;
		}
		
		return true;
	}

}
