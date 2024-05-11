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
  
  private void searchForMessage(FxRobot robot, int id)
  {
	  robot.clickOn("#searchBtnCSS");
	  robot.clickOn("#allPagesBtnCSS");
	  robot.clickOn("#simpleMessagePageTypeBtnCSS");
	  scrollToItem(robot, id);
	  robot.clickOn(messages.get(id).getName()); //can be refactored

  }
  
  private void expandTreeView(TreeItem<PageModel> item){
	    if(item != null && !item.isLeaf()){
	    	
	    	/**
	    	 * Sleep for the purpose of showing the test
	    	 */
	    	try
			{
				Thread.sleep(300);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        item.setExpanded(true);
	        for(TreeItem<PageModel> child:item.getChildren()){
	            expandTreeView(child);
	        }
	    }
	}

    
  @Test
  void testAllPagesListViews(FxRobot robot)
  {
	  //log in as user zero
	  
	  Person sampleUser = people.get(0);	  
	  
	  setUserPass(robot, sampleUser.getId(), "w");
	  robot.clickOn("#loginButton");
	  searchForMessage(robot, 0);
	  
	  
	  TreeItem<PageModel> actual = getTree(robot).getRoot();
	  
	  TreeItem<PageModel> expected = new TreeItem<PageModel>(messages.get(0).createPageModel());
	  
	  assertEquals(actual.toString(), new TreeItem<String>("root message").toString()); 
	       // check the tree node is displaying the correct thing
	  	  	  
	  //EXPECTED 
	  
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
		
		
		
		//TEST ROBOT CLICKING
		
		expandTreeView(actual);

		try
		{
			robot.clickOn(expected.getValue().getDescription().getValue());
			robot.clickOn(expectedChildOne.getValue().getDescription().getValue());
			robot.clickOn(expectedOnlyLeaf.getValue().getDescription().getValue());

			robot.clickOn(expectedChildTwo.getValue().getDescription().getValue());
			robot.clickOn(expectedChildThree.getValue().getDescription().getValue());

			robot.clickOn(expectedLeftLeaf.getValue().getDescription().getValue());
			robot.clickOn(expectedRightLeaf.getValue().getDescription().getValue());

		}
		catch(Exception e)
		{
			fail("Robot coud not click on expected tree label.");
			e.printStackTrace();
		}

		
		
		
		
		
		// now test the recursive version for a different tree element.
		searchForMessage(robot, 1);
		actual = getTree(robot).getRoot();
		 expandTreeView(actual);

		  
		expectedChildOne = new TreeItem<PageModel>(messages.get(1).createPageModel());
		expectedLeftLeaf = new TreeItem<PageModel>(messages.get(4).createPageModel());
		expectedRightLeaf = new TreeItem<PageModel>(messages.get(5).createPageModel());

		
		assertEquals(actual.getValue().getAssociatedPage(), 
				expectedChildOne.getValue().getAssociatedPage());

		assertEquals(actual.getChildren().get(0).getValue().getAssociatedPage(), 
				expectedLeftLeaf.getValue().getAssociatedPage());
		assertEquals(actual.getChildren().get(1).getValue().getAssociatedPage(), 
				expectedRightLeaf.getValue().getAssociatedPage());

		try
		{
			robot.clickOn(expectedChildOne.getValue().getDescription().getValue());
			robot.clickOn(expectedLeftLeaf.getValue().getDescription().getValue());
			robot.clickOn(expectedRightLeaf.getValue().getDescription().getValue());

		}
		catch(Exception e)
		{
			fail("Robot coud not click on expected tree label.");
			e.printStackTrace();
		}
		
		try
		{
			robot.clickOn(expectedOnlyLeaf.getValue().getDescription().getValue());
			fail("Robot should not be able to click on root.");
		}
		catch(Exception e)
		{

		}

	  
	  
  }
  
  
  
  
  
}
