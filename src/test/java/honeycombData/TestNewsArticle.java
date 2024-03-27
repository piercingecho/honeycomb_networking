package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestNewsArticle
{
	
	NewsArticle testNews;
	Person testPerson;
	
	@BeforeEach
	void setUp() throws Exception
	{
		testNews = new NewsArticle("testnews", "testnews");
		testPerson = new Person("testperson", "testperson");
	}

	@Test
	void testRolesIs() throws RoleNotAllowedException
	{
		testPerson.addInternalLink(testNews, "news");
	}

}
