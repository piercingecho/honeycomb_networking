package honeycombData;

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
	
	public final String[] getAssumableRoles()
	{
		String[] assumableRoles ={
				"applicant",
				"contributor",
				"employee",
				"editor",
				"follower",
				"mentor",
				"viewer",
				"friend"};
		return assumableRoles;
	}
	
	public final String[] getLinkableRoles()
	{
		String[] linkableRoles ={
				"application",
				"employer",
				"follower",
				"friend",
				"project",
				"skill",
				"viewer",
				"editor"};
		return linkableRoles;
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
	
	

}
