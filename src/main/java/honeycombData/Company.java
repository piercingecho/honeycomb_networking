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
				"contributor"};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"employee",
				"follower",
				"project",
				"job_posting",
				"editor",
				"viewer",
				"mentor",
				"follower",
				"news"
		};
		return linkableRoles;
	}
}
