package honeycombData;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Page
{
	String id;
	String name;
	String description;
	ArrayList<String> externalLinks;
	HashMap<String, ArrayList<Page>> internalLinks;
	
	// Each page subclass will have their own version of roles_has
	// and roles_is, static functions.
	// This allows for pages to ensure consistency with
	// which link names are connected to which subclasses.
	
	//   the roles a page can take when instantiated in another page.
	//   e.g. a person can be an "applicant" to a job 

	public abstract String[] getRolesIs();
	
	//   the roles a page can store from others. 
	//   e.g. a person can link their "employer" (a company).
	public abstract String[] getRolesHas();
	
	
	public Page(String name, String description)
	{
		IDGeneratorSingleton generator = IDGeneratorSingleton.getInstance();
		
		this.id = generator.getNextID();
		this.name = name;
		this.description = description;
		//things like a github or linkedin url.
		this.externalLinks = new ArrayList<String>();
		//links to others within honeycomb.
		this.internalLinks = new HashMap<String, ArrayList<Page>>();
	}
	
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
	/**
	 * @param target
	 * @return whether the page can assume target
	 * 
	 */
	public boolean canAssumeRole(String target)
	{
		String[] assumableRoles = this.getRolesIs();
		for(int i=0; i<assumableRoles.length; i++)
		{
			if(assumableRoles[i].equals(target))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param target
	 * @return whether the page can be linked to
	 * another page as target
	 */

	public boolean canBeLinkedAsRole(String target)
	{
		String[] linkableRoles = this.getRolesHas();
		for(int i=0; i<linkableRoles.length; i++)
		{
			if(linkableRoles[i].equals(target))
			{
				return true;
			}
		}
		return false;
	}


	//External Links getters and setters
	
	public ArrayList<String> getExternalLinks()
	{
		return this.externalLinks;
	}
	
	public void removeExternalLink(String link)
	{
		this.externalLinks.remove(link);
	}
	
	public void addExternalLink(String link)
	{
		this.externalLinks.add(link);
	}
	
	public boolean hasExternaLink(String link)
	{
		return this.externalLinks.contains(link);
	}
	
	
	
	
	/**
	 * 
	 * @param page the page to add as a link
	 * @param role the role to assign to this page
	 * @throws RoleNotAllowedException if the role is invalid on either side,
	 * that is, not in this.linkedRoles or page.assumedRoles
	 * */
	public void addInternalLink(Page page, String role) throws RoleNotAllowedException
	{
		// check for linkability
		if(!page.canAssumeRole(role))
		{
			throw new RoleNotAllowedException(this, role);
		}
		if(!this.canBeLinkedAsRole(role))
		{
			throw new RoleNotAllowedException(this, role);
		}
		
		// add while ensuring no duplicates
		if(!internalLinks.containsKey(role))
		{
			internalLinks.put(role, new ArrayList<Page>());
		}
		
		ArrayList<Page> current_pages = internalLinks.get(role);
		
		if(!current_pages.contains(page))
		{
			current_pages.add(page);
		}
	}
	
	/**
	 * 
	 * @param page The page to remove
	 * @param role The role to remove it from
	 * @throws RoleNotAllowedException if the role isn't in this.linkedRoles.
	 */
	public void deleteInternalLink(Page page, String role) throws RoleNotAllowedException
	{
		if(!this.canBeLinkedAsRole(role))
		{
			throw new RoleNotAllowedException(this, role);
		}
		
		if(!this.internalLinks.containsKey(role))
		{
			return;
		}
		ArrayList<Page> current_pages = internalLinks.get(role);
		current_pages.remove(page);
	}
	
	/**
	 * 
	 * @param role the role to get the internal links for
	 * @return all pages linked as role to this
	 * @throws RoleNotAllowedException if this cannot have links to that role
	 */
	public ArrayList<Page> getInternalLinks(String role) throws RoleNotAllowedException
	{
		if(!this.canBeLinkedAsRole(role))
		{
			throw new RoleNotAllowedException(this, role);
		}

		if(!this.internalLinks.containsKey(role))
		{
			this.internalLinks.put(role, new ArrayList<Page>());
		}
		return this.internalLinks.get(role);
	}
	
	
	
	
	
}
