package mvcViews;

import javafx.fxml.FXML;
import mvcModel.DirectoryTransitionModel;
import mvcModel.PersonDemoModel;
import javafx.event.ActionEvent;

public class DirectoryController {

	PersonDemoModel person;
	DirectoryTransitionModel dirModel;
	
	public void setModel(DirectoryTransitionModel dirModel) {
		this.dirModel = dirModel;
	}
    @FXML
    void onClickAll(ActionEvent event) {
    	dirModel.showAllPages();
    }

    @FXML
    void onClickMyLinks(ActionEvent event) {
    	dirModel.showLinkPages();
    }
}
