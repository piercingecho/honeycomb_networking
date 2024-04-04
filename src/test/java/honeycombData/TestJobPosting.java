package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJobPosting
{
	Person testPerson;
	JobPosting testJob;
	
	@BeforeEach
	void setUp() throws Exception
	{
		testPerson = new Person("", "");
		testJob = new JobPosting("","");
	}

	@Test
	void testRolesIs() throws RoleNotAllowedException
	{
		testPerson.addInternalLink(testJob, "job_posting");
	}
	
	@Test
	void testRolesHas() throws RoleNotAllowedException
	{
		testJob.addInternalLink(testPerson, "follower");
	}

	@Test
	void testRecommender() throws RoleNotAllowedException
	{
		Person p = new Person("","");
		Person p2 = new Person("","");
		JobPosting j = new JobPosting("","");
		
		assertEquals(false, p.getInternalLinks("pending_job").contains(j.getId()));

		ArrayList<Person> people = new ArrayList<Person>();
		
		people.add(p);
		people.add(p2);
		
		j.recommendJob(people, new AllRecommender());
		
		assertEquals(true, p.getInternalLinks("pending_job").contains(j.getId()));
		assertEquals(true, p2.getInternalLinks("pending_job").contains(j.getId()));

	}
}
