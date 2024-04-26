package honeycombData;

import RESTAPI.*;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import java.util.Comparator;




public class Storage
{		
	static RestClient client = RestClient.create();
	static String uriBase = "http://localhost:8080/v1/Honeycomb";	

	
	// For GET/pull to work, we must go through each class that we store.
	static final String[] pageTypes = {
			"Person",
			"Company",
			"Skill",
			"JobPosting",
			"Project",
			"NewsArticle"
	};
	
	
	// uri format: uriBase/classname/objectid
	
	public static boolean create(Page newPage)
	{
		String uri = Storage.uriBase + Storage.getUriExtension(newPage);
		//does something exist there?
		RObjectResp getExisting = Storage.client.get()
				.uri(uri)
				.retrieve()
				.body(RObjectResp.class);
				
		if(getExisting.successful() == true)
		{
			//already exists. Use update for changing values.
			return false;
		}
				
		
		RObjectResp rest_result = Storage.client.post()
				.uri(uri)
				.body(newPage)
				.retrieve()
				.body(RObjectResp.class);
		
		return rest_result.successful();
	}
	
	public static Page pull(String id)
	{
		
		//we try each page type
		for(int i=0; i<pageTypes.length; i++)
		{
			String classString = pageTypes[i];
			Response r = Storage.responseFactory(id, classString);
			if(r != null)
			{
				Page p = Storage.pageFactory(r, classString);
				return p;
			}
		}
		return null;
	}
	
	
	public static boolean update(Page p)
	{
		String uri = Storage.uriBase + Storage.getUriExtension(p);
		RObjectResp getExisting = Storage.client.get()
				.uri(uri)
				.retrieve()
				.body(RObjectResp.class);
				
		if(getExisting.successful() == false)
		{
			//does not exist. Use post for creating objects.
			return false;
		}

		RObjectResp rest_result = Storage.client.put()
				.uri(uri)
				.body(p)
				.retrieve()
				.body(RObjectResp.class);

		return rest_result.successful();
		
	}
	
	static String getNextID()
	{
		//IMPLEMENT THIS
		
		String uri = uriBase + "/IDGenerator/0";
		RNextIDResp nextIDResp = Storage.client.get()
				.uri(uri)
				.retrieve()
				.body(RNextIDResp.class);
		
		int nextId = nextIDResp.data().nextIdToGive();
		
		String idToReturn = Integer.toString(nextId);
		
		RNextID newIDToStore = new RNextID("0",nextId + 1);
		
		RObjectResp resp = Storage.client.put()
				.uri(uri)
				.body(newIDToStore)
				.retrieve()
				.body(RObjectResp.class);
		if(!resp.successful())
		{
			// pushing new ID to rest server did not succeed
			idToReturn = "-1";
		}
		return idToReturn;

	}
		
	public static ArrayList<Person> getAllPeople()
	{
		RRestDescriptionResp peopleResponse = Storage.client.get()
				.uri(Storage.uriBase + "/" + "Person")
				.retrieve()
				.body(RRestDescriptionResp.class);
		
		ArrayList<RRestDescription> peopleDescriptions = peopleResponse.data();
		
		ArrayList<Person> people = new ArrayList<Person>();
		for(int i=0; i<peopleDescriptions.size(); i++)
		{
			String personId = peopleDescriptions.get(i).name();
			Person nextPerson = (Person) Storage.pull(personId);
			people.add(nextPerson);
		}
		
				
		people.sort(
			      (Person p1, Person p2) -> Integer.compare(
			    		  Integer.parseInt(p1.getId()),
			    		  Integer.parseInt(p2.getId()))
			      );
		
		return people;
	}
	
	static void updateAllPeople(ArrayList<Person> people)
	{
		for(int i=0; i<people.size(); i++)
		{
			Person p = people.get(i);
			Storage.update(p);
		}
	}
	public static ArrayList<Page> getLinkedPages(Page p, String link)
	{
		ArrayList<Page> pages = new ArrayList<Page>();
		if(!p.canBeLinkedAsRole(link))
		{
			return pages;
		}
		for(String id: p.getInternalLinks(link))
		{
			pages.add(Storage.pull(id));
		}
		pages.sort(
			      (Page p1, Page p2) -> Integer.compare(
			    		  Integer.parseInt(p1.getId()),
			    		  Integer.parseInt(p2.getId()))
			      );

		return pages;
	}
	
	
	private static String getUriExtension(Page page)
	{
		String uriExtension = "/";
		
		String pageClassName = page.getClass().getName();
		uriExtension += pageClassName.split("\\.")[1];
		uriExtension += "/" + page.getId();
		return uriExtension;
	}
	
	
	//IMPLEMENT THESE
	static Response responseFactory(String id, String classString)
	{
		String uri = Storage.uriBase + "/" + classString + "/" + id;
		RObjectResp getResult = Storage.client.get()
				.uri(uri)
				.retrieve()
				.body(RObjectResp.class);
		if(!getResult.successful())
		{
			return null;
		}
		
		//response got something, return the specific response type
		if(classString == "Person")
		{			
			RPersonResp getPerson = Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RPersonResp.class);
			return getPerson;

		}
		else if(classString == "Company")
		{
			RCompanyResp getCompany = Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RCompanyResp.class);
			return getCompany;

		}
		else if(classString == "Skill")
		{
			RSkillResp getSkill = Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RSkillResp.class);
			return getSkill;
		}
		else if(classString == "JobPosting")
		{
			RJobPostingResp getJobPosting= Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RJobPostingResp.class);
			return getJobPosting;

		}
		else if(classString == "Project")
		{
			RProjectResp getProject = Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RProjectResp.class);
			
			return getProject;
		}
		else
		{
			//classString == "NewsArticle"
			RNewsArticleResp getNews = Storage.client.get()
					.uri(uri)
					.retrieve()
					.body(RNewsArticleResp.class);
			return getNews;
		}
	}
	
	static Page pageFactory(Response r, String classString)
	{
		/**
		 * Uses a response and class string to
		 * generate a page based on the response.
		 * 
		 */
		if(classString == "Person")
		{
			RPerson responseData = ((RPersonResp) r).data();
			Person person = new Person(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks(),
					responseData.pronouns(),
					responseData.email(),
					responseData.phone());
			return person;
		}
		else if(classString == "Company")
		{
			RCompany responseData = ((RCompanyResp) r).data();
			Company company = new Company(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks());
				
			return company;

		}
		else if(classString == "Skill")
		{
			RSkill responseData = ((RSkillResp) r).data();
			Skill skill = new Skill(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks());
			return skill;
		}
		else if(classString == "Project")
		{
			RProject responseData = ((RProjectResp) r).data();
			Project project = new Project(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks());
			return project;
		}
		else if(classString == "NewsArticle")
		{
			RNewsArticle responseData = ((RNewsArticleResp) r).data();
			NewsArticle skill = new NewsArticle(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks());
			return skill;
		}
		else if(classString == "JobPosting")
		{
			RJobPosting responseData = ((RJobPostingResp) r).data();
			JobPosting job = new JobPosting(
					responseData.id(),
					responseData.name(),
					responseData.description(),
					responseData.externalLinks(),
					responseData.internalLinks());
			return job;
		}

		return null;
	}

	public static ArrayList<Skill> getAllSkills()
	{
		RRestDescriptionResp skillResponse = Storage.client.get()
				.uri(Storage.uriBase + "/" + "Skill")
				.retrieve()
				.body(RRestDescriptionResp.class);
		
		ArrayList<RRestDescription> skillDescriptions = skillResponse.data();
		
		ArrayList<Skill> skills = new ArrayList<Skill>();
		for(int i=0; i<skillDescriptions.size(); i++)
		{
			String skillId = skillDescriptions.get(i).name();
			Skill nextSkill = (Skill) Storage.pull(skillId);
			skills.add(nextSkill);
		}
		
		skills.sort(
			      (Page p1, Page p2) -> Integer.compare(
			    		  Integer.parseInt(p1.getId()),
			    		  Integer.parseInt(p2.getId()))
			      );

		return skills;
	}
	public static ArrayList<Company> getAllCompanies()
	{
		RRestDescriptionResp companyResponse = Storage.client.get()
				.uri(Storage.uriBase + "/" + "Company")
				.retrieve()
				.body(RRestDescriptionResp.class);
		
		ArrayList<RRestDescription> companyDescriptions = companyResponse.data();
		
		ArrayList<Company> companies = new ArrayList<Company>();
		for(int i=0; i<companyDescriptions.size(); i++)
		{
			String companyId = companyDescriptions.get(i).name();
			Company nextCompany = (Company) Storage.pull(companyId);
			companies.add(nextCompany);
		}
		
		companies.sort(
			      (Page p1, Page p2) -> Integer.compare(
			    		  Integer.parseInt(p1.getId()),
			    		  Integer.parseInt(p2.getId()))
			      );

		
		return companies;
	}
	
	public static ArrayList<JobPosting> getAllJobs()
	{
		RRestDescriptionResp jobResponse = Storage.client.get()
				.uri(Storage.uriBase + "/" + "JobPosting")
				.retrieve()
				.body(RRestDescriptionResp.class);
		
		ArrayList<RRestDescription> jobDescriptions = jobResponse.data();
		
		ArrayList<JobPosting> jobs = new ArrayList<JobPosting>();
		for(int i=0; i<jobDescriptions.size(); i++)
		{
			String jobId = jobDescriptions.get(i).name();
			JobPosting nextJob = (JobPosting) Storage.pull(jobId);
			jobs.add(nextJob);
		}
		
		jobs.sort(
			      (Page p1, Page p2) -> Integer.compare(
			    		  Integer.parseInt(p1.getId()),
			    		  Integer.parseInt(p2.getId()))
			      );

		
		return jobs;
	}


}
