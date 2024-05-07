package honeycombMVC;

import java.io.IOException;

import org.testfx.framework.junit5.ApplicationExtension;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import mvcModel.GetTreeStrategyFirstLayer;
import mvcModel.GetTreeStrategyFullTree;
import mvcModel.HomeNavigationModelInterface;
import mvcModel.LoginNavigationModel;
import mvcModel.PageModel;
import mvcViews.HomeBarController;
import mvcViews.MessageTreeController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.Start;

import honeycombData.Person;
import honeycombData.SimpleMessage;
import honeycombData.Storage;
import honeycombData.UtilTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
public class TestMessageTreeView
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

	
	@Start
	private void start(Stage stage)
	{
		createMessagesInRestServer();
		
		//TreeItem<PageModel> treeRoot = msgRoot.createPageModel().getTree(new GetTreeStrategyFullTree());
		TreeItem<PageModel> treeRoot = msgRoot.createPageModel().getTree(new GetTreeStrategyFirstLayer());
				
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

		msgRoot = new SimpleMessage("Starting message!", "someDescription");
		msgChild1 = new SimpleMessage("First reply", "someDescription");
		msgChild2= new SimpleMessage("Second reply", "someDescription");
		msgChild3= new SimpleMessage("Third reply", "someDescription");
		msgLeftLeafOfChild1 = new SimpleMessage("I like the above reply", "someDescription");
		msgRightLeafOfChild1 = new SimpleMessage("I don't tho... :/", "someDescription");
		msgSingleLeafOfChild2 = new SimpleMessage("I'm but a leaf, in a world of tree...", "someDescription");

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
	
	@Test
	public void testSetup()
	{
		try
		{
			Thread.sleep(1000 * 60);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
