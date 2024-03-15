package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPerson
{
	Person alice;
	Person bob;
	Person viewingPerson;
	Person editingPerson;
	JobPosting jobApplyingTo;
	NewsArticle articleOne;
	NewsArticle articleContributed;
	Company employerOne; //Person employee
	Person follower;
	Person friend;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		alice = new Person("alice", "test one");
		bob = new Person("bob", "test two", "he/him", "bobwhenhesob@gmail.com", "111-111-1111");
		
		viewingPerson = new Person("View", "test person");
		editingPerson = new Person("View", "test person");
		jobApplyingTo = new JobPosting("View", "test job");
		articleOne = new NewsArticle("A", "B");
		articleContributed = new NewsArticle("A","B");
		employerOne = new Company("A","b");
		follower = new Person("a", "b");
		friend = new Person("a", "b");
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

	@Test
	void testAssumableLinks() throws RoleNotAllowedException
	{
		alice.addInternalLink(viewingPerson, "viewer");
		
		// Note the relationship: someone viewing is added to the
		// viewed link, rather than adding the viewed to the person.
		employerOne.addInternalLink(viewingPerson, "viewer");
		employerOne.addInternalLink(editingPerson, "editor");
		jobApplyingTo.addInternalLink(alice, "applicant");
		
		
		//test both can contribute
		articleContributed.addInternalLink(alice, "contributor");
		articleContributed.addInternalLink(employerOne, "contributor");
		
		alice.addInternalLink(employerOne, "employer");
		alice.addInternalLink(follower, "follower");
		alice.addInternalLink(friend, "friend");
		friend.addInternalLink(alice, "friend");
		
	}
}
