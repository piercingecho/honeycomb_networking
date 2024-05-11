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
			"NewsArticle",
			"SimpleMessage"
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
			System.out.println(pageTypes[i]);
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

	public static void createSampleData(ArrayList<Person> people, 
			ArrayList<Company> companies, 
			ArrayList<Skill> skills,
			ArrayList<JobPosting> jobs,
			ArrayList<SimpleMessage> messages)
	{
		
		Person loggedInPerson = new Person("sampleUserIsAPersonToo","TheValuedTestUser","it/its","robot@css.html","123-456-7890");
		people.add(loggedInPerson);
		Storage.create(loggedInPerson);
		
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

		for(int i=0; i<10; i++)
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
		
		//we update links for person0, company0, skill0, job0 from the loop above.
		//person0 is index 4, all others are index 3
		
		//person
		people.get(4).addInternalLink(companies.get(4), "employer");
		people.get(4).addInternalLink(companies.get(5), "employer");
		people.get(4).addInternalLink(people.get(5), "friend");
		people.get(5).addInternalLink(people.get(4), "friend");
		people.get(4).addInternalLink(jobs.get(3), "job_posting");
		people.get(4).addInternalLink(jobs.get(6), "job_posting");
		

		
		//company
		companies.get(3).addInternalLink(people.get(6), "employee");
		companies.get(3).addInternalLink(people.get(7), "employee");
		companies.get(3).addInternalLink(people.get(8), "employee");
		
		companies.get(3).addInternalLink(jobs.get(3), "job_posting");
		companies.get(3).addInternalLink(jobs.get(4), "job_posting");


		
		//skill
		
		
		//job
		jobs.get(3).addInternalLink(people.get(4), "contributor");
		jobs.get(3).addInternalLink(companies.get(4), "contributor");
		jobs.get(3).addInternalLink(people.get(4), "applicant");
		jobs.get(3).addInternalLink(people.get(5), "applicant");
		jobs.get(3).addInternalLink(skills.get(4), "qualification");
		jobs.get(3).addInternalLink(skills.get(5), "qualification");
		jobs.get(6).addInternalLink(people.get(4), "applicant");



		
		//everyone's viewers
		people.get(4).addInternalLink(people.get(0), "viewer");
		companies.get(3).addInternalLink(people.get(0), "viewer");
		skills.get(3).addInternalLink(people.get(0), "viewer");
		jobs.get(3).addInternalLink(people.get(0), "viewer");

		
		//everyone's editors
		people.get(4).addInternalLink(people.get(5), "editor");
		companies.get(3).addInternalLink(people.get(5), "editor");
		skills.get(3).addInternalLink(people.get(5), "editor");
		jobs.get(3).addInternalLink(people.get(5), "editor");

		
		
		//everything's mentors
		people.get(4).addInternalLink(people.get(3), "mentor");
		companies.get(3).addInternalLink(people.get(4), "mentor");
		skills.get(3).addInternalLink(people.get(4), "mentor");
		jobs.get(3).addInternalLink(people.get(4), "mentor");

		
		//everyone's followers
		people.get(4).follow(skills.get(3));
		people.get(4).follow(companies.get(3));
		people.get(4).follow(jobs.get(3));
		people.get(4).follow(people.get(3));

		
		for(Person p: people)
		{
			Storage.update(p);
		}
		for(Company c: companies)
		{
			Storage.update(c);
		}
		for(Skill s: skills)
		{
			Storage.update(s);
		}
		for(JobPosting j: jobs)
		{
			Storage.update(j);
		}


		
		// creating the messages
		
		SimpleMessage msgRoot = new SimpleMessage("Reply0", "root message");
		SimpleMessage msgChild1 = new SimpleMessage("Reply1", "reply1 to root");
		SimpleMessage msgChild2= new SimpleMessage("Reply2", "reply2 to root");
		SimpleMessage msgChild3= new SimpleMessage("Reply3", "reply3 to root");
		SimpleMessage msgLeftLeafOfChild1 = new SimpleMessage("Reply4", "left reply to reply1");
		SimpleMessage msgRightLeafOfChild1 = new SimpleMessage("Reply5", "right reply to reply1");
		SimpleMessage msgSingleLeafOfChild2 = new SimpleMessage("Reply6", "reply to reply2");


		
		Person authorOne = new Person("person", "person desc");
		Person authorTwo = new Person("person", "person desc");
		Person authorThree = new Person("person", "person desc");

		
		msgRoot.addReply(msgChild1);
		msgRoot.addReply(msgChild2);
		msgRoot.addReply(msgChild3);
		msgChild1.addReply(msgLeftLeafOfChild1);
		msgChild1.addReply(msgRightLeafOfChild1);
		msgChild2.addReply(msgSingleLeafOfChild2);

		msgRoot.addInternalLink(authorOne, "author");
		msgChild1.addInternalLink(authorOne, "author");
		msgChild2.addInternalLink(authorTwo, "author");
		msgChild3.addInternalLink(authorThree, "author");
		msgLeftLeafOfChild1.addInternalLink(authorTwo, "author");
		msgRightLeafOfChild1.addInternalLink(authorThree, "author");
		msgSingleLeafOfChild2.addInternalLink(authorTwo, "author");
		
		Storage.create(msgRoot);
		Storage.create(msgChild1);
		Storage.create(msgChild2);
		Storage.create(msgChild3);
		Storage.create(msgLeftLeafOfChild1);
		Storage.create(msgRightLeafOfChild1);
		Storage.create(msgSingleLeafOfChild2);
		Storage.create(authorOne);
		Storage.create(authorTwo);
		Storage.create(authorThree);
		
		messages.add(msgRoot);
		messages.add(msgChild1);
		messages.add(msgChild2);
		messages.add(msgChild3);
		messages.add(msgLeftLeafOfChild1);
		messages.add(msgRightLeafOfChild1);
		messages.add(msgSingleLeafOfChild2);

		
		people.add(authorOne);
		people.add(authorTwo);
		people.add(authorThree);

		

		
	}


}
