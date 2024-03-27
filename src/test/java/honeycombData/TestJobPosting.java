package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

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

}
