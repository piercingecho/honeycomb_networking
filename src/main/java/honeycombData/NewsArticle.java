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
	public String[] getRolesIs()
	{
		String[] rolesIs ={
				"news"
				};
		return rolesIs;
	}
	
	public String[] getRolesHas()
	{
		String[] rolesHas = {
				"viewer",
				"editor",
				"contributor",
				"mentor",
				"follower"
		};
		return rolesHas;
	}
}
