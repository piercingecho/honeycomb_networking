package mvcViews;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mvcModel.PersonDemoModel;
import mvcModel.PersonModel;
import mvcModel.PersonTransitionModel;

public class PersonCanEditController {

	PersonTransitionModel transitionModel;
	PersonModel model;
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

    public void setModel(String id, PersonTransitionModel newTransitionModel)
    {
    	transitionModel = newTransitionModel;
    	personId = id;
    	model = new PersonModel(id);
    	
    	System.out.println(personId);
    	System.out.println(model.getName().getValue());
    	System.out.println(model.getDescription().getValue());
    	
    	Bindings.bindBidirectional(nameLabel.textProperty(), model.getName());
    	Bindings.bindBidirectional(pronounsLabel.textProperty(), model.getPronouns());
    	Bindings.bindBidirectional(emailLabel.textProperty(), model.getEmail());
    	Bindings.bindBidirectional(phoneLabel.textProperty(), model.getPhone());
    	Bindings.bindBidirectional(descriptionLabel.textProperty(), model.getDescription());
    }
    @FXML
    public void onEditClick(ActionEvent event) {
    	transitionModel.showEditable();
    }
    @FXML
    public void onFollowClick(ActionEvent event) {
    	transitionModel.showFollowed();
    }
    @FXML
    public void onUnfollowClick(ActionEvent event) {
    	transitionModel.showUneditable();
    }
}
