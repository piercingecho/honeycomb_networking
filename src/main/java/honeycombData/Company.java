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
	public String[] getRolesIs()
	{
		String[] rolesIs ={
				"employer",
				"contributor"};
		return rolesIs;
	}
	
	public String[] getRolesHas()
	{
		String[] rolesHas = {
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
		return rolesHas;
	}
}
