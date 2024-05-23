package honeycombMVC;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcModel.SessionSingleton;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestLoginView implements LoginNavigationModelInterface
{
	BorderPane mainview;
	int numTimesHomepageShown = 0;
	public void showHomepage()
	{
		numTimesHomepageShown++;
	}
	public void showLogin()
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(LoginNavigationModel.class
	        .getResource("../mvcViews/LoginPage.fxml"));
	    try {
	      Pane view = loader.load();
	      mainview.setCenter(view);
	      LoginPageController cont = loader.getController();
	      cont.setModel(this);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

	}
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
		
		numTimesHomepageShown = 0;
	  
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("../mvcViews/BackgroundView.fxml"));
	    
	    // empty data
	    BorderPane view = new BorderPane();
	    
	    try
	    {
	    	view = loader.load();
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
		
	    LoginPageController cont = loader.getController();
	    
	    
	    //LoginNavigationModelInterface navModel = new LoginNavigationModel(view); 
	    this.mainview = view;
	    
	    cont.setModel(this);
	    
	    this.showLogin();
	    
	 
	    Scene s = new Scene(view);
	    stage.setScene(s);
	    stage.show();
  }
  
  private void checkTextValue(FxRobot robot,String value, String labelName)
  {
    Assertions.assertThat(robot.lookup("#invalidLoginLabelCSS")
        .queryAs(Label.class)).hasText(value);    
  }
  
  private void setUserPass(FxRobot robot, String user, String pass)
  {
	  robot.clickOn("#usernameLabel");
	  robot.write(user);
	  
	  robot.clickOn("#passwordLabel");
	  robot.write(pass);
  }
  
  @Test
  void testLoginTextFields(FxRobot robot)
  {
	  checkTextValue(robot, "", "#invalidLoginLabelCSS");

	  //empty value, check that it fails
	  setUserPass(robot, "", "");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,0);
	  checkTextValue(robot, "Invalid login.", "#invalidLoginLabelCSS");
	  
	  //valid person, check that it works
	  setUserPass(robot, people.get(0).getId(), "a");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,1);
	  checkTextValue(robot, "", "#invalidLoginLabelCSS");
	  assertEquals(people.get(0).getId(), 
			  SessionSingleton.getInstance().getUserId());

	  
	  //valid person invalid pass, check it fails.
	  setUserPass(robot, people.get(0).getId(), "");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,1);
	  checkTextValue(robot, "Invalid login.", "#invalidLoginLabelCSS");
	  
	  //different valid person, check that session ID changes
	  setUserPass(robot, people.get(1).getId(), "a");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,2);
	  
	  checkTextValue(robot, "", "#invalidLoginLabelCSS");
	  
	  assertEquals(people.get(1).getId(), 
			  SessionSingleton.getInstance().getUserId());

	  //invalid username, check it fails
	  setUserPass(robot, "", "42");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,2);
	  checkTextValue(robot, "Invalid login.", "#invalidLoginLabelCSS");
	  
	  //pass in a company id, check for failure
	  setUserPass(robot, companies.get(0).getId(), "42");
	  robot.clickOn("#loginButton");
	  assertEquals(numTimesHomepageShown,2);
	  checkTextValue(robot, "Invalid login.", "#invalidLoginLabelCSS");

	  

	  
	  
	  
  }
  
  
  
  
  
  
  
  
}
