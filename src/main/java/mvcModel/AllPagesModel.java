package mvcModel;

import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class AllPagesModel
{
	BorderPane mainview;
	PersonDemoModel model;
	
	AllPagesModel(BorderPane mainview, PersonDemoModel model)
	{
		this.mainview = mainview;
		this.model = model;
	}
	public ObservableList<String> getPeople()
	{
		DemoAllInstances storageDemo = new DemoAllInstances();
		return storageDemo.getAllPeople();
	}
	public ObservableList<String> getCompanies()
	{
		DemoAllInstances storageDemo = new DemoAllInstances();
		return storageDemo.getAllCompanies();
	}
	public void getSkills()
	{
		
	}
	public void getJobPostings()
	{
		
	}
}
