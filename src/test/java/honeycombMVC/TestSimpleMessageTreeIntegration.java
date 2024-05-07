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
import org.testfx.util.WaitForAsyncUtils;

import honeycombData.Company;
import honeycombData.JobPosting;
import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Skill;
import honeycombData.UtilTest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvcMain.Main;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcModel.PageModel;
import mvcModel.StorageModel;
import mvcViews.LoginPageController;

@ExtendWith(ApplicationExtension.class)
public class TestSimpleMessageTreeIntegration
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
  TreeView<PageModel> getTree(FxRobot robot)
  {
   return (TreeView<PageModel>) robot.lookup("#MessageTreeViewCSS")
       .queryAll().iterator().next();
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
  @Test
  void testAllPagesListViews(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "w");
	  robot.clickOn("#loginButton");
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#simpleMessagePageTypeBtnCSS");
	  scrollToItem(robot, 0);
	  robot.clickOn(messages.get(0).getName()); //can be refactored
	  
	  TreeItem<PageModel> actual = getTree(robot).getRoot();	  
	  
	  //EXPECTED 
	  
		TreeItem<PageModel> expected = new TreeItem<PageModel>(messages.get(0).createPageModel());
		TreeItem<PageModel> expectedChildOne = new TreeItem<PageModel>(messages.get(1).createPageModel());
		TreeItem<PageModel> expectedChildTwo = new TreeItem<PageModel>(messages.get(2).createPageModel());
		TreeItem<PageModel> expectedChildThree = new TreeItem<PageModel>(messages.get(3).createPageModel());
		TreeItem<PageModel> expectedLeftLeaf = new TreeItem<PageModel>(messages.get(4).createPageModel());
		TreeItem<PageModel> expectedRightLeaf = new TreeItem<PageModel>(messages.get(5).createPageModel());
		TreeItem<PageModel> expectedOnlyLeaf = new TreeItem<PageModel>(messages.get(6).createPageModel());
		
		expectedChildTwo.getChildren().add(expectedOnlyLeaf);
		expectedChildOne.getChildren().add(expectedLeftLeaf);
		expectedChildOne.getChildren().add(expectedRightLeaf);

		expected.getChildren().add(expectedChildOne);
		expected.getChildren().add(expectedChildTwo);
		expected.getChildren().add(expectedChildThree);

	  // TEST TREES ARE SAME
		
		assertEquals(actual.getValue().getAssociatedPage(), expected.getValue().getAssociatedPage());
		
		PageModel receivedChildOne = actual.getChildren().get(0).getValue();
		PageModel receivedChildTwo = actual.getChildren().get(1).getValue();
		PageModel receivedChildThree = actual.getChildren().get(2).getValue();
		
		assertEquals(receivedChildOne.getAssociatedPage(), expectedChildOne.getValue().getAssociatedPage());
		assertEquals(receivedChildThree.getAssociatedPage(), expectedChildThree.getValue().getAssociatedPage());
		assertEquals(receivedChildTwo.getAssociatedPage(), expectedChildTwo.getValue().getAssociatedPage());
		
		PageModel receivedLeftLeaf = actual.getChildren().get(0)
											.getChildren().get(0).getValue();
		
		PageModel receivedRightLeaf = actual.getChildren().get(0)
											.getChildren().get(1).getValue();
		
		PageModel receivedOnlyLeaf = actual.getChildren().get(1)
											.getChildren().get(0).getValue();

		assertEquals(receivedLeftLeaf.getAssociatedPage(), expectedLeftLeaf.getValue().getAssociatedPage());
		assertEquals(receivedRightLeaf.getAssociatedPage(), expectedRightLeaf.getValue().getAssociatedPage());
		assertEquals(receivedOnlyLeaf.getAssociatedPage(), expectedOnlyLeaf.getValue().getAssociatedPage());


	  
	  
  }
  
  
  
  
  
}
