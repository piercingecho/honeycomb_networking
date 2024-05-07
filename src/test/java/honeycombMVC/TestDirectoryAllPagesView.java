package honeycombMVC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
public class TestDirectoryAllPagesView
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
  
  @Test
  void testAllPagesListViews(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  
	  robot.clickOn("#searchBtnCSS");
	  
	  robot.clickOn("#allPagesBtnCSS");
	  
	  //verify it starts empty
	  ObservableList<PageModel> pages = getPages(robot).getItems();
	  testObservableListEquality(pages, FXCollections.observableArrayList());
	  
	  robot.clickOn("#personPageTypeBtnCSS");
	  ObservableList<PageModel> personPages = getPages(robot).getItems();
	  testObservableListEquality(personPages, StorageModel.getAllPeopleModels());
	  
	  robot.clickOn("#companyPageTypeBtnCSS");
	  ObservableList<PageModel> companyPages = getPages(robot).getItems();
	  testObservableListEquality(companyPages, StorageModel.getAllCompanyModels());

	  robot.clickOn("#skillPageTypeBtnCSS");
	  ObservableList<PageModel> skillPages = getPages(robot).getItems();
	  testObservableListEquality(skillPages, StorageModel.getAllSkillModels());

	  robot.clickOn("#jobPostingPageTypeBtnCSS");
	  ObservableList<PageModel> jobPages = getPages(robot).getItems();
	  testObservableListEquality(jobPages, StorageModel.getAllJobModels());

  }
}
  
