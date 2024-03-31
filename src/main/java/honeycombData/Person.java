package honeycombData;

import java.util.ArrayList;

public class Person extends Page
{ 
	String pronouns;
	String email;
	String phone;
	public Person(String name, String description)
	{
		super(name, description);
		this.pronouns = "";
		this.email = "";
		this.phone = "";
	}

	public Person(String name, String description, String pronouns, String email, String phone)
	{
		super(name, description);
		this.pronouns = pronouns;
		this.email = email;
		this.phone = phone;
	}
	
	public final String[] getRolesIs()
	{
		String[] rolesIs ={
				"applicant",
				"contributor",
				"employee",
				"editor",
				"follower",
				"mentor",
				"viewer",
				"friend"};
		return rolesIs;
	}
	
	public final String[] getRolesHas()
	{
		String[] rolesHas ={
				"employer",
				"follower",
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
		ArrayList<Page> viewers = page.getInternalLinks("viewer");
		if(viewers.isEmpty())
		{
			return true;
		}
		
		if(viewers.contains(this))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canEdit(Page page) throws RoleNotAllowedException
	{
		ArrayList<Page> viewers = page.getInternalLinks("editor");
		
		if(viewers.contains(this))
		{
			return true;
		}
		
		return false;

	}
	

}
