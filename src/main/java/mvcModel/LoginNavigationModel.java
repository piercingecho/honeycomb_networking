package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import mvcViews.HomeBarController;
import mvcViews.LoginPageController;

public class LoginNavigationModel implements LoginNavigationModelInterface
{
	BorderPane mainview;
		
	 public LoginNavigationModel(BorderPane view)
	 {
	    this.mainview = view;
	 }
	@Override
	public void showLogin()
	{
			    FXMLLoader loader = new FXMLLoader();
			    loader.setLocation(LoginNavigationModel.class
			        .getResource("../mvcViews/LoginPage.fxml"));
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
	    
	    //get the new person with the session singleton!
	    
	    try {
	    	
	    //set top
		  homeLoader.setLocation(LoginNavigationModel.class
			      .getResource("../mvcViews/Home.fxml"));

	      Pane topBanner = homeLoader.load();
	      HomeBarController cont = homeLoader.getController();
	  	  HomeNavigationModelInterface homeTransitionModel = new HomeNavigationModel(mainview);
	      cont.setModel(homeTransitionModel);
	      mainview.setTop(topBanner);
	      
	    //set center
	      
	      homeTransitionModel.showHome();
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

}
