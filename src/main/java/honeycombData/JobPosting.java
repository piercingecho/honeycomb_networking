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
	public String[] getRolesIs()
	{
		String[] rolesIs ={
				"job_posting",
				"contribution"};
		return rolesIs;
	}
	
	public String[] getRolesHas()
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
