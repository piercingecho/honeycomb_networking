package honeycombData;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TestPage
{
	Person alice;
	Person bob;
	
	Company place;
	Company hub;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		alice = new Person("Alice", "Test person one");
		bob = new Person("Bob", "Test person two");
		place = new Company("Place", "can be a place people work");
		hub = new Company("Hub", "can be a hub people work");
	}

	@Test
	void testConstructor()
	{
		assertEquals("Alice", alice.getName());
		assertEquals("Test person one", alice.getDescription());
		
		alice.setDescription("Test person on");
		assertEquals("Test person on", alice.getDescription());
		
		alice.setName("Alic");
		assertEquals("Alic", alice.getName());


		}
	
	@Test
	void testIDs()
	{
		//ensures IDs increment across classes
		int aliceID = Integer.parseInt(alice.getId());
		assertEquals(Integer.toString(aliceID + 1), bob.getId());
		assertEquals(Integer.toString(aliceID + 2), place.getId());

	}

	@Test
	void testingExternalLinks()
	{
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("linkedin.com");
		place.addExternalLink("linkedin.com");
		assertEquals(strings, place.getExternalLinks());
		place.addExternalLink("github.com");
		place.removeExternalLink("github.com");
		assertEquals(strings, place.getExternalLinks());
		
		assertEquals(true, place.hasExternaLink("linkedin.com"));
		assertEquals(false, place.hasExternaLink("notalink.org"));
	}
	
	
	@Test
	void addingLinks() throws RoleNotAllowedException{
		
		place.addInternalLink(alice, "employee");
		
		alice.addInternalLink(place, "employer");
		
		ArrayList<Page> testPageList = new ArrayList<>();
		testPageList.add(place);
		
		ArrayList<Page> emptyPagesList = new ArrayList<>();
		
		assertEquals(alice.getInternalLinks("employer"), testPageList);
		
		// testing multiple links at the same time and deleting them
		alice.deleteInternalLink(place, "employer");

		alice.addInternalLink(hub, "employer");
		alice.addInternalLink(place, "employer");

		alice.deleteInternalLink(hub, "employer");
		
		assertEquals(alice.getInternalLinks("employer"), testPageList);

		// testing empty links
		alice.deleteInternalLink(place, "employer");
		assertEquals(alice.getInternalLinks("employer"), emptyPagesList);
			//should make a new empty ArrayList
		assertEquals(alice.getInternalLinks("skill"), emptyPagesList);
	
	}
	
	@Test
	void testRepeatAddLinks() throws RoleNotAllowedException
	{
		alice.addInternalLink(place, "employer");
		alice.addInternalLink(place, "employer");
		ArrayList<Page> aliceEmployerLinks = new ArrayList<>();
		aliceEmployerLinks.add(place);
		assertEquals(aliceEmployerLinks, alice.getInternalLinks("employer"));
		
	}
	
	@Test
	void testExceptionalLinks()
	{
		//Tests the ways we expect addLink() to fail.
		
		try
		{
			place.deleteInternalLink(alice, "THING THAT'S NOT ALLOWED");
			fail("Didn't catch invalid  link");
		} catch(RoleNotAllowedException e)
		{}
	
		try
		{
			place.deleteInternalLink(alice, "employer");
			fail("Alice can't be an employer of place.");
		} catch(RoleNotAllowedException e)
		{}
		
		try
		{
			place.deleteInternalLink(alice, "employee");
		}
		catch(Exception e)
		{
			fail("Deleting where a page isn't there should fail silently.");
		}
		
		try
		{
			place.addInternalLink(alice, "skill");
			fail("Alice can't assume that");
		}
		catch(Exception e)
		{	
		}
		
		try
		{
			alice.getInternalLinks("notalinkalicehas");
			fail("Alice can't assume that");
		}
		catch(Exception e)
		{
		}
		
		try
		{
			place.addInternalLink(alice, "friend");
			fail("place can't have friends");
		}
		catch(Exception e)
		{
		}

		

	}
}
