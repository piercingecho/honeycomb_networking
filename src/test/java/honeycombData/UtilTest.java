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
		for(int i=0; i<3; i++)
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
		
	}


}
