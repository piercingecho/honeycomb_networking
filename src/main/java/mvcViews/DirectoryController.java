package mvcViews;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mvcModel.DirectoryTransitionModel;
import javafx.event.ActionEvent;

public class DirectoryController {

	DirectoryTransitionModel dirModel;
	
	@FXML
	Button myLinksBtn;
	
	@FXML
	Button allPagesBtn;
	
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
