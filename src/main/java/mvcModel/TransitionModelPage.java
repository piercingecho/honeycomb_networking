package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.PageCanEditController;
import mvcViews.PageEditController;

abstract public class TransitionModelPage extends TransitionModel {

	PageModel currentPage;
	
	public TransitionModelPage(BorderPane mainview, PageModel currentPage) {
		super(mainview, currentPage);	
		this.currentPage = StorageModel.pull(currentPage.getId().getValue());

	}
	
	public void showLinkedPage()
	{
		
		if(!currentPage.canBeViewedByUser())
		{
			showCannotView();
		}
		else
		{
			if(currentPage.canBeEditedByUser())
			{
				showCanEdit();
			}
			else
			{
				showNoEdit();
			}
		}
	}
	
	public void showCannotView()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(TransitionModelPerson.class
				.getResource("../mvcViews/NoPermissionPageView.fxml"));
		try
		{
		      Node view = loader.load();
		      mainview.setCenter(view);
		}
		catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }		

	}

	public void showEditable()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageEditView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageEditController cont = loader.getController();
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      e.printStackTrace();
			    }		
	}
	public void showCanEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageCanEditView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageCanEditController cont = loader.getController();
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	public void showNoEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageCanEditController cont = loader.getController();
			      
			      cont.setModel(currentlyViewedId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	
	@Override
	public void showFollowed()
	{
		// TODO Auto-generated method stub
		
	}

	

}
