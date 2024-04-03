package honeycombData;
import RESTAPI.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Skill extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Skill(String id,
			String name, 
			String description, 
			ArrayList<String> externalLinks, 
			HashMap<String, ArrayList<String>> internalLinks)
		{
		super(id, name, description, externalLinks, internalLinks);
		}
	public Skill(String name, String description)
	{
		super(name, description);
		Storage.create(this);
	}
	public String[] rolesIs()
	{
		String[] rolesIs = {
				"skill",
				"qualification",
				"following"};
		return rolesIs;
	}
	
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"editor",
				"viewer",
				"follower",
				"mentor"
		};
		return rolesHas;
	}
}
