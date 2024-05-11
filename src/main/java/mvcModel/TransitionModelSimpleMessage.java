package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.SimpleMessageCanEditController;

public class TransitionModelSimpleMessage extends TransitionModelPage
{


	public TransitionModelSimpleMessage(BorderPane view, PageModel p)
	{
		super(view, p);
	}
	
	@Override
	public void showNoEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/SimpleMessageView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      SimpleMessageCanEditController cont = loader.getController();
			      
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}

	@Override
	public void showLinks()
	{
		
		//TODO implement this after sprint 4
		
//		FXMLLoader loader = new FXMLLoader();
//		 loader.setLocation(TransitionModelPerson.class
//			        .getResource("../mvcViews/PageLinksCompanyView.fxml"));
//			    try {
//			      Node view = loader.load();
//			      mainview.setCenter(view);
//			      PageLinksCompanyController cont = loader.getController();
//			      cont.setModel(new AllLinksModel(mainview, currentPage.getId().getValue()), mainview);
//			    } catch (IOException e) {
//			      e.printStackTrace();
//			    }
//
	}

}
