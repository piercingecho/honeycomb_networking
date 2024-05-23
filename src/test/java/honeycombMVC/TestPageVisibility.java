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
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.application.Platform;
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
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestPageVisibility
{
	BorderPane mainview;
	
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
  ListView<PageModel> getPages(FxRobot robot)

  {
   return (ListView<PageModel>) robot.lookup("#allPagesListViewCSS")
       .queryAll().iterator().next();
  }
  
  private void scrollToItem(FxRobot robot, int index)
  {
	  Platform.runLater(()->{
		  ListView<PageModel> pages = getPages(robot);
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
  private void checkLabelValue(FxRobot robot,String value, String labelName)
  {
    Assertions.assertThat(robot.lookup(labelName)
        .queryAs(Label.class)).hasText(value);    
  }

  
  @Test
  void testPersonPagesVisibility(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  //unviewable person: 
	  //people.get(1) is unviewable, 
	  //2 is editable,
	  //3 is viewable uneditable
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#personPageTypeBtnCSS");
	  scrollToItem(robot, 1);
	  robot.clickOn(people.get(1).getName()); //can be refactored
	  
	  
	  checkLabelValue(robot, 
			  "Sorry, you don't have permission to view this page :(", 
			  "#permissionLabelCSS");
	  
	  //uneditable person
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#personPageTypeBtnCSS");
	  scrollToItem(robot, 3);
	  robot.clickOn(people.get(3).getName());//can be refactored
	  
	  try {
		  robot.clickOn("#editBtnCSS");
		  fail("Should be an uneditable page.");
	  }
	  catch (Exception e) {
		  //success
	  }
	  
	  //editable person
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#personPageTypeBtnCSS");
	  scrollToItem(robot, 2);
	  robot.clickOn(people.get(2).getName());//can be refactored
	  robot.clickOn("#editBtnCSS");
  }
  
  @Test
  void testCompanyPagesVisibility(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  //unviewable company: 
	  //companies.get(0) is unviewable, 
	  //1 is editable,
	  //2 is viewable uneditable
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#companyPageTypeBtnCSS");
	  scrollToItem(robot, 0);
	  robot.clickOn(companies.get(0).getName()); //can be refactored
	  
	  
	  checkLabelValue(robot, 
			  "Sorry, you don't have permission to view this page :(", 
			  "#permissionLabelCSS");
	  
	  //uneditable company
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#companyPageTypeBtnCSS");
	  scrollToItem(robot, 2);
	  robot.clickOn(companies.get(2).getName());//can be refactored
	  
	  try {
		  robot.clickOn("#editBtnCSS");
		  fail("Should be an uneditable page.");
	  }
	  catch (Exception e) {
		  //success
	  }
	  
	  //editable company
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#companyPageTypeBtnCSS");
	  scrollToItem(robot, 1);
	  robot.clickOn(companies.get(1).getName());//can be refactored
	  robot.clickOn("#editBtnCSS");
  }
  
  @Test
  void testSkillPagesVisibility(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  //unviewable skill: 
	  //skills.get(0) is unviewable, 
	  //1 is editable,
	  //2 is viewable uneditable
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#skillPageTypeBtnCSS");
	  scrollToItem(robot, 0);
	  robot.clickOn(skills.get(0).getName()); //can be refactored
	  
	  
	  checkLabelValue(robot, 
			  "Sorry, you don't have permission to view this page :(", 
			  "#permissionLabelCSS");
	  
	  //uneditable skill
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#skillPageTypeBtnCSS");
	  scrollToItem(robot, 2);
	  robot.clickOn(skills.get(2).getName());//can be refactored
	  
	  try {
		  robot.clickOn("#editBtnCSS");
		  fail("Should be an uneditable page.");
	  }
	  catch (Exception e) {
		  //success
	  }
	  
	  //editable skill
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#skillPageTypeBtnCSS");
	  scrollToItem(robot, 1);
	  robot.clickOn(skills.get(1).getName());//can be refactored
	  robot.clickOn("#editBtnCSS");
  }
  
  @Test
  void testJobPagesVisibility(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  //unviewable job: 
	  //jobs.get(0) is unviewable, 
	  //1 is editable,
	  //2 is viewable uneditable
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#jobPostingPageTypeBtnCSS");
	  scrollToItem(robot, 0);
	  robot.clickOn(jobs.get(0).getName()); //can be refactored
	  
	  
	  checkLabelValue(robot, 
			  "Sorry, you don't have permission to view this page :(", 
			  "#permissionLabelCSS");
	  
	  //uneditable job
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#jobPostingPageTypeBtnCSS");
	  scrollToItem(robot, 2);
	  robot.clickOn(jobs.get(2).getName());//can be refactored
	  
	  try {
		  robot.clickOn("#editBtnCSS");
		  fail("Should be an uneditable page.");
	  }
	  catch (Exception e) {
		  //success
	  }
	  
	  //editable job
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#jobPostingPageTypeBtnCSS");
	  scrollToItem(robot, 1);
	  robot.clickOn(jobs.get(1).getName());//can be refactored
	  robot.clickOn("#editBtnCSS");
  }

}