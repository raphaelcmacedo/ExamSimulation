package tests;

import junit.framework.TestCase;
import answers.Palindrome;
import org.junit.Test;


public class PalindromeTest extends TestCase {

	@Test
	public void testNull() {
		boolean actual = Palindrome.isPalindrome(null);
		assertEquals(false, actual);
	}
	
	@Test
	public void testEmpty() {
		boolean actual = Palindrome.isPalindrome("");
		assertEquals(false, actual);
	}
	
	@Test
	public void test1221() {
		boolean actual = Palindrome.isPalindrome("1221");
		assertEquals(true, actual);
	}

	@Test
	public void test1121() {
		boolean actual = Palindrome.isPalindrome("1121");
		assertEquals(false, actual);
	}
	
	@Test
	public void testLetters() {
		boolean actual = Palindrome.isPalindrome("abba");
		assertEquals(true, actual);
	}
	
	@Test
	public void testOddChars() {
		boolean actual = Palindrome.isPalindrome("abcba");
		assertEquals(true, actual);
	}
}
