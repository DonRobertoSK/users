package sk.skog.users.data;

import org.junit.Assert;
import org.junit.Test;



public class UserTest {
	
	@Test
	public void test() {
		User user = new User(3, "a3", "Peter");
		Assert.assertEquals(Integer.valueOf(3), user.getUserId());
		Assert.assertEquals("a3", user.getUserGuid());
		Assert.assertEquals("Peter", user.getUserName());
		
	}

}
