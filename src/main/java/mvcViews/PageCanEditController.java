package mvcViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mvcModel.PageTransitionModel;
import mvcModel.PersonDemoModel;
import mvcModel.PersonModel;

public class PageCanEditController
{
	String personId;
	PersonModel model;
	PageTransitionModel transition;
	public void setModel(String id, PageTransitionModel transitionModel)
	{
		personId = id;
		model = new PersonModel(id);
		transition = transitionModel;
	}
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label nameLabel;
    
    @FXML
    public void onEditClick(ActionEvent event) {
    	transition.showEditable();
    }
    @FXML
    public void onFollowClick(ActionEvent event) {
    	transition.showFollowed();
    }
    @FXML
    public void onUnfollowClick(ActionEvent event) {
    	transition.showUneditable();
    }
    
    public void setModel()
    {
    	
    }
}
