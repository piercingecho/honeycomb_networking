package mvcModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class AllPagesModel
{
	BorderPane mainview;
	PageModelPerson currentUser;
	
	AllPagesModel(BorderPane mainview)
	{
		this.mainview = mainview;
		currentUser = new PageModelPerson(SessionSingleton.getInstance()
											.getUserId());
	}
	
	
	public ObservableList<PageModel> getPeople()
	{
		return FXCollections.observableList(StorageModel.getAllPeopleModels());
	}
	public ObservableList<PageModel> getCompanies()
	{
		return FXCollections.observableList(StorageModel.getAllCompanyModels());
	}
	public ObservableList<PageModel> getSkills()
	{
		return FXCollections.observableList(StorageModel.getAllSkillModels());

	}
	public ObservableList<PageModel> getJobPostings()
	{
		return FXCollections.observableList(StorageModel.getAllJobModels());
	}
}
