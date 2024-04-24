package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.PageCanEditController;
import mvcViews.PageEditController;

abstract public class PageTransitionModel extends TransitionModel {

	PageModel currentPage;
	String pageId;
	
	public PageTransitionModel(BorderPane view, String pageId) {
		super(view, pageId);	
		currentPage = StorageModel.pull(pageId);

		
		
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
			      cont.setModel(model,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
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
			      cont.setModel(pageId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	public void showFollowed()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageFollowedCanEditView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageCanEditController cont = loader.getController();
			      cont.setModel(pageId,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}	
	public void showFollowedNoEdit()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageFollowedView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageCanEditController cont = loader.getController();
			      cont.setModel(pageId,this);
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
			      cont.setModel(model,this);
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }		
	}
	

}