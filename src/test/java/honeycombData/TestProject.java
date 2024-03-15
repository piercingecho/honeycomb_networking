package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestProject
{

	Person testPerson;
	Project testProject;
	Company testCompany;
	
	@BeforeEach
	void setUp() throws Exception
	{
		testPerson = new Person("","");
		testCompany = new Company("","");
		testProject = new Project("","");
	}

	@Test
	void testAssumableRoles() throws RoleNotAllowedException
	{
		testCompany.addInternalLink(testProject, "project");
		testPerson.addInternalLink(testProject, "project");
	}

}
