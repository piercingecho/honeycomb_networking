package honeycombMVC;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import mvcModel.GetTreeStrategyFirstLayer;
import mvcModel.LoginNavigationModel;
import mvcModel.PageModel;
import mvcViews.MessageTreeController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.Start;

import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Storage;
import honeycombData.UtilTest;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
public class TestGetTreeStrategyFirstLayer
{
	SimpleMessage msgRoot;
	SimpleMessage msgChild1;
	SimpleMessage msgChild2;
	SimpleMessage msgChild3;
	SimpleMessage msgLeftLeafOfChild1;
	SimpleMessage msgRightLeafOfChild1;
	SimpleMessage msgSingleLeafOfChild2;
	
	Person authorOne;
	Person authorTwo;
	Person authorThree;
	
	TreeItem<PageModel> treeRoot;
	
	@Start
	private void start(Stage stage)
	{
		createMessagesInRestServer();
		
		//TreeItem<PageModel> treeRoot = msgRoot.createPageModel().getTree(new GetTreeStrategyFullTree());
		treeRoot = msgRoot.createPageModel().getTree(new GetTreeStrategyFirstLayer());
				
		BorderPane center;
		
	    FXMLLoader loader = new FXMLLoader();	    
	    try {
	    	loader.setLocation(LoginNavigationModel.class
			      .getResource("../mvcViews/MessageTreeView.fxml"));
	      center = loader.load();
	      MessageTreeController cont = loader.getController();
	      
	      
	      cont.setModel(treeRoot);
	      
	
		  Scene s = new Scene(center);
		  stage.setScene(s);
		  stage.show();

	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}
	
	
	
	
	public void createMessagesInRestServer()
	{
		UtilTest.recreateRestDirectory();

		msgRoot = new SimpleMessage("Starting message!", "root");
		msgChild1 = new SimpleMessage("First reply", "reply1");
		msgChild2= new SimpleMessage("Second reply", "reply2");
		msgChild3= new SimpleMessage("Third reply", "reply3");
		msgLeftLeafOfChild1 = new SimpleMessage("I like the above reply", "leaf1");
		msgRightLeafOfChild1 = new SimpleMessage("I don't tho... :/", "leaf2");
		msgSingleLeafOfChild2 = new SimpleMessage("I'm but a leaf, in a world of tree...", "leaf3");

		authorOne = new Person("person", "person desc");
		authorTwo = new Person("person", "person desc");
		authorThree = new Person("person", "person desc");

		
		msgRoot.addReply(msgChild1);
		msgRoot.addReply(msgChild2);
		msgRoot.addReply(msgChild3);
		msgChild1.addReply(msgLeftLeafOfChild1);
		msgChild1.addReply(msgRightLeafOfChild1);
		msgChild2.addReply(msgSingleLeafOfChild2);

		msgRoot.addInternalLink(authorOne, "author");
		msgChild1.addInternalLink(authorOne, "author");
		msgChild2.addInternalLink(authorTwo, "author");
		msgChild3.addInternalLink(authorThree, "author");
		msgLeftLeafOfChild1.addInternalLink(authorTwo, "author");
		msgRightLeafOfChild1.addInternalLink(authorThree, "author");
		msgSingleLeafOfChild2.addInternalLink(authorTwo, "author");
		
		Storage.create(msgRoot);
		Storage.create(msgChild1);
		Storage.create(msgChild2);
		Storage.create(msgChild3);
		Storage.create(msgLeftLeafOfChild1);
		Storage.create(msgRightLeafOfChild1);
		Storage.create(msgSingleLeafOfChild2);
		Storage.create(authorOne);
		Storage.create(authorTwo);
		Storage.create(authorThree);
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
	public void testSetup(FxRobot robot)
	{
		 expandTreeView(treeRoot);

			try
			{
				robot.clickOn(msgRoot.getDescription());
				robot.clickOn(msgChild1.getDescription());
				robot.clickOn(msgChild2.getDescription());
				robot.clickOn(msgChild3.getDescription());

			}
			catch(Exception e)
			{
				fail("Robot coud not click on expected tree label.");
				e.printStackTrace();
			}
			
			try
			{
				robot.clickOn(msgLeftLeafOfChild1.getDescription());
				fail("Robot should not be able to click on tree label.");

			}
			catch(Exception e)
			{
			}

	}
}
