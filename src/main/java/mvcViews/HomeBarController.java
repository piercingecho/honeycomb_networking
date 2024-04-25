package mvcViews;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mvcModel.HomeNavigationModelInterface;
import javafx.event.ActionEvent;

public class HomeBarController {

	HomeNavigationModelInterface model;
	
	@FXML
	Button homeBtn;
	
	@FXML
	Button logoutBtn;
	
	@FXML
	Button searchBtn;
	
	public HomeBarController()
	{
		
	}
	public void setModel(HomeNavigationModelInterface homeTransitionModel) {
		model = homeTransitionModel;
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
