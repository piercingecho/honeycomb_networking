package honeycombData;

public class Skill extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public Skill(String name, String description)
	{
		super(name, description);
	}
	public String[] getRolesIs()
	{
		String[] rolesIs = {
				"skill",
				"qualification"};
		return rolesIs;
	}
	
	public String[] getRolesHas()
	{
		String[] rolesHas = {
				"editor",
				"viewer",
				"follower",
				"mentor"
		};
		return rolesHas;
	}
}
