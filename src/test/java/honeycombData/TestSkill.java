package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSkill
{
	Skill testSkill;
	JobPosting testJob;
	Person testPerson;
	@BeforeEach
	void setUp() throws Exception
	{
		testPerson = new Person("A","");
		testSkill = new Skill("", "");
		testJob = new JobPosting("","");
	}

	@Test
	void testAssumableRoles() throws RoleNotAllowedException
	{
		testPerson.addInternalLink(testSkill, "skill");
		testJob.addInternalLink(testSkill, "qualification");	
	}

}
