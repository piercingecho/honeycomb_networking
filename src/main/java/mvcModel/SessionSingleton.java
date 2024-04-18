package mvcModel;

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
