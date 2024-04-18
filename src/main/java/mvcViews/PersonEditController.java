package mvcViews;

import javafx.beans.binding.Bindings;
//import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mvcModel.PersonModel;
import mvcModel.PersonTransitionModel;

public class PersonEditController {
	PersonModel model;
	PersonTransitionModel transition;
    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField idField;

    @FXML
    private ChoiceBox<?> linksChoices;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField pronounsField;
    
    @FXML
    void onAddLinkClick(ActionEvent event) {

    }
    public void setModel(PersonModel newModel, PersonTransitionModel transitionModel)
    {
    	model = newModel;
    	transition = transitionModel;
    	
    	nameField.setText(model.getName().get()); 
    	emailField.setText(model.getEmail().get()); 
    	pronounsField.setText(model.getPronouns().get()); 
    	phoneField.setText(model.getPhone().get()); 
    	descriptionField.setText(model.getDescription().get()); 

    	
    	
//    	Bindings.bindBidirectional(nameField.textProperty(), model.getName());
//    	Bindings.bindBidirectional(pronounsField.textProperty(), model.getPronouns());
//    	Bindings.bindBidirectional(emailField.textProperty(), model.getEmail());
//    	Bindings.bindBidirectional(phoneField.textProperty(), model.getPhone());
//    	Bindings.bindBidirectional(descriptionField.textProperty(), model.getDescription());

    	
    }
    @FXML
    public void onUpdateClick(ActionEvent event) {
    	//update variables
    	model.getName().setValue(nameField.getText());
    	model.getEmail().setValue(emailField.getText());
    	model.getPronouns().setValue(pronounsField.getText());
    	model.getPhone().setValue(phoneField.getText());
    	model.getDescription().setValue(descriptionField.getText());


    	
    	transition.showUneditable();
    }
    public void onCancelClick(ActionEvent event) {
    	transition.showUneditable();
    }
}
