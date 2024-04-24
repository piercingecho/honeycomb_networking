package mvcModel;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import mvcViews.DirectoryController;
import mvcViews.LoginPageController;

public class HomeNavigationModel implements HomeNavigationModelInterface{
	BorderPane mainview;
	
	public HomeNavigationModel(BorderPane view) {
		mainview = view;
	}
	
	public void showHome() {
		//person edit for themselves
		String personId = SessionSingleton.getInstance().getUserId();
	    PageModel userModel = StorageModel.pull(personId);
	    
	    TransitionModel transitionModel = userModel.getTransitionModel(mainview);
	    transitionModel.showCanEdit();
		
		
//	    FXMLLoader loader = new FXMLLoader();
//	    loader.setLocation(HomeNavigationModel.class
//	        .getResource("../mvcViews/PersonCanEditView.fxml"));
//	    try {
//	      Node view = (Node)loader.load();
//	      PersonCanEditController cont = loader.getController();
//	      
//	      PageModelPerson userModel = StorageModel.get(personId);
//	  	  PageTransitionModelPerson personTransitionModel = new PageTransitionModelPerson(mainview,personId);
//	  	  	  	  
//	      cont.setModel(personId,personTransitionModel);
//	      mainview.setCenter(view);
//
//	    } catch (IOException e) {
//	      // TODO Auto-generated catch block
//	      e.printStackTrace();
//	    }
	}
	public void showSearch() {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(HomeNavigationModel.class
	        .getResource("../mvcViews/Directory.fxml"));
	    try {
	      Node view = (Node)loader.load();
	      DirectoryController cont = loader.getController();
	      DirectoryTransitionModel transitionModel = new DirectoryTransitionModel(mainview);
	      cont.setModel(transitionModel);
	      mainview.setCenter(view);
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
	public void showLogin() {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(HomeNavigationModel.class
	        .getResource("../mvcViews/LoginPage.fxml"));
	    try {
	      Pane view = loader.load();

	      LoginPageController cont = loader.getController();
	  	  LoginNavigationModel loginNavigationModel = new LoginNavigationModel(mainview);
	      cont.setModel(loginNavigationModel);
	      mainview.setTop(null);
	      mainview.setCenter(view);
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
