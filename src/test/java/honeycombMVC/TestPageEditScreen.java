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
import org.testfx.robot.TypeRobot;
import org.testfx.util.WaitForAsyncUtils;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.Skill;
import honeycombData.Storage;
import honeycombData.UtilTest;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestPageEditScreen
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

  
  
  private void setPersonData(FxRobot robot, String name, String pronouns, 
		  String email, String phone, String description)
  {
	  clearTextField(robot, "#nameFieldCSS");
	  clearTextField(robot, "#pronounsFieldCSS");
	  clearTextField(robot, "#emailFieldCSS");
	  clearTextField(robot, "#phoneFieldCSS");
	  clearTextArea(robot, "#descriptionFieldCSS");
	  
	  robot.clickOn("#nameFieldCSS");
	  robot.write(name);
	  
	  robot.clickOn("#pronounsFieldCSS");
	  robot.write(pronouns);

	  robot.clickOn("#emailFieldCSS");
	  robot.write(email);

	  robot.clickOn("#phoneFieldCSS");
	  robot.write(phone);

	  robot.clickOn("#descriptionFieldCSS");
	  robot.write(description);

	  
  }
  
  @Test
  void testUserEditSelf(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);
	  
	  
	  fail("Haven't started implementing");
	  
	  
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");
	  
	  
	  
	  checkLabelValue(robot, sampleUser.getName(), "#nameLabelCSS");
	  checkLabelValue(robot, sampleUser.getPronouns(), "#pronounsLabelCSS");
	  checkLabelValue(robot, sampleUser.getEmail(), "#emailLabelCSS");
	  checkLabelValue(robot, sampleUser.getPhone(), "#phoneLabelCSS");
	  checkLabelValue(robot, sampleUser.getDescription(), "#descriptionLabelCSS");

	  robot.clickOn("#editBtnCSS");
	  
	  checkTextFieldValue(robot, sampleUser.getName(), "#nameFieldCSS");
	  checkTextFieldValue(robot, sampleUser.getPronouns(), "#pronounsFieldCSS");
	  checkTextFieldValue(robot, sampleUser.getEmail(), "#emailFieldCSS");
	  checkTextFieldValue(robot, sampleUser.getPhone(), "#phoneFieldCSS");
	  checkTextAreaValue(robot, sampleUser.getDescription(), "#descriptionFieldCSS");

	  
	  String newPersonName = "newname";
	  String newPersonPhone = "707-070-7070";
	  String newPersonPronouns = "he/her";
	  String newPersonDescription = "This is a new description!";
	  String newPersonEmail = "email@newplace.org";


	  setPersonData(robot, newPersonName, 
			  newPersonPronouns,
			  newPersonEmail,
			  newPersonPhone, 
			  newPersonDescription);
	  

	  
	  robot.clickOn("#cancelBtnCSS");

	  checkLabelValue(robot, sampleUser.getName(), "#nameLabelCSS");
	  checkLabelValue(robot, sampleUser.getPronouns(), "#pronounsLabelCSS");
	  checkLabelValue(robot, sampleUser.getEmail(), "#emailLabelCSS");
	  checkLabelValue(robot, sampleUser.getPhone(), "#phoneLabelCSS");
	  checkLabelValue(robot, sampleUser.getDescription(), "#descriptionLabelCSS");

	  robot.clickOn("#editBtnCSS");
	  setPersonData(robot, newPersonName, 
			  newPersonPronouns,
			  newPersonEmail,
			  newPersonPhone, 
			  newPersonDescription);
	  
	  robot.clickOn("#updateBtnCSS");
	  
	  checkLabelValue(robot, newPersonName, "#nameLabelCSS");
	  checkLabelValue(robot, newPersonPronouns, "#pronounsLabelCSS");
	  checkLabelValue(robot, newPersonEmail, "#emailLabelCSS");
	  checkLabelValue(robot, newPersonPhone, "#phoneLabelCSS");
	  checkLabelValue(robot, newPersonDescription, "#descriptionLabelCSS");
	  
	  //check that the person is updated in the REST server
	  
	  Person newPerson = (Person) Storage.pull(sampleUser.getId());
	  
	  assertEquals(newPersonName, newPerson.getName());
	  assertEquals(newPersonPronouns, newPerson.getPronouns());
	  assertEquals(newPersonEmail, newPerson.getEmail());
	  assertEquals(newPersonDescription, newPerson.getDescription());
	  assertEquals(newPersonPhone, newPerson.getPhone());
	  
	  
	  //check that the edit button is robust to this change
	  
	  robot.clickOn("#editBtnCSS");
	  
	  
	  checkTextFieldValue(robot, newPerson.getName(), "#nameFieldCSS");
	  checkTextFieldValue(robot, newPerson.getPronouns(), "#pronounsFieldCSS");
	  checkTextFieldValue(robot, newPerson.getEmail(), "#emailFieldCSS");
	  checkTextFieldValue(robot, newPerson.getPhone(), "#phoneFieldCSS");
	  checkTextAreaValue(robot, newPerson.getDescription(), "#descriptionFieldCSS");
	  
	  //check that logging out and back in is robust to this change
	  
	  robot.clickOn("#logoutBtnCSS");
	  setUserPass(robot, sampleUser.getId(), "warblgarbl");
	  robot.clickOn("#loginButton");

	  checkLabelValue(robot, newPersonName, "#nameLabelCSS");
	  checkLabelValue(robot, newPersonPronouns, "#pronounsLabelCSS");
	  checkLabelValue(robot, newPersonEmail, "#emailLabelCSS");
	  checkLabelValue(robot, newPersonPhone, "#phoneLabelCSS");
	  checkLabelValue(robot, newPersonDescription, "#descriptionLabelCSS");	  
	  
  }
  
  @Test
  void testPageEdit(FxRobot robot)
  {
	  fail("Not yet implemented");
  }



}