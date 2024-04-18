package mvcModel;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import mvcViews.DirectoryController;
import mvcViews.LoginPageController;
import mvcViews.PersonCanEditController;

public class HomeTransitionModel {
	BorderPane mainview;
	PersonModel model;
	
	public HomeTransitionModel(BorderPane view, PersonModel newModel) {
		mainview = view;
		model = newModel;
	}
	
	public void showHome() {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(HomeTransitionModel.class
	        .getResource("../views/PersonCanEditView.fxml"));
	    try {
	      Node view = (Node)loader.load();
	      PersonCanEditController cont = loader.getController();
	  	  PersonTransitionModel personTransitionModel = new PersonTransitionModel(mainview,model);
	      cont.setModel(model,personTransitionModel);
	      mainview.setCenter(view);

	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
	public void showSearch() {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(HomeTransitionModel.class
	        .getResource("../views/Directory.fxml"));
	    try {
	      Node view = (Node)loader.load();
	      DirectoryController cont = loader.getController();
	      DirectoryTransitionModel transitionModel = new DirectoryTransitionModel(mainview,model);
	      cont.setModel(model,transitionModel);
	      mainview.setCenter(view);
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
	public void showLogin() {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(HomeTransitionModel.class
	        .getResource("../views/LoginPage.fxml"));
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
