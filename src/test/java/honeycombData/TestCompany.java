package honeycombData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TestCompany
{
	Company c;
	Person p;
	NewsArticle n;
	

	@BeforeEach
	void setUp() throws Exception
	{
		c = new Company("a","b");
		p = new Person("a","b");
		n = new NewsArticle("a","b");
	}

	@Test
	void testAssumableLinks() throws RoleNotAllowedException
	{
		p.addInternalLink(c, "employer");
		n.addInternalLink(c, "contributor");
		
		c.addInternalLink(p, "follower");
	}

}
