package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

public class JobPosting extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	
	public JobPosting(String id,
				String name, 
				String description, 
				ArrayList<String> externalLinks, 
				HashMap<String, ArrayList<String>> internalLinks)
	{
	super(id, name, description, externalLinks, internalLinks);
	}

	public JobPosting(String name, String description)
	{
		super(name, description);
	}
	public String[] rolesIs()
	{
		String[] rolesIs ={
				"job_posting",
				"following",
				"contribution"};
		return rolesIs;
	}
	
	public String[] rolesHas()
	{
		String[] rolesHas = {
				"contributor",
				"follower",
				"editor",
				"viewer",
				"qualification",
				"applicant",
				"mentor"
		};
		return rolesHas;
	}
}
