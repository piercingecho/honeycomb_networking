package mvcModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.beans.property.SimpleMapProperty;
import java.util.HashMap;

public class PersonModel
{
	StringProperty  name = new SimpleStringProperty();
	StringProperty  email = new SimpleStringProperty();
	StringProperty  pronouns = new SimpleStringProperty();
	StringProperty phone = new SimpleStringProperty();
	StringProperty  id = new SimpleStringProperty();
	StringProperty  description= new SimpleStringProperty();
	ObservableList<String> externalLinks = FXCollections.observableArrayList();
	HashMap<String, ObservableList<String>> internalLinks;
	/**
	 * @param name
	 * @param email
	 * @param pronoun
	 * @param phone
	 * @param id
	 * @param description
	 * @param external_links
	 * @param page_links
	 */
	public PersonModel(StringProperty name, StringProperty email, StringProperty pronouns, StringProperty phone,
			StringProperty id, StringProperty description, ObservableList<String> external_links,
			HashMap<String, ObservableList<String>> page_links)
	{
		super();
		this.name = name;
		this.email = email;
		this.pronouns = pronouns;
		this.phone = phone;
		this.id = id;
		this.description = description;
		this.externalLinks = external_links;
		this.internalLinks = page_links;
	}
	
	public PersonModel() {
		this.name = new SimpleStringProperty();
		this.name.set("John Doe");
		
		this.email = new SimpleStringProperty();
		this.email.set("johndoent@gmail.com");
		
		this.pronouns = new SimpleStringProperty();
		this.pronouns.set("he/him");
		
		this.phone = new SimpleStringProperty();
		this.phone.set("333-333-4444");

		this.id = new SimpleStringProperty();
		this.id.set("25");
		
		this.description = new SimpleStringProperty();
		this.description.set("Hi yes this is a John Doe description");
		
		this.externalLinks = FXCollections.observableArrayList();
		this.externalLinks.add("github.com");
		this.externalLinks.add("linkedin.com");
		
		this.internalLinks = new HashMap<String, ObservableList<String>>();;
		
		this.initializeInternalLinksSampleValues();
		
		
	}
	
	public void initializeInternalLinksSampleValues()
	{
		String[] linkNames = {
				"employer",
				"follower",
				"following",
				"friend",
				"project",
				"skill",
				"news",
				"job_posting",
				"pending_job",
				"viewer",
				"editor"
		};
		
		String[][] linkValues = {
				// employers
				{
					"Amazon",
					"Big Tech",
					"Centre College"
				}
				,
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{}
		};
		

		for(int i=0; i<linkNames.length; i++)
		{
			ObservableList<String> currArrayList = FXCollections.observableArrayList();
			String currName = linkNames[i];
			
			
			for(int j=0; j<linkValues[i].length; j++)
			{
				currArrayList.add(linkValues[i][j]);
			}
			
			this.internalLinks.put(currName, currArrayList);
		}

		
		
	}
	
	/**
	 * @return the name
	 */
	public StringProperty getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(StringProperty name)
	{
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public StringProperty getEmail()
	{
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(StringProperty email)
	{
		this.email = email;
	}
	/**
	 * @return the pronoun
	 */
	public StringProperty getPronouns()
	{
		return pronouns;
	}
	/**
	 * @param pronoun the pronoun to set
	 */
	public void setPronouns(StringProperty pronouns)
	{
		this.pronouns = pronouns;
	}
	/**
	 * @return the phone
	 */
	public StringProperty getPhone()
	{
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(StringProperty phone)
	{
		this.phone = phone;
	}
	/**
	 * @return the id
	 */
	public StringProperty getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(StringProperty id)
	{
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public StringProperty getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(StringProperty description)
	{
		this.description = description;
	}
	/**
	 * @return the external_links
	 */
	public ObservableList<String> getExternal_links()
	{
		return externalLinks;
	}
	
	
	/**
	 * @return the page_links
	 */
	public HashMap<String, ObservableList<String>> getPage_links()
	{
		return internalLinks;
	}
	
	
	
	
}
