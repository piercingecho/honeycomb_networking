package honeycombData;

import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import RESTAPI.RNextID;
import RESTAPI.RObjectResp;
import simpleRESTServer.RDesc;

public class UtilTest
{
	static final String[] pageTypes = {
			"Person",
			"Company",
			"Skill",
			"JobPosting",
			"Project",
			"NewsArticle"
	};

	public static void recreateRestDirectory()
	{
		try {
		Storage.client.delete()
				.uri(Storage.uriBase)
				.retrieve()
				.body(String.class);
		}
		catch(Exception e)
		{
			//do nothing
		}

		
		//create the base directory for Honeycomb
		RObjectResp rest_result = Storage.client.post()
				.uri(Storage.uriBase)
				.body(new RDesc("Honeycomb", "Professional networking app", "v1/Honeycomb"))
				.retrieve()
				.body(RObjectResp.class);
		
		
		//System.out.println(rest_result.successful());
		
		//create the base directories for other classes
		for(int i=0; i<Storage.pageTypes.length; i++)
		{
			String pageType = pageTypes[i];
			rest_result = Storage.client.post()
					.uri(Storage.uriBase + "/" + pageType)
					.body(new RDesc(pageType, "An instance of " + pageType, "v1/Honeycomb/" + pageType))
					.retrieve()
					.body(RObjectResp.class);
		}
		
		//class for ids
		rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/" + "IDGenerator")
				.body(new RDesc("IDGenerator", "An instance of ID Generator", "v1/Honeycomb/IDGeneratorSingleton"))
				.retrieve()
				.body(RObjectResp.class);
		
		rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/" + "IDGenerator" + "/" + "0")
				.body(new RNextID("0", 0))
				.retrieve()
				.body(RObjectResp.class);
		}

	public static void createSampleData(ArrayList<Person> people, ArrayList<Company> companies, ArrayList<Skill> skills,
			ArrayList<JobPosting> jobs)
	{
		
		Person loggedInPerson = new Person("sampleUserIsAPersonToo","TheValuedTestUser","it/its","robot@css.html","123-456-7890");
		people.add(loggedInPerson);
		Storage.create(loggedInPerson);
		
		int maxIter = 3;
		for(int i=0; i<maxIter; i++)
		{
			Person personi = new Person("person"+Integer.toString(i),"Indexed person","theythem","e@mai.l","000-000-0000");
			personi.addExternalLink("github.io/" + Integer.toString(i*5));
			personi.addExternalLink("linkedin.com/" + Integer.toString(i*10+2));

			people.add(personi);
			
			Storage.create(personi);
			
			Company companyi = new Company("company"+Integer.toString(i),"Indexed company");
			companyi.addExternalLink("github.io/" + Integer.toString(i*5));
			companyi.addExternalLink("linkedin.com/" + Integer.toString(i*10+2));

			companies.add(companyi);
			
			Storage.create(companyi);
			
			Skill skilli = new Skill("skill"+Integer.toString(i),"Indexed company");
			skilli.addExternalLink("github.io/" + Integer.toString(i*5));
			skilli.addExternalLink("linkedin.com/" + Integer.toString(i*10+2));

			skills.add(skilli);
			
			Storage.create(skilli);
			
			JobPosting jobi = new JobPosting("job"+Integer.toString(i),"Indexed company");
			jobi.addExternalLink("github.io/" + Integer.toString(i*5));
			jobi.addExternalLink("linkedin.com/" + Integer.toString(i*10+2));

			jobs.add(jobi);
			
			Storage.create(jobi);
		}
		
		// pages that cannot be viewed
		Person unviewablePerson = new Person("~secret person~007","CLASSIFIED","they/them","CLASSIFIED","***-***-****");
		unviewablePerson.addInternalLink(unviewablePerson, "viewer");
		people.add(unviewablePerson);
		Storage.create(unviewablePerson);
		
		Company unviewableCompany = new Company("~secret company~007","CLASSIFIED");
		unviewableCompany.addInternalLink(unviewablePerson, "viewer");
		companies.add(unviewableCompany);
		Storage.create(unviewableCompany);
		
		Skill unviewableSkill = new Skill("~secret skill~007","CLASSIFIED");
		unviewableSkill.addInternalLink(unviewablePerson, "viewer");
		skills.add(unviewableSkill);
		Storage.create(unviewableSkill);

		
		JobPosting unviewableJobPosting = new JobPosting("~secret job~007","CLASSIFIED");
		unviewableJobPosting.addInternalLink(unviewablePerson, "viewer");
		jobs.add(unviewableJobPosting);
		Storage.create(unviewableJobPosting);
		
		
		// pages that can be edited
		Person editablePerson = new Person("EditMePerson","editableDescription","they/she","email@example.com","111-111-1111");
		editablePerson.addInternalLink(loggedInPerson, "editor");
		people.add(editablePerson);
		Storage.create(editablePerson);
		
		Company editableCompany = new Company("~woa u lead this company~","cool description");
		editableCompany.addInternalLink(loggedInPerson, "editor");
		companies.add(editableCompany);
		Storage.create(editableCompany);
		
		Skill editableSkill = new Skill("~woa u manage this skill~","fun description");
		editableSkill.addInternalLink(loggedInPerson, "editor");
		skills.add(editableSkill);
		Storage.create(editableSkill);

		
		JobPosting editableJob = new JobPosting("~woah u made this job!~","classy description");
		editableJob.addInternalLink(loggedInPerson, "editor");
		jobs.add(editableJob);
		Storage.create(editableJob);



		

		
	}


}
