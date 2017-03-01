package waes.junittest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import waes.test.Client;

public class UnitTest {

	@Test
	public void testEquals() {
		String ep1 = Client.postResponse("http://localhost:8080/v1/diff/1/left", "RW5jb2Rl".toString());
		String ep2 = Client.postResponse("http://localhost:8080/v1/diff/1/right", "RW5jb2Rl".toString());
		assertEquals(ep1, ep2);
	}

	@Test
	public void testDiffs() {
		String ep1 = Client.postResponse("http://localhost:8080/v1/diff/1/left", "RW5jb2Rl".toString());
		String ep2 = Client.postResponse("http://localhost:8080/v1/diff/1/right", "RW5yaWM=".toString());
		
		Assert.assertFalse(ep1.equals(ep2));
	}
}
