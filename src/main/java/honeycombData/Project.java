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
	public String[] getAssumableRoles()
	{
		String[] assumableRoles ={
				"project",
				"followed"};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"viewer",
				"contributor",
				"editor"
		};
		return linkableRoles;
	}
}
