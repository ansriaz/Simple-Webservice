package waes.junittest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
	
	String l = "Encode";
	String r = "Encode";
	
	@Test
	public void testEquals(String left, String right) {		
		assertEquals(left, right);
	}
	
	@Test
	public void testDiffs(String left, String right) {				
		Assert.assertFalse(left.equals(right));
	}
}
