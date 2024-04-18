package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import mvcViews.HomeBarController;
import mvcViews.LoginPageController;
import mvcViews.PersonCanEditController;

public class LoginNavigationModel implements LoginNavigationModelInterface
{
	BorderPane mainview;
	PersonModel personModel;
	
	 public LoginNavigationModel(BorderPane view)
	 {
	    this.mainview = view;
	    this.personModel = new PersonModel();
	 }
	@Override
	public void showLogin()
	{
			    FXMLLoader loader = new FXMLLoader();
			    loader.setLocation(LoginNavigationModel.class
			        .getResource("../views/LoginPage.fxml"));
			    try {
			      Pane view = loader.load();
			      mainview.setCenter(view);
			      LoginPageController cont = loader.getController();
			      cont.setModel(this);
			      
			      
			    } catch (IOException e) {
			      // TODO Auto-generated catch block
			      e.printStackTrace();
			    }
	}
	
	@Override
	public void showHomepage()
	{
	    FXMLLoader homeLoader = new FXMLLoader();
	    FXMLLoader personLoader = new FXMLLoader();

	    
	    try {
	    	
	    //set top
		  homeLoader.setLocation(LoginNavigationModel.class
			      .getResource("../views/Home.fxml"));

	      Pane topBanner = homeLoader.load();
	      HomeBarController cont = homeLoader.getController();
	  	  HomeTransitionModel homeTransitionModel = new HomeTransitionModel(mainview, personModel);
	      cont.setModel(homeTransitionModel);
	      mainview.setTop(topBanner);
	      
	    //set center
		  personLoader.setLocation(LoginNavigationModel.class
			      .getResource("../views/PersonCanEditView.fxml"));

	      Node center = (Node)personLoader.load();
	      PersonCanEditController personCont = personLoader.getController();
	      
	  	  PersonTransitionModel personTransitionModel = new PersonTransitionModel(mainview,personModel);
	      personCont.setModel(personModel, personTransitionModel);
	      mainview.setCenter(center);	      
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

}
