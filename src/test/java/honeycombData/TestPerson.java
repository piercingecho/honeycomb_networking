package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPerson
{
	Person alice;
	Person bob;
	
	@BeforeEach
	void setUp() throws Exception
	{
		alice = new Person("alice", "test one");
		bob = new Person("bob", "test two", "he/him", "bobwhenhesob@gmail.com", "111-111-1111");
	}

	@Test
	void testConstructors()
	{
		assertEquals("", alice.getPronouns());
		assertEquals("he/him", bob.getPronouns());
		assertEquals("", alice.getEmail());
		assertEquals("bobwhenhesob@gmail.com", bob.getEmail());
		assertEquals("111-111-1111", bob.getPhone());
		assertEquals("", alice.getPhone());
		
	}

}
