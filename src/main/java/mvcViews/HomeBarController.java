package mvcViews;

import javafx.fxml.FXML;
import mvcModel.HomeTransitionModel;
import javafx.event.ActionEvent;

public class HomeBarController {

	HomeTransitionModel model;
	
	public void setModel(HomeTransitionModel newModel) {
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
