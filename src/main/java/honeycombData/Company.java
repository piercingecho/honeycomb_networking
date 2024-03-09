package honeycombData;

public class Company extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Company(String name, String description)
	{
		super(name, description);
	}
	public String[] getAssumableRoles()
	{
		String[] assumableRoles ={
				"employer",
				"contributor",
				"editor",
				"followed",
				"viewer"};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"employee",
				"contribution",
				"follower",
				"project",
				"job_posting"
		};
		return linkableRoles;
	}
}
