package honeycombData;

import RESTAPI.*;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import RESTAPI.PostResponse;
import RESTAPI.RGetString;

import org.springframework.http.MediaType;
import java.util.ArrayList;




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
			"NewsArticle",
			"PageWithoutStorageImplementation"
	};
	
	
	// uri format: uriBase/classname/objectid
	
	static boolean create(Page newPage)
	{
		String uri = Storage.uriBase + Storage.getUriExtension(newPage);
		//does something exist there?
		RGetString getExisting = Storage.client.get()
				.uri(uri)
				.retrieve()
				.body(RGetString.class);
				
		if(getExisting.successful() == true)
		{
			//already exists. Use update for changing values.
			return false;
		}
				
		
		PostResponse rest_result = Storage.client.post()
				.uri(uri)
				.body(newPage)
				.retrieve()
				.body(PostResponse.class);
		
		return rest_result.successful();
	}
	
	static Page pull(String id)
	{
//		for(int i=0; i<pageTypes.length; i++)
//		{
//			String classString = pageTypes[i];
//			Response r = Storage.responseFactory(id, classString);
//			if(r.successful())
//			{
//				Page p = Storage.pageFactory(r, classString);
//				return p;
//			}
//		}
		return null;
	}
	
	
	static boolean update(Page p)
	{
		return true;

//		String uri = Storage.uriBase + Storage.getUriExtension(p);
//		PostResponse rest_result = Storage.client.post()
//				.uri(uri)
//				.body(p)
//				.retrieve()
//				.body(PostResponse.class);
//
		//return rest_result.successful();
	}
	
	static String getNextId()
	{
		//IMPLEMENT THIS
		
		String uri = uriBase + "IDGeneratorSingleton" + "/" + "generator";
		return uri;

	}
	
	static ArrayList<Person> getAllPeople()
	{
		return new ArrayList<Person>();
	}
	
	private static String getUriExtension(Page page)
	{
		String uriExtension = "/";
		
		String pageClassName = page.getClass().getName();
		uriExtension += pageClassName.split("\\.")[1];
		uriExtension += "/" + page.getId();
		return uriExtension;
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.println(Storage.client);
		
		
		Person alice = new Person("","");
		System.out.println(alice.getClass().getName());
		Page bob = new Person("","");
		System.out.println(bob.getClass().getName());
		
		
		System.out.println(alice.getId());
		
		/*
		Storage.push(alice);
		System.out.println("Alice pushed!");
		Storage.push(bob);
		System.out.println("Bob pushed!");
		try
		{
			Storage).push(alice);
		}
		catch (Exception e)
		{
			System.out.print("Bad: ");
			System.out.print(e);
		}
		*/
		
	}
	
}
