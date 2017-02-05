package tests;

import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;
import answers.Palindrome;
import answers.TopPhrases;

import org.junit.Test;


public class TopPhrasesTest extends TestCase {

	@Test
	public void test() {
		Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
		expected.put("CNET", 3);
		expected.put("Google", 3);
		expected.put("PGA", 2);
		expected.put("Foobar Candy", 1);
		expected.put("Olympics 2012", 1);
		expected.put("Microsoft Bing", 1);
		expected.put("Microsoft", 1);
		expected.put("Rio Olympics", 1);
		expected.put("Baz", 1);
		expected.put("Amazon", 1);
		expected.put("Overflow", 1);
		
		Map<String, Integer> actual = TopPhrases.getTopPhrases();
		
		assertEquals(expected, actual);
	}
	
	
}
