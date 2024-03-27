package honeycombData;

public class JobPosting extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public JobPosting(String name, String description)
	{
		super(name, description);
	}
	public String[] getAssumableRoles()
	{
		String[] assumableRoles ={
				"job_posting",
				"contribution"};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"contributor",
				"follower",
				"editor",
				"viewer",
				"qualification",
				"applicant",
				"mentor"
		};
		return linkableRoles;
	}
}
