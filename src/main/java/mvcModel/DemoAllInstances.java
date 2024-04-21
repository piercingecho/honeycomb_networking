package mvcModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DemoAllInstances
{
	ObservableList<String> allPeople;
	ObservableList<String> allCompanies;
	
	
	public DemoAllInstances()
	{
		allPeople = FXCollections.observableArrayList();
		allCompanies = FXCollections.observableArrayList();
		allPeople.add("Alice");
		allPeople.add("John Doe");
		allPeople.add("Brad Keith");
		allPeople.add("Bob");
		allPeople.add("Steve");
		allPeople.add("Maya");
		allPeople.add("MIchella");
		allPeople.add("Mira");
		allPeople.add("Alison");
		allPeople.add("Presley");
		allPeople.add("Pia");
		allPeople.add("Stella");
		allPeople.add("Amy");
	}
	
	public ObservableList<String> getAllPeople()
	{
		return allPeople;
	}
	public ObservableList<String> getAllCompanies()
	{
		return allCompanies;
	}
	
	public ObservableList<String> getAllLinks(String id, String link)
	{
		if(id == "25")
		{
			if(link == "employer")
			{
				ObservableList<String> allEmployers = FXCollections.observableArrayList();
				allEmployers.add("Amazon");
				return allEmployers;

			}
			else if(link == "friend")
			{
				ObservableList<String> allFriends = FXCollections.observableArrayList();
				allFriends.add("Bob");
				allFriends.add("Charlie");
				
				return allFriends;

			}
		}
		return null;
	}
	
	public static PersonDemoModel getPageFromId(String id)
	{
		if(id == "26")
		{
			return new PersonDemoModel();
		}
		
		return null;
	}
}
