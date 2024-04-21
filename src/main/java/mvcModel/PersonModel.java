package mvcModel;

import honeycombData.Page;
import honeycombData.Person;
import honeycombData.Storage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonModel extends PageModel
{
	StringProperty pronouns;
	StringProperty email;
	StringProperty phone;

	public PersonModel(Person page)
	{
		super(page);

		this.pronouns = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.phone = new SimpleStringProperty();
		if(this.associatedPage != null)
		{
			this.pronouns.setValue(page.getPronouns());
			this.email.setValue(page.getEmail());
			this.phone.setValue(page.getPhone());
		}
	}
	
	public PersonModel(String id)
	{
		this((Person) Storage.pull(id));
	}
	
	@Override
	public boolean updatePageInStorage()
	{
		((Person) this.associatedPage).setPronouns(this.pronouns.getValue());
		((Person) this.associatedPage).setEmail(this.email.getValue());
		((Person) this.associatedPage).setPhone(this.phone.getValue());
		
		
		return super.updatePageInStorage();

	}

	@Override
	public Person getAssociatedPage()
	{
		return (Person) this.associatedPage;
	}
		
	
	/**
	 * @return the pronouns
	 */
	public StringProperty getPronouns()
	{
		return pronouns;
	}

	/**
	 * @param pronouns the pronouns to set
	 */
	public void setPronouns(StringProperty pronouns)
	{
		this.pronouns = pronouns;
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

	






}
