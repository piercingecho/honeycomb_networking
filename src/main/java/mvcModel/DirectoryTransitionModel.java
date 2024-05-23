package mvcModel;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.AllPageTypesFlowController;


public class DirectoryTransitionModel 
{
	BorderPane mainview;
	public DirectoryTransitionModel(BorderPane view) {
		mainview = view;
	}
	
	
	public void showAllPages() {
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/AllPageTypesFlowView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      AllPageTypesFlowController cont = loader.getController();
			      cont.setModel(new AllPagesModel(mainview), mainview);
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
	}

	public void showLinkPages()
	{
		String userId = SessionSingleton.getInstance().getUserId();
		PageModel userPageModel = StorageModel.pull(userId);
		TransitionModel userTransitionModel = userPageModel.getTransitionModel(mainview);
		
		userTransitionModel.showLinks();
		
//		FXMLLoader loader = new FXMLLoader();
//		 loader.setLocation(PageTransitionModelPerson.class
//			        .getResource("../mvcViews/PersonLinksTypesFlowView.fxml"));
//			    try {
//			      Node view = loader.load();
//			      mainview.setCenter(view);
//			      PersonLinksTypesFlowController cont = loader.getController();
//			      cont.setModel(, new AllLinksModel(mainview, model));
//			    } catch (IOException e) {
//			      e.printStackTrace();
//			    }
	}
	
}
