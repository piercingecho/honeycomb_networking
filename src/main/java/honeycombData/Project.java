package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

public class Project extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Project(String id,
			String name, 
			String description, 
			ArrayList<String> externalLinks, 
			HashMap<String, ArrayList<String>> internalLinks)
		{
		super(id, name, description, externalLinks, internalLinks);
		}		
	public Project(String name, String description)
	{
		super(name, description);
		Storage.create(this);
	}
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"project",
				"following"};
		return rolesIs;
	}
	
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"viewer",
				"contributor",
				"editor",
				"follower",
				"mentor"
		};
		return rolesHas;
	}
}
