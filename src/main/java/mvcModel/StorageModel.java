package mvcModel;

import java.util.ArrayList;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Page;
import honeycombData.Person;
import honeycombData.Skill;
import honeycombData.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StorageModel
{
	public static ObservableList<PageModel> getAllPeopleModels()
	{
		ArrayList<Person> allPeoplePages = Storage.getAllPeople();
		
		ArrayList<PageModel> peopleModels = new ArrayList<>();
		for(Person p: allPeoplePages)
		{
			peopleModels.add(p.createPageModel());
		}
		
		return FXCollections.observableList(peopleModels);
	}
	
	
	
	public static ObservableList<PageModel> getAllSkillModels()
	{
		ArrayList<Skill> allSkillPages = Storage.getAllSkills();
		
		ArrayList<PageModel> skillModels = new ArrayList<>();
		for(Skill s: allSkillPages)
		{
			skillModels.add(s.createPageModel());
		}
		
		return FXCollections.observableList(skillModels);
	}

	public static ObservableList<PageModel> getAllCompanyModels()
	{
		ArrayList<Company> allCompanyPages = Storage.getAllCompanies();
		
		ArrayList<PageModel> companyModels = new ArrayList<>();
		for(Company c: allCompanyPages)
		{
			companyModels.add(c.createPageModel());
		}
		
		return FXCollections.observableList(companyModels);
	}

	public static ObservableList<PageModel> getAllJobModels()
	{
		ArrayList<JobPosting> allJobPages = Storage.getAllJobs();
		
		ArrayList<PageModel> jobModels = new ArrayList<>();
		for(JobPosting j: allJobPages)
		{
			jobModels.add(j.createPageModel());
		}
		
		return FXCollections.observableList(jobModels);
	}
	
	public static ObservableList<PageModel> getAllLinkedPagesModels(Page page, String link)
	{
		ArrayList<Page> allLinkedPages = Storage.getLinkedPages(page, link);
		
		ArrayList<PageModel> pageModels = new ArrayList<>();
		for(Page p: allLinkedPages)
		{
			pageModels.add(p.createPageModel());
		}
		
		return FXCollections.observableList(pageModels);
	}



	public static PageModel pull(String userId)
	{
		// TODO Auto-generated method stub
		return Storage.pull(userId).createPageModel();
	}


	
}
