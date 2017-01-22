package answers;

public class Palindrome {

	//Complexity: O(n)
	public static boolean isPalindrome(String text){
		
		//I decided to separate validation and processing in order to achieve greater cohesion  
		if(!isInputValid(text)){
			return false;
		}
		
		//I declare the first and last index of the string in order to compare them to each interaction, 
		//so that the first index always increases and the last always decreases. 
		//That way I can get each pair correctly 
		int startIndex = 0;
		int endIndex = text.length() - 1;
		
		while (startIndex < endIndex) {
			//I separate the values obtained from this interaction and compare the chars. 
			//It would be possible to make this comparison in less lines of code, 
			//but I believe this way the code became more readable
			char startChar = text.charAt(startIndex);
			char endChar = text.charAt(endIndex);
			if (startChar != endChar) {
				return false;
			}
			//As stated above, I make the necessary increases and decrements
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
