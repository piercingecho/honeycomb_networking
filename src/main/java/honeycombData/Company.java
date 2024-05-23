package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

import mvcModel.PageModel;
import mvcModel.PageModelCompany;

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
	}
	
	@Override
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"employer",
				"contributor",
				"following"};
		return rolesIs;
	}
	
	@Override
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
				"news"
		};
		return rolesHas;
	}
	
	@Override
	public PageModel createPageModel()
	{
		return new PageModelCompany(this);
		
	}

}
