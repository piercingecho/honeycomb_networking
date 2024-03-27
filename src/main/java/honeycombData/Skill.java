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
	public String[] getAssumableRoles()
	{
		String[] assumableRoles = {
				"skill",
				"qualification"};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"editor",
				"viewer",
				"follower",
				"mentor"
		};
		return linkableRoles;
	}
}
