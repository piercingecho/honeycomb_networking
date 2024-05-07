package honeycombData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSimpleMessage
{
	SimpleMessage c;
	Person p;
	SimpleMessage d;

	@BeforeEach
	void setUp() throws Exception
	{
		UtilTest.recreateRestDirectory();

		c = new SimpleMessage("a","b");
		p = new Person("a","b");
		d = new SimpleMessage("a","b");
	}

	@Test
	void testAssumableLinks() throws RoleNotAllowedException
	{
		p.addInternalLink(c, "post");
		
		c.addInternalLink(d, "root");
		d.addInternalLink(c, "reply");

		c.addInternalLink(p, "editor");
	}

}
