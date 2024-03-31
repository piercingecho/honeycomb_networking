package honeycombData;

public class Project extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Project(String name, String description)
	{
		super(name, description);
	}
	public String[] getRolesIs()
	{
		String[] rolesIs ={
				"project"};
		return rolesIs;
	}
	
	public String[] getRolesHas()
	{
		String[] rolesHas = {
				"viewer",
				"contributor",
				"editor",
				"follower",
				"mentor"
		};
		return rolesHas;
	}
}
