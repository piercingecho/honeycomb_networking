package mvcViews;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvcModel.PersonDemoModel;
import mvcModel.PageModelPerson;
import mvcModel.TransitionModelPerson;

public class PersonCanEditController {

	TransitionModelPerson transitionModel;
	PageModelPerson model;
	String personId;
	
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label pronounsLabel;

    @FXML
    private Button followedButton;
    
    public void setModel(String id, TransitionModelPerson newTransitionModel)
    {
    	transitionModel = newTransitionModel;
    	personId = id;
    	model = new PageModelPerson(id);
    	    	
    	Bindings.bindBidirectional(nameLabel.textProperty(), model.getName());
    	Bindings.bindBidirectional(pronounsLabel.textProperty(), model.getPronouns());
    	Bindings.bindBidirectional(emailLabel.textProperty(), model.getEmail());
    	Bindings.bindBidirectional(phoneLabel.textProperty(), model.getPhone());
    	Bindings.bindBidirectional(descriptionLabel.textProperty(), model.getDescription());
    }
    @FXML
    public void onEditClick(ActionEvent event) 
    {
    	transitionModel.showEditable();
    }
    @FXML
    public void onFollowClick(ActionEvent event)
    {
    	transitionModel.handleFollowClick(followedButton);
    }
    @FXML
    public void onLinksClick(ActionEvent event)
    {
    	
    }
    
}
