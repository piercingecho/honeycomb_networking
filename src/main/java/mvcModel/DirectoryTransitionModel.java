package mvcModel;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.AllPageTypesFlowController;
import mvcViews.DirectoryController;
import mvcViews.PersonCanEditController;
import mvcViews.PersonLinksTypesFlowController;


public class DirectoryTransitionModel 
{
	BorderPane mainview;
	public DirectoryTransitionModel(BorderPane view) {
		mainview = view;
	}
	
	
	public void showAllPages() {
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(PersonTransitionModel.class
			        .getResource("../mvcViews/AllPageTypesFlowView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      AllPageTypesFlowController cont = loader.getController();
			      cont.setModel(new AllPagesModel(mainview));
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
	}

	public void showLinkPages()
	{
		String userId = SessionSingleton.getInstance().getUserId();
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(PersonTransitionModel.class
			        .getResource("../mvcViews/PersonLinksTypesFlowView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PersonLinksTypesFlowController cont = loader.getController();
			      cont.setModel(model, new AllLinksModel(mainview, model));
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
	}
	
}
