package mvcViews;

import javafx.fxml.FXML;
import mvcModel.DirectoryTransitionModel;
import mvcModel.PersonModel;
import javafx.event.ActionEvent;

public class DirectoryController {

	PersonModel person;
	DirectoryTransitionModel dirModel;
	
	public void setModel(PersonModel person, DirectoryTransitionModel dirModel) {
		this.person = person;
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
