package honeycombData;

/**
 * This class is to created
 * to test push and pull
 * 
 */
public class PageWithoutStorageImplementation extends Page
{
	
	
	/**
	 * @param name
	 * @param description
	 */
	public PageWithoutStorageImplementation(String name, String description)
	{
		super(name, description);
	}

	@Override
	public String[] rolesIs()
	{
		String[] s = {};
		return s;
	}
	
	@Override
	public String[] rolesHas()
	{
		String[] s = {};
		return s;
	}
	
	@Override
	public void setDescription(String description)
	{
		this.description = description;
	}
}
