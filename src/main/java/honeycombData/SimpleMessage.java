package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

import mvcModel.PageModel;
import mvcModel.PageModelSimpleMessage;

public class SimpleMessage extends Page
{

	public SimpleMessage(String id, String name, String description, ArrayList<String> externalLinks, HashMap<String, ArrayList<String>> internalLinks)
	{
		super(id, name, description, externalLinks, internalLinks);
	}

	public SimpleMessage(String name, String description)
	{
		super(name, description);
	}
	
	@Override
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"reply",
				"root",
				"post"};
		return rolesIs;
	}
	
	@Override
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"author",
				"reply",
				"root",
				"editor",
				"viewer"
		};
		return rolesHas;
	}
	
	@Override
	public PageModel createPageModel()
	{
		return new PageModelSimpleMessage(this);
		
	}

}
