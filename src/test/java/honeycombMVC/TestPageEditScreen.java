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
import org.testfx.util.WaitForAsyncUtils;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.Storage;
import honeycombData.UtilTest;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcModel.PageModel;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestPageEditScreen
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
  
  private void checkLabelValue(FxRobot robot,String value, String labelName)
  {
	    Assertions.assertThat(robot.lookup(labelName)
	            .queryAs(Label.class)).hasText(value);    
  }
  
  private void checkTextFieldValue(FxRobot robot,String value, String labelName)
  {
    Assertions.assertThat(robot.lookup(labelName)
        .queryAs(TextField.class)).hasText(value);    
  }

  private void checkTextAreaValue(FxRobot robot,String value, String labelName)
  {
    Assertions.assertThat(robot.lookup(labelName)
        .queryAs(TextArea.class)).hasText(value);    
  }

  
  
  private void setUserPass(FxRobot robot, String user, String pass)
  {
	  robot.clickOn("#usernameLabel");
	  robot.write(user);
	  
	  robot.clickOn("#passwordLabel");
	  robot.write(pass);
  }
  
  private void clearTextField(FxRobot robot,String selector)
  {
	  TextField tf = robot.lookup(selector)
	  .queryAs(TextField.class);
	  
	  Platform.runLater(()->{tf.clear();});
	  WaitForAsyncUtils.waitForFxEvents();
	  
  }
  private void clearTextArea(FxRobot robot,String selector)
  {
	  TextArea ta = robot.lookup(selector)
	  .queryAs(TextArea.class);
	  
	  Platform.runLater(()->{ta.clear();});
	  WaitForAsyncUtils.waitForFxEvents();
	  
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


  
  
  private void setPageData(FxRobot robot, String name, String description)
  {
	  clearTextField(robot, "#nameFieldCSS");
	  clearTextArea(robot, "#descriptionFieldCSS");
	  
	  robot.clickOn("#nameFieldCSS");
	  robot.write(name);
	  
	  robot.clickOn("#descriptionFieldCSS");
	  robot.write(description);

	  
  }
  
  private void goToCompanyView(FxRobot robot, Company sampleCompany, int index)
  {
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#companyPageTypeBtnCSS");
	  scrollToItem(robot,index);
	  robot.clickOn(sampleCompany.getName());
  }

  
  /**
   * The views are designed such that this 
   * exact functionality is shared across all
   * page subclasses that are not Person. Their
   * transition model implementations all call
   * these edit screens, and they each only have
   * a name and description to display.
   * 
   * Hence, by testing one subclass in company,
   * this is shown to work for all subclasses.
   */
  @Test
  void testCompanyEdit(FxRobot robot)
  {
	  
	  Person sampleUser = people.get(0);
	  
	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  
	  
	  int companyIndex = 1;
	  Company sampleCompany = companies.get(companyIndex);
	  
	  goToCompanyView(robot, sampleCompany, companyIndex);

	  
	  checkLabelValue(robot, sampleCompany.getName(), "#nameLabelCSS");
	  checkLabelValue(robot, sampleCompany.getDescription(), "#descriptionLabelCSS");

	  robot.clickOn("#editBtnCSS");
	  
	  checkTextFieldValue(robot, sampleCompany.getName(), "#nameFieldCSS");
	  checkTextAreaValue(robot, sampleCompany.getDescription(), "#descriptionFieldCSS");

	  
	  String newCompanyName = "Neo Company";
	  String newCompanyDescription = "This is a Neo description!\nall rights reserved.";


	  setPageData(robot, newCompanyName, 
			  newCompanyDescription);
	  

	  
	  robot.clickOn("#cancelBtnCSS");

	  checkLabelValue(robot, sampleCompany.getName(), "#nameLabelCSS");
	  checkLabelValue(robot, sampleCompany.getDescription(), "#descriptionLabelCSS");

	  robot.clickOn("#editBtnCSS");
	  setPageData(robot, newCompanyName, 
			  newCompanyDescription);
	  
	  robot.clickOn("#updateBtnCSS");
	  
	  checkLabelValue(robot, newCompanyName, "#nameLabelCSS");
	  checkLabelValue(robot, newCompanyDescription, "#descriptionLabelCSS");
	  
	  //check that the person is updated in the REST server
	  
	  Company newCompany = (Company) Storage.pull(sampleCompany.getId());
	  
	  assertEquals(newCompanyName, newCompany.getName());
	  assertEquals(newCompanyDescription, newCompany.getDescription());
	  
	  
	  //check that the edit button is robust to this change
	  
	  robot.clickOn("#editBtnCSS");
	  
	  
	  checkTextFieldValue(robot, newCompany.getName(), "#nameFieldCSS");
	  checkTextAreaValue(robot, newCompany.getDescription(), "#descriptionFieldCSS");
	  
	  //check that logging out and back in is robust to this change
	  
	  robot.clickOn("#logoutBtnCSS");
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  goToCompanyView(robot, newCompany, companyIndex);
	  
	  checkLabelValue(robot, newCompanyName, "#nameLabelCSS");
	  checkLabelValue(robot, newCompanyDescription, "#descriptionLabelCSS");	  

  }




}