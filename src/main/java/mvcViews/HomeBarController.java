package mvcViews;

import javafx.fxml.FXML;
import mvcModel.HomeNavigationModel;
import javafx.event.ActionEvent;

public class HomeBarController {

	HomeNavigationModel model;
	public HomeBarController()
	{
		
	}
	public void setModel(HomeNavigationModel newModel) {
		model = newModel;
	}
	
    @FXML
    void onClickHome(ActionEvent event) {
    	model.showHome();
    }

    @FXML
    void onClickLogout(ActionEvent event) {
    	model.showLogin();
    }

    @FXML
    void onClickSearch(ActionEvent event) {
    	model.showSearch();
    }

}
