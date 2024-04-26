package honeycombMVC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcModel.PageModel;
import mvcModel.StorageModel;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestLinkButtons
{
	BorderPane mainview;
	
  	ArrayList<Person> people;
  	ArrayList<Company> companies;
  	ArrayList<Skill> skills;
  	ArrayList<JobPosting> jobs;

  @Start  //Before
  private void start(Stage stage)
  {
	  	people = new ArrayList<>();
	  	companies = new ArrayList<>();
	  	skills = new ArrayList<>();
	  	jobs = new ArrayList<>();
		UtilTest.recreateRestDirectory();
		UtilTest.createSampleData(people, companies, skills, jobs);
		
		
		// from mvcMain.main
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("../mvcViews/BackgroundView.fxml"));
	    
	    BorderPane view;
	    try
	    {
	    	view = loader.load();
			
		    LoginPageController cont = loader.getController();
		    LoginNavigationModelInterface navModel = new LoginNavigationModel(view); 
	
		    cont.setModel(navModel);
		    
		    navModel.showLogin();
		    
		 
		    Scene s = new Scene(view);
		    stage.setScene(s);
		    stage.show();
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }


  }

  
  
  private void setUserPass(FxRobot robot, String user, String pass)
  {
	  robot.clickOn("#usernameLabel");
	  robot.write(user);
	  
	  robot.clickOn("#passwordLabel");
	  robot.write(pass);
  }
  
  @SuppressWarnings("unchecked")
  ListView<PageModel> getPages(FxRobot robot, String listViewName)
  {
   return (ListView<PageModel>) robot.lookup(listViewName)
       .queryAll().iterator().next();
  }
  
  private void scrollToItem(FxRobot robot, int index, String listViewName)
  {
	  Platform.runLater(()->{
		  ListView<PageModel> pages = getPages(robot, listViewName);
		  pages.scrollTo(index);
		  //pages.getSelectionModel().clearAndSelect(index);
	  });
	  WaitForAsyncUtils.waitForFxEvents();
}
  
  
  void testObservableListEquality(ObservableList<PageModel> first,
		  ObservableList<PageModel> second)
  {
	  if(first.size() != second.size()) 
	  {
		  fail("First observable list " + first.toString()
		  + " doesn't have the same size as " + second.toString());
	  }
	  
	  for(int i=0; i<first.size(); i++)
	  {
		  PageModel firstPage = first.get(i);
		  PageModel secondPage = second.get(i);
		  
		  //page models assert that their associated
		  //pages are equal
		  assertEquals(firstPage, secondPage);
	  }
  }

  void testPersonLinks(FxRobot robot, Person person)
  {
	  
	  //verify it starts empty
	  ObservableList<PageModel> pages;
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, FXCollections.observableArrayList());

	  robot.clickOn("#employerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "employer"));

	  robot.clickOn("#friendLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "friend"));

	  robot.clickOn("#followerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "follower"));
	  
	  robot.clickOn("#followingLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "following"));

	  robot.clickOn("#projectLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "project"));

	  robot.clickOn("#newsArticleLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "news_article"));

	  robot.clickOn("#mentorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "mentor"));

	  robot.clickOn("#viewerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "viewer"));

	  robot.clickOn("#editorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(person, "editor"));


	  
  }
  
  @Test
  void testPersonLinksFromEdit(FxRobot robot)
  {
	  Person sampleUser = people.get(4);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#linksBtnCSS");
	  testPersonLinks(robot, people.get(4));
  }
  
  @Test
  void testCompanyLinks(FxRobot robot)
  {
	  Person sampleUser = people.get(0);	  
	  //go to a company
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#companyPageTypeBtnCSS");
	  scrollToItem(robot, 3, "#allPagesListViewCSS");
	  robot.clickOn(companies.get(3).getName()); //can be refactored
	  
	  Company company = companies.get(3);
	  
	  
	  //TEST COMPANY LINKS
	  
	  robot.clickOn("#linksBtnCSS");

	  //verify it starts empty
	  ObservableList<PageModel> pages;
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, FXCollections.observableArrayList());

	  robot.clickOn("#employeeLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "employee"));

	  robot.clickOn("#jobPostingLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "job_posting"));

	  robot.clickOn("#followerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "follower"));
	  
	  robot.clickOn("#mentorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "mentor"));

	  robot.clickOn("#viewerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "viewer"));

	  robot.clickOn("#editorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(company, "editor"));
  }
  
  @Test
  void testSkillLinks(FxRobot robot)
  {
	  Person sampleUser = people.get(0);	  
	  //go to a skill
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#skillPageTypeBtnCSS");
	  scrollToItem(robot, 3, "#allPagesListViewCSS");
	  robot.clickOn(skills.get(3).getName()); //can be refactored
	  
	  Skill skill = skills.get(3);
	  
	  
	  //TEST SKILL LINKS
	  
	  robot.clickOn("#linksBtnCSS");

	  //verify it starts empty
	  ObservableList<PageModel> pages;
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, FXCollections.observableArrayList());

	  robot.clickOn("#followerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(skill, "follower"));
	  
	  robot.clickOn("#mentorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(skill, "mentor"));

	  robot.clickOn("#viewerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(skill, "viewer"));

	  robot.clickOn("#editorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(skill, "editor"));  
	  
  }

  @Test
  void testJobLinks(FxRobot robot)
  {
	  Person sampleUser = people.get(0);	  
	  //go to a job
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#jobPostingPageTypeBtnCSS");
	  scrollToItem(robot, 3, "#allPagesListViewCSS");
	  robot.clickOn(jobs.get(3).getName()); //can be refactored
	  
	  JobPosting job = jobs.get(3);
	  
	  
	  //TEST COMPANY LINKS
	  
	  robot.clickOn("#linksBtnCSS");

	  //verify it starts empty
	  ObservableList<PageModel> pages;
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, FXCollections.observableArrayList());

	  robot.clickOn("#followerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "follower"));
	  
	  robot.clickOn("#mentorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "mentor"));

	  robot.clickOn("#viewerLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "viewer"));

	  robot.clickOn("#editorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "editor"));  
	 
	  robot.clickOn("#applicantLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "applicant"));  
	  
	  robot.clickOn("#qualificationLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "qualification"));  
	  
	  robot.clickOn("#contributorLinkBtnCSS");
	  pages = getPages(robot, "#allLinksListViewCSS").getItems();
	  testObservableListEquality(pages, StorageModel.getAllLinkedPagesModels(job, "contributor"));  

  }

  @Test
  void testPersonLinksFromDirectory(FxRobot robot)
  {
	  Person sampleUser = people.get(4);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#myLinksBtnCSS");
	  testPersonLinks(robot, people.get(4));

  }
  


}