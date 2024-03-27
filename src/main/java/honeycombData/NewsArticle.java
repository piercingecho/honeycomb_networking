package honeycombData;

public class NewsArticle extends Page
{

	/**
	 * @param name
	 * @param description
	 */
	public NewsArticle(String name, String description)
	{
		super(name, description);
	}
	public String[] getAssumableRoles()
	{
		String[] assumableRoles ={
				"news"
				};
		return assumableRoles;
	}
	
	public String[] getLinkableRoles()
	{
		String[] linkableRoles = {
				"viewer",
				"editor",
				"contributor",
				"mentor",
				"follower"
		};
		return linkableRoles;
	}
}
