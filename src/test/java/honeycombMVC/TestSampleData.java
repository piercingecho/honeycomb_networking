package honeycombMVC;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TestSampleData
{
	
  	ArrayList<Person> people;
  	ArrayList<Company> companies;
  	ArrayList<Skill> skills;
  	ArrayList<JobPosting> jobs;
  	ArrayList<SimpleMessage> messages;


  	@Start  //Before
  	private void start(Stage stage)
  	{
	  	people = new ArrayList<>();
	  	companies = new ArrayList<>();
	  	skills = new ArrayList<>();
	  	jobs = new ArrayList<>();
	  	messages = new ArrayList<>();

		UtilTest.recreateRestDirectory();
		UtilTest.createSampleData(people, companies, skills, jobs, messages);
  	}	
  	@Test
  	void test()
  	{
  		
  	}
}
