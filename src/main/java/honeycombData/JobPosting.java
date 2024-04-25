package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

import mvcModel.PageModel;
import mvcModel.PageModelJobPosting;

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
				"pending_job",
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
	
	public ArrayList<Person> recommendJob(ArrayList<Person> peopleToRecommend, RecommenderStrategy recommender) throws RoleNotAllowedException
	{
		for(int i=0;i<peopleToRecommend.size();i++)
		{
			Person currPerson = peopleToRecommend.get(i);
			boolean isRecommended = recommender.recommend(this, currPerson);
			if(isRecommended)
			{
				currPerson.addInternalLink(this, "pending_job");
			}
		}
		
		return peopleToRecommend;
	}
	
	public PageModel createPageModel()
	{
		return new PageModelJobPosting(this);
	}

}
