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
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.HomeNavigationModel;
import mvcModel.HomeNavigationModelInterface;
import mvcModel.LoginNavigationModel;
import mvcModel.SessionSingleton;
import mvcViews.HomeBarController;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestHomeNavigation implements HomeNavigationModelInterface
{
		BorderPane mainview;
		int numTimesHomepageShown = 0;
		int numTimesLoginShown = 0;
		int numTimesSearchShown = 0;
		
		public void showHome()
		{
			numTimesHomepageShown++;
		}
		
		
		public void showLogin()
		{
			numTimesLoginShown++;
		}
		public void showSearch()
		{
			numTimesSearchShown++;
		}
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
			
			numTimesHomepageShown = 0;
			
			mainview = new BorderPane();
		  
		    FXMLLoader homeLoader = new FXMLLoader();	    
		    try {
		    	homeLoader.setLocation(LoginNavigationModel.class
				      .getResource("../mvcViews/Home.fxml"));
		      Pane topBanner = homeLoader.load();
		      HomeBarController cont = homeLoader.getController();
		  	  HomeNavigationModelInterface homeTransitionModel = this;
		      cont.setModel(homeTransitionModel);
		      mainview.setTop(topBanner);
		      
		    } catch (IOException e) {
		      e.printStackTrace();
		    }

		    Scene s = new Scene(mainview);
		    stage.setScene(s);
		    stage.show();

	  
	  }
    
  
	@Test
	void testHomeButton(FxRobot robot)
	{
		for(int i=0; i<5; i++)
		{
			robot.clickOn("#homeBtnCSS");
			assertEquals(numTimesHomepageShown,i+1);
			assertEquals(numTimesLoginShown,0);
			assertEquals(numTimesSearchShown,0);
		}
			  
	}
	
	@Test
	void testLoginButton(FxRobot robot)
	{
		for(int i=0; i<5; i++)
		{
			robot.clickOn("#logoutBtnCSS");
			assertEquals(numTimesHomepageShown,0);
			assertEquals(numTimesLoginShown,i+1);
			assertEquals(numTimesSearchShown,0);
		}
			  
	}

	@Test
	void testSearchButton(FxRobot robot)
	{
		for(int i=0; i<5; i++)
		{
			robot.clickOn("#searchBtnCSS");
			assertEquals(numTimesHomepageShown,0);
			assertEquals(numTimesLoginShown,0);
			assertEquals(numTimesSearchShown,i+1);
		}
			  
	}

	
	
	
	
	
  }