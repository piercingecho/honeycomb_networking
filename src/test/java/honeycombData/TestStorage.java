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
	}
	@Test
	void testNewsPost()
	{
		NewsArticle testNews = new NewsArticle("testnews", "testnews");
	}
	
	@Test
	void testPost()
	{
		// post a skill by instantiating it
		Skill s = new Skill("Python", "Can write in python");
		PostResponse rest_result = Storage.client.post()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.body(s)
				.retrieve()
				.body(PostResponse.class);
		
		//the post was already sent in skill's Storage.create().
		assertEquals(false, rest_result.successful());
		
		//what is working
		PostResponse objRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.retrieve()
				.body(PostResponse.class);
		
		System.out.println(objRetrieved);
		Object newobj = objRetrieved.data();
		System.out.println(newobj);
		

		//what i want to work
		RSkillResp skillRetrieved = Storage.client.get()
				.uri(Storage.uriBase + "/Skill/" + s.getId())
				.retrieve()
				.body(RSkillResp.class);
		
		System.out.println(skillRetrieved);
		RSkill newskill = skillRetrieved.data();
		System.out.println(newskill);

		
		
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

