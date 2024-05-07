package honeycombMVC;
import honeycombData.*;
import javafx.scene.control.TreeItem;
import mvcModel.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class TestTreeStrategyAndSimpleMessage
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
	
	@BeforeEach
	void SetUp() throws Exception
	{
		UtilTest.recreateRestDirectory();

		msgRoot = new SimpleMessage("somename", "someDescription");
		msgChild1 = new SimpleMessage("somename", "someDescription");
		msgChild2= new SimpleMessage("somename", "someDescription");
		msgChild3= new SimpleMessage("somename", "someDescription");
		msgLeftLeafOfChild1 = new SimpleMessage("somename", "someDescription");
		msgRightLeafOfChild1 = new SimpleMessage("somename", "someDescription");
		msgSingleLeafOfChild2 = new SimpleMessage("somename", "someDescription");

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
	void testEntireTreeStrategy()
	{
		
		// create expected tree structure for entire tree strategy
		TreeItem<PageModel> expected = new TreeItem<PageModel>(msgRoot.createPageModel());
		TreeItem<PageModel> expectedChildOne = new TreeItem<PageModel>(msgChild1.createPageModel());
		TreeItem<PageModel> expectedChildTwo = new TreeItem<PageModel>(msgChild2.createPageModel());
		TreeItem<PageModel> expectedChildThree = new TreeItem<PageModel>(msgChild3.createPageModel());
		TreeItem<PageModel> expectedLeftLeaf = new TreeItem<PageModel>(msgLeftLeafOfChild1.createPageModel());
		TreeItem<PageModel> expectedRightLeaf = new TreeItem<PageModel>(msgRightLeafOfChild1.createPageModel());
		TreeItem<PageModel> expectedOnlyLeaf = new TreeItem<PageModel>(msgSingleLeafOfChild2.createPageModel());
		
		expectedChildTwo.getChildren().add(expectedOnlyLeaf);
		expectedChildOne.getChildren().add(expectedLeftLeaf);
		expectedChildOne.getChildren().add(expectedRightLeaf);

		expected.getChildren().add(expectedChildOne);
		expected.getChildren().add(expectedChildTwo);
		expected.getChildren().add(expectedChildThree);

		// the thing we're testing
		TreeItem<PageModel> actual = msgRoot.createPageModel().getTree(new GetTreeStrategyFullTree());
		
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
	
	@Test
	void testSingleLayerStrategy()
	{
		
		// create expected tree structure for single layer tree strategy
		TreeItem<PageModel> expected = new TreeItem<PageModel>(msgRoot.createPageModel());
		TreeItem<PageModel> expectedChildOne = new TreeItem<PageModel>(msgChild1.createPageModel());
		TreeItem<PageModel> expectedChildTwo = new TreeItem<PageModel>(msgChild2.createPageModel());
		TreeItem<PageModel> expectedChildThree = new TreeItem<PageModel>(msgChild3.createPageModel());
		expected.getChildren().add(expectedChildOne);
		expected.getChildren().add(expectedChildTwo);
		expected.getChildren().add(expectedChildThree);

		
		// the thing we're testing
		TreeItem<PageModel> actual = msgRoot.createPageModel().getTree(new GetTreeStrategyFirstLayer());
		
		assertEquals(actual.getValue().getAssociatedPage(), expected.getValue().getAssociatedPage());
		
		PageModel receivedChildOne = actual.getChildren().get(0).getValue();
		PageModel receivedChildTwo = actual.getChildren().get(1).getValue();
		PageModel receivedChildThree = actual.getChildren().get(2).getValue();
		
		assertEquals(receivedChildOne.getAssociatedPage(), expectedChildOne.getValue().getAssociatedPage());
		assertEquals(receivedChildThree.getAssociatedPage(), expectedChildThree.getValue().getAssociatedPage());
		assertEquals(receivedChildTwo.getAssociatedPage(), expectedChildTwo.getValue().getAssociatedPage());

		// make sure that this is NOT creating a recursive tree - we only want the first layer.
		assertEquals(0, actual.getChildren().get(0).getChildren().size());
		
	}

	
}