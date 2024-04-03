package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

public class Company extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Company(String id, String name, String description, ArrayList<String> externalLinks, HashMap<String, ArrayList<String>> internalLinks)
	{
		super(id, name, description, externalLinks, internalLinks);
	}

	public Company(String name, String description)
	{
		super(name, description);
		Storage.create(this);
	}
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"employer",
				"contributor",
				"following"};
		return rolesIs;
	}
	
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"employee",
				"follower",
				"project",
				"job_posting",
				"editor",
				"viewer",
				"mentor",
				"follower",
				"news"
		};
		return rolesHas;
	}
}
