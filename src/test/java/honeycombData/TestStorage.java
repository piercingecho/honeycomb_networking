package honeycombData;
import RESTAPI.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import RESTAPI.*;
import simpleRESTServer.*;

class TestStorage
{
	static final String[] pageTypes = {
			"Person",
			"Company",
			"Skill",
			"JobPosting",
			"Project",
			"PageWithoutStorageImplementation",
			"NewsArticle"
	};
	void recreateRestDirectory()
	{
		String deleteMainDir = Storage.client.delete()
				.uri(Storage.uriBase)
				.retrieve()
				.body(String.class);

		
		//create the base directory for Honeycomb
		PostResponse rest_result = Storage.client.post()
				.uri(Storage.uriBase)
				.body(new RDesc("Honeycomb", "Professional networking app", "v1/Honeycomb"))
				.retrieve()
				.body(PostResponse.class);
		
		
		System.out.println(rest_result.successful());
		
		//create the base directories for other classes
		for(int i=0; i<Storage.pageTypes.length; i++)
		{
			String pageType = pageTypes[i];
			rest_result = Storage.client.post()
					.uri(Storage.uriBase + "/" + pageType)
					.body(new RDesc(pageType, "An instance of " + pageType, "v1/Honeycomb/" + pageType))
					.retrieve()
					.body(PostResponse.class);
		}
		
		//class for ids
		rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/" + "IDGeneratorSingleton")
				.body(new RDesc("IDGenerator", "An instance of ID Generator", "v1/Honeycomb/IDGeneratorSingleton"))
				.retrieve()
				.body(PostResponse.class);
	}
	
	@BeforeAll
	static void setup() throws Exception
	{
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		recreateRestDirectory();
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
		
		
		PostResponse rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.body(s)
				.retrieve()
				.body(PostResponse.class);
		
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

		PostResponse getExisting = Storage.client.get()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.retrieve()
				.body(PostResponse.class);

		
		PostResponse res = Storage.client.put()
				.uri(Storage.uriBase + "/Person/" + p.getId())
				.body(p)
				.retrieve()
				.body(PostResponse.class);
		
		System.out.println(res);
		
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

	
}




	/*
	@Test
	void testSinglePush()
	{
		boolean baseResult = Storage.create(new Skill("Python", "Can write in python"));
		assertEquals(true, baseResult);

		
		//boolean baseResult = Storage.create(new PageWithoutStorageImplementation("SecondPage", "This is another page!"));
		//assertEquals(true, baseResult);
	}
	/*
	@Test
	void testSinglePush()
	{
		boolean baseResult = Storage.create(new PageWithoutStorageImplementation("SecondPage", "This is another page!"));
		assertEquals(true, baseResult);
	}
	
	
	@Test
	void testStoringUsingBruteCode()
	{
		PageWithoutStorageImplementation p = new PageWithoutStorageImplementation("SecondPage", "This is another page!");
		String rest_result = Storage.client.post()
				.uri("http://localhost:8080/v1/Honeycomb/PageWithoutStorageImplementation/" + p.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				.retrieve()
				.body(String.class);
		System.out.println(rest_result);
		
		ResponsePage getExisting = Storage.client.get()
				.uri("http://localhost:8080/v1/Honeycomb/PageWithoutStorageImplementation/" + p.getId())
				.retrieve()
				.body(ResponsePage.class);
		
		System.out.println(getExisting.successful());

	}
	
	@Test	
	void testPush()
	{
		PageWithoutStorageImplementation p = new PageWithoutStorageImplementation("SecondPage", "This is another page!");
		boolean baseResult = Storage.create(p);
		assertEquals(true, baseResult);
		
//		String getExisting = Storage.client.get()
//				.uri(Storage.uriBase + "/PageWithoutStorageImplementation/" + p.getId())
//				.retrieve()
//				.body(String.class);
//		
//		RResponse existingResponse = Storage.getRResponseFromString(getExisting);
		

		
		PageWithoutStorageImplementation repeatedPage= new PageWithoutStorageImplementation("SamplePage", "This is a page!");
		boolean unrepeatedResult = Storage.create(repeatedPage);
		assertEquals(true, unrepeatedResult);
		
		
		System.out.println("Hello");
		boolean repeatedResult = Storage.create(repeatedPage);
		assertEquals(false, repeatedResult);
		
		//assertEquals(true, result);
	}*/

