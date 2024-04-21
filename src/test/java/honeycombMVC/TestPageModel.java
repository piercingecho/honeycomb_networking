package honeycombMVC;
import honeycombData.*;
import mvcModel.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class TestPageModel
{
	ArrayList<Person> people;
	ArrayList<String> peopleIds;
	ArrayList<PersonModel> peopleModels;
	ArrayList<Page> pages;
	ArrayList<PageModel> pageModels;
	ArrayList<String> pageIds;
	
	@BeforeEach
	void SetUp() throws Exception
	{
		UtilTest.recreateRestDirectory();
		people = new ArrayList<>();
		peopleIds = new ArrayList<>();
		
		peopleModels = new ArrayList<>();
		
		pages = new ArrayList<>();
		pageIds = new ArrayList<>();
		pageModels = new ArrayList<>();
		
		// sample data
		for(int i=0; i<10; i++)
		{
			//add to people
			Person personi = new Person("number"+Integer.toString(i),"Indexed person","theythem","e@mai.l","000-000-0000");
			personi.addExternalLink("github.io/" + Integer.toString(i*5));
			personi.addExternalLink("linkedin.com/" + Integer.toString(i*10+2));

			people.add(personi);
			
			Storage.create(personi);
			peopleIds.add(personi.getId());
			
			peopleModels.add(new PersonModel(personi));
			
			//add to pages
			
			Company companyi = new Company("number"+Integer.toString(i),"Indexed company");
			Skill skilli = new Skill("number"+Integer.toString(i),"Indexed skill");
			JobPosting jobi = new JobPosting("number"+Integer.toString(i),"Indexed job");
			
			Storage.create(companyi);
			Storage.create(skilli);
			Storage.create(jobi);
			
			pages.add(companyi);
			pages.add(skilli);
			pages.add(jobi);
			
			pageModels.add(new PageModel(companyi));
			pageModels.add(new PageModel(skilli));
			pageModels.add(new PageModel(jobi));
			
			pageIds.add(companyi.getId());
			pageIds.add(skilli.getId());
			pageIds.add(jobi.getId());
		}
		
	}
	
	@Test
	void testGetPageFromStorage()
	{
		for(int i=0;i<10;i++)
		{
			Person p = (Person) Storage.pull(peopleIds.get(i));
			assertEquals(p, people.get(i));
			
			Company c = (Company) Storage.pull(pageIds.get(i*3));
			assertEquals(c,pages.get(i*3));
			
			Skill s = (Skill) Storage.pull(pageIds.get(i*3+1));
			assertEquals(s,pages.get(i*3+1));

			JobPosting j = 	(JobPosting) Storage.pull(pageIds.get(i*3+2));
			assertEquals(j,pages.get(i*3+2));

		}
	}
	
	@Test
	void updatePageModelAndTest()
	{
		Company c = new Company(pages.get(0).getId(),
				pages.get(0).getName(),
				pages.get(0).getDescription(),
				pages.get(0).getExternalLinks(),
				pages.get(0).getInternalLinks()
				);
		
		c.setName("The new value of company name!");
		c.addInternalLink(people.get(1), "employee");
		
		PageModel pModel = pageModels.get(0);
		String personId = peopleIds.get(1);
		
		//adding a company to person's employer
		pModel.getInternalLinks().get("employee").add(peopleIds.get(1));
		pModel.getName().setValue("The new value of company name!");

		pModel.updatePageInStorage();

		
		assertEquals(c, pModel.getAssociatedPage());
		
		
		Company personFromStorage = (Company) Storage.pull(pages.get(0).getId());
		
		assertEquals(c, personFromStorage);




	}
	
	@Test
	void updatePersonModelAndTest()
	{
		//create an identical person, change some stuff about it. The goal is to
		//make sure our model can create these same changes on the front-end,
		//which can then be pushed and pulled from the REST server.
		
		Person a = new Person(people.get(0).getId(),
				people.get(0).getName(),
				people.get(0).getDescription(),
				people.get(0).getExternalLinks(),
				people.get(0).getInternalLinks(),
				people.get(0).getPronouns(),
				people.get(0).getEmail(),
				people.get(0).getPhone()
				);
		
		a.setName("The new value of person's name!");
		a.addInternalLink(pages.get(3), "employer");
		a.setPronouns("she/his");
		a.setEmail("a@b.com");
		a.setPhone("111");

		
		PersonModel pModel = peopleModels.get(0);
		
		String companyTwoId = pageIds.get(3);
		//adding a company to person's employer
		pModel.getInternalLinks().get("employer").add(companyTwoId);
		pModel.getName().setValue("The new value of person's name!");
		pModel.getPronouns().setValue("she/his");
		pModel.getEmail().setValue("a@b.com");
		pModel.getPhone().setValue("111");
		
		
		
		pModel.updatePageInStorage();
		
	
		assertEquals(a, pModel.getAssociatedPage());
		
		Person personFromStorage = (Person) Storage.pull(people.get(0).getId());
		
		assertEquals(a, personFromStorage);
		
	}

	
}