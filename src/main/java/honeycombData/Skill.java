package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

import mvcModel.PageModel;
import mvcModel.PageModelPerson;
import mvcModel.PageModelSkill;

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
	
	public PageModel createPageModel()
	{
		return new PageModelSkill(this);
	}

}
