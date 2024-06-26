package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

import mvcModel.PageModel;

public class NewsArticle extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public NewsArticle(String id,
						String name, 
						String description, 
						ArrayList<String> externalLinks, 
						HashMap<String, ArrayList<String>> internalLinks)
	{
		super(id, name, description, externalLinks, internalLinks);
	}
	
	public NewsArticle(String name, String description)
	{
		super(name, description);
	}
	
	@Override
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"news",
				"following"
				};
		return rolesIs;
	}
	
	@Override
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"viewer",
				"editor",
				"contributor",
				"mentor",
				"follower"
		};
		return rolesHas;
	}
	
	public PageModel createPageModel()
	{
		return null;
	}

}
