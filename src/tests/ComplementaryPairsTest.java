package tests;

import junit.framework.TestCase;
import answers.ComplementaryPairs;
import org.junit.Test;


public class ComplementaryPairsTest extends TestCase {

	@Test
	public void testArrayNull() {
		int actual = ComplementaryPairs.countComplementaryPairs(null, 0);
		assertEquals(0, actual);
	}
	
	@Test
	public void testArrayEmpty() {
		int actual = ComplementaryPairs.countComplementaryPairs(new int[0], 0);
		assertEquals(0, actual);
	}
	
	@Test
	public void testPairs() {
		//Pairs = {1,9}, {3,7}
		int[] A = {1,3,9,7};
		int K = 10;
		int actual = ComplementaryPairs.countComplementaryPairs(A, K);
		assertEquals(2, actual);
	}
	
	@Test
	public void testOcorrencies() {
		//Pairs = {3,4}, {3,4}
		int[] A = {3,3,4,8,7};
		int K = 7;
		int actual = ComplementaryPairs.countComplementaryPairs(A, K);
		assertEquals(2, actual);
	}
	
	@Test
	public void testNegatives() {
		//Pairs = {4,-2}, {10,-8}, {8,-6}, {-4, 6}
		int[] A = {4,-2,10,-8,8,-4,6,-6};
		int K = 2;
		int actual = ComplementaryPairs.countComplementaryPairs(A, K);
		assertEquals(4, actual);
	}
	
}
