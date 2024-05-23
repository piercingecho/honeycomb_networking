package mvcModel;

import honeycombData.Page;
import honeycombData.Person;
import honeycombData.Storage;

public class SessionSingleton
{
	static SessionSingleton currentSession = new SessionSingleton();
	String userId;
	
	private SessionSingleton()
	{
		this.userId = "";
	}
	
	public static SessionSingleton getInstance()
	{
		return currentSession;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public void endSession()
	{
		this.userId = "";
	}
	
	public boolean startSession(String id, String password)
	{
		if(id == "")
		{
			return false;
		}
		
		Page page = Storage.pull(id);
		if(page == null)
		{
			return false;
		}
		
		//This is a valid page. Is it a person?
		try
		{
			Person person = (Person) page;
			person.getPronouns();
		}
		catch(Exception e)
		{
			//only people have pronouns, and so this is not a person
			
			return false;
		}
		
		if(this.checkPassword(id, password))
		{
			this.userId = id;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkPassword(String id, String password)
	{
		if(password == "" || id == "")
		{
			return false;
		}
		
		return true;
	}
	
}
