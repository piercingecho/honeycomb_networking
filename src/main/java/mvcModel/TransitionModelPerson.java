package mvcModel;

import java.io.IOException;

import honeycombData.Person;
import honeycombData.Storage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import mvcViews.PageCanEditController;
import mvcViews.PersonCanEditController;
import mvcViews.PersonEditController;

public class TransitionModelPerson extends TransitionModel
{
	
	public TransitionModelPerson(BorderPane view, String id)
	{
		super(view,id);
		
	}
	
	public TransitionModelPerson(BorderPane view, PageModel p)
	{
		super(view,p.getId().getValue());
	}
	
	public void showNoEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PersonView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PersonCanEditController cont = loader.getController();
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	
	public void showEditable()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PersonEditView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PersonEditController cont = loader.getController();
			      cont.setModel(currentlyViewedId,this);
			      
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	public void showCanEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PersonCanEditView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PersonCanEditController cont = loader.getController();
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
		
	public void initializeFollowButton(Button followedButton)
	{
		Person p = (Person) Storage.pull(currentlyViewedId);
		Person u = (Person) Storage.pull(SessionSingleton.getInstance()
								.getUserId());
		
		if(u.isFollowing(p))
		{
			followedButton.setText("Unfollow");
		}
		else
		{
			followedButton.setText("Follow");

		}

	}

	
	public void handleFollowClick(Button followedButton)
	{
		Person p = (Person) Storage.pull(currentlyViewedId);
		Person u = (Person) Storage.pull(SessionSingleton.getInstance()
								.getUserId());
		
		if(u.isFollowing(p))
		{
			u.unfollow(p);
		}
		else
		{
			u.follow(p);
		}
		// TODO the button should be bound to the current value of 'following'
	}	
	
//	public void showFollowedNoEdit()
//	{
//		FXMLLoader loader = new FXMLLoader();
//		 loader.setLocation(PageTransitionModelPerson.class
//			        .getResource("../mvcViews/PersonFollowedView.fxml"));
//			    try {
//			      Node view = loader.load();
//			      mainview.setCenter(view);
//			      PersonCanEditController cont = loader.getController();
//			      cont.setModel(personPageId,this);
//			    } catch (IOException e) {
//			      e.printStackTrace();
//			    }		
//	}

	@Override
	public void showFollowed()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLinks()
	{
		// TODO auto-gen method stub
	};
	
	
}
