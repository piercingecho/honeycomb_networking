package honeycombData;

import java.util.ArrayList;
import java.util.HashMap;

public class Person extends Page
{ 
	String pronouns;
	String email;
	String phone;
	public Person(String id, String name, String description, ArrayList<String> externalLinks, HashMap<String, ArrayList<String>> internalLinks, String pronouns, String email, String phone)
	{
		super(id, name, description, externalLinks, internalLinks);
		this.pronouns = pronouns;
		this.email = email;
		this.phone = phone;

	}
	public Person(String name, String description)
	{
		super(name, description);
		this.pronouns = "";
		this.email = "";
		this.phone = "";
		Storage.create(this);
	}

	public Person(String name, String description, String pronouns, String email, String phone)
	{
		super(name, description);
		this.pronouns = pronouns;
		this.email = email;
		this.phone = phone;
		Storage.create(this);
	}
	
	public final String[] rolesIs()
	{
		String[] rolesIs ={
				"applicant",
				"contributor",
				"employee",
				"editor",
				"follower",
				"following",
				"mentor",
				"viewer",
				"friend"};
		return rolesIs;
	}
	
	public final String[] rolesHas()
	{
		String[] rolesHas ={
				"employer",
				"follower",
				"following",
				"friend",
				"project",
				"skill",
				"news",
				"job_posting",
				"viewer",
				"editor"};
		return rolesHas;
	}

	/**
	 * @return the pronouns
	 */
	public String getPronouns()
	{
		return pronouns;
	}

	/**
	 * @param pronouns the pronouns to set
	 */
	public void setPronouns(String pronouns)
	{
		this.pronouns = pronouns;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public boolean canView(Page page) throws RoleNotAllowedException
	{
		ArrayList<String> viewers = page.getInternalLinks("viewer");
		if(viewers.isEmpty())
		{
			return true;
		}
		
		if(viewers.contains(this.getId()))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canEdit(Page page) throws RoleNotAllowedException
	{
		ArrayList<String> viewers = page.getInternalLinks("editor");
		
		if(viewers.contains(this.getId()))
		{
			return true;
		}
		
		return false;

	}
	
	public void follow(Page page) throws RoleNotAllowedException
	{
		this.addInternalLink(page, "following");
		page.addInternalLink(this, "follower");
	}
	

}
