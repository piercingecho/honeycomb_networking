package honeycombData;
import RESTAPI.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.print.attribute.standard.PagesPerMinute;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;

//import RESTAPI.*;
import simpleRESTServer.*;


class TestStorage
{
	static final String[] pageTypes = {
			"Person",
			"Company",
			"Skill",
			"JobPosting",
			"Project",
			"NewsArticle"
	};

	
	@BeforeAll
	static void setup() throws Exception
	{
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		UtilTest.recreateRestDirectory();
	}

	@Test
	void testSetup()
	{
		
	}
	@Test
	void testPersonPost()
	{
		Person p = new Person("Alice", "Alice al", "she/her", "alice@alice.alice", "222-222-2222");
		p.createInStorage();
	}
	@Test
	void testNewsPost()
	{
		NewsArticle testNews = new NewsArticle("testnews", "testnews");
		testNews.createInStorage();
	}
	
	@Test
	void testRepeatedPost()
	{
		// post a skill by instantiating it
		Skill s = new Skill("Python", "Can write in python");
		
		s.createInStorage();
		
		
		RObjectResp rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.body(s)
				.retrieve()
				.body(RObjectResp.class);
		
		//the post was already sent in skill's Storage.create().
		assertEquals(false, rest_result.successful());

	}
	
	@Test
	void testRepeatedCreate()
	{
		// post a skill by instantiating it
		Skill s = new Skill("Python", "Can write in python");
		
		boolean first_post = s.createInStorage();
		
		boolean second_post = s.createInStorage();
				
		//the post was already sent in skill's Storage.create().
		assertEquals(false, second_post);
		assertEquals(true, first_post);

	}

	
	@Test
	void testPostOtherPage()
	{
		//skill
		Skill s = new Skill("Python", "Can write in python");
		
		s.createInStorage();
		
		RSkillResp skillRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.retrieve()
				.body(RSkillResp.class);
		
		RSkill newskill = skillRetrieved.data();
		
		assertEquals(newskill.description(), s.getDescription());
		assertEquals(newskill.id(), s.getId());
		assertEquals(newskill.name(), s.getName());
		
		
		//company
		Company c = new Company("Python", "Can write in python");
		
		c.createInStorage();
		
		RCompanyResp companyRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Company/" + c.getId())
				.retrieve()
				.body(RCompanyResp.class);
		
		RCompany newcompany = companyRetrieved.data();
		
		assertEquals(newcompany.description(), c.getDescription());
		assertEquals(newcompany.id(), c.getId());
		assertEquals(newcompany.name(), c.getName());

	}
	
	@Test
	void testPostPerson()
	{
		Person p = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		
		p.createInStorage();
		
		RPersonResp personRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.retrieve()
				.body(RPersonResp.class);
		
		RPerson newperson = personRetrieved.data();
		
		assertEquals(newperson.description(), p.getDescription());
		assertEquals(newperson.id(), p.getId());
		assertEquals(newperson.name(), p.getName());
		
		assertEquals(newperson.email(), p.getEmail());
		assertEquals(newperson.pronouns(), p.getPronouns());


	}
	
	@Test
	void testPutManual()
	{
		Person p = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		p.createInStorage();

		p.setEmail("c@d.org");
		p.setPronouns("she/they");
		p.setPhone("222-222-2221");

		Storage.client.get()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.retrieve()
				.body(RObjectResp.class);

		
		Storage.client.put()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.body(p)
				.retrieve()
				.body(RObjectResp.class);
				
	}
	
	@Test
	void testUpdatePerson()
	{
		Person p = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		p.createInStorage();

		p.setEmail("c@d.org");
		p.setPronouns("she/they");
		p.setPhone("222-222-2221");
		
		p.updateInStorage();
		
		RPersonResp personRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.retrieve()
				.body(RPersonResp.class);

		
		RPerson newperson = personRetrieved.data();
		
		assertEquals(newperson.description(), p.getDescription());
		assertEquals(newperson.id(), p.getId());
		assertEquals(newperson.name(), p.getName());
		
		assertEquals(newperson.email(), p.getEmail());
		assertEquals(newperson.pronouns(), p.getPronouns());


	}
	
	@Test
	void testUpdateOtherPage()
	{
		Skill s = new Skill("Python", "Can write in python");
		
		s.createInStorage();
		
		s.setDescription("A snake");
		s.setName("Live Python");
		
		s.updateInStorage();
		
		RSkillResp skillRetrieved = Storage.client.get()
		.uri(Storage.uriBase + "/Skill/" + s.getId())
		.retrieve()
		.body(RSkillResp.class);

		RSkill newskill = skillRetrieved.data();
		
		assertEquals(newskill.description(), s.getDescription());
		assertEquals(newskill.id(), s.getId());
		assertEquals(newskill.name(), s.getName());

	}

	
	@Test
	void testUpdateAllPeople()
	{
		Person p1 = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		Person p2 = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		Person p3 = new Person("person", "holds their purse in", "he/she", "a@b.com", "111-111-1112");
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		for(int i=0; i<people.size(); i++)
		{
			Storage.create(people.get(i));
			people.get(i).setDescription("Hi new description! :D");
		}
		
		Storage.updateAllPeople(people);
		
		
		for(int i=0; i<people.size(); i++)
		{

			RPersonResp personRetrieved = Storage.client.get()
					.uri(Storage.uriBase + "/Person/" + people.get(i).getId())
					.retrieve()
					.body(RPersonResp.class);
			
			assertEquals(personRetrieved.data().description(), people.get(i).getDescription());
		}

	}
	
	@Test
	void testResponseFactory()
	{
		Person person = new Person("Alice", "likes malice");
		Company company = new Company("Company", "likes company");
		NewsArticle news = new NewsArticle("News", "woah it's new");
		Project project = new Project("Project", "projected to do well");
		Skill skill = new Skill("Skill", "seems skillful");
		JobPosting job = new JobPosting("Job", "post pun idk");
		
		Storage.create(person);
		Storage.create(news);
		Storage.create(company);
		Storage.create(project);
		Storage.create(skill);
		Storage.create(job);
		
		//person
		String id = person.getId();
		RPersonResp personRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Person/" + id)
				.retrieve()
				.body(RPersonResp.class);
		
		RPersonResp storagePerson= (RPersonResp) Storage.responseFactory(id, "Person");
		
		assertEquals(personRetrieved, storagePerson);
		
		
		//company
		id = company.getId();
		RCompanyResp retrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Company/" + id)
				.retrieve()
				.body(RCompanyResp.class);
		
		RCompanyResp storage= (RCompanyResp) Storage.responseFactory(id, "Company");
		
		assertEquals(retrieved, storage);

		//skill
		id = skill.getId();
		RSkillResp skillRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Skill/" + id)
				.retrieve()
				.body(RSkillResp.class);
		
		RSkillResp skillStorage= (RSkillResp) Storage.responseFactory(id, "Skill");
		
		assertEquals(skillRetrieved, skillStorage);

		
		//project
		id = project.getId();
		RProjectResp retrievedProject = Storage.client.get()
				.uri(Storage.uriBase + "/Project/" + id)
				.retrieve()
				.body(RProjectResp.class);
		
		RProjectResp storageProject = (RProjectResp) Storage.responseFactory(id, "Project");
		
		assertEquals(retrievedProject, storageProject);

		//news
		id = news.getId();
		RNewsArticleResp retrievedNewsArticle = Storage.client.get()
				.uri(Storage.uriBase + "/NewsArticle/" + id)
				.retrieve()
				.body(RNewsArticleResp.class);
		
		RNewsArticleResp storageNewsArticle = (RNewsArticleResp) Storage.responseFactory(id, "NewsArticle");
		
		assertEquals(retrievedNewsArticle, storageNewsArticle);

		//job
		id = job.getId();
		RJobPostingResp retrievedJobPosting = Storage.client.get()
				.uri(Storage.uriBase + "/JobPosting/" + id)
				.retrieve()
				.body(RJobPostingResp.class);
		
		RJobPostingResp storageJobPosting = (RJobPostingResp) Storage.responseFactory(id, "JobPosting");
		
		assertEquals(retrievedJobPosting, storageJobPosting);
	}
	
	@Test
	void testPageFactory()
	{
		Person person = new Person("Alice", "likes malice");
		Company company = new Company("Company", "likes company");
		NewsArticle news = new NewsArticle("News", "woah it's new");
		Project project = new Project("Project", "projected to do well");
		Skill skill = new Skill("Skill", "seems skillful");
		JobPosting job = new JobPosting("Job", "post pun idk");
		SimpleMessage message = new SimpleMessage("Message", "simply stated");
		
		Storage.create(person);
		Storage.create(news);
		Storage.create(company);
		Storage.create(project);
		Storage.create(skill);
		Storage.create(job);
		Storage.create(message);
		
		//person
		String id = person.getId();
		Response storagePersonResp = Storage.responseFactory(id, "Person");
		Person storagePerson = (Person) Storage.pageFactory(storagePersonResp, "Person");
		
		assertEquals(storagePerson, person);
	
		
		
		//company
		id = company.getId();
		Response storageCompanyResp = Storage.responseFactory(id, "Company");
		Company storageCompany = (Company) Storage.pageFactory(storageCompanyResp, "Company");

		
		assertEquals(company, storageCompany);

		//skill
		id = skill.getId();
		Response storageSkillResp = Storage.responseFactory(id, "Skill");
		Page storageSkill = Storage.pageFactory(storageSkillResp, "Skill");

		
		assertEquals(skill, storageSkill);

		
		//project
		id = project.getId();
		Response storageProjectResp = Storage.responseFactory(id, "Project");
		Page storageProject = Storage.pageFactory(storageProjectResp, "Project");

		
		assertEquals(project, storageProject);

		//news
		id = news.getId();
		Response storageNewsResp = Storage.responseFactory(id, "NewsArticle");
		Page storageNews= Storage.pageFactory(storageNewsResp, "NewsArticle");
		assertEquals(news, storageNews);

		//job
		id = job.getId();
		Response storageJobResp = Storage.responseFactory(id, "JobPosting");
		Page storageJob =  Storage.pageFactory(storageJobResp, "JobPosting");
		assertEquals(job, storageJob);

		//simple messsage
		//job
		id = message.getId();
		Response messageResp = Storage.responseFactory(id, "SimpleMessage");
		Page storageMessage =  Storage.pageFactory(messageResp, "SimpleMessage");
		assertEquals(message, storageMessage);

	}
	
	@Test
	void testGet()
	{
		Person person = new Person("Alice", "likes malice");
		Company company = new Company("Company", "likes company");
		NewsArticle news = new NewsArticle("News", "woah it's new");
		Project project = new Project("Project", "projected to do well");
		Skill skill = new Skill("Skill", "seems skillful");
		JobPosting job = new JobPosting("Job", "post pun idk");
		SimpleMessage message = new SimpleMessage("Message", "simply stated");
		
		Storage.create(person);
		Storage.create(news);
		Storage.create(company);
		Storage.create(project);
		Storage.create(skill);
		Storage.create(job);
		Storage.create(message);

		ArrayList<Page> pages = new ArrayList<Page>();
		pages.add(person);
		pages.add(company);
		pages.add(news);
		pages.add(project);
		pages.add(skill);
		pages.add(job);
		pages.add(message);
		
		
		//RPersonResp r = (RPersonResp) Storage.responseFactory("2", "Person");
		Storage.responseFactory("2", "Person");
		
		//System.out.println("R" + r);
		
		String id;
		for(int i=0; i<pages.size(); i++)
		{
			Page currpage = pages.get(i);
			id = currpage.getId();

			Page storageRetrieved = Storage.pull(id);
			assertEquals(currpage, storageRetrieved);
		}
		
	}
	@Test
	void testNulls()
	{
		assertEquals(null, Storage.pull("-1"));
		
		Person p = new Person("","");
		p.setId("5"); //never do this in production
		assertEquals(false, p.updateInStorage());
		
		assertEquals(null, Storage.responseFactory("2", "NOTAVALIDNAME.mp4"));
		
		String id = p.getId();
		Response storagePersonResp = Storage.responseFactory(id, "Person");
		Page storageNull = Storage.pageFactory(storagePersonResp, "NOTAVALIDNAME.mp4");

		assertEquals(null, storageNull);
	}
	
	@Test
	void testIDs()
	{
		String idone = Storage.getNextID();
		String idtwo = Storage.getNextID();
		
		assertEquals(Integer.parseInt(idtwo), Integer.parseInt(idone)+1);
	}
	
	@Test
	void testGetAllPeople()
	{
		ArrayList<Person> allPeopleInRest = Storage.getAllPeople();
		assertEquals(allPeopleInRest, new ArrayList<Person>());
		
		Person a = new Person("a","b");
		Person b = new Person("c","d");
		Storage.create(a);
		Storage.create(b);
		
		ArrayList<Person> compareAgainst = new ArrayList<Person>();

		compareAgainst.add(a);
		compareAgainst.add(b);
		
		allPeopleInRest = Storage.getAllPeople();
		assertEquals(allPeopleInRest, compareAgainst);
		
	}

	
}



